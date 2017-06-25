package com.roguehcf.neon.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class NightVisionCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(LocaleConfiguration.PLAYER_ONLY);
			return true;
		}
		
		Player player = (Player) sender;
		
		if(!player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 3));
			sender.sendMessage(LocaleConfiguration.NIGHT_VISION);
			return true;
		}
		
		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		sender.sendMessage(LocaleConfiguration.NIGHT_VISION);
		
		return true;
	}

}
