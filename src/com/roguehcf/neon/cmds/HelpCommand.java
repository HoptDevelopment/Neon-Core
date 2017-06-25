package com.roguehcf.neon.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class HelpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		for(String s : LocaleConfiguration.HELP_LINES){
			sender.sendMessage(LocaleConfiguration.C(s));
		}
		
		return true;
	}

}
