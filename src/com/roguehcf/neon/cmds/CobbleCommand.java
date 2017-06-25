package com.roguehcf.neon.cmds;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.NeonPlayer;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class CobbleCommand implements CommandExecutor, Listener{

	@Getter public static ArrayList<UUID> disabled = new ArrayList<UUID>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return true;
		}
		
		NeonPlayer player = NeonPlayer.getByPlayer((Player)sender);
		
		boolean enabled = true;
		
		if(disabled.contains(player.getPlayer().getUniqueId())){
			disabled.remove(player.getPlayer().getUniqueId());
			enabled=false;
		}else{
			disabled.add(player.getPlayer().getUniqueId());
		}
		
		player.sendMessage(LocaleConfiguration.COBBLE_COMMAND.replaceAll("\\{0\\}", enabled ? ChatColor.RED + "no longer" : ChatColor.GREEN + "now"));
		
		return true;
	}
	
	@EventHandler
	public void onCobblePickup(PlayerPickupItemEvent event){
		if(event.getItem().getItemStack().getType() == Material.COBBLESTONE){
			if(disabled.contains(event.getPlayer().getUniqueId())){
				event.setCancelled(true);
			}
		}
	}

}
