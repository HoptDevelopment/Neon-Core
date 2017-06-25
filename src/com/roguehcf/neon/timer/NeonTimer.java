package com.roguehcf.neon.timer;

import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

import com.roguehcf.neon.timer.type.TimerType;
import com.roguehcf.neon.util.DurationFormatter;

import lombok.Getter;

public class NeonTimer {

	@Getter private long timeEnding;
	@Getter public TimerType type;
	@Getter public Player player;
	
	public NeonTimer(TimerType type, long timeInSeconds, Player player) {
		this.timeEnding = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeInSeconds);
		this.type = type;
		this.player = player;
	}
	
	public long getRemainingMillis() {
		return timeEnding - System.currentTimeMillis();
	}
	
	public String getRemainingFormatted() {
		return DurationFormatter.getFormatted(getRemainingMillis(), true);
	}
	
	public void setTimeRemaining(long timeInSeconds){
		this.timeEnding = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeInSeconds);
	}
	
}
