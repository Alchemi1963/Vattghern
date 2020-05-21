package me.alchemi.vattghern.utils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

public class Scheduler {
	
	private final Runnable task;
	private int ticksRemaining;
	
	public Scheduler(int ticks, Runnable task) {
		this.task = task;
		ticksRemaining = ticks;
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onTick(ServerTickEvent e) {
		if (ticksRemaining <= 0) {
			task.run();
			MinecraftForge.EVENT_BUS.unregister(this);
		}
		
		ticksRemaining --;
	}
	
}
