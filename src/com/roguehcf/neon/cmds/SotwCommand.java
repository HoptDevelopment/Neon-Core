package com.roguehcf.neon.cmds;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.timer.GlobalTimer;
import com.roguehcf.neon.timer.type.TimerType;

import net.md_5.bungee.api.ChatColor;

public class SotwCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		
		if(args.length == 0){
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/sotw [start/stop] <time>"));
			return true;
		}
		
		if(args[0].equalsIgnoreCase("stop")){
			if(Neon.getInstance().getTimerManager().getGlobalTimer(TimerType.SOTW) != null){
				Neon.getInstance().getTimerManager().getGlobalTimer(TimerType.SOTW).setTimeRemaining(0);
				Bukkit.broadcastMessage(LocaleConfiguration.SOTW_ENDED);
				return true;
			}else{
				sender.sendMessage(ChatColor.RED + "SOTW is not active.");
				return true;
			}
		}
		
		Long time = TimeUnit.HOURS.toSeconds(2L); /* 2 hours */
		
		if(args.length == 2){
			try{
				time = Long.parseLong(args[1]);
			}catch(Exception e){
				sender.sendMessage(LocaleConfiguration.INVALID_NUMBER);
				return true;
			}
		}
		
		Neon.getInstance().getTimerManager().addGlobalTimer(new GlobalTimer(TimerType.SOTW, time));
		Bukkit.broadcastMessage(LocaleConfiguration.SOTW_STARTED
				.replaceAll("\\{0\\}", Neon.getInstance().getTimerManager().getGlobalTimer(TimerType.SOTW).getRemainingFormatted()));
		
		return true;
	}

}
