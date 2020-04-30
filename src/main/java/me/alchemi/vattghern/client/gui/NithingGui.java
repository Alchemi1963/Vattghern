package me.alchemi.vattghern.client.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.common.containers.NithingContainer;
import me.alchemi.vattghern.common.tileentities.TileNithing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;

public class NithingGui extends GuiContainer {

	private GuiTextField textField;
	private final TileNithing te;
	private FontRenderer font;
	
	private static final ResourceLocation BACKGROUND = new ResourceLocation(Vattghern.MOD_ID, "textures/gui/container/nithing.png");
	
	public NithingGui(TileNithing te, NithingContainer container) {
		super(container);
		this.font = Minecraft.getMinecraft().fontRenderer;
		this.te = te;
	}
		
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(BACKGROUND);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		textField.drawTextBox();
		
		String s = te.getDisplayName().getUnformattedText();
		font.drawString(s, xSize/2 - font.getStringWidth(s)/2, 7, 4210752, false);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (textField.isFocused()) {
			String text = textField.getText();
			if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
				text = text + typedChar;
			} else if (keyCode == Keyboard.KEY_ESCAPE) {
				super.keyTyped(typedChar, keyCode);
				
			} else if (keyCode == Keyboard.KEY_BACK) {
				if (text.length() > 1) text = text.substring(0, text.length()-1);
				else text = "";
			
			} else if (keyCode == Keyboard.KEY_LEFT) {
				int cursor = textField.getCursorPosition();
				if (cursor > 0) {
					System.out.println(cursor);
					textField.moveCursorBy(1);
					System.out.println(cursor);
				}
			
			} else if (keyCode == Keyboard.KEY_RIGHT) {
				int cursor = textField.getCursorPosition();
				if (cursor < text.length() - 1) {
					textField.setCursorPosition(cursor + 1);
				}
			
			} else if (keyCode == Keyboard.KEY_HOME) {
				int cursor = textField.getCursorPosition();
				if (cursor > 0) {
					textField.setCursorPositionZero();
				}
				
			} else if (keyCode == Keyboard.KEY_END) {
				int cursor = textField.getCursorPosition();
				if (cursor < text.length() - 1) {
					textField.setCursorPositionEnd();
				}
				
			}
			
			textField.setText(text);
			return;
		}
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (mouseX < textField.x + guiLeft || mouseX > textField.x + textField.width + guiLeft
				|| mouseY < textField.y + guiTop || mouseY > textField.y + textField.height + guiTop) {
			textField.setFocused(false);
		} else if (mouseX > textField.x + guiLeft && mouseX < textField.x + textField.width + guiLeft
				&& mouseY > textField.y + guiTop && mouseY < textField.y + textField.height + guiTop) {
			textField.setFocused(true);
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public void initGui() {
		
		super.initGui();
		
		textField = new GuiTextField(1, this.font, 16, 16, 144, 32);
		Keyboard.enableRepeatEvents(true);
		
		textField.setEnabled(true);
		textField.setFocused(true);
		textField.setCanLoseFocus(true);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
		
	}
	
	@Override
	public void onGuiClosed() {
		
		if (textField.getText() == null || textField.getText().isEmpty()) {
			Minecraft.getMinecraft().player.sendChatMessage("You need to input the victim's name...");
//			te.destroy();
		} else {
			te.setVictim(textField.getText());
		}
		
		super.onGuiClosed();
	}

}
