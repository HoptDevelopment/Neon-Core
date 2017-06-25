package com.roguehcf.neon.pvpclass.type;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.roguehcf.neon.pvpclass.ArmorType;
import com.roguehcf.neon.pvpclass.PvPClass;

public class ArcherClass extends PvPClass{

	public ArcherClass() {
		super(ArmorType.LEATHER, "Archer", new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0) });
	}

}
