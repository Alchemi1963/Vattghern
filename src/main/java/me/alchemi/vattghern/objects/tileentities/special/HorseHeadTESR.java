package me.alchemi.vattghern.objects.tileentities.special;

import me.alchemi.vattghern.objects.models.blocks.ModelHorseHead;
import me.alchemi.vattghern.objects.tileentities.TileEntityHorseHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HorseHeadTESR extends TileEntitySpecialRenderer<TileEntityHorseHead>{

	private static final ModelHorseHead MODEL = new ModelHorseHead();
	
	@Override
	public void render(TileEntityHorseHead te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		
		if (destroyStage >= 0) {
			bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 2.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else bindTexture(te.getHorseType().getTextureLocation());
		
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();
		GlStateManager.translate(x + 0.5F, y + 1.5F, z + 0.5F);
		float f = 0.0625F;
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();

		GlStateManager.depthMask(true);
		float brightnessX = OpenGlHelper.lastBrightnessX;
		float brightnessY = OpenGlHelper.lastBrightnessY;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680, 0);

		MODEL.renderAll((float) te.getSkullRotation());

		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, brightnessX, brightnessY);
		GlStateManager.disableBlend();
        
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
		
	}
	
}
