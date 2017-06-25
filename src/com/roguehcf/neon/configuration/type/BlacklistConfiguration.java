package com.roguehcf.neon.configuration.type;

import java.util.UUID;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.ConfigurationManager;
import com.roguehcf.neon.configuration.util.ConfigFile;

import lombok.Getter;

public class BlacklistConfiguration {

	@Getter public static ConfigFile config;
	
	public BlacklistConfiguration(ConfigurationManager manager) {
		
		config = new ConfigFile(Neon.getInstance(), "blacklist");
		
	}
	
	public static boolean isBlacklisted(UUID uuid) {
		if(config.get("blacklist." + uuid.toString()) != null) {
			return true;
		}
		return false;
	}
	
	public static void setBlacklisted(UUID uuid, boolean status) {
		config.set("blacklist." + uuid.toString(), status ? status : null);
	}
	
}
