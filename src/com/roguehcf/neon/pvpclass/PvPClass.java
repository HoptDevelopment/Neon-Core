package com.roguehcf.neon.pvpclass;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lombok.Getter;

public class PvPClass implements Listener {

	@Getter private ArmorType type;
	@Getter private String name;
	@Getter private Collection<PotionEffect> passiveEffects;
	
	public PvPClass(ArmorType type, String name, PotionEffect... effects){
		this.name = name;
		this.type = type;
		this.passiveEffects = new HashSet<PotionEffect> (Arrays.asList(effects) );
	}
	
	public boolean isApplicableFor(Player player) {
		for(ItemStack armor : player.getInventory().getArmorContents()){
			if(armor == null || (!armor.getType().name().toLowerCase().contains(this.type.getName().toLowerCase()))){
				return false;
			}
		}
		return true;
	}
	
	public void addPassiveEffects(Player player){
		player.addPotionEffects(passiveEffects);
	}
	
	public void removeEffects(Player player){
		for(PotionEffect effect : player.getActivePotionEffects()){
			PotionEffectType type = effect.getType();
			for(PotionEffect effect2 : passiveEffects){
				PotionEffectType type2 = effect2.getType();
				if(type.equals(type2)){
					player.removePotionEffect(type);
				}
			}
		}
	}
	
	public PotionEffect getClickableEffect(Material item) {
		switch(item) {
		
		case SUGAR: {
			return new PotionEffect(PotionEffectType.SPEED, 120, 1);
		}
		
		case WHEAT: {
			return new PotionEffect(PotionEffectType.SATURATION, 240, 1);
		}
		
		case BLAZE_POWDER: {
			return new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, 1);
		}
		
		case SPIDER_EYE: {
			return new PotionEffect(PotionEffectType.WITHER, 120, 1);
		}
		
		case GHAST_TEAR: {
			return new PotionEffect(PotionEffectType.REGENERATION, 120, 2);
		}
		
		case IRON_INGOT: {
			
		}
		
		case FEATHER: {
			
		}
		
		default:{
			return null;
		}
		}
	}
	
}
