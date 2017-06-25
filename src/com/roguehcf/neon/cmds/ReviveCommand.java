package com.roguehcf.neon.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.DeathbanConfiguration;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.timer.NeonTimer;
import com.roguehcf.neon.timer.type.TimerType;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class ReviveCommand implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "You must be a player to use the donator revive. To revive players from console use /lives revive.");
			return true;
		}
		
		if(args.length == 0){
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/revive [player]"));
			return true;
		}
		
		Player player = (Player) sender;
		
		if(Neon.getInstance().getTimerManager().hasTimer(player, TimerType.REVIVE)){
			String time = "00:00";
			
			for(NeonTimer timer : Neon.getInstance().getTimerManager().getTimersOf(player)){
				if(timer.getType() == TimerType.REVIVE){
					time = timer.getRemainingFormatted();
				}
			}
			
			player.sendMessage(LocaleConfiguration.STILL_ON_TIMER.replaceAll("\\{0\\}", TimerType.REVIVE.getDisplayName())
					.replaceAll("\\{1\\}", time));
			
			return true;
		}
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
		
		if(!DeathbanConfiguration.isDeathbanned(target.getUniqueId())){
			sender.sendMessage(LocaleConfiguration.NOT_DEATHBANNED);
			return true;
		}
		
		DeathbanConfiguration.removeDeathban(target.getUniqueId());
		Neon.getInstance().getTimerManager().addTimer(player, new NeonTimer(TimerType.REVIVE, TimeUnit.MINUTES.toSeconds(60), player));
		sender.sendMessage(LocaleConfiguration.STAFF_LIVES_REVIVED.replaceAll("\\{0\\}", target.getName()));
		Bukkit.broadcastMessage(LocaleConfiguration.DONATOR_REVIVE_BROADCAST
				.replaceAll("\\{0\\}", player.getName())
				.replaceAll("\\{1\\}", target.getName()));
		
		return true;
	}

}
