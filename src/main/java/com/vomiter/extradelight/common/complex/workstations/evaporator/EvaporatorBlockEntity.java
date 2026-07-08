package com.vomiter.extradelight.common.complex.workstations.evaporator;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.vomiter.extradelight.registry.ExtraDelightBlockEntities;
import com.vomiter.extradelight.registry.ExtraDelightRecipes;
import com.vomiter.extradelight.common.complex.cap.ExtraDelightSyncedCapabilityBlockEntity;
import com.vomiter.extradelight.common.recipes.ingredients.SizedFluidIngredient;
import com.vomiter.extradelight.util.BlockEntityUtils;
import com.vomiter.extradelight.common.complex.workstations.evaporator.recipes.EvaporatorRecipe;
import com.vomiter.extradelight.common.complex.workstations.evaporator.recipes.EvaporatorRecipeWrapper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class EvaporatorBlockEntity extends ExtraDelightSyncedCapabilityBlockEntity implements RecipeHolder {

	public static ResourceLocation ice = BuiltInRegistries.BLOCK.getKey(Blocks.ICE);
	public static ResourceLocation air = BuiltInRegistries.BLOCK.getKey(Blocks.AIR);

	public static final String INV_TAG = "inv";

	private final ItemStackHandler items = createHandler();
	private final Lazy<IItemHandlerModifiable> itemHandler = Lazy.of(() -> items);
	public static final int NUM_SLOTS = 9;

	public static final String FLUID_TAG = "fluid";
	private final FluidTank tank = createFluidHandler();

	private int cookTime = 0;
	private int cookTimeTotal = 0;

	private ResourceLocation displayBlock = BuiltInRegistries.BLOCK.getKey(Blocks.AIR);

	public ResourceLocation getDisplayBlock() {
		return displayBlock;
	}

	public int getCookTime() {
		return cookTime;
	}

	public int getCookTimeTotal() {
		return cookTimeTotal;
	}

	private ResourceLocation lastRecipeID;
	private boolean checkNewRecipe;
	private final CachedCheck<EvaporatorRecipeWrapper, EvaporatorRecipe> quickCheck = RecipeManager
			.createCheck(ExtraDelightRecipes.EVAPORATOR.get());

	private FluidTank createFluidHandler() {
		FluidTank tank = new FluidTank(FluidType.BUCKET_VOLUME) {
			@Override
			protected void onContentsChanged() {
				EvaporatorBlockEntity.this.requestModelDataUpdate();
				EvaporatorBlockEntity.this.getLevel().sendBlockUpdated(EvaporatorBlockEntity.this.getBlockPos(),
						EvaporatorBlockEntity.this.getBlockState(), EvaporatorBlockEntity.this.getBlockState(), 3);
				EvaporatorBlockEntity.this.setChanged();

				zeroProgress();
				updateInventory();
			}
		};

		return tank;
	}

	public EvaporatorBlockEntity(BlockPos pPos, BlockState pState) {
		super(ExtraDelightBlockEntities.EVAPORATOR.get(), pPos, pState);
	}

	public float getFullness() {
		return (float) tank.getFluidAmount() / (float) tank.getCapacity();
	}

	public IItemHandlerModifiable getItemHandler() {
		return itemHandler.get();
	}

	public FluidTank getFluidTank() {
		return tank;
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler(NUM_SLOTS) {
			@Override
			protected void onContentsChanged(int slot) {
				EvaporatorBlockEntity.this.requestModelDataUpdate();
				EvaporatorBlockEntity.this.getLevel().sendBlockUpdated(EvaporatorBlockEntity.this.getBlockPos(),
						EvaporatorBlockEntity.this.getBlockState(), EvaporatorBlockEntity.this.getBlockState(), 3);
				EvaporatorBlockEntity.this.setChanged();

				zeroProgress();
				updateInventory();
			}
		};
	}

	public void insertItem(ItemStack stack) {
		BlockEntityUtils.Inventory.insertItem(items, stack, NUM_SLOTS);
		this.updateInventory();
	}

	public boolean isInventoryEmpty() {
		boolean flag = true;

		for (int i = 0; i < this.items.getSlots(); i++) {
			if (!this.items.getStackInSlot(i).isEmpty())
				flag = false;
		}

		return flag;
	}

	public void zeroProgress() {
		cookTime = cookTimeTotal = 0;
	}

	public void updateInventory() {
		requestModelDataUpdate();
		this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
		this.setChanged();
	}

	public ItemStack getInsertedItem() {
		return items.getStackInSlot(0);
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag nbt = super.getUpdateTag();

		writeNBT(nbt);

		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		readNBT(tag);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		CompoundTag tag = pkt.getTag();
		// InteractionHandle your Data
		readNBT(tag);
	}

	void readNBT(CompoundTag nbt) {
		if (nbt.contains(INV_TAG)) {
			items.deserializeNBT(nbt.getCompound(INV_TAG));
		}
		tank.readFromNBT(nbt);

		this.cookTime = nbt.getInt("cookTime");
		this.cookTimeTotal = nbt.getInt("cookTimeTotal");

		this.displayBlock = ResourceLocation.parse(nbt.getString("display"));
	}

	CompoundTag writeNBT(CompoundTag tag) {

		tag.put(INV_TAG, items.serializeNBT());
		tank.writeToNBT(tag);
		tag.putInt("cookTime", this.cookTime);
		tag.putInt("cookTimeTotal", this.cookTimeTotal);

		tag.putString("display", this.displayBlock.toString());

		return tag;
	}

	@Override
	public void load(@Nonnull CompoundTag nbt) {
		super.load(nbt);
		readNBT(nbt);
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt) {
		super.saveAdditional(nbt);
		writeNBT(nbt);
	}

	public Optional<EvaporatorRecipe> matchRecipe() {
		if (this.level != null) {
			return level.getRecipeManager().getRecipeFor(
                    ExtraDelightRecipes.EVAPORATOR.get(),
					new EvaporatorRecipeWrapper(this.tank), level);
		}
		return Optional.empty();

	}

	@Override
	public void setRecipeUsed(Recipe p_300902_) {
		// TODO Auto-generated method stub

	}

	@Override
	public Recipe<?> getRecipeUsed() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
		EvaporatorBlockEntity evaporator = (EvaporatorBlockEntity) be;
		float temp = level.getBiome(pos).value().getBaseTemperature();
		if (temp > 0.05) {
			if (level.random.nextFloat() <= temp) {

				EvaporatorRecipe recipeholder = evaporator.quickCheck
						.getRecipeFor(new EvaporatorRecipeWrapper(evaporator.tank), level).orElse(null);

				if (recipeholder != null) {
					evaporator.cookTimeTotal = recipeholder.getCookTime();
					evaporator.displayBlock = recipeholder.getDisplay();

					if (evaporator.cookTime >= evaporator.cookTimeTotal) {
						dropLoot(evaporator, recipeholder.getOutput());
						SizedFluidIngredient sfi = recipeholder.getFluid();
						evaporator.tank.drain(sfi.getAmount(), IFluidHandler.FluidAction.EXECUTE);
					} else {
						evaporator.cookTime++;
					}
				}
			}
		} else {
			evaporator.displayBlock = ice;
		}
	}

    private static void dropLoot(EvaporatorBlockEntity evaporator, ResourceLocation rc) {
        if (evaporator.level instanceof ServerLevel serverLevel) {
            LootParams lootParams = new LootParams.Builder(serverLevel)
                    .withParameter(LootContextParams.ORIGIN, evaporator.worldPosition.getCenter())
                    .create(LootContextParamSets.ARCHAEOLOGY);

            serverLevel.getServer()
                    .getLootData()
                    .getLootTable(rc)
                    .getRandomItems(lootParams)
                    .forEach(itemStack -> evaporator.insertItem(itemStack.copy()));
        }
    }

	public void dropItems() {
		for (int i = 0; i < items.getSlots(); i++) {
			ItemStack stack = items.getStackInSlot(i);
			if (!stack.isEmpty()) {
				level.addFreshEntity(new ItemEntity(level, this.worldPosition.getX() + 0.5f,
						worldPosition.getY() + 0.5f, worldPosition.getZ() + 0.5f, stack.copy()));
				items.setStackInSlot(i, ItemStack.EMPTY);
			}
		}
	}

    private final IFluidHandler automationFluidHandler = new EvaporatorAutomationFluidHandler(this);

    public IFluidHandler getAutomationFluidHandler() {
        return automationFluidHandler;
    }
}