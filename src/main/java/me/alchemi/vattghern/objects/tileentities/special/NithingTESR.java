package me.alchemi.vattghern.objects.tileentities.special;

import org.lwjgl.opengl.GL11;

import me.alchemi.vattghern.objects.tileentities.TileEntityNithing;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class NithingTESR extends TileEntitySpecialRenderer<TileEntityNithing>{

	private ResourceLocation textureHead;
	
	@Override
	public void render(TileEntityNithing te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x, y, z);
		GlStateManager.disableRescaleNormal();
		
		renderFence(te);
		renderHead(te);
		
		GlStateManager.popMatrix();
	}
	
	private void renderFence(TileEntityNithing te) {
		
		ItemStack fence = te.getFenceItem();
		if (!(fence == null || fence.isEmpty())) {
			
			GlStateManager.pushMatrix();

			GlStateManager.scale(1.0F, 1.6F, 1.0F);

	        RenderHelper.disableStandardItemLighting();
	        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	        if (Minecraft.isAmbientOcclusionEnabled()) {
	            GlStateManager.shadeModel(GL11.GL_SMOOTH);
	        } else {
	            GlStateManager.shadeModel(GL11.GL_FLAT);
	        }

	        World world = te.getWorld();
	        // Translate back to local view coordinates so that we can do the acual rendering here
	        GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());

	        Tessellator tessellator = Tessellator.getInstance();
	        BufferBuilder bufferBuilder = tessellator.getBuffer();
	        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

	        IBlockState state = Block.getBlockFromItem(fence.getItem()).getDefaultState();
	        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
	        IBakedModel model = dispatcher.getModelForState(state);
	        dispatcher.getBlockModelRenderer().renderModel(world, model, state, te.getPos(), bufferBuilder, true);
	        tessellator.draw();

	        RenderHelper.enableStandardItemLighting();
	        GlStateManager.popMatrix();
		}
	}
	
	private void renderHead(TileEntityNithing te) {
		ItemStack head = te.getHeadItem();
		if (!(head == null || head.isEmpty())) {
			
			GlStateManager.pushMatrix();

			GlStateManager.translate(-0.05F, 1.2F, 0.05F);
			GlStateManager.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(1.1D, 1.1D, 1.1D);
			
	        RenderHelper.disableStandardItemLighting();
	        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	        if (Minecraft.isAmbientOcclusionEnabled()) {
	            GlStateManager.shadeModel(GL11.GL_SMOOTH);
	        } else {
	            GlStateManager.shadeModel(GL11.GL_FLAT);
	        }

	        World world = te.getWorld();
	        // Translate back to local view coordinates so that we can do the acual rendering here
	        GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());

	        Tessellator tessellator = Tessellator.getInstance();
	        BufferBuilder bufferBuilder = tessellator.getBuffer();
	        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

	        IBlockState state = Block.getBlockFromItem(head.getItem()).getStateFromMeta(head.getMetadata());
	        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
	        IBakedModel model = dispatcher.getModelForState(state);
	        dispatcher.getBlockModelRenderer().renderModel(world, model, state, te.getPos(), bufferBuilder, true);
	        tessellator.draw();

	        RenderHelper.enableStandardItemLighting();
	        GlStateManager.popMatrix();
		}
	}
	
}
