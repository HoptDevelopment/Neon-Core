package com.roguehcf.neon.event.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.roguehcf.neon.event.PlayerAttackEvent;

public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event){
		if(event.getEntityType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.PLAYER){
			Player victim = (Player) event.getEntity();
			Player attacker = (Player)event.getDamager();
			Bukkit.getServer().getPluginManager().callEvent(new PlayerAttackEvent(victim, attacker));
		}
	}
}
