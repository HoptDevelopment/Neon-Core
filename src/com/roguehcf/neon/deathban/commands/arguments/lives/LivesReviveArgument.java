package com.roguehcf.neon.deathban.commands.arguments.lives;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.configuration.type.DeathbanConfiguration;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.CommandArgument;

public class LivesReviveArgument implements CommandArgument{

	@SuppressWarnings({ "deprecation" })
	@Override
	public boolean onExecute(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length == 1){
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/lives revive [player]"));
		}else if(args.length > 1){
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[1]);

			if(!target.hasPlayedBefore()){
				sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
				return true;
			}
			
			if(!DeathbanConfiguration.isDeathbanned(target.getUniqueId())){
				sender.sendMessage(LocaleConfiguration.NOT_DEATHBANNED);
				return true;
			}

			DeathbanConfiguration.removeDeathban(target.getUniqueId());
			sender.sendMessage(LocaleConfiguration.STAFF_LIVES_REVIVED.replaceAll("\\{0\\}", target.getName()));

		}
		return true;
	}

	@Override
	public String getPermission() {
		return "neon.command.lives.argument.revive";
	}

	@Override
	public String getName() {
		return "revive";
	}

	@Override
	public String getHelp() {
		return LocaleConfiguration.COMMAND_HELP_ENTRY.replaceAll("\\{0\\}", "lives revive [player]")
				.replaceAll("\\{1\\}", "Staff command which revives the specified player.");
	}
	
	@Override
	public String getUsage() {
		return "/lives revive [player]";
	}
	
	@Override
	public String getDescription() {
		return null;
	}
}
