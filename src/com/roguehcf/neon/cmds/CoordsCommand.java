package com.roguehcf.neon.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class CoordsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		for(String s : LocaleConfiguration.COORDS){
			sender.sendMessage(LocaleConfiguration.C(s));
		}
		
		return true;
	}

}
