package com.roguehcf.neon.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class OresCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0){
			if(!(sender instanceof Player)){
				sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/ores [player]"));
				return true;
			}
			
			Player player = (Player) sender;
			
			for(String s : LocaleConfiguration.ORES){
				player.sendMessage(LocaleConfiguration.C(s)
						.replaceAll("\\{0\\}", player.getName())
						.replaceAll("\\{1\\}", "" + player.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE))
						.replaceAll("\\{2\\}", "" + player.getStatistic(Statistic.MINE_BLOCK, Material.EMERALD_ORE))
						.replaceAll("\\{3\\}", "" + player.getStatistic(Statistic.MINE_BLOCK, Material.REDSTONE_ORE))
						.replaceAll("\\{4\\}", "" + player.getStatistic(Statistic.MINE_BLOCK, Material.IRON_ORE))
						.replaceAll("\\{5\\}", "" + player.getStatistic(Statistic.MINE_BLOCK, Material.COAL_ORE))
						.replaceAll("\\{6\\}", "" + player.getStatistic(Statistic.MINE_BLOCK, Material.GOLD_ORE))
						.replaceAll("\\{7\\}", "" + player.getStatistic(Statistic.MINE_BLOCK, Material.LAPIS_ORE)));
				
			}
			
		}else{
			Player target = Bukkit.getServer().getPlayer(args[0]);
			
			if(target != null){
				
				for(String s : LocaleConfiguration.ORES){
					sender.sendMessage(LocaleConfiguration.C(s)
							.replaceAll("\\{0\\}", target.getName())
							.replaceAll("\\{1\\}", "" + target.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE))
							.replaceAll("\\{2\\}", "" + target.getStatistic(Statistic.MINE_BLOCK, Material.EMERALD_ORE))
							.replaceAll("\\{3\\}", "" + target.getStatistic(Statistic.MINE_BLOCK, Material.REDSTONE_ORE))
							.replaceAll("\\{4\\}", "" + target.getStatistic(Statistic.MINE_BLOCK, Material.IRON_ORE))
							.replaceAll("\\{5\\}", "" + target.getStatistic(Statistic.MINE_BLOCK, Material.COAL_ORE))
							.replaceAll("\\{6\\}", "" + target.getStatistic(Statistic.MINE_BLOCK, Material.GOLD_ORE))
							.replaceAll("\\{7\\}", "" + target.getStatistic(Statistic.MINE_BLOCK, Material.LAPIS_ORE)));
					
				}
				
				return true;
			}
			
			sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
		}
		
		
		return true;
	}

}
