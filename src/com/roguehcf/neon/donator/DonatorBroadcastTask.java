package com.roguehcf.neon.donator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;

public class DonatorBroadcastTask extends BukkitRunnable {
	
	private boolean cancelled;
	
	public DonatorBroadcastTask(){ 
		this.cancelled = false;
	}
	
	/*
	 * enable-donator-broadcast: true
broadcast-every-x-seconds: 120(non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void run(){
		
		if(cancelled) {
			return;
		}
		
		List<String> donatorNames = new ArrayList<String>();
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(player.hasPermission(LocaleConfiguration.DONATOR_BROADCAST_PERMISSION)) {
				donatorNames.add(player.getName());
			}
		}
		
		Bukkit.broadcastMessage(LocaleConfiguration.DONATOR_BROADCAST
				.replaceAll("\\{0\\}", donatorNames.isEmpty() ? "None" : StringUtils.join(donatorNames, ", ")));
	}
	
	public boolean isCancelled(){
		return cancelled;
	}
	
	public void setCancelled(boolean cancelled){
		this.cancelled = cancelled;
	}
	
}
