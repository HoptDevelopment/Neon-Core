package com.roguehcf.neon.timer.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.roguehcf.neon.timer.NeonTimer;
import com.roguehcf.neon.util.NeonPlayer;

import lombok.Getter;

public class TimerExpireEvent extends Event{

	@Getter public NeonTimer timer;
	@Getter public Player player;
	@Getter public NeonPlayer neonPlayer;
	
	private static final HandlerList handlers = new HandlerList();
	
	public TimerExpireEvent(NeonTimer timer, Player player) {
		this.timer = timer;
		this.player = player;
		this.neonPlayer = NeonPlayer.getByPlayer(player);
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}
