package com.roguehcf.neon.configuration.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.ConfigurationManager;
import com.roguehcf.neon.configuration.util.ConfigFile;

import lombok.Getter;

public class ScoreboardConfiguration {

	@Getter public static ConfigFile config;
	
	public ScoreboardConfiguration(ConfigurationManager manager) {
		
		config = new ConfigFile(Neon.getInstance(), "scoreboard");
		
		TITLE = C(config.getString("title"));
		LINE = C(config.getString("line"));
		BOTTOM_TEXT_ENABLED = config.getBoolean("bottomtext.enabled");
		BOTTOM_TEXT = config.getStringList("bottomtext.text");
		ENDERPEARL = C(config.getString("displaynames.enderpearl"));
		COMBAT_TAG = C(config.getString("displaynames.combat_tag"));
		GOPPLE = C(config.getString("displaynames.gopple"));
		GAPPLE = C(config.getString("displaynames.gapple"));
		LOGOUT = C(config.getString("displaynames.logout"));
		STUCK = C(config.getString("displaynames.stuck"));
		TELEPORT = C(config.getString("displaynames.teleport"));
		SOTW = C(config.getString("displaynames.sotw"));
		KOTH = C(config.getString("displaynames.koth"));
		IMMUNITY = C(config.getString("displaynames.immunity"));
		DTC = C(config.getString("displaynames.dtc"));
		REVIVE = C(config.getString("displaynames.revive"));
		FORMAT = C(config.getString("entries.format"));
		
		if(config.get("staff.staff_board") != null){
			getStaffLines().clear();
			getStaffLines().addAll(colorStringList(config.getStringList("staff.staff_board")));
		}
		
	}
	
	public ArrayList<String> colorStringList(List<String> list) {
		ArrayList<String> list2 = new ArrayList<>();
		for(String s : list){
			list2.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		return list2;
	}
	
	public static String C(String stringToColor) {
		return ChatColor.translateAlternateColorCodes('&', stringToColor);
	}
	/**
	 * displaynames:
  enderpearl: "&9Enderpearl"
  combat_tag: "&cSpawn Tag"
  gopple: "&6God Apple"
  gapple: "&eGolden Apple"
  logout: "&4Logout"
  stuck: "&9Stuck"
  teleport: "&aTeleport"
  sotw: "&aSOTW"
  koth: "&9{0}"
  immunity: "&aImmunity"
  dtc: "&5{0}"
  class_warmup: '&2Class Warmup'
  equipped_class: '&6Active Class'
  bard_energy: ' &eEnergy'
  bard_delay: ' &eDelay'
  archer_speed_delay: ' &eSpeed'
  revive: '&dRevive'
sotw_scoreboard: '&a&lSOTW &aends in &l{0}'
	 */
	public static String TITLE = C("&5&lNeonCore &7[Default]");
	public static String ENDERPEARL = C("&9Enderpearl");
	public static String COMBAT_TAG = C("&cSpawn Tag");
	public static String GOPPLE = C("&6God Apple");
	public static String GAPPLE = C("&eGolden Apple");
	public static String LOGOUT = C("&4Logout");
	public static String STUCK = C("&9Stuck");
	public static String TELEPORT = C("&aTeleport");
	public static String SOTW = C("&aSOTW");
	public static String KOTH = C("&9{0}");
	public static String IMMUNITY = C("&aImmunity");
	public static String FORMAT = C("{0} &6» &r{1}");
	public static String DTC = C("&5{0}");
	public static String REVIVE = C("&dRevive");
	public static String ARCHER_SPEED_DELAY = C(" &eSpeed");
	public static String BARD_DELAY = C(" &eDelay");
	public static String BARD_ENERGY = C(" &eEnergy");
	public static String EQUIPPED_CLASS = C("&6Active Class");
	public static String SOTW_SB = C("&a&lSOTW &aends in &l{0}");
	public static String LINE = C("&7&m-------------------------------");
	public static List<String> BOTTOM_TEXT = Collections.emptyList();
	public static boolean BOTTOM_TEXT_ENABLED = false;
	
	public static ArrayList<String> getStaffLines() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(C("&eModerator Mode &6»"));
		list.add(C("&6» &eVanish&7: %vanish%"));
		list.add(C("&6» &eGameMode&7: %gamemode%"));
		list.add(C("&6» &eChatMode&7: %chatmode%"));
		if(config.getStringList("staff.staff_board") != null){
			list.clear();
			list.addAll(config.getStringList("staff.staff_board"));
		}
		return list;
	}
	
	
}
