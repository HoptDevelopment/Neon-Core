package com.roguehcf.neon.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.NeonServer;

public class ChatCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0) {
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/chat [toggle | slow] <timeBetweenMessages>"));
			return true;
		}
		
		NeonServer neonServer = Neon.getInstance().getNeonServer();
		
		if(args[0].equalsIgnoreCase("toggle")) {
			neonServer.setChatStatus(neonServer.getChatStatus() == ChatStatus.NORMAL ? 
					ChatStatus.LOCKED : ChatStatus.NORMAL);
			
			Bukkit.broadcastMessage(LocaleConfiguration.TOGGLED_CHAT_MODE
					.replaceAll("\\{0\\}", neonServer.getChatStatus().getPrintableName()));
 		}
		
		if(args[0].equalsIgnoreCase("slow")) {
			
			int cooldownSeconds = 10;
			
			if(args.length == 2) {
				try{
					cooldownSeconds = Integer.parseInt(args[1]);
				}catch(NumberFormatException e) {
					sender.sendMessage(LocaleConfiguration.INVALID_NUMBER);
					return true;
				}
			}
			
			
			neonServer.setChatStatus(neonServer.getChatStatus() == ChatStatus.NORMAL ?
					ChatStatus.SLOWED : ChatStatus.NORMAL);
			
			neonServer.setChatDelay(neonServer.getChatStatus() == ChatStatus.NORMAL ? 
					0 : cooldownSeconds); 
			
			Bukkit.broadcastMessage(LocaleConfiguration.TOGGLED_CHAT_MODE
					.replaceAll("\\{0\\}", neonServer.getChatStatus().getPrintableName()));
		}
		
		return true;
	}

}
