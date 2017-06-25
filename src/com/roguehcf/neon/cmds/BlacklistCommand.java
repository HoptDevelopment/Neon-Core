package com.roguehcf.neon.cmds;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.configuration.type.BlacklistConfiguration;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class BlacklistCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0) {
			sender.sendMessage(LocaleConfiguration.USAGE
					.replaceAll("\\{0\\}", "/blacklist [player]"));
			return true;
		}
		
		OfflinePlayer toBlacklist = Bukkit.getOfflinePlayer(args[0]);
		
		if(!toBlacklist.hasPlayedBefore()) {
			sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
			return true;
		}
		
		UUID uuid = toBlacklist.getUniqueId();
		
		if(BlacklistConfiguration.isBlacklisted(uuid)) {
			sender.sendMessage(ChatColor.RED + "That player is already blacklisted.");
			sender.sendMessage(ChatColor.RED + "Blacklists can only be removed via config file. (blacklist.yml)");
			return true;
		}
		
		BlacklistConfiguration.setBlacklisted(uuid, true);
		Bukkit.broadcastMessage(LocaleConfiguration.BLACKLIST_BROADCAST
				.replaceAll("\\{0\\}", toBlacklist.getName()));
		
		return true;
	}

}
