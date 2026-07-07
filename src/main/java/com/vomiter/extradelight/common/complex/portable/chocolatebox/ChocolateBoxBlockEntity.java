package com.vomiter.extradelight.common.complex.portable.chocolatebox;

import javax.annotation.Nonnull;

import com.vomiter.extradelight.registry.ExtraDelightBlockEntities;

import com.vomiter.extradelight.common.complex.cap.ExtraDelightCapabilityBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class ChocolateBoxBlockEntity extends ExtraDelightCapabilityBlockEntity {
	public static final String TAG = "inv";
	private final ChocolateBoxItemStackHandler items = createHandler();
	private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);
	public final static int NUM_SLOTS = 8;

	public ChocolateBoxBlockEntity(BlockPos pos, BlockState blockState) {
		super(ExtraDelightBlockEntities.CHOCOLATE_BOX.get(), pos, blockState);
	}

	private ChocolateBoxItemStackHandler createHandler() {
		return new ChocolateBoxItemStackHandler(NUM_SLOTS) {

			@Override
			public int getSlotLimit(int slot) {
				return 1;
			}

			@Override
			protected void onContentsChanged(int slot) {
				ChocolateBoxBlockEntity.this.requestModelDataUpdate();
				ChocolateBoxBlockEntity.this.getLevel().sendBlockUpdated(ChocolateBoxBlockEntity.this.getBlockPos(),
						ChocolateBoxBlockEntity.this.getBlockState(), ChocolateBoxBlockEntity.this.getBlockState(),
						Block.UPDATE_CLIENTS);
				ChocolateBoxBlockEntity.this.setChanged();
			}
		};
	}

	public IItemHandler getItems() {
		return this.itemHandler.get();
	}

	@Override
	public @NotNull CompoundTag getUpdateTag() {
		CompoundTag nbt = super.getUpdateTag();

		writeNBT(nbt);

		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
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
		if (tag != null)
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
}
