package com.roguehcf.neon.configuration.type;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.ConfigurationManager;
import com.roguehcf.neon.configuration.util.ConfigFile;

import lombok.Getter;

public class OptionsConfiguration {

	@Getter public static ConfigFile config;
	
	public OptionsConfiguration(ConfigurationManager configurationManager){
		config = new ConfigFile(Neon.getInstance(), "options");
		
		SECURITY_ENABLED = config.getBoolean("enable-security");
		DONATOR_BROADCAST = config.getBoolean("enable-donator-broadcast");
		DONATOR_BROADCAST_DELAY = config.getInt("broadcast-every-x-seconds");
	}
	
	public static boolean SECURITY_ENABLED = false;
	public static boolean DONATOR_BROADCAST = true;
	public static int DONATOR_BROADCAST_DELAY = 120;
	
}	/*
 * enable-donator-broadcast: true
broadcast-every-x-seconds: 120(non-Javadoc)
 * @see java.lang.Runnable#run()
 */
