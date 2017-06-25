package com.roguehcf.neon.factions.type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;

import com.roguehcf.neon.factions.claim.Claim;
import com.roguehcf.neon.factions.struct.Role;

import lombok.Getter;
import lombok.Setter;

public class PlayerFaction extends Faction {

	private double DTR_PER_MEMBER = 1.01;

	@Getter @Setter private String name;
	@Getter @Setter private Location home;
	@Getter private HashSet<Claim> claims;
	@Getter @Setter private UUID leader;
	@Getter @Setter private String announcement;
	@Getter private ArrayList<UUID> coleaders;
	@Getter private ArrayList<UUID> officers;
	@Getter private ArrayList<UUID> members;
	@Getter private ArrayList<PlayerFaction> allies;
	@Getter private ArrayList<UUID> invites;
	@Getter @Setter private int balance;
	@Getter @Setter private BigDecimal dtr;
	@Getter @Setter private BigDecimal maxdtr;
	@Getter @Setter private int lives;
	@Getter @Setter private boolean autorevive;
	@Getter @Setter private boolean open;

	public PlayerFaction(UUID uuid, String name) {
		super(uuid, name);
		this.leader = uuid;
		this.name = name;
		this.announcement = "No announcement set. Set an announcement by using /f announcement [text]";
		this.balance = 250;
		this.coleaders = new ArrayList<UUID>();
		this.officers = new ArrayList<UUID>();
		this.allies = new ArrayList<PlayerFaction>();
		this.members = new ArrayList<UUID>();
		this.invites = new ArrayList<UUID>();

		this.autorevive = false;
		this.open = false;
	}

	public Role getRole(UUID uuid) {
		if (leader.equals(uuid)) {
			return Role.LEADER;
		} else if (this.coleaders.contains(uuid)) {
			return Role.COLEADER;
		} else if (this.officers.contains(uuid)) {
			return Role.OFFICER;
		} else if (this.members.contains(uuid)) {
			return Role.MEMBER;
		}
		return null;
	}
	
	
	
}
