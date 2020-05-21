package me.alchemi.vattghern.common.entities;

import me.alchemi.vattghern.Vattghern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityLeshen extends EntityMob {

	public static final ResourceLocation LOOT = new ResourceLocation(Vattghern.MOD_ID, "entities/leshen");
	
	public EntityLeshen(World world) {
		super(world);
		setSize(2.25F, 2.95F);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
		
	}
	
	@Override
	protected void initEntityAI() {
		tasks.addTask(5, new EntityAIAttackMelee(this, 0.5D, true));
		tasks.addTask(6, new EntityAIMoveTowardsTarget(this, 0.5D, 50.0F));
		tasks.addTask(7, new EntityAIWander(this, 0.25D));
		tasks.addTask(8, new EntityAILookIdle(this));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[] {EntityBat.class, EntityWolf.class}));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, false));
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		// TODO Auto-generated method stub
		return super.attackEntityAsMob(entityIn);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		// TODO Auto-generated method stub
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return LOOT;
	}
	
	@Override
	protected boolean isValidLightLevel() {
		return true;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return world.getBiome(getPosition()).getBiomeName().toLowerCase().contains("forest") && super.getCanSpawnHere();
	}
}
