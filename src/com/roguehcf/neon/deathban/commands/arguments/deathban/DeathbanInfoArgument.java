package com.roguehcf.neon.deathban.commands.arguments.deathban;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.roguehcf.neon.configuration.type.DeathbanConfiguration;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.CommandArgument;

import org.bukkit.ChatColor;

public class DeathbanInfoArgument implements CommandArgument {

	/*
	 * 
	 *   - '&7&m---------------------------------------------------'
  - '&9&lDeathban Information &7- &9{0}'
  - '&7&m---------------------------------------------------'
  - ' &9Deathbanned by&7: &r{1}'
  - ' &9Deathbanned on&7: &r{2}'
  - ' &9Deathban expiry&7: &r{3}'
  - ' &9Deathbanned in territory of&7: &r{4}'
  - '&7&m---------------------------------------------------'(non-Javadoc)
	 * @see com.roguehcf.neon.util.CommandArgument#onExecute(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onExecute(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1){
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/deathban info [player]"));
		}else if(args.length > 1){
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[1]);
			
			if(!target.hasPlayedBefore()){
				sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
				return true;
			}
			
			for(String string : LocaleConfiguration.DEATHBAN_INFO){
				sender.sendMessage(LocaleConfiguration.C(string)
						.replaceAll("\\{0\\}", target.getName())
						.replaceAll("\\{1\\}", DeathbanConfiguration.getKiller(target.getUniqueId()) != null ? DeathbanConfiguration.getKiller(target.getUniqueId()) : ChatColor.RED + "Cannot find a deathban for this player.")
						.replaceAll("\\{2\\}", DeathbanConfiguration.getMillisOfBan(target.getUniqueId()) != null ? new Date(DeathbanConfiguration.getMillisOfBan(target.getUniqueId())).toLocaleString() : ChatColor.RED + "Cannot find a deathban for this player.")
						.replaceAll("\\{3\\}", DeathbanConfiguration.getBanExpiryMillis(target.getUniqueId()) != null ? new Date(DeathbanConfiguration.getBanExpiryMillis(target.getUniqueId())).toLocaleString() : ChatColor.RED + "Cannot find a deathban for this player.")
						.replaceAll("\\{4\\}", ChatColor.RED + "Not implemented yet."));
			}
			
		}
		return true;
	}

	@Override
	public String getPermission() {
		return "neon.command.deathban.argument.info";
	}

	@Override
	public String getName() {
		return "info";
	}

	@Override
	public String getHelp() {
		return LocaleConfiguration.COMMAND_HELP_ENTRY.replaceAll("\\{0\\}", "deathban info [player]")
				.replaceAll("\\{1\\}", "Staff command which views information about the specified player's latest deathban.");
	}

	@Override
	public String getUsage() {
		return "/deathban info [player]";
	}

	@Override
	public String getDescription() {
		return null;
	}

}
