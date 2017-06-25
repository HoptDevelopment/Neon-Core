package com.roguehcf.neon.pvpclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PotionEffectExpireEvent;
import org.bukkit.event.inventory.EquipmentSetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.potion.PotionEffect;

import com.roguehcf.neon.pvpclass.type.ArcherClass;
import com.roguehcf.neon.pvpclass.type.BardClass;
import com.roguehcf.neon.pvpclass.type.miner.MinerClass;
import com.roguehcf.neon.pvpclass.type.miner.MinerLevelUpgradeEvent;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

public class PvPClassListener implements Listener {

	@Getter private ArrayList<PvPClass> pvpClasses = new ArrayList<PvPClass>();
	@Getter private HashMap<UUID, PvPClass> equipped = new HashMap<UUID, PvPClass>();
	
	public PvPClassListener(){
		pvpClasses.add(new ArcherClass());
		pvpClasses.add(new BardClass());
		pvpClasses.add(new MinerClass());
	}
	
	@EventHandler
	public void equip(EquipmentSetEvent event){
		HumanEntity he = event.getHumanEntity();
		
		if(he instanceof Player){
			Player player = (Player) he;
			for(PvPClass pvpclass : pvpClasses){
				if(pvpclass.isApplicableFor(player)){
					pvpclass.addPassiveEffects(player);
					equipped.put(player.getUniqueId(), pvpclass);
					player.sendMessage(ChatColor.GREEN + "You equipped " + pvpclass.getName() + " class.");
				}
				
				if(equipped.containsKey(player.getUniqueId()) && equipped.get(player.getUniqueId()).equals(pvpclass) && !pvpclass.isApplicableFor(player)){
					pvpclass.removeEffects(player);
					player.sendMessage(ChatColor.RED + "You unequipped " + pvpclass.getName() + " class.");
					equipped.remove(player.getUniqueId());
				}
			}
		}
	}
	
	private HashMap<UUID, ArrayList<PotionEffect>> toRestore = new HashMap<UUID, ArrayList<PotionEffect>>();
	
	/* BARD: click item give effects to nearby faction members ARCHER: Speed 4 */
	@EventHandler
	public void onBardItemClick(PlayerInteractEvent event){
		
	}
	
	/* BARD: held item give effects to nearby faction members */
	@EventHandler
	public void onBardItemHeld(PlayerItemHeldEvent event){
		
	}
	
	/* Definitely not the most efficient way to do this but it works I guess ._. */
	@EventHandler
	public void restore(PotionEffectExpireEvent event){
		if(event.getEntity() instanceof Player) {/* check if player */
			Player restore = (Player) event.getEntity();
			if(toRestore.containsKey(restore.getUniqueId())){ /* check if it has a pending restore */
				
				for(PotionEffect effect : toRestore.get(restore.getUniqueId())){ /* iterates through effects */
					if(effect.getType() == event.getEffect().getType()){ /* if it finds matching types add the restore effect */
						restore.addPotionEffect(effect);
						toRestore.get(restore.getUniqueId()).remove(effect); /* remove so it doesn't infinitely restore your effects for no reason */
					}
				}
				
			} 
		}
	}
	
	@EventHandler
	public void onMine(BlockBreakEvent event) {
		
	}
	
	@EventHandler
	public void onMinerLevelUpgrade(MinerLevelUpgradeEvent event) {
		Bukkit.broadcastMessage("");
	}
	
}
