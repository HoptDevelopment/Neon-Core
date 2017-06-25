package com.roguehcf.neon.chat;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.roguehcf.neon.util.DurationFormatter;

import lombok.Getter;

public class ChatCooldown {

	@Getter private Long timeEnding;
	
	public ChatCooldown(UUID uuid, Long timeInSeconds) {
		timeEnding = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeInSeconds);
	}
	
	public Long getRemaining() {
		return timeEnding - System.currentTimeMillis();
	}
	
	public String getRemainingString() {
		return DurationFormatter.getFormatted(getRemaining(), true);
	}
}
