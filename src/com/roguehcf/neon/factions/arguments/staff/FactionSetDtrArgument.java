package com.roguehcf.neon.factions.arguments.staff;

import org.bukkit.command.CommandSender;

import com.roguehcf.neon.factions.struct.FactionArgument;
import com.roguehcf.neon.factions.struct.Role;

public class FactionSetDtrArgument implements FactionArgument {

	@Override
	public void execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "setdtr";
	}

	@Override
	public String getPermission() {
		return "neon.command.faction.argument.setdtr";
	}

	@Override
	public String getDescription() {
		return "Staff command to set the dtr of a faction.";
	}

	@Override
	public String getUsage() {
		return "/f setdtr [factionName] [dtr]";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "dtrset" };
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
