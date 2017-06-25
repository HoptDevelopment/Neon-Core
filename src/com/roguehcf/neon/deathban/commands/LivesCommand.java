package com.roguehcf.neon.deathban.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.deathban.commands.arguments.lives.LivesCheckArgument;
import com.roguehcf.neon.deathban.commands.arguments.lives.LivesReviveArgument;
import com.roguehcf.neon.deathban.commands.arguments.lives.LivesSendArgument;
import com.roguehcf.neon.deathban.commands.arguments.lives.LivesTakeArgument;
import com.roguehcf.neon.util.CommandArgument;

public class LivesCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		ArrayList<CommandArgument> arguments = new ArrayList<CommandArgument>();
		
		CommandArgument send = new LivesSendArgument();
		CommandArgument revive = new LivesReviveArgument();
		CommandArgument check = new LivesCheckArgument();
		CommandArgument take = new LivesTakeArgument();
		
		arguments.add(send);
		arguments.add(check);
		arguments.add(revive);
		arguments.add(take);
		
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
	
	public void sendHelpMsg(CommandSender sender, ArrayList<CommandArgument> arguments){
		for(String s : LocaleConfiguration.COMMAND_HELP_TITLE){
			sender.sendMessage(LocaleConfiguration.C(s).replaceAll("\\{0\\}", "Lives"));
		}
		
		for(CommandArgument argument : arguments){
			if(sender.hasPermission(argument.getPermission())){
				sender.sendMessage(argument.getHelp());
			}
		}
	}
}
