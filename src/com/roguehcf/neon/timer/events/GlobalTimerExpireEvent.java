package com.roguehcf.neon.timer.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.roguehcf.neon.timer.GlobalTimer;

import lombok.Getter;

public class GlobalTimerExpireEvent extends Event{

	@Getter public GlobalTimer timer;
	
	private static final HandlerList handlers = new HandlerList();
	
	public GlobalTimerExpireEvent(GlobalTimer timer) {
		this.timer = timer;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
