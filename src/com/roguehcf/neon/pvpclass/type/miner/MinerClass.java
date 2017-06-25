package com.roguehcf.neon.pvpclass.type.miner;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.roguehcf.neon.pvpclass.ArmorType;
import com.roguehcf.neon.pvpclass.PvPClass;

public class MinerClass extends PvPClass{

	public MinerClass() {
		super(ArmorType.IRON, "Miner", new PotionEffect[] { new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0) });
	}
	
}
