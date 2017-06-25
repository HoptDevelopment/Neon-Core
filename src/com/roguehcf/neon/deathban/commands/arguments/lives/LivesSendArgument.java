package com.roguehcf.neon.deathban.commands.arguments.lives;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.configuration.type.PlayerConfiguration;
import com.roguehcf.neon.util.CommandArgument;

import org.bukkit.OfflinePlayer;

public class LivesSendArgument implements CommandArgument{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onExecute(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length <= 2){
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/lives send [player] [amount]"));
			return true;
		}
		
		OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[1]);
		Long amount = 0L;
		
		try{
			amount = Long.parseLong(args[2]);
		}catch(NumberFormatException exc){
			sender.sendMessage(LocaleConfiguration.INVALID_NUMBER);
			return true;
		}
		
		if(amount <= 0L){
			sender.sendMessage(LocaleConfiguration.LIVES_QUANTITY);
		}
		
		if(!target.hasPlayedBefore()){
			sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
			return true;
		}
		
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(PlayerConfiguration.getLives(player.getUniqueId()) < 1){
				player.sendMessage(LocaleConfiguration.LIVES_NOT_ENOUGH);
				return true;
			}
			
			PlayerConfiguration.setLives(player.getUniqueId(), PlayerConfiguration.getLives(player.getUniqueId()) - 1);
		}
		
		PlayerConfiguration.setLives(target.getUniqueId(), PlayerConfiguration.getLives(target.getUniqueId()) + 1);
		
		sender.sendMessage(LocaleConfiguration.PLAYER_LIVES_SENT.replaceAll("\\{0\\}", target.getName())
				.replaceAll("\\{1\\}", "" + amount));
		
		return true;
	}

	@Override
	public String getPermission() {
		return "neon.command.lives.argument.send";
	}

	@Override
	public String getHelp() {
		return LocaleConfiguration.COMMAND_HELP_ENTRY.replaceAll("\\{0\\}", "lives send [player] [amount]")
				.replaceAll("\\{1\\}", "Sends a specified amount of lives to the target player.");
	}

	@Override
	public String getName() {
		return "send";
	}
	
	@Override
	public String getUsage() {
		return "/lives send [player] [amount]";
	}
	
	@Override
	public String getDescription() {
		return null;
	}

}
