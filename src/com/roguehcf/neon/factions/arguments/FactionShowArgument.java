package com.roguehcf.neon.factions.arguments;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.factions.profile.FactionMember;
import com.roguehcf.neon.factions.struct.FactionArgument;
import com.roguehcf.neon.factions.struct.Role;
import com.roguehcf.neon.factions.type.PlayerFaction;
import com.roguehcf.neon.util.NeonPlayer;

public class FactionShowArgument implements FactionArgument {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length == 0) {
			
			if(!(sender instanceof Player)) {
				sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			}
			
			Player player = (Player) sender;
			FactionMember member = NeonPlayer.getByPlayer(player).getFactionMember();
			PlayerFaction faction = Neon.getInstance().getFactionManager().getFactionByMember(member);
			
			if(faction != null) {
				faction.printDetails(sender);
			}else {
				sender.sendMessage("no faction");
			}
		}
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
