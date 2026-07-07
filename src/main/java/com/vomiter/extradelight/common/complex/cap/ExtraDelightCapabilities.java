package com.vomiter.extradelight.common.complex.cap;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.complex.portable.chocolatebox.ChocolateBoxBlock;
import com.vomiter.extradelight.common.complex.portable.chocolatebox.ChocolateBoxBlockEntity;
import com.vomiter.extradelight.common.complex.portable.picnicbasket.PicnicBasketBlock;
import com.vomiter.extradelight.common.complex.portable.picnicbasket.PicnicBasketBlockEntity;
import com.vomiter.extradelight.registry.ExtraDelightBlockEntities;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExtraDelightCapabilities {
    public static final Map<BlockEntityType<?>, CapabilityProvider<?, IItemHandler>> ITEM_HANDLERS_BLOCK = new HashMap<>();
    public static final Map<BlockEntityType<?>, CapabilityProvider<?, IFluidHandler>> FLUID_HANDLERS_BLOCK = new HashMap<>();

    @FunctionalInterface
    public interface CapabilityProvider<O extends BlockEntity, T> {
        @Nullable T getCapability(O blockEntity, @Nullable Direction side);
    }

    public static <O extends BlockEntity> void registerItemHandler(
            BlockEntityType<O> blockEntityType,
            CapabilityProvider<O, IItemHandler> provider
    ) {
        ITEM_HANDLERS_BLOCK.put(blockEntityType, provider);
    }

    public static <O extends BlockEntity> void registerFluidHandler(
            BlockEntityType<O> blockEntityType,
            CapabilityProvider<O, IFluidHandler> provider
    ) {
        FLUID_HANDLERS_BLOCK.put(blockEntityType, provider);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static IItemHandler getItemHandler(BlockEntity blockEntity, @Nullable Direction side) {
        CapabilityProvider<BlockEntity, IItemHandler> provider =
                (CapabilityProvider<BlockEntity, IItemHandler>) ITEM_HANDLERS_BLOCK.get(blockEntity.getType());

        if (provider == null) {
            return null;
        }

        return provider.getCapability(blockEntity, side);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static IFluidHandler getFluidHandler(BlockEntity blockEntity, @Nullable Direction side) {
        CapabilityProvider<BlockEntity, IFluidHandler> provider =
                (CapabilityProvider<BlockEntity, IFluidHandler>) FLUID_HANDLERS_BLOCK.get(blockEntity.getType());

        if (provider == null) {
            return null;
        }

        return provider.getCapability(blockEntity, side);
    }

    public static void registerCapabilities() {
        registerItemHandler(
                ExtraDelightBlockEntities.DRYING_RACK.get(),
                (be, side) -> be.getItemHandler()
        );

        registerItemHandler(
                ExtraDelightBlockEntities.MIXING_BOWL.get(),
                (be, side) -> be.getItemHandler()
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.MIXING_BOWL.get(),
                (be, side) -> be.getFluidTank()
        );

        registerItemHandler(
                ExtraDelightBlockEntities.MORTAR.get(),
                (be, side) -> be.getItemHandler()
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.MORTAR.get(),
                (be, side) -> be.getFluidTank()
        );

        registerItemHandler(
                ExtraDelightBlockEntities.OVEN.get(),
                (be, side) -> {
                    if (side == Direction.DOWN) {
                        return be.inputHandler;
                    }

                    if (side == Direction.UP) {
                        return be.outputHandler;
                    }

                    return be.getInventory();
                }
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.TAP.get(),
                (be, side) -> be.getFluidHandler()
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.MELTING_POT.get(),
                (be, side) -> be.getFluidTank()
        );

        registerItemHandler(
                ExtraDelightBlockEntities.MELTING_POT.get(),
                (be, side) -> be.getItemHandler()
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.CHILLER.get(),
                (be, side) -> {
                    if (side == Direction.UP) {
                        return be.getDripTray();
                    }

                    return be.getFluidTank();
                }
        );

        registerItemHandler(
                ExtraDelightBlockEntities.CHILLER.get(),
                (be, side) -> be.getInventory()
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.KEG.get(),
                (be, side) -> be.getTank()
        );

        registerItemHandler(
                ExtraDelightBlockEntities.CHOCOLATE_BOX.get(),
                (be, side) -> be.getItems()
        );

        registerItemHandler(
                ExtraDelightBlockEntities.PICNIC_BASKET.get(),
                (be, side) -> be.getItems()
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.FUNNEL.get(),
                (be, side) -> be.getFluidTank()
        );

        registerItemHandler(
                ExtraDelightBlockEntities.EVAPORATOR.get(),
                (be, side) -> be.getItemHandler()
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.EVAPORATOR.get(),
                (be, side) -> be.getAutomationFluidHandler()
        );

        registerItemHandler(
                ExtraDelightBlockEntities.JUICER.get(),
                (be, side) -> be.getItemHandler()
        );

        registerFluidHandler(
                ExtraDelightBlockEntities.JUICER.get(),
                (be, side) -> be.getFluidTank()
        );
    }

    static Map<Item, Integer> SLOT_COUNT_MAP = new HashMap<>();
    public static void setupSlotCountMap(){
        ForgeRegistries.BLOCKS.getValues().forEach(
                block -> {
                    if(block instanceof PicnicBasketBlock){
                        Optional.of(block.asItem())
                                .ifPresent(i -> SLOT_COUNT_MAP
                                        .put(i, PicnicBasketBlockEntity.NUM_SLOTS));
                    }
                    if (block instanceof ChocolateBoxBlock){
                        Optional.of(block.asItem())
                                .ifPresent(i -> SLOT_COUNT_MAP
                                        .put(i, ChocolateBoxBlockEntity.NUM_SLOTS));
                    }
                }
        );
    }

    public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();

        Optional.ofNullable(SLOT_COUNT_MAP.get(stack.getItem())).ifPresent(slot -> {
            event.addCapability(
                    ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "block_entity_items"),
                    new BlockEntityTagItemHandlerProvider(stack, slot)
            );
        });
    }
}