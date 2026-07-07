package com.vomiter.extradelight.data.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class FoodLoot extends LootModifier {

    public static final Codec<FoodLoot> CODEC = RecordCodecBuilder.create(instance ->
            codecStart(instance)
                    .and(ResourceLocation.CODEC.fieldOf("loot_table").forGetter(FoodLoot::lootTableID))
                    .apply(instance, FoodLoot::new)
    );

    private final ResourceLocation lootTableID;

    public ResourceLocation lootTableID() {
        return this.lootTableID;
    }

    public FoodLoot(LootItemCondition[] conditions, ResourceLocation lootTableID) {
        super(conditions);
        this.lootTableID = lootTableID;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        MinecraftServer server = context.getLevel().getServer();

        if (server == null) {
            return generatedLoot;
        }

        LootTable extraTable = server.getLootData().getLootTable(this.lootTableID);

        if (extraTable == LootTable.EMPTY) {
            return generatedLoot;
        }

        extraTable.getRandomItemsRaw(
                context,
                LootTable.createStackSplitter(context.getLevel(), generatedLoot::add)
        );

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}