package com.roguehcf.neon.timer;

import java.util.concurrent.TimeUnit;

import com.roguehcf.neon.timer.type.TimerType;
import com.roguehcf.neon.util.DurationFormatter;

import lombok.Getter;

public class GlobalTimer {

	@Getter private long timeEnding;
	@Getter public TimerType type;
	
	public GlobalTimer(TimerType type, long timeInSeconds) {
		this.timeEnding = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeInSeconds);
		this.type = type;
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
