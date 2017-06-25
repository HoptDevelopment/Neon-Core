package com.roguehcf.neon.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import com.roguehcf.neon.configuration.type.BlacklistConfiguration;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class BlacklistListener implements Listener {
	
	@EventHandler
	public void onConnect(PlayerLoginEvent event) {
		
		if(BlacklistConfiguration.isBlacklisted(event.getPlayer().getUniqueId())) {
			event.disallow(Result.KICK_BANNED, LocaleConfiguration.BLACKLIST_KICK);
		}
		
	}

}
