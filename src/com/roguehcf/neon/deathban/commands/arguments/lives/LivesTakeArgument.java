package com.roguehcf.neon.deathban.commands.arguments.lives;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.lang.NumberFormatException;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.configuration.type.PlayerConfiguration;
import com.roguehcf.neon.util.CommandArgument;

import net.md_5.bungee.api.ChatColor;

public class LivesTakeArgument implements CommandArgument {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onExecute(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length <= 2){
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/lives take [player] [amount]"));
			return true;
		}
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
		
		if(!target.hasPlayedBefore()){
			sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
			return true;
		}
		
		Long amount = 0L;
		
		try{
			amount = Long.parseLong(args[2]);
		}catch(NumberFormatException e){
			sender.sendMessage(LocaleConfiguration.INVALID_NUMBER);
			return true;
		}
		
		if(PlayerConfiguration.getLives(target.getUniqueId()) == 0){
			sender.sendMessage(ChatColor.RED + "This player already has no lives.");
			return true;
		}
		PlayerConfiguration.setLives(target.getUniqueId(), PlayerConfiguration.getLives(target.getUniqueId()) - amount);
		
		sender.sendMessage(LocaleConfiguration.LIVES_TOOK
				.replaceAll("\\{0\\}", String.valueOf(amount))
				.replaceAll("\\{1\\}", target.getName())
				.replaceAll("\\{2\\}", String.valueOf(PlayerConfiguration.getLives(target.getUniqueId()))));
		
		return true;
	}

	@Override
	public String getPermission() {
		return "neon.command.lives.argument.take";
	}

	@Override
	public String getName() {
		return "take";
	}

	@Override
	public String getHelp() {
		return LocaleConfiguration.COMMAND_HELP_ENTRY.replaceAll("\\{0\\}", "lives take [player] [amount]")
				.replaceAll("\\{1\\}", "Staff command which takes the specified amount of lives away from a player.");
	}
	
	@Override
	public String getUsage() {
		return "/lives [take] [player] [amount]";
	}
	
	@Override
	public String getDescription() {
		return null;
	}

}
