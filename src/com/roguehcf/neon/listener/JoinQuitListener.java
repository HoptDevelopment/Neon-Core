package com.roguehcf.neon.listener;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.timer.NeonTimer;
import com.roguehcf.neon.util.NeonPlayer;

public class JoinQuitListener implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		event.setJoinMessage(null);
		Neon.getInstance().getTimerManager().getActiveTimers().put(event.getPlayer().getUniqueId(), new ArrayList<NeonTimer>());
		for(String string : LocaleConfiguration.JOIN_LINES){
			event.getPlayer().sendMessage(LocaleConfiguration.C(string));
		}
		NeonPlayer np = NeonPlayer.getByPlayer(event.getPlayer());
		if(np.isVanished()) {
			np.setVanished(true); /* to rerun the hide player */
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
	}
}
