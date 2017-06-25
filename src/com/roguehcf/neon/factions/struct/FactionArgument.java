package com.roguehcf.neon.factions.struct;

import org.bukkit.command.CommandSender;

public interface FactionArgument {

	public void execute(CommandSender sender, String[] args);
	public String getName();
	public String getPermission();
	public String getDescription();
	public String getUsage();
	public String[] getAliases();
	public boolean mustBeInFaction();
	public Role getRequiredRole(); /* return null if no faction/role needed */
	
}
