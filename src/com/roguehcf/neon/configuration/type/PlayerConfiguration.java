package com.roguehcf.neon.configuration.type;

import java.util.UUID;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.ConfigurationManager;
import com.roguehcf.neon.configuration.util.ConfigFile;

import lombok.Getter;

public class PlayerConfiguration {

	@Getter public static ConfigFile config;
	
	public PlayerConfiguration(ConfigurationManager configurationManager){
		config = new ConfigFile(Neon.getInstance(), "players");
	}
	
	public static Long getLives(UUID uuid){
		return config.get(uuid.toString() + ".lives") != null ? config.getLong(uuid.toString() + ".lives") : 0L;
	}
	
	public static void setLives(UUID uuid, Long lives){
		config.set(uuid.toString() + ".lives", lives);
		config.save();
	}
	
	public static boolean isBlacklisted(UUID uuid){
		return config.get(uuid.toString() + ".blacklist.status") != null ? config.getBoolean(uuid.toString() + ".blacklist.status") : false;
	}
	
	public static void setBlacklisted(UUID uuid, boolean status){
		config.set(uuid.toString() + ".blacklist.status", status);
		config.save();
	}
	
	public static String getBlacklistReason(UUID uuid){
		return config.get(uuid.toString() + ".blacklist.reason") != null ? config.getString(uuid.toString() + ".blacklist.reason") : "Cannot find a reason on database.";
	}
	
	public static void setBlacklistReason(UUID uuid, String reason){
		config.set(uuid.toString() + ".blacklist.reason", reason);
		config.save();
	}
	
	public static int getKills(UUID uuid){
		return config.get(uuid.toString() + ".kills") != null ? config.getInt(uuid.toString() + ".kills") : 0;
	}
	
	public static void setKills(UUID uuid, int kills){
		config.set(uuid.toString() + ".kills", kills);
		config.save();
	}
	
}
