package com.roguehcf.neon.cmds;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class SmeltCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return true;
		}
		
		Player player = (Player) sender;
		
		for(ItemStack i : player.getInventory()) {
			
			if(i != null) {
				if(i.getType() == Material.IRON_ORE) {
					i.setType(Material.IRON_INGOT);
				}
				
				if(i.getType() == Material.GOLD_ORE) {
					i.setType(Material.GOLD_INGOT);
				}
			}
			
		}
		
		sender.sendMessage(LocaleConfiguration.SMELTED_ORES);
		
		return true;
	}
}
