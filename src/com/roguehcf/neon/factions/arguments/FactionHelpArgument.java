package com.roguehcf.neon.factions.arguments;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.factions.FactionExecutor;
import com.roguehcf.neon.factions.struct.FactionArgument;
import com.roguehcf.neon.factions.struct.Role;

public class FactionHelpArgument implements FactionArgument {

	private FactionExecutor factionExectuor;
	
	@SuppressWarnings("static-access")
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return;
		}
		
		FactionExecutor.help(sender);
		
	}

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Shows Faction Help";
	}

	@Override
	public String getUsage() {
		return "f help";
	}

	@Override
	public String[] getAliases() {
		return new String[] { "h" };
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
