package com.roguehcf.neon.factions;

import java.util.HashMap;
import java.util.UUID;

import com.roguehcf.neon.factions.profile.FactionMember;
import com.roguehcf.neon.factions.type.Faction;

public interface FactionManager {

	public Faction getFactionByMember(FactionMember member);
	public Faction getFactionByUUID(UUID uuid);
	public void createFaction(String factionName, FactionMember leader);
	public void removeFaction(Faction faction);
	public void saveFactions();
	public void clearFaction();
	public HashMap<UUID, Faction> getLoadedFactions();
	
}
