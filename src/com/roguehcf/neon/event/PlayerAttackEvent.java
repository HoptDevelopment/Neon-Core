package com.roguehcf.neon.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;

public class PlayerAttackEvent extends Event {

	public static final HandlerList handlers = new HandlerList();
	
	@Getter public Player victim;
	@Getter public Player damager;
	
	public PlayerAttackEvent(Player victim, Player damager) {
		this.damager = damager;
		this.victim = victim;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
