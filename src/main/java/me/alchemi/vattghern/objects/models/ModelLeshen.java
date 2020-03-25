package me.alchemi.vattghern.objects.models;

import org.apache.logging.log4j.Level;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLeshen extends ModelBase {
	private final ModelRenderer leg2;
	private final ModelRenderer lower_leg2;
	private final ModelRenderer foot2;
	private final ModelRenderer leg1;
	private final ModelRenderer foot1;
	private final ModelRenderer lower_leg1;
	private final ModelRenderer twine_rope;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer antler1;
	private final ModelRenderer antler0;
	private final ModelRenderer arm1;
	private final ModelRenderer elbow1;
	private final ModelRenderer lower_arm1;
	private final ModelRenderer hand1;
	private final ModelRenderer finger0;
	private final ModelRenderer section;
	private final ModelRenderer finger1;
	private final ModelRenderer section2;
	private final ModelRenderer finger2;
	private final ModelRenderer section3;
	private final ModelRenderer finger3;
	private final ModelRenderer section4;
	private final ModelRenderer arm2;
	private final ModelRenderer elbow2;
	private final ModelRenderer lower_arm2;
	private final ModelRenderer hand2;
	private final ModelRenderer finger4;
	private final ModelRenderer section5;
	private final ModelRenderer finger5;
	private final ModelRenderer section6;
	private final ModelRenderer finger6;
	private final ModelRenderer section7;
	private final ModelRenderer finger7;
	private final ModelRenderer section8;

	public ModelLeshen() {
		textureWidth = 64;
		textureHeight = 64;

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(5.0F, -2.0F, -2.5F);
		leg2.cubeList.add(new ModelBox(leg2, 0, 49, -1.5F, 0.0F, 2.0F, 3, 12, 3, 0.0F, false));

		lower_leg2 = new ModelRenderer(this);
		lower_leg2.setRotationPoint(0.0F, 11.5F, 1.5F);
		leg2.addChild(lower_leg2);
		lower_leg2.cubeList.add(new ModelBox(lower_leg2, 0, 49, -1.0F, 0.5F, 1.0F, 2, 13, 2, 0.0F, false));

		foot2 = new ModelRenderer(this);
		foot2.setRotationPoint(0.0F, 14.0F, -2.5F);
		lower_leg2.addChild(foot2);
		foot2.cubeList.add(new ModelBox(foot2, 0, 49, -2.0F, -0.5F, -0.5F, 4, 1, 7, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-5.0F, -2.0F, -1.5F);
		leg1.cubeList.add(new ModelBox(leg1, 13, 34, -1.5F, 0.0F, 1.0F, 3, 12, 3, 0.0F, false));

		foot1 = new ModelRenderer(this);
		foot1.setRotationPoint(0.0F, 25.5F, 0.0F);
		leg1.addChild(foot1);
		foot1.cubeList.add(new ModelBox(foot1, 13, 34, -2.0F, -0.5F, -2.5F, 4, 1, 7, 0.0F, false));

		lower_leg1 = new ModelRenderer(this);
		lower_leg1.setRotationPoint(0.0F, 11.5F, 0.5F);
		leg1.addChild(lower_leg1);
		lower_leg1.cubeList.add(new ModelBox(lower_leg1, 13, 34, -1.0F, 0.5F, 1.0F, 2, 13, 2, 0.0F, false));

		twine_rope = new ModelRenderer(this);
		twine_rope.setRotationPoint(0.0F, 24.0F, 0.0F);
		twine_rope.cubeList.add(new ModelBox(twine_rope, 0, 18, -10.0F, -29.0F, -3.0F, 1, 1, 8, 0.0F, false));
		twine_rope.cubeList.add(new ModelBox(twine_rope, 0, 18, -1.0F, -29.0F, -4.0F, 1, 4, 1, 0.0F, false));
		twine_rope.cubeList.add(new ModelBox(twine_rope, 0, 18, 0.0F, -29.0F, -4.0F, 1, 3, 1, 0.0F, false));
		twine_rope.cubeList.add(new ModelBox(twine_rope, 0, 18, -10.0F, -29.0F, -4.0F, 9, 1, 1, 0.0F, false));
		twine_rope.cubeList.add(new ModelBox(twine_rope, 0, 18, -10.0F, -29.0F, 5.0F, 20, 1, 1, 0.0F, false));
		twine_rope.cubeList.add(new ModelBox(twine_rope, 0, 18, 1.0F, -29.0F, -4.0F, 9, 1, 1, 0.0F, false));
		twine_rope.cubeList.add(new ModelBox(twine_rope, 0, 18, 9.0F, -29.0F, -3.0F, 1, 1, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 24, 46, -6.0F, -45.0F, -3.0F, 12, 10, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 24, 46, -9.0F, -29.0F, -3.0F, 18, 3, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 26, 18, -9.0F, -29.0F, -3.05F, 18, 13, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 26, 18, -9.0F, -29.0F, 5.05F, 18, 13, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 24, 46, -4.0F, -35.0F, -2.0F, 8, 6, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -21.0F, -0.3333F);
		head.cubeList.add(new ModelBox(head, 16, 0, -4.5F, -9.0F, -3.1667F, 9, 9, 9, 0.0F, false));

		antler1 = new ModelRenderer(this);
		antler1.setRotationPoint(0.0F, 45.0F, 0.3333F);
		head.addChild(antler1);
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -7.0F, -56.0F, -1.0F, 4, 2, 4, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -11.0F, -57.0F, -1.0F, 4, 2, 4, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -13.0F, -58.0F, -1.0F, 2, 2, 4, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -25.0F, -58.0F, -1.0F, 12, 1, 4, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -28.0F, -59.0F, -0.75F, 5, 1, 3, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -30.0F, -60.0F, -0.5F, 3, 1, 3, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -31.0F, -61.0F, -0.25F, 2, 1, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -11.0F, -58.0F, -1.0F, 1, 1, 4, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -12.0F, -59.0F, -1.0F, 1, 1, 4, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -25.0F, -60.0F, -0.75F, 1, 1, 3, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -32.0F, -63.0F, 0.0F, 1, 2, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -33.0F, -64.0F, 0.5F, 1, 1, 1, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -17.0F, -59.0F, -1.0F, 2, 1, 4, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -21.0F, -59.0F, -1.0F, 2, 1, 4, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -17.0F, -60.0F, 0.0F, 2, 1, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -21.0F, -60.0F, 0.0F, 2, 1, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -15.0F, -61.0F, 0.0F, 1, 1, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -21.0F, -61.0F, 0.0F, 1, 1, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -22.0F, -67.0F, 0.0F, 1, 6, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -14.0F, -66.0F, 0.0F, 1, 5, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -15.0F, -68.0F, 0.0F, 1, 2, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -23.0F, -69.0F, 0.0F, 1, 2, 2, 0.0F, false));
		antler1.cubeList.add(new ModelBox(antler1, 48, 18, -24.0F, -70.0F, 0.0F, 1, 1, 2, 0.0F, false));

		antler0 = new ModelRenderer(this);
		antler0.setRotationPoint(0.0F, 45.0F, 0.3333F);
		setRotationAngle(antler0, 0.0F, 3.1416F, 0.0F);
		head.addChild(antler0);
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -7.0F, -56.0F, -3.0F, 4, 2, 4, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -11.0F, -57.0F, -3.0F, 4, 2, 4, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -13.0F, -58.0F, -3.0F, 2, 2, 4, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -25.0F, -58.0F, -3.0F, 12, 1, 4, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -28.0F, -59.0F, -2.75F, 5, 1, 3, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -30.0F, -60.0F, -2.5F, 3, 1, 3, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -31.0F, -61.0F, -2.25F, 2, 1, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -11.0F, -58.0F, -3.0F, 1, 1, 4, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -12.0F, -59.0F, -3.0F, 1, 1, 4, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -25.0F, -60.0F, -2.75F, 1, 1, 3, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -32.0F, -63.0F, -2.0F, 1, 2, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -33.0F, -64.0F, -1.5F, 1, 1, 1, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -17.0F, -59.0F, -3.0F, 2, 1, 4, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -21.0F, -59.0F, -3.0F, 2, 1, 4, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -17.0F, -60.0F, -2.0F, 2, 1, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -21.0F, -60.0F, -2.0F, 2, 1, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -15.0F, -61.0F, -2.0F, 1, 1, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -21.0F, -61.0F, -2.0F, 1, 1, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -22.0F, -67.0F, -2.0F, 1, 6, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -14.0F, -66.0F, -2.0F, 1, 5, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -15.0F, -68.0F, -2.0F, 1, 2, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -23.0F, -69.0F, -2.0F, 1, 2, 2, 0.0F, false));
		antler0.cubeList.add(new ModelBox(antler0, 0, 21, -24.0F, -70.0F, -2.0F, 1, 1, 2, 0.0F, false));

		arm1 = new ModelRenderer(this);
		arm1.setRotationPoint(-6.0F, -19.0F, -0.5F);
		setRotationAngle(arm1, 0.0F, 0.0F, -1.1345F);
		arm1.cubeList.add(new ModelBox(arm1, 26, 37, -16.0F, -1.0F, 0.5F, 16, 2, 3, 0.0F, false));

		elbow1 = new ModelRenderer(this);
		elbow1.setRotationPoint(-19.0F, 0.0F, 0.0F);
		setRotationAngle(elbow1, 0.0F, -0.6109F, 0.0F);
		arm1.addChild(elbow1);
		elbow1.cubeList.add(new ModelBox(elbow1, 26, 37, -1.8528F, -2.0F, -0.8617F, 6, 4, 5, 0.0F, false));

		lower_arm1 = new ModelRenderer(this);
		lower_arm1.setRotationPoint(-3.0F, 0.0F, 0.0F);
		setRotationAngle(lower_arm1, 0.0F, 0.1745F, 0.0F);
		elbow1.addChild(lower_arm1);
		lower_arm1.cubeList.add(new ModelBox(lower_arm1, 26, 37, -15.1548F, -1.0F, 0.3126F, 16, 2, 3, 0.0F, false));

		hand1 = new ModelRenderer(this);
		hand1.setRotationPoint(-16.0556F, -0.3333F, 0.0F);
		lower_arm1.addChild(hand1);
		hand1.cubeList.add(new ModelBox(hand1, 26, 37, -0.0992F, -1.6667F, -0.6874F, 1, 4, 5, 0.0F, false));

		finger0 = new ModelRenderer(this);
		finger0.setRotationPoint(-0.4444F, 1.8333F, 2.5F);
		hand1.addChild(finger0);
		finger0.cubeList.add(new ModelBox(finger0, 26, 37, 0.3452F, -0.5F, 1.8126F, 1, 1, 4, 0.0F, false));

		section = new ModelRenderer(this);
		section.setRotationPoint(-0.5F, 0.0F, 3.5F);
		finger0.addChild(section);
		section.cubeList.add(new ModelBox(section, 26, 37, -5.1548F, -0.5F, 1.3126F, 6, 1, 1, 0.0F, false));

		finger1 = new ModelRenderer(this);
		finger1.setRotationPoint(-0.4444F, -1.6667F, 2.0F);
		hand1.addChild(finger1);
		finger1.cubeList.add(new ModelBox(finger1, 26, 37, 0.3452F, -3.0F, 1.3126F, 1, 3, 1, 0.0F, false));

		section2 = new ModelRenderer(this);
		section2.setRotationPoint(0.0F, -3.0F, 0.0F);
		finger1.addChild(section2);
		section2.cubeList.add(new ModelBox(section2, 26, 37, -6.6548F, -1.0F, 1.3126F, 8, 1, 1, 0.0F, false));

		finger2 = new ModelRenderer(this);
		finger2.setRotationPoint(-0.4444F, -1.6667F, -2.0F);
		hand1.addChild(finger2);
		finger2.cubeList.add(new ModelBox(finger2, 26, 37, 0.3452F, -3.0F, 1.3126F, 1, 3, 1, 0.0F, false));

		section3 = new ModelRenderer(this);
		section3.setRotationPoint(0.0F, -3.0F, 0.0F);
		finger2.addChild(section3);
		section3.cubeList.add(new ModelBox(section3, 26, 37, -6.6548F, -1.0F, 1.3126F, 8, 1, 1, 0.0F, false));

		finger3 = new ModelRenderer(this);
		finger3.setRotationPoint(-0.4444F, 1.8333F, -2.5F);
		hand1.addChild(finger3);
		finger3.cubeList.add(new ModelBox(finger3, 26, 37, 0.3452F, -0.5F, -2.1874F, 1, 1, 4, 0.0F, false));

		section4 = new ModelRenderer(this);
		section4.setRotationPoint(-0.5F, 0.0F, -3.5F);
		finger3.addChild(section4);
		section4.cubeList.add(new ModelBox(section4, 26, 37, -5.1548F, -0.5F, 1.3126F, 6, 1, 1, 0.0F, false));

		arm2 = new ModelRenderer(this);
		arm2.setRotationPoint(6.0F, -19.0F, -0.5F);
		setRotationAngle(arm2, 0.0F, 0.0F, 1.1345F);
		arm2.cubeList.add(new ModelBox(arm2, 13, 46, 0.0F, -1.0F, 0.5F, 16, 2, 3, 0.0F, false));

		elbow2 = new ModelRenderer(this);
		elbow2.setRotationPoint(18.0F, 0.0F, 0.0F);
		setRotationAngle(elbow2, 0.0F, 0.6109F, 0.0F);
		arm2.addChild(elbow2);
		elbow2.cubeList.add(new ModelBox(elbow2, 13, 46, -3.1472F, -2.0F, -0.8617F, 6, 4, 5, 0.0F, false));

		lower_arm2 = new ModelRenderer(this);
		lower_arm2.setRotationPoint(4.0F, 0.0F, 0.0F);
		elbow2.addChild(lower_arm2);
		lower_arm2.cubeList.add(new ModelBox(lower_arm2, 13, 46, -1.1472F, -1.0F, 0.1383F, 16, 2, 3, 0.0F, false));

		hand2 = new ModelRenderer(this);
		hand2.setRotationPoint(16.0F, -0.3333F, 0.0F);
		setRotationAngle(hand2, -0.4363F, 0.0F, 0.0F);
		lower_arm2.addChild(hand2);
		hand2.cubeList.add(new ModelBox(hand2, 13, 46, -1.1472F, -2.359F, -1.0152F, 1, 4, 5, 0.0F, false));

		finger4 = new ModelRenderer(this);
		finger4.setRotationPoint(0.5F, 1.8333F, 2.5F);
		setRotationAngle(finger4, -0.6109F, 0.9599F, 0.0F);
		hand2.addChild(finger4);
		finger4.cubeList.add(new ModelBox(finger4, 13, 46, -2.3743F, -1.0167F, -0.4693F, 1, 1, 4, 0.0F, false));

		section5 = new ModelRenderer(this);
		section5.setRotationPoint(0.5F, 0.0F, 3.5F);
		setRotationAngle(section5, 0.0F, -0.3491F, 0.0F);
		finger4.addChild(section5);
		section5.cubeList.add(new ModelBox(section5, 13, 46, -1.9217F, -1.0167F, -0.2999F, 6, 1, 1, 0.0F, false));

		finger5 = new ModelRenderer(this);
		finger5.setRotationPoint(0.5F, -1.6667F, 2.0F);
		setRotationAngle(finger5, -0.3491F, 0.1745F, 0.6109F);
		hand2.addChild(finger5);
		finger5.cubeList.add(new ModelBox(finger5, 13, 46, -2.0743F, -3.3354F, 0.687F, 1, 3, 1, 0.0F, false));

		section6 = new ModelRenderer(this);
		section6.setRotationPoint(0.0F, -3.0F, 0.0F);
		finger5.addChild(section6);
		section6.cubeList.add(new ModelBox(section6, 13, 46, -2.0743F, -1.3354F, 0.687F, 8, 1, 1, 0.0F, false));

		finger6 = new ModelRenderer(this);
		finger6.setRotationPoint(0.5F, -1.6667F, -2.0F);
		setRotationAngle(finger6, 0.1745F, 0.1745F, 0.7854F);
		hand2.addChild(finger6);
		finger6.cubeList.add(new ModelBox(finger6, 13, 46, -2.0388F, -2.4686F, 0.6618F, 1, 3, 1, 0.0F, false));

		section7 = new ModelRenderer(this);
		section7.setRotationPoint(0.0F, -3.0F, 0.0F);
		finger6.addChild(section7);
		section7.cubeList.add(new ModelBox(section7, 13, 46, -2.0388F, -0.4686F, 0.6618F, 8, 1, 1, 0.0F, false));

		finger7 = new ModelRenderer(this);
		finger7.setRotationPoint(0.5F, 1.8333F, -2.5F);
		setRotationAngle(finger7, 0.6981F, 0.5236F, 0.0F);
		hand2.addChild(finger7);
		finger7.cubeList.add(new ModelBox(finger7, 13, 46, -2.2359F, -0.5725F, -3.0093F, 1, 1, 4, 0.0F, false));

		section8 = new ModelRenderer(this);
		section8.setRotationPoint(0.5F, 0.0F, -3.5F);
		finger7.addChild(section8);
		section8.cubeList.add(new ModelBox(section8, 13, 46, -1.7359F, -0.5725F, 0.4907F, 6, 1, 1, 0.0F, false));
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		leg2.render(scale);
		leg1.render(scale);
		twine_rope.render(scale);
		body.render(scale);
		head.render(scale);
		arm1.render(scale);
		arm2.render(scale);
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		setRotationAngle(head, (float) Math.toRadians(headPitch), (float) Math.toRadians(netHeadYaw/100), head.rotateAngleZ);
		arm1.rotateAngleX = MathHelper.cos(limbSwing) * limbSwingAmount;
		arm2.rotateAngleX = MathHelper.cos(limbSwing) * limbSwingAmount;
	}
	
	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}