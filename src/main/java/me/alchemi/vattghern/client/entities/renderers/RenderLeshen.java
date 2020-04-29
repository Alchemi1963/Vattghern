package me.alchemi.vattghern.client.entities.renderers;

import me.alchemi.vattghern.Vattghern;
import me.alchemi.vattghern.client.entities.models.ModelLeshen;
import me.alchemi.vattghern.common.entities.EntityLeshen;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderLeshen extends RenderLiving<EntityLeshen> {

	private ResourceLocation mobTexture = new ResourceLocation(Vattghern.MOD_ID, "textures/entity/leshen.png");
	
	public static final IRenderFactory<EntityLeshen> FACTORY = new IRenderFactory<EntityLeshen>() {
		
		@Override
		public Render<? super EntityLeshen> createRenderFor(RenderManager manager) {
			return new RenderLeshen(manager);
		}
		
	};
	
	public RenderLeshen(RenderManager renderManager) {
		super(renderManager, new ModelLeshen(), 1.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLeshen entity) {
		return mobTexture;
	}
	
}
