package com.roguehcf.neon.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandArgument {

	public boolean onExecute(CommandSender sender, Command cmd, String label, String[] args);
	public String getPermission();
	public String getName();
	public String getHelp();
	public String getUsage();
	public String getDescription();
	
}
