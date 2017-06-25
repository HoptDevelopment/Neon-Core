package com.roguehcf.neon.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.NeonPlayer;

public class PingCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			if(args.length == 0) {
				sender.sendMessage(LocaleConfiguration.PING_MSG
						.replaceAll("\\{0\\}", "0"));
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			
			if(target == null) {
				sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
				return true;
			}
			
			NeonPlayer nTarget = NeonPlayer.getByPlayer(target);
			
			sender.sendMessage(LocaleConfiguration.PING_MSG
					.replaceAll("\\{0\\}", target.getName())
					.replaceAll("\\{1\\}", String.valueOf(nTarget.getPing())));
			
			return true;
		}
		
		NeonPlayer player = NeonPlayer.getByPlayer((Player)sender);
		
		if(args.length == 0) {
			player.sendMessage(LocaleConfiguration.PING_MSG
					.replaceAll("\\{0\\}", String.valueOf(player.getPing())));
			return true;
		}
		
		Player target = Bukkit.getServer().getPlayer(args[0]);
		
		if(target == null) {
			player.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
			return true;
		}
		
		NeonPlayer nTarget = NeonPlayer.getByPlayer(target);
		
		player.sendMessage(LocaleConfiguration.PING_MSG
				.replaceAll("\\{0\\}", target.getName())
				.replaceAll("\\{1\\}", String.valueOf(nTarget.getPing())));
		
		return true;
	}

}
