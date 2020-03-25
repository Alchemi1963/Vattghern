package me.alchemi.vattghern.objects.gui;

import me.alchemi.vattghern.objects.tileentities.TileEntityNithing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class NithingGui extends GuiScreen {

	public static final int WIDTH = 180;
	public static final int HEIGHT = 152;
	public static final int ID = 0;
	
	private GuiButton buttonClose;
	private GuiTextField textField;
	private final TileEntityNithing te;
	private FontRenderer font;
	
	public NithingGui(TileEntityNithing te) {
		super();
		this.font = Minecraft.getMinecraft().fontRenderer;
		this.te = te;
		init();
	}
	
	protected void init() {
		this.buttonList.clear();
		buttonClose = new GuiButton(0, this.width / 2 - 50, this.height / 2 + 10, 100, 20, "Select");
		textField = new GuiTextField(1, this.font, this.width/2 - 100, this.height / 2 - 18, 200, 16);
		
		addButton(buttonClose);
		allowUserInput = true;
		textField.setFocused(true);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		int posX = (this.width - WIDTH)/2;
		int posY = (this.height - HEIGHT)/2;
		
		drawCenteredString(font, "Enter your victim here", posX, posY - 38, 0xFFFFFF);
		textField.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void onGuiClosed() {
		
		if (textField.getText() == null || textField.getText().isEmpty()) {
			Minecraft.getMinecraft().player.sendChatMessage("You need to input the victim's name...");
			te.destroy();
		} else {
			te.setVictim(textField.getText());
		}
		
		super.onGuiClosed();
	}

}
