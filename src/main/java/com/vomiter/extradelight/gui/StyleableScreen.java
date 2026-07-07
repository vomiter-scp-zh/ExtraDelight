package com.vomiter.extradelight.gui;

import com.vomiter.extradelight.ExtraDelight;
import com.vomiter.extradelight.common.blocks.IStyleable;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class StyleableScreen extends AbstractContainerScreen<StyleableMenu> {

	private PageButton forwardButton;
	private PageButton backwardButton;

	private BlockPos pos = BlockPos.ZERO;
	private IStyleable style;

	private static ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID,
			"textures/gui/styleable.png");

	private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller");
	private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.withDefaultNamespace(
			"container/stonecutter/scroller_disabled");
	private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.withDefaultNamespace(
			"container/stonecutter/recipe_selected");
	private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.withDefaultNamespace(
			"container/stonecutter/recipe_highlighted");
	private static final ResourceLocation RECIPE_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe");
	private static final ResourceLocation BG_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/container/stonecutter.png");

	private float scrollOffs;
	private boolean scrolling;
	private int startIndex;
	private boolean displayRecipes;
    protected int imageWidth = 176;
    protected int imageHeight = 166;

    private static final int SCROLLER_WIDTH = 12;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RECIPES_COLUMNS = 4;
    private static final int RECIPES_ROWS = 3;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_X = 52;
    private static final int RECIPES_Y = 14;



    public StyleableScreen(StyleableMenu p_99310_, Inventory p_99311_, Component p_99312_) {

		super(p_99310_, p_99311_, p_99312_);
	}

	@Override
	protected void init() {
		super.init();
		this.inventoryLabelX = -1000;
		this.forwardButton = this.addRenderableWidget(
				new PageButton(this.leftPos + 49, this.topPos + 86, true, p_98144_ -> style(0), true));
		this.backwardButton = this.addRenderableWidget(
				new PageButton(this.leftPos + 13, this.topPos + 86, false, p_98144_ -> style(1), true));
	}

    private void renderButtons(
            GuiGraphics guiGraphics,
            int mouseX,
            int mouseY,
            int x,
            int y,
            int endIndex
    ) {
		for (int i = this.startIndex; i < endIndex && i < style.numStyles(); ++i) {
			int j = i - this.startIndex;
			int recipeX = x + j % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
			int row = j / 4;
			int recipeY = y + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
			ResourceLocation resourcelocation;
            int state;
            if (/*i == this.menu.getSelectedRecipeIndex()*/ false) {
                state = 1;
            } else if (mouseX >= recipeX
                    && mouseY >= recipeY
                    && mouseX < recipeX + RECIPES_IMAGE_SIZE_WIDTH
                    && mouseY < recipeY + RECIPES_IMAGE_SIZE_HEIGHT) {
                state = 2;
            } else {
                state = 0;
            }

            /*
             * 1.20.1 stonecutter button UV:
             * normal:      u = 0, v = imageHeight + 0 * 18
             * selected:    u = 0, v = imageHeight + 1 * 18
             * highlighted: u = 0, v = imageHeight + 2 * 18
             */
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

	void renderBlock(GuiGraphics guiGraphics, BlockState state) {
		guiGraphics.pose().pushPose();

		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
		guiGraphics.pose().translate(0, 0, 0);

		MultiBufferSource.BufferSource buffers = Minecraft.getInstance().renderBuffers().bufferSource();

		guiGraphics.pose().pushPose();
		guiGraphics.pose().translate(0, 0.5, 0);
		guiGraphics.pose().scale(1f, -1f, 1f);

		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, guiGraphics.pose(), buffers, 0xffffff,
				OverlayTexture.NO_OVERLAY);

		buffers.endBatch();

		guiGraphics.pose().popPose();
		guiGraphics.pose().popPose();
	}

	private boolean isScrollBarActive() {
		if (style != null)
			return style.numStyles() > 12;
		return false;
	}

	@Override
	protected void renderBg(GuiGraphics gui, float p_97788_, int p_97789_, int p_97790_) {
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		if (this.minecraft == null)
			return;

		BlockState state = this.minecraft.level.getBlockState(this.pos);
		if (state != null) {
			if (state.getBlock() instanceof IStyleable style) {
				int i = this.leftPos + 8;
				int j = this.topPos + 36;

				RenderSystem.setShaderTexture(0, BACKGROUND);
				gui.blit(BACKGROUND, i, j, 97, 186, 159, 70);

				int k = (int) (41.0F * this.scrollOffs);
				//gui.blitSprite(resourcelocation, i + 138, j + k + 8, 12, 15);
                /*
                 * 1.20.1 stonecutter.png:
                 * active scroller:   u = 176, v = 0, w = 12, h = 15
                 * disabled scroller: u = 188, v = 0, w = 12, h = 15
                 */
                int scrollerU = this.isScrollBarActive() ? 176 : 188;
                gui.blit(
                        BG_LOCATION,
                        i + 138,
                        j + k + 8,
                        scrollerU,
                        0,
                        12,
                        15
                );



				int j1 = this.startIndex + 12;
				this.renderButtons(gui, p_97789_, p_97790_, i + 71, j + 7, j1);

				RenderSystem.enableBlend();
				RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
				Lighting.setupForFlatItems();

				gui.pose().pushPose();
				{
					renderRecipes(gui, 87, 47, j1);
				}
				gui.pose().popPose();

				gui.pose().pushPose();
				{
					gui.pose().translate(this.leftPos + 43.5, this.topPos + 60, 100);
					gui.pose().scale(30F, 30F, 30F);

					gui.pose().mulPose(Axis.XP.rotationDegrees(-30F));
					gui.pose().mulPose(Axis.YP.rotationDegrees(-45F));

					renderBlock(gui, state);

				}
				gui.pose().popPose();

				RenderSystem.disableBlend();
			}
		}
	}

	private void renderRecipes(GuiGraphics gui, int p_282658_, int p_282563_, int p_283352_) {

		for (int i = this.startIndex; i < p_283352_ && i < style.numStyles(); ++i) {
			int j = i - this.startIndex;
			int k = this.leftPos + p_282658_ + j % 4 * 16;
			int l = j / 4;
			int i1 = this.topPos + p_282563_ + l * 18 + 2;

			gui.pose().pushPose();
			{
				gui.pose().translate(k, i1 + 0.5f, 100);
				gui.pose().scale(9F, 9F, 9F);

				gui.pose().mulPose(Axis.XP.rotationDegrees(-30F));
				gui.pose().mulPose(Axis.YP.rotationDegrees(-45F));

				renderBlock(gui, style.getState(i));
			}
			gui.pose().popPose();

		}
	}

	void style(int button) {
		this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, button);
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
		Block block = this.minecraft.level.getBlockState(pos).getBlock();
		if (block instanceof IStyleable style)
			this.style = style;
	}

	@Override
	public boolean mouseClicked(double p_99318_, double p_99319_, int p_99320_) {
		this.scrolling = false;
		int i = this.leftPos + 8 + 71;
		int j = this.topPos + 36 + 7;
		int k = this.startIndex + 12;

		for (int l = this.startIndex; l < k; ++l) {
			int i1 = l - this.startIndex;
			double d0 = p_99318_ - (double) (i + i1 % 4 * 16);
			double d1 = p_99319_ - (double) (j + i1 / 4 * 18);
			if (d0 >= 0.0 && d1 >= 0.0 && d0 < 16.0 && d1 < 18.0
					&& this.menu.clickMenuButton(this.minecraft.player, l + 2)) {
				Minecraft.getInstance().getSoundManager()
						.play(SimpleSoundInstance.forUI(SoundEvents.MAGMA_CUBE_SQUISH, 1.0F));
				this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, l+2);
				return true;
			}
		}

		i = this.leftPos + 119;
		j = this.topPos + 9;
		if (p_99318_ >= (double) i && p_99318_ < (double) (i + 12) && p_99319_ >= (double) j
				&& p_99319_ < (double) (j + 54)) {
			this.scrolling = true;
		}

		return super.mouseClicked(p_99318_, p_99319_, p_99320_);
	}

	@Override
	public boolean mouseDragged(double p_99322_, double p_99323_, int p_99324_, double p_99325_, double p_99326_) {
		if (this.scrolling && this.isScrollBarActive()) {
			int i = this.topPos + 14;
			int j = i + 54;
			this.scrollOffs = ((float) p_99323_ - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
			this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
			this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + 0.5) * 4;
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
			this.startIndex = (int) ((double) (this.scrollOffs * (float) i) + 0.5) * 4;
		}

		return true;
	}

	protected int getOffscreenRows() {
		return (this.style.numStyles() + 4 - 1) / 4 - 3;
	}

}
