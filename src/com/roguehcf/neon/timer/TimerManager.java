package com.roguehcf.neon.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.timer.NeonTimer;
import com.roguehcf.neon.timer.events.TimerBeginEvent;
import com.roguehcf.neon.timer.events.TimerExpireEvent;
import com.roguehcf.neon.timer.type.TimerType;

import lombok.Getter;

@SuppressWarnings( { "deprecation" } )
public class TimerManager {

	@Getter public HashMap<UUID, ArrayList<NeonTimer>> activeTimers = new HashMap<UUID, ArrayList<NeonTimer>>();
	@Getter public ArrayList<GlobalTimer> activeGlobalTimers = new ArrayList<GlobalTimer>();
	
	@SuppressWarnings("unused")
	private Neon neon;
	
	public TimerManager(Neon neon) {
		this.neon = neon;
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			getActiveTimers().put(player.getUniqueId(), new ArrayList<NeonTimer>());
		}
		
		new BukkitRunnable(){
			public void run(){
				for(Player player : Bukkit.getServer().getOnlinePlayers()){
					
					Iterator<NeonTimer> playerTimers = getTimersOf(player).iterator();
					Iterator<GlobalTimer> globalTimers = getActiveGlobalTimers().iterator();
					
					while(playerTimers.hasNext()) {
						NeonTimer value = playerTimers.next();
						if(value.getRemainingMillis() < 2L){
							playerTimers.remove();
							Bukkit.getServer().getPluginManager().callEvent(new TimerExpireEvent(value, player));
						}
					}
					
					while(globalTimers.hasNext()) {
						GlobalTimer value = globalTimers.next();
						if(value.getRemainingMillis() < 2L){
							globalTimers.remove();
						}
					}
					
				}
			}
		}.runTaskTimer(Neon.getInstance(), 2L, 2L);
		
	}
	
	public ArrayList<NeonTimer> getTimersOf(Player player) {
		return getActiveTimers().get(player.getUniqueId());
	}

	public void removeTimersOfType(Player player, TimerType type) {
		for(NeonTimer timer : getTimersOf(player)) {
			if(timer.getType() == type){
				getTimersOf(player).remove(timer);
			}
		}
	}
	
	public void addTimer(Player player, NeonTimer timer) {
		getTimersOf(player).add(timer);
		Bukkit.getServer().getPluginManager().callEvent(new TimerBeginEvent(timer, player));
	}
	
	public void addGlobalTimer(GlobalTimer timer){
		this.activeGlobalTimers.add(timer);
	}
	
	public GlobalTimer getGlobalTimer(TimerType type){
		for(GlobalTimer timer : activeGlobalTimers){
			if(timer.getType() == type){
				return timer;
			}
		}
		return null;
	}
	
	public void updateTimer(Player player, TimerType type, Long time) {
		for(NeonTimer timer : getTimersOf(player)){
			if(timer.getType() == type){
				timer.setTimeRemaining(time);
			}
		}
	}
	
	public boolean hasTimer(Player player, TimerType type) {
		for(NeonTimer timer : getTimersOf(player)){
			if(timer.getType() == type){
				return timer.getType() == type;
			}
		}
		return false;
	}
}
