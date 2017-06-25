package com.roguehcf.neon.deathban.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.deathban.commands.arguments.deathban.DeathbanInfoArgument;
import com.roguehcf.neon.deathban.commands.arguments.deathban.DeathbanInventoryArgument;
import com.roguehcf.neon.util.CommandArgument;

public class DeathbanCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		ArrayList<CommandArgument> arguments = new ArrayList<CommandArgument>();
		
		CommandArgument info = new DeathbanInfoArgument();
		CommandArgument inventory = new DeathbanInventoryArgument();
		
		arguments.add(info);
		arguments.add(inventory);
		
		if(args.length == 0){
			sendHelpMsg(sender, arguments);
			return true;
		}
		
		for(CommandArgument argument : arguments){
			if(argument.getName().equalsIgnoreCase(args[0])){
				if(sender.hasPermission(argument.getPermission())){
					argument.onExecute(sender, cmd, label, args);
				}else{
					sender.sendMessage(LocaleConfiguration.NO_PERM);
				}
				return true;
			}
		}
		
		sendHelpMsg(sender, arguments);
		
		return true;
	}

	public void sendHelpMsg(CommandSender sender, ArrayList<CommandArgument> arguments) {
		for(String s : LocaleConfiguration.COMMAND_HELP_TITLE){
			sender.sendMessage(LocaleConfiguration.C(s).replaceAll("\\{0\\}", "Deathban"));
		}
		
		for(CommandArgument argument : arguments){
			if(sender.hasPermission(argument.getPermission())){
				sender.sendMessage(argument.getHelp());
			}
		}
	}

}
