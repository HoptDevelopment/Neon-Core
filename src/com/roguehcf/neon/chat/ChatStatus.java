package com.roguehcf.neon.chat;

import net.md_5.bungee.api.ChatColor;

public enum ChatStatus {

	LOCKED(false), SLOWED(true), NORMAL(true);
	
	private boolean canTalk;
	
	ChatStatus(boolean canTalk){
		this.canTalk = canTalk;
	}
	
	public boolean canTalk() {
		return canTalk;
	}
	public String getPrintableName() {
		switch(this) {
		case LOCKED: {
			return ChatColor.RED + "Locked";
		}
		case NORMAL: {
			return ChatColor.GREEN + "Normal";
		}
		case SLOWED: {
			return ChatColor.GRAY + "Slowed";
		}
		default: {
			return "Error";
		}
		}
	}
	
}
