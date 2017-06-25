package com.roguehcf.neon.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.roguehcf.neon.Neon;

import net.md_5.bungee.api.ChatColor;

public class ServerUtils {

	@SuppressWarnings("deprecation")
	public static ArrayList<String> getOnlineStaff(){
		ArrayList<String> list = new ArrayList<String>();
		for(Player player : Bukkit.getOnlinePlayers()){
			if(player.hasPermission("neon.staff")){
				list.add(player.getName());
			}
		}
		return list;
	}
	
	public static String getPluginListMsg(Neon neon){
		PluginManager manager = neon.getServer().getPluginManager();
		String plugins = "";
		for(Plugin plugin : manager.getPlugins()){
			plugins = plugins + (plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED) + plugin.getName() + ChatColor.RESET + ", ";
		}
		return plugins;
	}
	
	public static final long TICKS_PER_SECOND = 20;
	public static final long TICKS_PER_MINUTE = TICKS_PER_SECOND*60;
	
}
