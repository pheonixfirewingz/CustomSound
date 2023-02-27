package io.github.phoenixfirewingz.customsounds.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.block.entity.SoundNodeEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NodeScreen extends HandledScreen<NodeScreenHandle> {
    private static final Identifier TEXTURE = new Identifier(CustomSounds.MOD_ID, "textures/gui/container/sound_node_ui.png");
    private SoundNodeEntity entity;
    private TextFieldWidget text;
    private TexturedButtonWidget play;
    private TexturedButtonWidget stop;

    public NodeScreen(NodeScreenHandle handler, PlayerInventory inventory, Text title) {
        super(handler, inventory,title);
        this.entity = handler.getEntity();
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {}

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    protected void onPlay(ButtonWidget buttonWidget) {
        try
        {
        }
        catch (Exception e)
        {
            this.text.setText("Audio Play Not Possible: " + e.getMessage());
        }
    }

    protected void onPause(ButtonWidget buttonWidget) {
    }

    @Override
    public void close() {
        this.entity.getSaver().set(0, String.valueOf(this.text.getText()));
        super.close();
    }

    @Override
    protected void init() {
        super.init();
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        this.text = this.addDrawableChild(new TextFieldWidget(this.textRenderer, x + 7, y + 10, 160, 15, Text.translatable("sound_node.url")));
        this.text.setMaxLength(2048);
        text.setText(entity.getSaver().get(0));
        this.play = this.addDrawableChild(new TexturedButtonWidget(x + 7, y + 30, 20, 20, 176, 0, 20, TEXTURE, 256, 256,this::onPlay, Text.translatable("sound_node.button.play")));
        this.stop = this.addDrawableChild(new TexturedButtonWidget(x + 30, y + 30, 20, 20, 196, 0, 20, TEXTURE, 256, 256,this::onPause, Text.translatable("sound_node.button.stop")));
    }
}
