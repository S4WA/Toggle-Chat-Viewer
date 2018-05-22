package me.s4wa;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ToggleChatViewer.MODID, version = ToggleChatViewer.VERSION)
public class ToggleChatViewer {
    public static final String MODID = "Toggle-chat-viewer";
    public static final String VERSION = "1.0";
    public static boolean showEnabled = true;
    private KeyBinding KeyBinding;

	Minecraft mc = Minecraft.getMinecraft();

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		this.KeyBinding = new KeyBinding("Toggle Chat View Key", Keyboard.KEY_MINUS, "Toggle Chat Viewer MOD");
		MinecraftForge.EVENT_BUS.register(this);
	    ClientRegistry.registerKeyBinding(this.KeyBinding);
	}

	@SubscribeEvent
	public void onrender(RenderGameOverlayEvent event) {
		if (this.mc.currentScreen != null) return;
		
		if (this.KeyBinding.isPressed() || mc.gameSettings.keyBindChat.isPressed()) {
			showEnabled = !showEnabled;
			float sound = 0.0F;
			float slace = 1.0F;
			
			if (showEnabled == false) {
				sound = 0.8F;
				slace = 0.0F;
			} else {
				sound = 1.0F;
				slace = 1.0F;
			}
			mc.gameSettings.chatScale = slace;
//			mc.gameSettings.chatVisibility = EnumChatVisibility.HIDDEN;
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), sound));
		}
	}
}
