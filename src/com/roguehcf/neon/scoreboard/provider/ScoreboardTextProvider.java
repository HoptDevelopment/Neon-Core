package com.roguehcf.neon.scoreboard.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.cmds.StaffChatCommand;
import com.roguehcf.neon.configuration.type.ScoreboardConfiguration;
import com.roguehcf.neon.pvpclass.PvPClass;
import com.roguehcf.neon.scoreboard.ScoreboardProvider;
import com.roguehcf.neon.timer.GlobalTimer;
import com.roguehcf.neon.timer.NeonTimer;
import com.roguehcf.neon.timer.type.TimerType;
import com.roguehcf.neon.util.NeonPlayer;

public class ScoreboardTextProvider implements ScoreboardProvider {

	@Override
	public String getTitle(Player player) {
		return ScoreboardConfiguration.TITLE;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<String> getLines(Player player) {

		List<String> scoreboard = new ArrayList<String>();
		
		NeonPlayer np = NeonPlayer.getByPlayer(player);
		scoreboard.clear();
		
		if(np.isScoreboardEnabled()){
			int i = 0;
			boolean topEntry = true;

			/* Staff Board */
			if(player.hasPermission("neon.staff")) {
				/* If the entry is at the top add line */
				if(topEntry) {
					scoreboard.add(ScoreboardConfiguration.LINE);
					topEntry = false;
				}
				
				/* RogueHCF staff board */
				
				if(player.hasPermission("neon.staff")) {
					for(String line : ScoreboardConfiguration.getStaffLines()){
						scoreboard.add(line
								.replaceAll("%gamemode%", ChatColor.GOLD + StringUtils.capitalise(player.getGameMode().name().toLowerCase()))
								.replaceAll("%vanish%", np.isVanished() ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled")
								.replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().length))
								.replaceAll("%staffchat%", StaffChatCommand.getByPlayer(player) ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled")
								.replaceAll("%chatmode%", StaffChatCommand.getByPlayer(player) ? ChatColor.GREEN + "Staff Chat" : ChatColor.RED + "Global Chat")
								.replaceAll("%visibility%", np.isVanished() ? ChatColor.GREEN + "Vanished" : ChatColor.RED + "Visible"));
					}
				}

				 

				i++;
			}
			
			/* Player Timers */
			if(Neon.getInstance().getTimerManager().getTimersOf(player) != null){
				for(NeonTimer timer : Neon.getInstance().getTimerManager().getTimersOf(player)){
					/* If the entry is at the top add line */
					if(topEntry) {
						scoreboard.add(ScoreboardConfiguration.LINE);
						topEntry = false;
					}
					
					/* Formats the timer and adds to scoreboard */
					
					if(timer.getType().getDisplayName() != null){
						scoreboard.add(ScoreboardConfiguration.FORMAT
								.replaceAll("\\{0\\}", timer.getType().getDisplayName())
								.replaceAll("\\{1\\}", timer.getRemainingFormatted()));
						i++;
					}
					
				}
			}
			
			/* Global Timers */
			if(!Neon.getInstance().getTimerManager().getActiveGlobalTimers().isEmpty()){
				for(GlobalTimer timer : Neon.getInstance().getTimerManager().getActiveGlobalTimers()){
					/* If the entry is at the top add line */
					if(topEntry) {
						scoreboard.add(ScoreboardConfiguration.LINE);
						topEntry = false;
					}
					
					/* Formats the timer and adds to scoreboard */
					
					if(timer.getType().getDisplayName() != null){
						if(timer.getType() == TimerType.SOTW){
							scoreboard.add(ScoreboardConfiguration.SOTW_SB
									.replaceAll("\\{0\\}", timer.getRemainingFormatted()));
						}else{
							scoreboard.add(ScoreboardConfiguration.FORMAT
									.replaceAll("\\{0\\}", timer.getType().getDisplayName())
									.replaceAll("\\{1\\}", timer.getRemainingFormatted()));	
						}
						i++;
					}
				}
			}
			
			/* PvP Class */
			if(Neon.getInstance().getPvpClassListener().getEquipped().get(player.getUniqueId()) != null){
				PvPClass equipped = Neon.getInstance().getPvpClassListener().getEquipped().get(player.getUniqueId());
				scoreboard.add(ScoreboardConfiguration.FORMAT
						.replaceAll("\\{0\\}", ScoreboardConfiguration.EQUIPPED_CLASS)
						.replaceAll("\\{1\\}", equipped.getName()));
			}
			
			/* Bottom Text */
			
			if(ScoreboardConfiguration.BOTTOM_TEXT_ENABLED){
				if(!topEntry){
					for(String s : ScoreboardConfiguration.BOTTOM_TEXT){
						scoreboard.add(s);
					}
				}
			}

			/* Bottom Line */
			if(i >= 1) scoreboard.add(ScoreboardConfiguration.LINE);
		}
		
		return scoreboard;
	}

}
