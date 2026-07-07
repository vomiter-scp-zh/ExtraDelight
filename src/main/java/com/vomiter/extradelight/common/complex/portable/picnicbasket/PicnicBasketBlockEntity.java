package com.vomiter.extradelight.common.complex.portable.picnicbasket;

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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.items.IItemHandler;

public class PicnicBasketBlockEntity extends ExtraDelightCapabilityBlockEntity implements MenuProvider {
	public static final String TAG = "inv";
	private final PicnicBasketItemStackHandler items = createHandler();
	private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);
	public final static int NUM_SLOTS = 12;

	public PicnicBasketBlockEntity(BlockPos pos, BlockState blockState) {
		super(ExtraDelightBlockEntities.PICNIC_BASKET.get(), pos, blockState);
	}

    private PicnicBasketItemStackHandler createHandler() {
        return new PicnicBasketItemStackHandler(NUM_SLOTS) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);

                PicnicBasketBlockEntity.this.setChanged();

                if (PicnicBasketBlockEntity.this.level != null && !PicnicBasketBlockEntity.this.level.isClientSide) {
                    PicnicBasketBlockEntity.this.level.sendBlockUpdated(
                            PicnicBasketBlockEntity.this.worldPosition,
                            PicnicBasketBlockEntity.this.getBlockState(),
                            PicnicBasketBlockEntity.this.getBlockState(),
                            Block.UPDATE_CLIENTS
                    );
                }
            }
        };
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("screen.picnic_basket.name");
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory,
                                            Player playerEntity) {
        return new PicnicBasketMenu(windowId, playerInventory, this);
    }

    
	public IItemHandler getItems() {
		return this.itemHandler.get();
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