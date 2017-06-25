package com.roguehcf.neon.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class CraftCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return true;
		}
		
		Player player = (Player) sender;
		
		player.openWorkbench(player.getLocation(), true);
		player.sendMessage(LocaleConfiguration.CRAFT_WORKBENCH_OPEN);
		
		return true;
	}
	
}
