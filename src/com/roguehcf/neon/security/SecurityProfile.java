package com.roguehcf.neon.security;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.util.ConfigFile;

import lombok.Getter;
import lombok.Setter;

public class SecurityProfile {

	private UUID uuid;
	private ConfigFile configFile;
	@Getter private boolean isLoggedIn;
	@Getter @Setter private String ipAddress;
	
	public SecurityProfile(UUID uuid) {
		this.uuid = uuid;
		this.configFile = new ConfigFile(Neon.getInstance(), "security.yml");
		this.isLoggedIn = !hasPassword();
	}
	
	public boolean hasPassword() {
		return configFile.getString(uuid.toString() + ".password") != null;
	}
	
	public boolean isCorrectPassword(String password) {
		
		if(!hasPassword()) {
			System.out.println("No password found. Returning true.");
			return true;
		}
		
		try {
			return SecurePasswordFactory.decrypt(password, "neon").equals(configFile.getString(uuid.toString() + ".password"));
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public boolean setPassword(String password) {
		String encrypted = "";
		
		try{
			encrypted = SecurePasswordFactory.encrypt(password, "neon");
		}catch(Exception e) {
			return false;
		}
		
		configFile.set(uuid.toString() + ".password", encrypted);
		configFile.save();
		
		return true;
	}
	
	public boolean isOnline() {
		return Bukkit.getServer().getPlayer(uuid) != null;
	}
	
	public boolean requiresLogin() {
		if(getPlayer() != null) {
			return !getPlayer().hasPermission("neon.staff.bypasslogin");
		}
		return false;
	}
	
	public Player getPlayer() {
		if(isOnline()) {
			return Bukkit.getServer().getPlayer(uuid);
		}else{
			return null;
		}
	}
	
}
