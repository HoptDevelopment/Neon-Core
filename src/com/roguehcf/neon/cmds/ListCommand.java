package com.roguehcf.neon.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.joptsimple.internal.Strings;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.ServerUtils;

public class ListCommand implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		for(String s : LocaleConfiguration.LIST_MSG){
			sender.sendMessage(LocaleConfiguration.C(s)
					.replaceAll("\\{0\\}", String.valueOf(Bukkit.getOnlinePlayers().length))
					.replaceAll("\\{1\\}", String.valueOf(Bukkit.getMaxPlayers()))
					.replaceAll("\\{2\\}", ServerUtils.getOnlineStaff().size() > 0 ? Strings.join(ServerUtils.getOnlineStaff(), ", ") : "None"));
			
		}
		
		return true;
	}

}
