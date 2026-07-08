package com.vomiter.extradelight.common.complex.displays.spice;

import javax.annotation.Nonnull;

import com.vomiter.extradelight.common.complex.cap.ExtraDelightCapabilityBlockEntity;
import com.vomiter.extradelight.registry.ExtraDelightBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class SpiceRackEntity extends ExtraDelightCapabilityBlockEntity implements MenuProvider {

	public static final String TAG = "inv";

	private final ItemStackHandler items = createHandler();
	private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);
	public static final int NUM_SLOTS = 4;

	public SpiceRackEntity(BlockPos pPos, BlockState pBlockState) {

		super(ExtraDelightBlockEntities.SPICE_RACK.get(), pPos, pBlockState);

	}

	public int getNumSlots() {
		return NUM_SLOTS;
	}

	public IItemHandler getItemHandler() {
		return itemHandler.get();
	}

//	@Nonnull
//	@Override
//	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//		if (side != Direction.DOWN)
//			if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
//				return handler.cast();
//			}
//		return super.getCapability(cap, side);
//	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler(NUM_SLOTS) {
			@Override
			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
				return 1;
			}

		};

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
//		CompoundTag tag = new CompoundTag();
//
//		writeNBT(tag);

		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		CompoundTag tag = pkt.getTag();
		// InteractionHandle your Data
		readNBT(tag);
	}

	void readNBT(CompoundTag nbt) {
		if (nbt.contains(TAG)) {
			items.deserializeNBT(nbt.getCompound(TAG));
		}
	}

	CompoundTag writeNBT(CompoundTag tag) {
		tag.put(TAG, items.serializeNBT());
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

	public String getDisplayNameId() {
		return "screen.spice_rack.name";
	}
    @Override
    public Component getDisplayName() {
        return Component.translatable(getDisplayNameId());
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory,
                                            Player playerEntity) {
        return new SpiceRackMenu(windowId, playerInventory, this);
    }

}
