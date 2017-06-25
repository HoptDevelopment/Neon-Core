package com.roguehcf.neon.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.cmds.StaffChatCommand;
import com.roguehcf.neon.security.SecurityProfile;

import lombok.Getter;

public class NeonPlayer {

	private static HashMap<UUID, NeonPlayer> online;
	
	@Getter private Player player;
	
	private SecurityProfile securityProfile;
	private boolean vanished;
	
	public NeonPlayer(Player player) {
		this.player = player;
		this.vanished = false;
		this.securityProfile = new SecurityProfile(player.getUniqueId());
		online.put(player.getUniqueId(), this);
	}
	
	public NeonPlayer(Neon neon) {
		online = new HashMap<UUID, NeonPlayer>();
	}
	
	public void sendMessage(String message) {
		player.sendMessage(message);
	}
	
	public boolean inStaffChat() {
		return StaffChatCommand.getByPlayer(player);
	}
	
	public SecurityProfile getSecurityProfile() {
		if(securityProfile == null) {
			SecurityProfile newProfile = new SecurityProfile(player.getUniqueId());
			securityProfile = newProfile;
		}
		return securityProfile;
	}
	
	@SuppressWarnings("deprecation")
	public void setVanished(boolean vanish) {
		if(vanish) {
			for(Player online : Bukkit.getServer().getOnlinePlayers()) {
				if(!online.hasPermission("neon.seevanished")) {
					online.hidePlayer(player);
				}
			}
			vanished = true;
		}else {
			for(Player online : Bukkit.getServer().getOnlinePlayers()) {
				if(!online.canSee(player)) {
					online.showPlayer(player);;
				}
			}
			vanished = false;
		}
	}
	
	public boolean isVanished() {
		return this.vanished;
	}
	
	public boolean isScoreboardEnabled() {
		return !(Neon.getInstance().getScoreboardDisabled().contains(player.getUniqueId()));
	}
	
	private Method getHandleMethod;
    private Field pingField;
	
    
    /* Get player ping from CraftBukkit maintaining compatibility with all versions by using reflection. */
    
    public int getPing() {
        try {
            if (getHandleMethod == null) {
                getHandleMethod = player.getClass().getDeclaredMethod("getHandle");
                getHandleMethod.setAccessible(true);
            }
            Object entityPlayer = getHandleMethod.invoke(player);
            if (pingField == null) {
                pingField = entityPlayer.getClass().getDeclaredField("ping");
                pingField.setAccessible(true);
            }
            int ping = pingField.getInt(entityPlayer);

            return ping > 0 ? ping : 0;
        } catch (Exception e) {
            return 1;
        }
    }
	
	public static NeonPlayer getByPlayer(Player player) {
		if(!online.containsKey(player.getUniqueId())) {
			return new NeonPlayer(player);
		}
		return online.get(player.getUniqueId());
	}
}
