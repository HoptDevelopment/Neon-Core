package com.roguehcf.neon.timer.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.timer.events.GlobalTimerExpireEvent;
import com.roguehcf.neon.timer.events.TimerBeginEvent;
import com.roguehcf.neon.timer.events.TimerExpireEvent;
import com.roguehcf.neon.timer.type.TimerType;

public class TimerListener implements Listener{
	
	@EventHandler
	public void onBegin(TimerBeginEvent event){
		event.getNeonPlayer().sendMessage(LocaleConfiguration.TIMER_STARTED.replaceAll("\\{0\\}", event.getTimer().getType().getDisplayName()));
	}
	
	@EventHandler
	public void onExpire(TimerExpireEvent event) {
		event.getNeonPlayer().sendMessage(LocaleConfiguration.TIMER_EXPIRED.replaceAll("\\{0\\}", event.getTimer().getType().getDisplayName()));
	}
	
	@EventHandler
	public void onGlobalExpire(GlobalTimerExpireEvent event){
		if(event.getTimer().getType() == TimerType.SOTW){
			Bukkit.broadcastMessage(LocaleConfiguration.SOTW_ENDED);
		}
	}
	
}
