package com.roguehcf.neon.factions;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.factions.struct.FactionArgument;
import com.roguehcf.neon.util.CommandArgument;

import net.md_5.bungee.api.ChatColor;

public class FactionExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		
		ArrayList<FactionArgument> arguments = new ArrayList<FactionArgument>();
		
		//Commands
		
		if(args.length == 0) {
			help(commandSender);
			return true;
		}
		
		for(FactionArgument argument : arguments) {
			if(argument.getName().equalsIgnoreCase(args[0])) {
				if(argument.getPermission() != null) {
					if(commandSender.hasPermission(argument.getPermission())) {
						argument.execute(commandSender, args);
					} else {
						commandSender.sendMessage(LocaleConfiguration.NO_PERM);
					}
				} else {
					argument.execute(commandSender, args);
				}
			}
		}
		
		help(commandSender);
		
		return false;
	}
	
	public void help(CommandSender commandSender) {
		for(String s : LocaleConfiguration.FACTION_HELP) {
			commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
		}
	}
}
