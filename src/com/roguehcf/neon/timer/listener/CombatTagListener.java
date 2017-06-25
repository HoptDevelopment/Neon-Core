package com.roguehcf.neon.timer.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.event.PlayerAttackEvent;
import com.roguehcf.neon.timer.NeonTimer;
import com.roguehcf.neon.timer.TimerManager;
import com.roguehcf.neon.timer.type.TimerType;

public class CombatTagListener implements Listener{

	@EventHandler
	public void onCombatTag(PlayerAttackEvent event) {
		TimerManager manager = Neon.getInstance().getTimerManager();
		
		/** Victim Combat Tag **/
		if(!manager.hasTimer(event.getVictim(), TimerType.COMBAT_TAG)){
			NeonTimer victimTimer = new NeonTimer(TimerType.COMBAT_TAG, 45L, event.getVictim());
			manager.addTimer(event.getVictim(), victimTimer);
		}else{
			manager.updateTimer(event.getVictim(), TimerType.COMBAT_TAG, 45L);
		}
		
		/** Damager Combat Tag **/
		if(!manager.hasTimer(event.getDamager(), TimerType.COMBAT_TAG)){
			NeonTimer damagerTimer = new NeonTimer(TimerType.COMBAT_TAG, 45L, event.getDamager());
			manager.addTimer(event.getDamager(), damagerTimer);
		}else{
			manager.updateTimer(event.getDamager(), TimerType.COMBAT_TAG, 45L);
		}
		
		
		
		
	}
}
