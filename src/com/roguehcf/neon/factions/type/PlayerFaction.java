package com.roguehcf.neon.factions.type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;

import com.roguehcf.neon.factions.claim.Claim;
import com.roguehcf.neon.factions.profile.FactionMember;

import lombok.Getter;
import lombok.Setter;

public class PlayerFaction extends Faction {

	@Getter @Setter private String name;
	@Getter @Setter private Location home;
	@Getter private HashSet<Claim> claims;
	@Getter @Setter private BigDecimal dtr;
	@Getter private BigDecimal maxdtr;
	@Getter @Setter private String announcement;
	@Getter @Setter private int balance;
	@Getter @Setter private int lives;
	@Getter @Setter private boolean autorevive;
	@Getter @Setter private boolean open;
	@Getter private ArrayList<PlayerFaction> allies;
	@Getter @Setter private FactionMember leader;
	@Getter private HashSet<FactionMember> invited;
	@Getter private HashSet<FactionMember> members;
	
	private double DTR_PER_MEMBER = 1.01;

	public PlayerFaction(UUID uuid, String name, FactionMember leader) {
		super(uuid, name);
		this.leader = leader;
		this.name = name;
		this.announcement = "No announcement set. Set an announcement by using /f announcement [text]";
		this.balance = 0;
		this.allies = new ArrayList<PlayerFaction>();
		this.autorevive = false;
		this.open = false;
		this.invited = new HashSet<>();
		this.dtr = BigDecimal.valueOf(DTR_PER_MEMBER * members.size());
	}
	
	public void loadFaction() {
		
	}
}
