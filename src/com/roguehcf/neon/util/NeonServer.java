package com.roguehcf.neon.util;

import java.util.HashMap;
import java.util.UUID;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.chat.ChatCooldown;
import com.roguehcf.neon.chat.ChatStatus;

import lombok.Getter;
import lombok.Setter;

public class NeonServer {

	@Getter @Setter private ChatStatus chatStatus;
	@Getter @Setter private HashMap<UUID, ChatCooldown> chatCooldowns;
	@Getter @Setter private int chatDelay;
	
	public NeonServer(Neon instance) {
		chatStatus = ChatStatus.NORMAL;
		chatCooldowns = new HashMap<UUID, ChatCooldown>();
	}
	
	public ChatCooldown getChatCooldownByUUID(UUID uuid) {
		return getChatCooldowns().get(uuid);
	}
	
	public void setChatCooldown(UUID uuid, ChatCooldown cooldown) {
		getChatCooldowns().put(uuid, cooldown);
	}
	
	public void removeChatCooldown(UUID uuid) {
		getChatCooldowns().remove(uuid);
	}
	
}
