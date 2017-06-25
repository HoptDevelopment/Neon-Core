package com.roguehcf.neon.factions.struct;

public enum Role {

	MEMBER, OFFICER, COLEADER, LEADER;
	
	public String getAstrix() {
		switch(this) {
		case MEMBER: return "";
		case OFFICER: return "*";
		case COLEADER: return "**";
		case LEADER: return "**";
		default: return null;
		}
	}
	
}
