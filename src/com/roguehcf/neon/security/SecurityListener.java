package com.roguehcf.neon.security;

import org.bukkit.craftbukkit.libs.joptsimple.internal.Strings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.OptionsConfiguration;

import net.md_5.bungee.api.ChatColor;

public class SecurityListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		if(!OptionsConfiguration.SECURITY_ENABLED) {
			return;
		}
		
		if(event.getPlayer().hasPermission("neon.staff")) {
			
			SecurityProfile profile;
			
			if(!Neon.getInstance().getSecurityProfiles().containsKey(event.getPlayer())) {
				profile = new SecurityProfile(event.getPlayer().getUniqueId());
			}else {
				profile = Neon.getInstance().getSecurityProfiles().get(event.getPlayer());
			}
			
			Player player = event.getPlayer();
			if(profile.hasPassword()) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eSecurity&7: &rPlease log-in by using /login [pin/pass]"));
			}else{
				player.sendMessage(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + Strings.repeat('-', 50));
				player.sendMessage(ChatColor.GOLD + "Please create a password using /register [password]");
				player.sendMessage(ChatColor.YELLOW + "This password will be encrypted and stored securely.");
				player.sendMessage(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + Strings.repeat('-', 50));
			}
			
		}
	}
}
