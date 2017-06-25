package com.roguehcf.neon.timer.type;

import com.roguehcf.neon.configuration.type.ScoreboardConfiguration;

public enum TimerType {

	ENDERPEARL, COMBAT_TAG, GOPPLE, GAPPLE, STUCK, IMMUNITY, CLASS_WARMUP, LOGOUT, TELEPORT, ARCHER_MARK, REVIVE, SOTW;
	
	public String getDisplayName(){
		switch(this){
			case ENDERPEARL: {
				return ScoreboardConfiguration.ENDERPEARL;
			}
			
			case COMBAT_TAG: {
				return ScoreboardConfiguration.COMBAT_TAG;
			}
			
			case GOPPLE: {
				return ScoreboardConfiguration.GOPPLE;
			}
			
			case GAPPLE: {
				return ScoreboardConfiguration.GAPPLE;
			}
			
			case STUCK: {
				return ScoreboardConfiguration.STUCK;
			}
			
			case IMMUNITY: {
				return ScoreboardConfiguration.IMMUNITY;
			}
			
			case LOGOUT: {
				return ScoreboardConfiguration.LOGOUT;
			}
			
			case TELEPORT: {
				return ScoreboardConfiguration.TELEPORT;
			}
			
			case REVIVE: {
				return ScoreboardConfiguration.REVIVE;
			}
			
			case SOTW: {
				return ScoreboardConfiguration.SOTW;
			}
			
			default:{
				return null;
			}
		}
	}
	
}
