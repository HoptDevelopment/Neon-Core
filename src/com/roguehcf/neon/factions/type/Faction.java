package com.roguehcf.neon.factions.type;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;

import com.roguehcf.neon.factions.claim.Claim;

import lombok.Getter;
import lombok.Setter;

public abstract class Faction {
	
	@Getter @Setter private String name;
	@Getter @Setter private Location home;
	@Getter private HashSet<Claim> claims;
	@Getter private UUID uuid;
	
	public Faction(UUID uuid, String name) {
		this.name = name;
		this.home = null;
		this.claims = new HashSet<>();
		this.uuid = uuid;
	}
}
