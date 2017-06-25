package com.roguehcf.neon.configuration;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.DeathbanConfiguration;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.configuration.type.OptionsConfiguration;
import com.roguehcf.neon.configuration.type.PlayerConfiguration;
import com.roguehcf.neon.configuration.type.ScoreboardConfiguration;

import lombok.Getter;

public class ConfigurationManager {

	/**
	 * This class is purely for API purposes only. It is not referenced in any class apart from Neon.java to initialise the config files.
	 */
	
	@Getter public LocaleConfiguration locale;
	@Getter public ScoreboardConfiguration scoreboard;
	@Getter public DeathbanConfiguration deathban;
	@Getter public PlayerConfiguration players;
	@Getter public OptionsConfiguration options;
	
	public ConfigurationManager(Neon neon) {
		this.locale = new LocaleConfiguration(this);
		this.scoreboard = new ScoreboardConfiguration(this);
		this.deathban = new DeathbanConfiguration(this);
		this.players = new PlayerConfiguration(this);
		this.options = new OptionsConfiguration(this);
	}
	
}
