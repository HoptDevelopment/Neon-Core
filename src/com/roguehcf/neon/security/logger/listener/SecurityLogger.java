package com.roguehcf.neon.security.logger.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.roguehcf.neon.security.logger.ChatLoggerEntry;
import com.roguehcf.neon.security.logger.CommandLoggerEntry;

public class SecurityLogger implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		new ChatLoggerEntry(player, message);
	}
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		new CommandLoggerEntry(player, message);
	}
	
}
