package com.vomiter.extradelight.common.complex.workstations.doughshaping;

import java.util.List;

import com.vomiter.extradelight.common.complex.workstations.doughshaping.recipes.DoughShapingRecipe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DoughShapingScreen extends AbstractContainerScreen<DoughShapingMenu> {
    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller");
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller_disabled");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe_selected");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe_highlighted");
    private static final ResourceLocation RECIPE_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe");
    private static final ResourceLocation BG_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/container/stonecutter.png");
    private static final int SCROLLER_WIDTH = 12;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RECIPES_COLUMNS = 4;
    private static final int RECIPES_ROWS = 3;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_X = 52;
    private static final int RECIPES_Y = 14;
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;

    public DoughShapingScreen(DoughShapingMenu p_99310_, Inventory p_99311_, Component p_99312_) {
        super(p_99310_, p_99311_, p_99312_);
        p_99310_.registerUpdateListener(this::containerChanged);
        --this.titleLabelY;
    }

    @Override
    public void render(GuiGraphics p_281735_, int p_282517_, int p_282840_, float p_282389_) {
        super.render(p_281735_, p_282517_, p_282840_, p_282389_);
        this.renderTooltip(p_281735_, p_282517_, p_282840_);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
    int x = this.leftPos;
    int y = this.topPos;

    guiGraphics.blit(BG_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);

    int scrollOffset = (int) (41.0F * this.scrollOffs);

    /*
     * 1.20.1 stonecutter.png:
     * active scroller:   u = 176, v = 0, w = 12, h = 15
     * disabled scroller: u = 188, v = 0, w = 12, h = 15
     */
    int scrollerU = this.isScrollBarActive() ? 176 : 188;
    guiGraphics.blit(
            BG_LOCATION,
            x + 119,
            y + 15 + scrollOffset,
            scrollerU,
            0,
            SCROLLER_WIDTH,
            SCROLLER_HEIGHT
    );

    int recipesX = this.leftPos + RECIPES_X;
    int recipesY = this.topPos + RECIPES_Y;
    int endIndex = this.startIndex + RECIPES_COLUMNS * RECIPES_ROWS;

    this.renderButtons(guiGraphics, mouseX, mouseY, recipesX, recipesY, endIndex);
    this.renderRecipes(guiGraphics, recipesX, recipesY, endIndex);
    }

    @Override
    protected void renderTooltip(GuiGraphics p_282396_, int p_283157_, int p_282258_) {
        super.renderTooltip(p_282396_, p_283157_, p_282258_);
        if (this.displayRecipes) {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.startIndex + 12;
            List<DoughShapingRecipe> list = this.menu.getRecipes();

            for(int l = this.startIndex; l < k && l < this.menu.getNumRecipes(); ++l) {
                int i1 = l - this.startIndex;
                int j1 = i + i1 % 4 * 16;
                int k1 = j + i1 / 4 * 18 + 2;
                if (p_283157_ >= j1 && p_283157_ < j1 + 16 && p_282258_ >= k1 && p_282258_ < k1 + 18) {
                    p_282396_.renderTooltip(this.font, list.get(l).getResultItem(this.minecraft.level.registryAccess()), p_283157_, p_282258_);
                }
            }
        }
    }

    private void renderButtons(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY,
            int x,
            int y,
            int endIndex
    ) {
        for (int i = this.startIndex; i < endIndex && i < this.menu.getNumRecipes(); ++i) {
            int relativeIndex = i - this.startIndex;
            int recipeX = x + relativeIndex % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int row = relativeIndex / RECIPES_COLUMNS;
            int recipeY = y + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;

            /*
             * 1.20.1 stonecutter button UV:
             * normal:      u = 0, v = imageHeight + 0 * 18
             * selected:    u = 0, v = imageHeight + 1 * 18
             * highlighted: u = 0, v = imageHeight + 2 * 18
             */
            int state;
            if (i == this.menu.getSelectedRecipeIndex()) {
                state = 1;
            } else if (mouseX >= recipeX
                    && mouseY >= recipeY
                    && mouseX < recipeX + RECIPES_IMAGE_SIZE_WIDTH
                    && mouseY < recipeY + RECIPES_IMAGE_SIZE_HEIGHT) {
                state = 2;
            } else {
                state = 0;
            }

            guiGraphics.blit(
                    BG_LOCATION,
                    recipeX,
                    recipeY - 1,
                    0,
                    this.imageHeight + state * RECIPES_IMAGE_SIZE_HEIGHT,
                    RECIPES_IMAGE_SIZE_WIDTH,
                    RECIPES_IMAGE_SIZE_HEIGHT
            );
        }
    }

    private void renderRecipes(GuiGraphics p_281999_, int p_282658_, int p_282563_, int p_283352_) {
        List<DoughShapingRecipe> list = this.menu.getRecipes();

        for(int i = this.startIndex; i < p_283352_ && i < this.menu.getNumRecipes(); ++i) {
            int j = i - this.startIndex;
            int k = p_282658_ + j % 4 * 16;
            int l = j / 4;
            int i1 = p_282563_ + l * 18 + 2;
            p_281999_.renderItem(list.get(i).getResultItem(this.minecraft.level.registryAccess()), k, i1);
        }
    }

    @Override
    public boolean mouseClicked(double p_99318_, double p_99319_, int p_99320_) {
        this.scrolling = false;
        if (this.displayRecipes) {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.startIndex + 12;

            for(int l = this.startIndex; l < k; ++l) {
                int i1 = l - this.startIndex;
                double d0 = p_99318_ - (double)(i + i1 % 4 * 16);
                double d1 = p_99319_ - (double)(j + i1 / 4 * 18);
                if (d0 >= 0.0 && d1 >= 0.0 && d0 < 16.0 && d1 < 18.0 && this.menu.clickMenuButton(this.minecraft.player, l)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.MAGMA_CUBE_SQUISH, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, l);
                    return true;
                }
            }

            i = this.leftPos + 119;
            j = this.topPos + 9;
            if (p_99318_ >= (double)i && p_99318_ < (double)(i + 12) && p_99319_ >= (double)j && p_99319_ < (double)(j + 54)) {
                this.scrolling = true;
            }
        }

        return super.mouseClicked(p_99318_, p_99319_, p_99320_);
    }

    @Override
    public boolean mouseDragged(double p_99322_, double p_99323_, int p_99324_, double p_99325_, double p_99326_) {
        if (this.scrolling && this.isScrollBarActive()) {
            int i = this.topPos + 14;
            int j = i + 54;
            this.scrollOffs = ((float)p_99323_ - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffs * (float)this.getOffscreenRows()) + 0.5) * 4;
            return true;
        } else {
            return super.mouseDragged(p_99322_, p_99323_, p_99324_, p_99325_, p_99326_);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta){
        if (this.isScrollBarActive()) {
            int i = this.getOffscreenRows();
            float f = (float)delta / (float)i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffs * (float)i) + 0.5) * 4;
        }

        return true;
    }

    private boolean isScrollBarActive() {
        return this.displayRecipes && this.menu.getNumRecipes() > 12;
    }

    protected int getOffscreenRows() {
        return (this.menu.getNumRecipes() + 4 - 1) / 4 - 3;
    }

    private void containerChanged() {
        this.displayRecipes = this.menu.hasInputItem();
        if (!this.displayRecipes) {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }
}