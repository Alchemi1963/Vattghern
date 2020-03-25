package me.alchemi.vattghern.objects.models.blocks;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelHorseHead extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer upperMouth;
	private final ModelRenderer lowerMouth;
	private final ModelRenderer horseLeftEar;
	private final ModelRenderer horseRightEar;
	private final ModelRenderer neck;
	private final ModelRenderer mane;

	public ModelHorseHead() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(-0.05F, 17.0F, -1.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -2.45F, -7.0F, -3.5F, 5, 5, 7, 0.0F, false));

		upperMouth = new ModelRenderer(this);
		upperMouth.setRotationPoint(0.05F, -9.05F, -19.0F);
		upperMouth.rotateAngleX = 0.0F;
		upperMouth.rotateAngleY = 0.0F;
		upperMouth.rotateAngleZ = 0.0F;
		head.addChild(upperMouth);
		upperMouth.cubeList.add(new ModelBox(upperMouth, 24, 18, -2.0F, 2.4019F, 10.3038F, 4, 3, 6, 0.0F, false));

		lowerMouth = new ModelRenderer(this);
		lowerMouth.setRotationPoint(0.05F, -9.05F, -19.0F);
		head.addChild(lowerMouth);
		lowerMouth.cubeList.add(new ModelBox(lowerMouth, 24, 27, -2.0F, 5.9019F, 11.6699F, 4, 2, 5, 0.0F, false));

		horseLeftEar = new ModelRenderer(this);
		horseLeftEar.setRotationPoint(-0.05F, 17.0F, -1.0F);
		horseLeftEar.cubeList.add(new ModelBox(horseLeftEar, 0, 0, 0.5F, -9.0F, 2.0F, 2, 3, 1, 0.0F, false));

		horseRightEar = new ModelRenderer(this);
		horseRightEar.setRotationPoint(-0.05F, 17.0F, -1.0F);
		horseRightEar.cubeList.add(new ModelBox(horseRightEar, 0, 0, -2.4F, -9.0F, 2.0F, 2, 3, 1, 0.0F, false));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(-0.05F, 17.0F, -1.0F);
		neck.cubeList.add(new ModelBox(neck, 0, 12, -2.0F, -6.8F, -4.0F, 4, 14, 8, 0.0F, false));

		mane = new ModelRenderer(this);
		mane.setRotationPoint(-0.05F, 17.0F, -1.0F);
		mane.cubeList.add(new ModelBox(mane, 58, 0, -0.95F, -8.5F, 3.0F, 2, 16, 4, 0.0F, false));
	}

	public void renderAll(float rotation) {
		rotation = rotation * 017453292F;
		head.rotateAngleY = rotation;
		horseLeftEar.rotateAngleY = rotation;
		horseRightEar.rotateAngleY = rotation;
		neck.rotateAngleY = rotation;
		mane.rotateAngleY = rotation;
		
		head.render(0.0625F);
		horseLeftEar.render(0.0625F);
		horseRightEar.render(0.0625F);
		neck.render(0.0625F);
		mane.render(0.0625F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		horseLeftEar.render(f5);
		horseRightEar.render(f5);
		neck.render(f5);
		mane.render(f5);
	}
	public void setRotationAngles(float x, float y, float z) {
		for (ModelRenderer mod : new ModelRenderer[] {head, horseLeftEar, horseRightEar, neck, mane}) {
			mod.rotateAngleX = x;
			mod.rotateAngleY = y;
			mod.rotateAngleZ = z;
		}
	}
}