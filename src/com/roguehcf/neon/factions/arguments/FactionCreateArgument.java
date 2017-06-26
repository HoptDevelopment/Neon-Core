package com.roguehcf.neon.factions.arguments;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.factions.struct.FactionArgument;
import com.roguehcf.neon.factions.struct.Role;
import com.roguehcf.neon.util.NeonPlayer;
import com.roguehcf.neon.util.NeonStrings;

public class FactionCreateArgument implements FactionArgument {

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return;
		}
		
		if(args.length == 1) {
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", getUsage()));
			return;
		}
		
		Player player = (Player) sender;
		NeonPlayer np = NeonPlayer.getByPlayer(player);
		String name = args[1];
		
		if(!NeonStrings.isAlphanumeric(args[1])) {
			sender.sendMessage(LocaleConfiguration.MUST_BE_ALPHANUMERIC);
			return;
		}
		
		if(name.length() > 16) {
			sender.sendMessage(LocaleConfiguration.FACTION_NAME_LENGTH);
			return;
		}
		
		if(Neon.getInstance().getFactionManager().getFactionByMember(np.getFactionMember()) != null) {
			sender.sendMessage("in faction");
			return;
		}
		
		/*TODO : Check if faction with name already exists */
		
		/*create faction*/
		Neon.getInstance().getFactionManager().createFaction(name, np.getFactionMember());
		
		
		
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
