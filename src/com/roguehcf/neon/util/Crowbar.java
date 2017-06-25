package com.roguehcf.neon.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;

public class Crowbar {

	/**
	 * Util I made in 5 minutes, might work might not.
	 */
	
	private List<String> lore;
	@Getter private ItemStack item;
	
	public static final String NAME = ChatColor.GOLD + "Crowbar";
	
	public Crowbar() {
		lore = new ArrayList<String>();
		
		lore.add(ChatColor.GOLD + "Spawner Uses:");
		lore.add("1");
		lore.add(ChatColor.AQUA + "End Portal Uses:");
		lore.add("6");
		
		item = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(NAME);
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	
	public Crowbar(ItemStack item) {
		lore = item.getItemMeta().getLore();
		this.item = item;
	}
	
	public int getSpawnerUses() {
		return Integer.parseInt(lore.get(1));
	}
	
	public int getEndFrameUses() {
		return Integer.parseInt(lore.get(3));
	}
	
	public void setSpawnerUses(int uses) {
		item.getItemMeta().getLore().set(1, "" + uses);
	}
	
	public void setEndFrameUses(int uses) {
		item.getItemMeta().getLore().set(3, "" + uses);
	}
	
}
