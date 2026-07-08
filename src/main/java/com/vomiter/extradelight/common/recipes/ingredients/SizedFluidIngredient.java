package com.vomiter.extradelight.common.recipes.ingredients;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

//shim from 1.21.1 NEOFORGE
public class SizedFluidIngredient extends AbstractIngredient {
    private final Fluid fluid;
    private final TagKey<Fluid> fluidTag;
    private final int amount;

    public SizedFluidIngredient(Fluid fluid, @Nullable TagKey<Fluid> fluidTag, int amount) {
        super();
        this.fluid = fluid;
        this.fluidTag = fluidTag;
        this.amount = amount;
    }

    public static SizedFluidIngredient ofFluid(Fluid fluid, int amount) {
        return new SizedFluidIngredient(fluid, null, amount);
    }

    public static SizedFluidIngredient ofTag(TagKey<Fluid> fluidTag, int amount) {
        return new SizedFluidIngredient(null, fluidTag, amount);
    }

    public int getAmount(){
        return amount;
    }

    @Override
    public boolean test(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }

        return stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM)
                .map(this::matchesFluid)
                .orElse(false);
    }

    public boolean test(@Nullable FluidStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        if (stack.getAmount() < amount) {
            return false;
        }

        if (fluid != null && stack.getFluid() == fluid) {
            return true;
        }

        if (fluidTag != null && stack.getFluid().is(fluidTag)) {
            return true;
        }
        return false;
    }


    private boolean matchesFluid(IFluidHandlerItem handler) {
        for (int i = 0; i < handler.getTanks(); i++) {
            FluidStack stack = handler.getFluidInTank(i);

            if (stack.isEmpty()) {
                continue;
            }

            if (stack.getAmount() < amount) {
                continue;
            }

            if (fluid != null && stack.getFluid() == fluid) {
                return true;
            }

            if (fluidTag != null && stack.getFluid().is(fluidTag)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", Serializer.ID.toString());

        JsonObject fluidJson = new JsonObject();
        fluidJson.addProperty("amount", amount);

        if (fluidTag != null) {
            fluidJson.addProperty("tag", fluidTag.location().toString());
        } else {
            ResourceLocation id = ForgeRegistries.FLUIDS.getKey(fluid);
            if (id == null) {
                throw new IllegalStateException("Unregistered fluid: " + fluid);
            }
            fluidJson.addProperty("fluid", id.toString());
        }

        json.add("fluid", fluidJson);
        return json;
    }

    public FluidStack[] getFluids() {
        if(fluid != null) return new FluidStack[]{new FluidStack(fluid, amount)};
        else if (fluidTag != null) {
            ArrayList<FluidStack> fluidStacks = new ArrayList<>();
            for (Fluid fluid1 : ForgeRegistries.FLUIDS) {
                if(fluid1.is(fluidTag)) fluidStacks.add(new FluidStack(fluid1, amount));
            }
            return fluidStacks.toArray(new FluidStack[0]);
        }
        return null;
    }

    public static class Serializer implements IIngredientSerializer<SizedFluidIngredient> {
        public static final ResourceLocation ID = ExtraDelight.modLoc("fluid_ingredient");
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull SizedFluidIngredient parse(@NotNull JsonObject json) {
            int amount = GsonHelper.getAsInt(json, "amount");

            if (json.has("fluid")) {
                ResourceLocation id = ResourceLocation.tryParse(GsonHelper.getAsString(json, "fluid"));
                Fluid fluid =
                        ResourceLocation.fromNamespaceAndPath("minecraft", "milk").equals(id)?
                                ForgeMod.MILK.get()
                                :ForgeRegistries.FLUIDS.getValue(id);

                if (fluid == null) {
                    throw new JsonSyntaxException("Unknown fluid: " + id);
                }

                return SizedFluidIngredient.ofFluid(fluid, amount);
            }

            if (json.has("tag")) {
                ResourceLocation id = ResourceLocation.tryParse(GsonHelper.getAsString(json, "tag"));
                TagKey<Fluid> tag = TagKey.create(Registries.FLUID, id);
                return SizedFluidIngredient.ofTag(tag, amount);
            }

            throw new JsonSyntaxException("Fluid container ingredient requires either 'fluid' or 'tag'");
        }

        @Override
        public @NotNull SizedFluidIngredient parse(FriendlyByteBuf buffer) {
            boolean useTag = buffer.readBoolean();
            ResourceLocation id = buffer.readResourceLocation();
            int amount = buffer.readVarInt();

            if (useTag) {
                TagKey<Fluid> tag = TagKey.create(Registries.FLUID, id);
                return SizedFluidIngredient.ofTag(tag, amount);
            }

            Fluid fluid = ForgeRegistries.FLUIDS.getValue(id);
            if (fluid == null) {
                throw new IllegalStateException("Unknown fluid from network: " + id);
            }

            return SizedFluidIngredient.ofFluid(fluid, amount);
        }

        @Override
        public void write(FriendlyByteBuf buffer, SizedFluidIngredient ingredient) {
            boolean useTag = ingredient.fluidTag != null;
            buffer.writeBoolean(useTag);

            if (useTag) {
                buffer.writeResourceLocation(ingredient.fluidTag.location());
            } else {
                ResourceLocation id = ForgeRegistries.FLUIDS.getKey(ingredient.fluid);
                if (id == null) {
                    throw new IllegalStateException("Unregistered fluid: " + ingredient.fluid);
                }
                buffer.writeResourceLocation(id);
            }

            buffer.writeVarInt(ingredient.amount);
        }
    }
}