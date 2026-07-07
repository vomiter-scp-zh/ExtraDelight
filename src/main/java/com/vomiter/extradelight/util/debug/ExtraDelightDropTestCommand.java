package com.vomiter.extradelight.util.debug;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class ExtraDelightDropTestCommand {
    private static final int MAX_ATTEMPTS = 3;

    private static final GameProfile DROP_TEST_PROFILE = new GameProfile(
            UUID.fromString("5d7474df-8a60-4b91-a6d4-0f5946d5f3d1"),
            "[ExtraDelightDropTest]"
    );

    private ExtraDelightDropTestCommand() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("extradelight_drop_test")
                        .requires(source -> source.hasPermission(2))
                        .executes(context -> run(context.getSource()))
        );
    }

    private static int run(CommandSourceStack source) {
        ServerLevel level = source.getLevel();
        BlockPos testPos = BlockPos.containing(source.getPosition()).offset(0, 2, 0);

        FakePlayer fakePlayer = FakePlayerFactory.get(level, DROP_TEST_PROFILE);
        fakePlayer.setPos(
                testPos.getX() + 0.5D,
                testPos.getY(),
                testPos.getZ() + 0.5D
        );

        List<ResourceLocation> noDrops = new ArrayList<>();
        List<ResourceLocation> failed = new ArrayList<>();

        List<Block> blocks = ForgeRegistries.BLOCKS.getValues()
                .stream()
                .filter(block -> {
                    if(block instanceof LiquidBlock) return false;
                    if(block instanceof PieBlock) return false;
                    ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
                    return id != null && ExtraDelight.MOD_ID.equals(id.getNamespace());
                })
                .sorted(Comparator.comparing(block -> ForgeRegistries.BLOCKS.getKey(block).toString()))
                .toList();

        ExtraDelight.LOGGER.info("[DropTest] Starting ExtraDelight block drop test. blocks={}, pos={}", blocks.size(), testPos);

        int tested = 0;

        for (Block block : blocks) {
            ResourceLocation id = ForgeRegistries.BLOCKS.getKey(block);
            if (id == null) {
                continue;
            }

            BlockState state = prepareState(block.defaultBlockState());
            if (state.isAir()) {
                continue;
            }

            tested++;

            try {
                boolean hasDrop = testBlock(level, testPos, fakePlayer, block, state, id);

                if (!hasDrop) {
                    noDrops.add(id);
                }
            } catch (Exception exception) {
                failed.add(id);
                ExtraDelight.LOGGER.error("[DropTest] Exception while testing {}", id, exception);
            } finally {
                level.setBlock(testPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                fakePlayer.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            }
        }

        ExtraDelight.LOGGER.info("[DropTest] Finished. tested={}, noDrops={}, failed={}", tested, noDrops.size(), failed.size());

        if (!noDrops.isEmpty()) {
            ExtraDelight.LOGGER.warn("[DropTest] Blocks with no captured drops:");
            for (ResourceLocation id : noDrops) {
                ExtraDelight.LOGGER.warn("[DropTest] NO DROP: {}", id);
            }
        }

        if (!failed.isEmpty()) {
            ExtraDelight.LOGGER.warn("[DropTest] Blocks that threw exceptions:");
            for (ResourceLocation id : failed) {
                ExtraDelight.LOGGER.warn("[DropTest] FAILED: {}", id);
            }
        }

        int finalTested = tested;
        source.sendSuccess(
                () -> Component.literal("ExtraDelight drop test finished. Tested: " + finalTested
                        + ", no drops: " + noDrops.size()
                        + ", failed: " + failed.size()),
                true
        );

        return noDrops.size();
    }

    private static boolean testBlock(
            ServerLevel level,
            BlockPos testPos,
            FakePlayer fakePlayer,
            Block block,
            BlockState state,
            ResourceLocation id
    ) {
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            level.setBlock(testPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
            level.setBlock(testPos, state, Block.UPDATE_ALL);

            fakePlayer.setItemInHand(InteractionHand.MAIN_HAND, createDropTestTool());

            DropCapture.begin(level);

            try {
                boolean destroyed = level.destroyBlock(testPos, true, fakePlayer);
                List<ItemStack> drops = DropCapture.getCapturedDrops();

                if (!drops.isEmpty()) {
                    ExtraDelight.LOGGER.debug(
                            "[DropTest] {} dropped on attempt {}. destroyed={}, drops={}",
                            id,
                            attempt,
                            destroyed,
                            drops
                    );

                    return true;
                }

                ExtraDelight.LOGGER.debug(
                        "[DropTest] {} had no captured drops on attempt {}. destroyed={}",
                        id,
                        attempt,
                        destroyed
                );
            } finally {
                DropCapture.end();
                level.setBlock(testPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                fakePlayer.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            }
        }

        return false;
    }

    private static ItemStack createDropTestTool() {
        ItemStack stack = new ItemStack(Items.NETHERITE_PICKAXE);
        stack.enchant(Enchantments.SILK_TOUCH, 1);
        stack.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
        return stack;
    }

    private static BlockState prepareState(BlockState state) {
        for (Property<?> property : state.getProperties()) {
            if (property instanceof IntegerProperty integerProperty && shouldMaximize(integerProperty)) {
                int max = integerProperty.getPossibleValues()
                        .stream()
                        .mapToInt(Integer::intValue)
                        .max()
                        .orElse(0);

                state = state.setValue(integerProperty, max);
            }
        }

        return state;
    }

    private static boolean shouldMaximize(IntegerProperty property) {
        String name = property.getName();
        return "age".equals(name) || "stage".equals(name);
    }
}