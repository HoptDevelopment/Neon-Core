package com.roguehcf.neon.pvpclass.type.miner;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.roguehcf.neon.util.NeonPlayer;

import lombok.Getter;

public class MinerLevelUpgradeEvent extends Event {

	@Getter private static HandlerList handlerList = new HandlerList();
	@Getter private NeonPlayer player;
	@Getter private MinerLevel level;
	
	public MinerLevelUpgradeEvent(NeonPlayer player, MinerLevel level) {
		this.player = player;
		this.level = level;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}

}
