package com.roguehcf.neon.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class MapKitCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		sender.sendMessage(LocaleConfiguration.MAP_KIT_COMMAND);
		
		return true;
	}
}
