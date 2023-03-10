package io.github.phoenixfirewingz.customsounds.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.phoenixfirewingz.customsounds.CustomSounds;
import io.github.phoenixfirewingz.customsounds.block.SoundNode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import static io.github.phoenixfirewingz.customsounds.CustomSounds.SOUND_NODE_SCREEN_HANDLER_TYPE;

public class NodeScreenHandle extends ScreenHandler {
    public final SoundNode.SoundNodeEntity entity;

    public NodeScreenHandle(int syncId, @NotNull PlayerInventory playerInventory,BlockEntity entity) {
        super(SOUND_NODE_SCREEN_HANDLER_TYPE, syncId);
        this.entity = (SoundNode.SoundNodeEntity) entity;
    }

    public NodeScreenHandle(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return player.isCreative();
    }

    @Environment(EnvType.CLIENT)
    public static class NodeScreen extends HandledScreen<NodeScreenHandle> {
        private static final Identifier TEXTURE = new Identifier(CustomSounds.MOD_ID, "textures/gui/container/sound_node_ui.png");
        private boolean set_url = false;
        private TextFieldWidget text;
        private TexturedButtonWidget play;
        private TexturedButtonWidget stop;

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            if(keyCode == GLFW.GLFW_KEY_E)
                return false;
            return super.keyPressed(keyCode, scanCode, modifiers);
        }

        public NodeScreen(NodeScreenHandle handler, PlayerInventory inventory, Text title) {
            super(handler, inventory,title);
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

        @Override
        public void close() {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBlockPos(handler.entity.getPos());
            buf.writeString(handler.entity.getUrl());
            ClientPlayNetworking.send(CustomSounds.NODE_UPDATE_ID, buf);
            handler.entity.markDirty();
            super.close();
        }

        protected void onPause(ButtonWidget buttonWidget) {
        }

        protected void onTextChanged(String s)
        {
            if(!s.isEmpty())
                handler.entity.setUrl(s);
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            if(handler.entity.getUrl() != null && !set_url) {
                set_url = true;
                this.text.setText(handler.entity.getUrl());
            }
            super.render(matrices, mouseX, mouseY, delta);
        }

        @Override
        protected void init() {
            super.init();
            int x = (width - backgroundWidth) / 2;
            int y = (height - backgroundHeight) / 2;
            this.text = this.addDrawableChild(new TextFieldWidget(this.textRenderer, x + 7, y + 10, 160, 15, Text.translatable("sound_node.url")));
            this.text.setChangedListener(this::onTextChanged);
            this.text.setText(handler.entity.getUrl());
            this.text.setMaxLength(2048);
            this.play = this.addDrawableChild(new TexturedButtonWidget(x + 7, y + 30, 20, 20, 176, 0, 20, TEXTURE, 256, 256,this::onPlay, Text.translatable("sound_node.button.play")));
            this.stop = this.addDrawableChild(new TexturedButtonWidget(x + 30, y + 30, 20, 20, 196, 0, 20, TEXTURE, 256, 256,this::onPause, Text.translatable("sound_node.button.stop")));
        }
    }
}