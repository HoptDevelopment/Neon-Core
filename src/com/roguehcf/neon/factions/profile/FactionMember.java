package com.roguehcf.neon.factions.profile;

import java.util.UUID;

import lombok.Getter;

public class FactionMember {

	@Getter private UUID uniqueId;
	
	public FactionMember(UUID uuid) {
		this.uniqueId = uuid;		
	}
	
}
