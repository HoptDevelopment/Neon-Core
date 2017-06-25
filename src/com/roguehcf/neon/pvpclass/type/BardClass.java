package com.roguehcf.neon.pvpclass.type;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.roguehcf.neon.pvpclass.ArmorType;
import com.roguehcf.neon.pvpclass.PvPClass;

public class BardClass extends PvPClass{

	public BardClass() {
		super(ArmorType.GOLD, "Bard", new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0) });
	}

}
