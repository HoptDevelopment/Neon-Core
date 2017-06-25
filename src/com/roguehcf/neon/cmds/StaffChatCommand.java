package com.roguehcf.neon.cmds;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.util.NeonPlayer;

import org.bukkit.ChatColor;

public class StaffChatCommand  implements CommandExecutor{

	private static ArrayList<UUID> inStaffChat = new ArrayList<>();
	
	public static boolean getByPlayer(Player player) {
		return inStaffChat.contains(player.getUniqueId());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return true;
		}
		
		NeonPlayer player = NeonPlayer.getByPlayer((Player)sender);
		
		if(getByPlayer(player.getPlayer())){
			inStaffChat.remove(player.getPlayer().getUniqueId());
		}else{
			inStaffChat.add(player.getPlayer().getUniqueId());
		}
		
		player.sendMessage(LocaleConfiguration.STAFF_CHAT.replaceAll("\\{0\\}", inStaffChat.contains(player.getPlayer().getUniqueId()) ? ChatColor.GREEN + "now" : ChatColor.RED + "no longer"));
		
		return true;
	}

}
