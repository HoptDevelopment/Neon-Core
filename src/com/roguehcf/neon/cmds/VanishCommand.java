package com.roguehcf.neon.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.NeonPlayer;

public class VanishCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return true;
		}
		
		Player player = (Player) sender;
		NeonPlayer np = NeonPlayer.getByPlayer(player);
		
		np.setVanished(!np.isVanished());
		player.sendMessage(LocaleConfiguration.VANISH.replaceAll("\\{0\\}", np.isVanished() ? (ChatColor.GREEN + "enabled") : (ChatColor.RED + "disabled")));
		
		return true;
	}

}
