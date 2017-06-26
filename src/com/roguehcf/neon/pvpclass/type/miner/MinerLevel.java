package com.roguehcf.neon.pvpclass.type.miner;

import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lombok.Getter;

public enum MinerLevel {

	DEFAULT(0, "None", new PotionEffect[0]),
	BASIC(250, "Basic Miner", new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0) }),
	REGULAR(500, "Regular Miner", new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0), new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 2), new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0) }),
	ADVANCED(1000, "Advanced Miner", new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0), new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 2), new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0) }),
	EXPERT(1500, "Expert Miner", new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0), new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 3), new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 1) }),  
	ADEPT(2000, "Adept Miner", new PotionEffect[] { new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1), new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 3), new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 2) });

	@Getter private int diamondsNeeded;
	@Getter private String stringValue;
	@Getter private List<PotionEffect> give;
	@Getter private MinerLevel nextLevel;

	private MinerLevel(int diamondsNeeded, String string, PotionEffect... give) {
		this.stringValue = string;
		this.give = Arrays.asList(give);
		this.diamondsNeeded = diamondsNeeded;
		this.nextLevel = ordinal() + 1 < values().length ? values()[(ordinal() + 1)] : null;
	}

	public String toString() {
		return this.stringValue;
	}

	public List<PotionEffect> getEffects() {
		return this.give;
	}

	public int getAmount() {
		return this.diamondsNeeded;
	}
	
	public boolean isApplicableFor(int dmined) {
		return this.getAmount() <= dmined && this.getNextLevel().getAmount() > dmined;
	}
	
}
