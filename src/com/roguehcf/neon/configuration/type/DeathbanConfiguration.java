package com.roguehcf.neon.configuration.type;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.ConfigurationManager;
import com.roguehcf.neon.configuration.util.ConfigFile;

import lombok.Getter;

public class DeathbanConfiguration {

	@Getter public static ConfigFile config;
	
	public DeathbanConfiguration(ConfigurationManager configurationManager){
		config = new ConfigFile(Neon.getInstance(), "deathbans");
	}
	
	public static void addDeathban(UUID uuid, Long timeInMinutes, String killer){
		config.set("deathbans." + uuid.toString() + ".expire", System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(timeInMinutes));
		setKiller(uuid, killer);
		setMillisOfBan(uuid, System.currentTimeMillis());
		config.save();
	}
	
	public static void removeDeathban(UUID uuid){
		config.set("deathbans." + uuid.toString() + ".expire", System.currentTimeMillis());
		config.save();
	}
	
	public static boolean isDeathbanned(UUID uuid){
		return config.get("deathbans." + uuid.toString() + ".expire") != null ? config.getLong("deathbans." + uuid.toString() + ".expire") - System.currentTimeMillis() > 0 : false;
	}
	
	public static Long getRemaining(UUID uuid){
		
		if(config.get("deathbans." + uuid.toString() + ".expire") == null){
			return 0L;
		}
		
		return config.getLong("deathbans." + uuid.toString() + ".expire") - System.currentTimeMillis();
	}
	
	public static Long getBanExpiryMillis(UUID uuid){
		return config.get("deathbans." + uuid.toString() + ".expire") != null ? config.getLong("deathbans." + uuid.toString() + ".expire") : null;
	}
	
	public static Long getBanDuration(UUID uuid){//returns in minutes
		
		if(Bukkit.getServer().getPlayer(uuid).hasPermission("neon.staff")){
			return 0L;
		}
		
		return 120L;
	}
	
	public static boolean shouldDeathBan(UUID uuid){
		if(Bukkit.getServer().getPlayer(uuid).hasPermission("neon.staff")){
			return false;
		}//TODO: add kitmap
		return true;
	}
	
	public static String getKiller(UUID uuid){
		return config.get("deathbans." + uuid.toString() + ".killer") != null ? config.getString("deathbans." + uuid.toString() + ".killer") : "Player not deathbanned.";
	}
	
	public static void setKiller(UUID uuid, String killer){
		config.set("deathbans." + uuid.toString() + ".killer", killer);
		config.save();
	}
	
	public static Long getMillisOfBan(UUID uuid){
		return config.get("deathbans." + uuid.toString() + ".started") != null ? config.getLong("deathbans." + uuid.toString() + ".started") : null;
	}
	
	public static void setMillisOfBan(UUID uuid, Long millis){
		config.set("deathbans." + uuid.toString() + ".started", millis);
		config.save();
	}
	
}
