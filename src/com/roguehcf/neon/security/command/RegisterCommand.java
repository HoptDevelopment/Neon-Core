package com.roguehcf.neon.security.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.security.SecurityProfile;
import com.roguehcf.neon.util.NeonPlayer;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;

public class RegisterCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return true;
		}
		
		Player player = (Player) sender;
		SecurityProfile profile = NeonPlayer.getByPlayer(player).getSecurityProfile();
		
		if(args.length <= 1) {
			sender.sendMessage(LocaleConfiguration.USAGE.replaceAll("\\{0\\}", "/register [password] [confirmPassword]"));
			return true;
		}
		
		String password = args[0];
		String passwordToMatch = args[1];
		
		if(password.equals(passwordToMatch)) {
			profile.setPassword(password);
			profile.setIpAddress(player.getAddress().getAddress().getHostAddress());
			sender.sendMessage(ChatColor.GREEN + "Registered with the password " + StringUtils.repeat('*', password.length()) + " on the IP address " + player.getAddress().getAddress().getHostAddress());
			return true;
		}
		
		sender.sendMessage(ChatColor.RED + "Those passwords do not match.");
		
		return true;
	}

}
