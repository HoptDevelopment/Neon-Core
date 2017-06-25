package com.roguehcf.neon.deathban;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;

import com.roguehcf.neon.configuration.type.DeathbanConfiguration;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.DurationFormatter;

import lombok.Getter;

public class DeathbanListener implements Listener{

	@Getter public static HashMap<UUID, ItemStack[]> inventoryContents = new HashMap<>();
	@Getter public static HashMap<UUID, ItemStack[]> armorContents = new HashMap<>();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		Player player = event.getEntity();
		
		inventoryContents.put(player.getUniqueId(), player.getInventory().getContents());
		armorContents.put(player.getUniqueId(), player.getInventory().getArmorContents());
		if(DeathbanConfiguration.shouldDeathBan(player.getUniqueId())){
			DeathbanConfiguration.addDeathban(player.getUniqueId(), DeathbanConfiguration.getBanDuration(player.getUniqueId()), player.getKiller() != null ? player.getKiller().getName() : "Environment");
			player.kickPlayer(LocaleConfiguration.DEATHBAN_KICK.replaceAll("\\{0\\}", DurationFormatter.getFormatted(TimeUnit.MINUTES.toMillis(DeathbanConfiguration.getBanDuration(player.getUniqueId())), true)));
		}
		
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event){
		if(DeathbanConfiguration.isDeathbanned(event.getPlayer().getUniqueId())){
			event.disallow(Result.KICK_OTHER, LocaleConfiguration.DEATHBAN_KICK.replaceAll("\\{0\\}", DurationFormatter.getFormatted(DeathbanConfiguration.getRemaining(event.getPlayer().getUniqueId()), true)));
		}
	}
}
