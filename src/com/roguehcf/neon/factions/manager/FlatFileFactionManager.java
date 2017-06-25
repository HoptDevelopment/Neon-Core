package com.roguehcf.neon.factions.manager;

import java.util.HashMap;
import java.util.UUID;

import com.roguehcf.neon.factions.FactionManager;
import com.roguehcf.neon.factions.profile.FactionMember;
import com.roguehcf.neon.factions.type.Faction;
import com.roguehcf.neon.factions.type.PlayerFaction;

public class FlatFileFactionManager implements FactionManager {

	@Override
	public PlayerFaction getFactionByMember(FactionMember member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Faction getFactionByUUID(UUID uuid) {
		// TODO get faction based on factions uuid
		return null;
	}

	@Override
	public void removeFaction(Faction faction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveFactions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearFaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<UUID, Faction> getLoadedFactions() {
		// TODO Auto-generated method stub
		return null;
	}

	// Dont work on this.
	@Override
	public void createFaction(String factionName, FactionMember leader) {
		UUID facUUID = UUID.randomUUID();
		PlayerFaction faction = new PlayerFaction(facUUID, factionName, leader);
	}

	@Override
	public void loadFactions() {
		// TODO Auto-generated method stub
		
	}

}
