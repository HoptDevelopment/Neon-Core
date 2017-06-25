package com.roguehcf.neon.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.NeonPlayer;

import org.bukkit.ChatColor;

public class ToggleScoreboardCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return true;
		}
		
		NeonPlayer player = NeonPlayer.getByPlayer((Player)sender);
		
		if(Neon.getInstance().getScoreboardDisabled().contains(player.getPlayer().getUniqueId())) {
			Neon.getInstance().getScoreboardDisabled().remove(player.getPlayer().getUniqueId());
			player.sendMessage(LocaleConfiguration.SCOREBOARD_TOGGLED.replaceAll("\\{0\\}", ChatColor.GREEN + "enabled"));
			return true;
		}
		
		Neon.getInstance().getScoreboardDisabled().add(player.getPlayer().getUniqueId());
		player.sendMessage(LocaleConfiguration.SCOREBOARD_TOGGLED.replaceAll("\\{0\\}", ChatColor.RED + "disabled"));
		
		return true;
	}

}
