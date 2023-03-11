package me.djtheredstoner.shadercuts;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import ofconfig.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = "shadercuts", name = "Shadercuts", version = "1.0.0")
public class Shadercuts {

    public static final Logger LOGGER = LogManager.getLogger("Shadercuts");

    public static final KeyBinding TOGGLE_SHADERS = new KeyBinding("Toggle Shaders", Keyboard.KEY_B, "Shadercuts");
    public static final KeyBinding OPEN_SHADERS_GUI = new KeyBinding("Open Shaders Menu", Keyboard.KEY_N, "Shadercuts");
    public static final KeyBinding OPEN_SHADER_OPTIONS = new KeyBinding("Open Shader Options", Keyboard.KEY_M, "Shadercuts");

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerKeyBinding(TOGGLE_SHADERS);
        ClientRegistry.registerKeyBinding(OPEN_SHADERS_GUI);
        ClientRegistry.registerKeyBinding(OPEN_SHADER_OPTIONS);

        try {
            String version = Config.getVersion();
            LOGGER.info("Detected OptiFine version: {}", version);
        } catch (NoClassDefFoundError e) {
            LOGGER.info("No OptiFine detected!");
            return;
        }

        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

}
