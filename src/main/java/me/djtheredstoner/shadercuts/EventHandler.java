package me.djtheredstoner.shadercuts;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.gui.GuiShaderOptions;
import net.optifine.shaders.gui.GuiShaders;
import ofconfig.Config;

import java.util.Objects;

public class EventHandler {

    private String previousShaderPack;
    private String currentShaderPack;

    @SubscribeEvent
    public void renderFrame(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();

        try {
            String currentPack = Shaders.getShaderPackName();
            if (!Objects.equals(currentPack, currentShaderPack)) {
                previousShaderPack = currentShaderPack;
                currentShaderPack = currentPack;
            }

            if (Shadercuts.TOGGLE_SHADERS.isPressed()) {
                if (currentShaderPack == null && previousShaderPack == null) {
                    return;
                }

                if (currentShaderPack != null) {
                    previousShaderPack = currentPack;
                    currentShaderPack = null;

                    Shaders.uninit();
                    Shaders.setShaderPack("OFF");
                    Shaders.storeConfig();
                } else {
                    String toLoad = previousShaderPack;

                    previousShaderPack = null;
                    currentShaderPack = toLoad;

                    Shaders.uninit();
                    Shaders.setShaderPack(toLoad);
                    Shaders.storeConfig();
                }
            } else if (Shadercuts.OPEN_SHADERS_GUI.isPressed()) {
                mc.displayGuiScreen(new GuiShaders(mc.currentScreen, mc.gameSettings));
            } else if (Shadercuts.OPEN_SHADER_OPTIONS.isPressed()) {
                if (Config.isShaders()) {
                    mc.displayGuiScreen(new GuiShaderOptions(mc.currentScreen, mc.gameSettings));
                }
            }
        } catch (Throwable t) {
            Shadercuts.LOGGER.error("Error handling event", t);
            if (mc.player != null) {
                mc.player.sendMessage(new TextComponentString("§c[Shadercuts] Error handling event. Check your logs for details."));
            }
        }
    }

}
