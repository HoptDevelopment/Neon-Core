package com.roguehcf.neon.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.roguehcf.neon.util.Crowbar;
import com.roguehcf.neon.util.ItemBuilder;

public class CrowbarListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = event.getClickedBlock();
			
			if(block.getType() == Material.MOB_SPAWNER || block.getType() == Material.ENDER_PORTAL_FRAME) {
				Player player = event.getPlayer();
				if(player.getItemInHand() != null) {
					if(player.getItemInHand().hasItemMeta()) {
						if(player.getItemInHand().getItemMeta().hasDisplayName() && player.getItemInHand().getItemMeta().hasLore()) {
							if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Crowbar.NAME)) {
								Crowbar crowbar = new Crowbar(player.getItemInHand());
								if(block.getType() == Material.MOB_SPAWNER) {
									if(crowbar.getSpawnerUses() > 0) {
										crowbar.setSpawnerUses(crowbar.getSpawnerUses() - 1);
										block.breakNaturally();
										CreatureSpawner spawner = (CreatureSpawner) block.getState();
										block.getWorld().dropItemNaturally(block.getLocation(), new ItemBuilder(Material.MOB_SPAWNER).data((short)spawner.getData().getData()).loreLine(ChatColor.YELLOW + "Spawner: " + ChatColor.RESET + spawner.getSpawnedType().getName()).build());
									}
								}
							}
 						}
					}
				}
			}
		}
	}
}
