package com.roguehcf.neon.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.ServerUtils;

import org.bukkit.ChatColor;

public class NeonCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.getName().equalsIgnoreCase("subbotted")){
			sender.sendMessage(ChatColor.DARK_GRAY + "-------------------------------------------------------------------------------");
			sender.sendMessage(ChatColor.GREEN + "Developer Information:");
			sender.sendMessage(ChatColor.YELLOW + " » Version: " + ChatColor.RED + Neon.getInstance().getDescription().getVersion());
			sender.sendMessage(ChatColor.YELLOW + " » Copy ID: " + ChatColor.RED + "1");
			sender.sendMessage(ChatColor.YELLOW + " » Plugin List: " + ChatColor.RESET + ServerUtils.getPluginListMsg(Neon.getInstance()));
			sender.sendMessage(ChatColor.DARK_GRAY + "-------------------------------------------------------------------------------");
			return true;
		}
		
		if(sender.isOp()){
			sender.sendMessage(LocaleConfiguration.C("&4[Operator Message] &eVisit the &aNeonPanel &eat &awww.roguehcf.com/neon/panel &eto view your copy and whitelist servers."));
			return true;
		}
		
		sender.sendMessage(LocaleConfiguration.C("&eThis server is running &aNeon &eby subbotted."));
		sender.sendMessage(LocaleConfiguration.C("&eThe version running on this server is &a" + Neon.getInstance().getDescription().getVersion() + "&e."));
		sender.sendMessage(LocaleConfiguration.C("&7More information about Neon can be found @ www.roguehcf.com/neon/"));
		
		return true;
	}

}
