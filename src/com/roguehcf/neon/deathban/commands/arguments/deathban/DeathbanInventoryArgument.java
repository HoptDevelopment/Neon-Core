package com.roguehcf.neon.deathban.commands.arguments.deathban;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.deathban.DeathbanListener;
import com.roguehcf.neon.util.CommandArgument;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class DeathbanInventoryArgument implements CommandArgument {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onExecute(CommandSender sender, Command cmd, String label, String[] args) {
		/* restore_inventory_restored: '&eYou have restored &6{0}&e''s inventory.'
restore_inventory_player_msg: '&6{0} &ehas restored your inventory.' */
		if(args.length <= 2){
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/deathban inventory [restore/view] [player]"));
			return true;
		}
		
		String type = args[1];
		
		switch(type){
		
			case "restore": {
				
				Player target = Bukkit.getServer().getPlayer(args[2]);
				
				if(target == null){
					sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
					return true;
				}
				
				boolean refunded = false;
				
				if(DeathbanListener.getArmorContents().containsKey(target.getUniqueId())) {
					refunded = true;
					target.getInventory().setArmorContents(DeathbanListener.getArmorContents().get(target.getUniqueId()));
				}
				
				if(DeathbanListener.getInventoryContents().containsKey(target.getUniqueId())){
					refunded = true;
					target.getInventory().setContents(DeathbanListener.getInventoryContents().get(target.getUniqueId()));
				}
				
				if(!refunded){
					sender.sendMessage(ChatColor.RED + "The target player has no saved inventory contents.");
					return true;
				}
				
				DeathbanListener.getInventoryContents().remove(target.getUniqueId());
				DeathbanListener.getArmorContents().remove(target.getUniqueId());
				
				sender.sendMessage(LocaleConfiguration.RESTORE_INVENTORY_RESTORED
						.replaceAll("\\{0\\}", target.getName()));
				target.sendMessage(LocaleConfiguration.RESTORE_INVENTORY_PLAYER_MSG
						.replaceAll("\\{0\\}", sender.getName()));
				
				return true;
			}
		
			case "view": {
				
				if(!(sender instanceof Player)){
					sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
					return true;
				}
				
				Player player = (Player) sender;
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[2]);
				
				if(target == null){
					sender.sendMessage(LocaleConfiguration.PLAYER_OFFLINE);
					return true;
				}
				
				Inventory inventory = Bukkit.createInventory(null, 45, ChatColor.GREEN.toString() + "Death inventory of " + target.getName());
				
				if(DeathbanListener.getArmorContents().containsKey(target.getUniqueId())){
					for(ItemStack i : DeathbanListener.getArmorContents().get(target.getUniqueId())){
						if(i != null){
							inventory.addItem(i);
						}
					}
				}
				
				if(DeathbanListener.getInventoryContents().containsKey(target.getUniqueId())){
					for(ItemStack i : DeathbanListener.getInventoryContents().get(target.getUniqueId())){
						if(i != null){
							inventory.addItem(i);
						}
					}
				}
				
				if(inventory.getContents().length == 0){
					sender.sendMessage(ChatColor.RED + "That player has an empty inventory stored, cannot display.");
					return true;
				}
				
				player.openInventory(inventory);
				
				return true;
			}
		
		}
		
		sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/deathban inventory [restore/view] [player]"));
		
		return true;
	}

	@Override
	public String getPermission() {
		return "neon.command.deathban.argument.inventory";
	}

	@Override
	public String getName() {
		return "inventory";
	}

	@Override
	public String getHelp() {
		return LocaleConfiguration.COMMAND_HELP_ENTRY.replaceAll("\\{0\\}", "deathban inventory [restore/view] [player]")
				.replaceAll("\\{1\\}", "Staff command which restores a players inventory.");
	}
	
	@Override
	public String getUsage() {
		return "/deathban inventory [restore/view] [player]";
	}
	
	@Override
	public String getDescription() {
		return null;
	}

}
