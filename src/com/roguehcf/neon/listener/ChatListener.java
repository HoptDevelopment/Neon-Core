package com.roguehcf.neon.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.chat.ChatCooldown;
import com.roguehcf.neon.chat.ChatStatus;
import com.roguehcf.neon.cmds.StaffChatCommand;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.NeonServer;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;

public class ChatListener implements Listener {

	/**
	 * This chat listener handles chat cooldowns and locking.
	 * - This does not handle chat formatting, Neon doesn't handle chat at all.
	 */
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		NeonServer neonServer = Neon.getInstance().getNeonServer();
		
		/* Handle Chat Lock */
		if(neonServer.getChatStatus() == ChatStatus.LOCKED) {
			player.sendMessage(ChatColor.RED + "The server chat is currently locked.");
			event.setCancelled(true);
			return;
		}
		
		/* Handle Chat Slowing */
		if(neonServer.getChatStatus() == ChatStatus.SLOWED) {
			if(neonServer.getChatCooldownByUUID(player.getUniqueId()) != null) {
				ChatCooldown cooldown = neonServer.getChatCooldownByUUID(player.getUniqueId());
				if(cooldown.getRemaining() > 0) {
					player.sendMessage(ChatColor.RED + "The server chat is currently slowed. You are still on a chat cooldown for another " + cooldown.getRemainingString() + '.');
					event.setCancelled(true);
					return;
				}
				
			}
			neonServer.setChatCooldown(player.getUniqueId(), new ChatCooldown(player.getUniqueId(), (long) neonServer.getChatDelay()));
		}
		
		/* Handle Staff Chat */
		if(StaffChatCommand.getByPlayer(player)) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(all.hasPermission("neon.staff")) {
					all.sendMessage(LocaleConfiguration.STAFF_CHAT_FORMAT
							.replaceAll("\\{0\\}", player.getName())
							.replaceAll("\\{1\\}", event.getMessage()));
				}
			}
			event.setCancelled(true);
		}
		
	}
}
