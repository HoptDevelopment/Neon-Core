package com.roguehcf.neon.factions.arguments;

import org.bukkit.command.CommandSender;

import com.roguehcf.neon.factions.struct.FactionArgument;
import com.roguehcf.neon.factions.struct.Role;

public class FactionShowArgument implements FactionArgument {

	@Override
	public void execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "show";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Display information about a faction.";
	}

	@Override
	public String getUsage() {
		return "/f show [factionName / playerName]";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "who" };
	}

	@Override
	public boolean mustBeInFaction() {
		return false;
	}

	@Override
	public Role getRequiredRole() {
		return null;
	}

}
