package com.roguehcf.neon.deathban.commands.arguments.lives;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.configuration.type.PlayerConfiguration;
import com.roguehcf.neon.util.CommandArgument;

public class LivesCheckArgument implements CommandArgument{

	@SuppressWarnings({ "deprecation" })
	@Override
	public boolean onExecute(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1){
			if(sender instanceof Player){
				Player player = (Player) sender;
				sender.sendMessage(LocaleConfiguration.LIVES_CHECK.replaceAll("\\{0\\}", player.getName()).replaceAll("\\{1\\}", "" + PlayerConfiguration.getLives(player.getUniqueId())));
			}else{
				sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/lives check [player]"));
			}
		}else if(args.length > 1){
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[1]);
			
			if(!target.hasPlayedBefore()){
				sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
				return true;
			}
			
			sender.sendMessage(LocaleConfiguration.LIVES_CHECK.replaceAll("\\{0\\}", target.getName()).replaceAll("\\{1\\}", "" + PlayerConfiguration.getLives(target.getUniqueId())));
			
		}
		return true;
	}

	@Override
	public String getPermission() {
		return "neon.command.lives.argument.check";
	}

	@Override
	public String getName() {
		return "check";
	}

	@Override
	public String getHelp() {
		return LocaleConfiguration.COMMAND_HELP_ENTRY.replaceAll("\\{0\\}", "lives check [player]")
				.replaceAll("\\{1\\}", "Checks the amount of lives the specified player owns.");
	}
	
	@Override
	public String getUsage() {
		return "/lives check [player]";
	}

	@Override
	public String getDescription() {
		return null;
	}
}
