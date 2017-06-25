package com.roguehcf.neon.factions.arguments;

import org.bukkit.command.CommandSender;

import com.roguehcf.neon.factions.struct.FactionArgument;
import com.roguehcf.neon.factions.struct.Role;

public class FactionCreateArgument implements FactionArgument {

	@Override
	public void execute(CommandSender sender, String[] args) {
		
	}

	@Override
	public String getName() {
		return "create";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Create a faction";
	}

	@Override
	public String getUsage() {
		return "f create [factionName]";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "make" };
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
