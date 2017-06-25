package com.roguehcf.neon.configuration.type;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.configuration.ConfigurationManager;
import com.roguehcf.neon.configuration.util.ConfigFile;

import lombok.Getter;

public class LocaleConfiguration {

	@Getter public ConfigFile config;
	
	public LocaleConfiguration(ConfigurationManager manager) {
		
		config = new ConfigFile(Neon.getInstance(), "locale");
		
		LIVES_CHECK = C(config.getString("lives_check"));
		COMMAND_HELP_TITLE = config.getStringList("command_help_title");
		COMMAND_HELP_ENTRY = C(config.getString("command_help_entry"));
		PLAYER_ONLY = C(config.getString("player_only"));
		SCOREBOARD_TOGGLED = C(config.getString("scoreboard_toggled"));
		TIMER_EXPIRED = C(config.getString("timer_expired"));
		TIMER_STARTED = C(config.getString("timer_started"));
		NO_PERM = C(config.getString("no_permission"));
		JOIN_LINES = config.getStringList("join_lines");
		HELP_LINES = config.getStringList("help_lines");
		STAFF_CHAT = C(config.getString("staff_chat"));
		VANISH = C(config.getString("vanish"));
		STAFF_CHAT_FORMAT = C(config.getString("staff_chat_format"));
		XP_BOTTLE = C(config.getString("xp_bottled"));
		SOTW_CANNOT_HIT = C(config.getString("sotw_cannot_hit"));
		SOTW_ENDED = C(config.getString("sotw_ended"));
		SOTW_STARTED = C(config.getString("sotw_started"));
		CANT_ATTACK_PVP_PROTECTED = C(config.getString("cant_attack_pvp_protected"));
		DONATOR_REVIVE_BROADCAST = C(config.getString("donator_revive_broadcast"));
		RESTORE_INVENTORY_PLAYER_MSG = C(config.getString("restore_inventory_player_msg"));
		RESTORE_INVENTORY_RESTORED = C(config.getString("restore_inventory_restored"));
		REQUEST_MSG_TO_STAFF = C(config.getString("request_message_to_staff"));
		REQUEST_MSG_TO_PLAYER = C(config.getString("request_message_to_player"));
		REPORT_MSG_TO_PLAYER = C(config.getString("report_message_to_staff"));
		REPORT_MSG_TO_STAFF = C(config.getString("report_message_to_player"));
		PLAYTIME_OTHER_MSG = C(config.getString("play_time_other_message"));
		PLAYTIME_MSG = C(config.getString("play_time_message"));
		PING_MSG = C(config.getString("ping_message"));
		PING_OTHER_MSG = C(config.getString("ping_other_message"));
		ORES = config.getStringList("ores");
		NIGHT_VISION = C(config.getString("night_vision"));
		MOD_JOINED_IN_MOD = C(config.getString("mod_joined_in_mod"));
		MOD_TOGGLED_FOR_OTHER = C(config.getString("mod_toggled_for_other"));
		MOD_CANT_TOGGLE = C(config.getString("mod_cant_toggle"));
		MOD_TOGGLED = C(config.getString("mod_toggled"));
		MAP_KIT_COMMAND = C(config.getString("map_kit_command"));
		PLAYER_LIVES_SENT = C(config.getString("player_lives_sent"));
		PLAYER_LIVES_REVIVED = C(config.getString("player_lives_revived"));
		STAFF_LIVES_REVIVED = C(config.getString("staff_lives_revived"));
		LIST_MSG = config.getStringList("list_message");
		FREEZE_MSG_TO_PLAYER = config.getStringList("freeze_message_to_player");
		FREEZE_UNFROZEN_PLAYER = C(config.getString("freeze_unfrozen_player"));
		FREEZE_FROZEN_PLAYER = C(config.getString("freeze_frozen_player"));
		END_WORLD_MUST_BE_OVERWORLD = C(config.getString("end_world_must_be_overworld"));
		END_WORLD_MUST_BE_END = C(config.getString("end_world_must_be_end"));
		END_WORLD_EXIT_SET = C(config.getString("end_world_exit_set"));
		END_WORLD_ENTRANCE_SET = C(config.getString("end_world_entrance_set"));
		CRAFT_WORKBENCH_OPEN = C(config.getString("craft_workbench_opened"));
		COORDS = config.getStringList("coords");
		COBBLE_COMMAND = C(config.getString("cobble_command"));
		BLACKLIST_BROADCAST = C(config.getString("blacklist_broadcast"));
		USAGE = C(config.getString("invalid_usage"));
		DEATHBAN_INFO = config.getStringList("deathban_info");
		STILL_ON_TIMER = config.getString("still_on_timer");
		SMELTED_ORES = C(config.getString("smelt_msg"));
		AUTO_SAVE_BROADCAST = config.getStringList("auto_save_broadcast");
		AUTO_SAVE_DELAY = config.getInt("auto_save_delay_seconds");
		TOGGLED_CHAT_MODE = C(config.getString("toggled_chat_mode"));
		MINER_UPGRADED = C(config.getString("miner_upgraded"));
		MINER_COMMAND = config.getStringList("miner_command");
		ON_MINE = C(config.getString("on_mine"));
		
	}
	
	public static String C(String stringToColor) {
		return ChatColor.translateAlternateColorCodes('&', stringToColor)
				.replaceAll("<n>", "\n");
	}
	
	public static String FACTION_NAME_LENGTH = C("&cThe faction name must be under 16 chars long");
	public static String MUST_BE_ALPHANUMERIC = C("&cThe faction name must be alphanumeric.");
	public static List<String> FACTION_HELP = new ArrayList<String>();
	public static int AUTO_SAVE_DELAY = 120;
	public static String PLAYER_OFFLINE = C("&cThat player is not online.");
	public static List<String> COMMAND_HELP_TITLE = new ArrayList<String>(); 
	public static String COMMAND_HELP_ENTRY = C("&9/{0} &7- &e{1}");
	public static String PLAYER_ONLY = C("&cThis command can only be executed by a player.");
	public static String SCOREBOARD_TOGGLED = C("&eYour scoreboard visibility is now set to &r{0}&e.");
	public static String TIMER_EXPIRED = C("&eYour &r{0} &etimer has &c&lexpired&e.");
	public static String TIMER_STARTED = C("&eYour &r{0} &etimer has &a&lstarted&e.");
	public static String NO_PERM = C("&cYou have no permission to use this command.");
	public static String USAGE = C("&cCorrect Usage: {0}");
	public static List<String> JOIN_LINES = new ArrayList<String>();
	public static List<String> HELP_LINES = new ArrayList<String>();
	public static String STAFF_CHAT = C("&eYou are {0} in staff chat.");
	public static String VANISH = C("&eYou are {0} vanished.");
	public static String STAFF_CHAT_FORMAT = C("&6[Staff] &e{0}&8: &7{1}");
	public static String XP_BOTTLE = C("&eYou have bottled &a&l{0} xp &einto a xp bottle.");
	public static String SOTW_CANNOT_HIT = C("&a&lSOTW &ahas &lended&a. &c&lPVP IS NOW ENABLED!");
	public static String SOTW_ENDED = C("&a&lSOTW &ahas &lended&a. &c&lPVP IS NOW ENABLED!");
	public static String SOTW_STARTED = C("&a&lSOTW &ahas started for a period of &l{0}&a.");
	public static String CANT_ATTACK_PVP_PROTECTED = C("&eYou cannot attack &c&l{0} &ebecause they are currently under PvP Protection.");
	public static String DONATOR_REVIVE_BROADCAST = C("&6&l{0} &ehas revived their friend &c&l{1} &eusing their &5&lNeon &erank.");
	public static String RESTORE_INVENTORY_PLAYER_MSG = C("&6{0} &ehas restored your inventory.");
	public static String RESTORE_INVENTORY_RESTORED = C("&eYou have restored &6{0}&e''s inventory.");
	public static String REQUEST_MSG_TO_STAFF = C("&2[Request] &a{0}&7: &r{1}");
	public static String REQUEST_MSG_TO_PLAYER = C("&aYour message has been received.");
	public static String REPORT_MSG_TO_PLAYER = C("&aYou have reported {0} and all staff have been alerted.");
	public static String REPORT_MSG_TO_STAFF = C("&4[Staff] &a[Report] &c{0} &7reported &c{1} &7for &r{2}");
	public static String PLAYTIME_OTHER_MSG = C("&6{1} &ehas been playing this map for a total of &r{0}&e.");
	public static String PLAYTIME_MSG = C("&eYou have been playing this map for a total of &r{0}&e.");
	public static String PING_MSG = C("&eYou are connected to the server with a ping of &a{0} &ems.");
	public static String PING_OTHER_MSG = C("&6{0} &eis connected to the server with a ping of &a{1} &ems.");
	public static List<String> ORES = new ArrayList<String>();
	public static String NIGHT_VISION = C("&eYou have &r{0} &eyour night vision perk.");
	public static String MOD_JOINED_IN_MOD = C("&eYou have joined in moderator mode.");
	public static String MOD_TOGGLED_FOR_OTHER = C("&6{0} &eis &r{1} &ein moderator mode.");
	public static String MOD_CANT_TOGGLE = C("&cYou are locked in moderator mode.");
	public static String MOD_TOGGLED = C("&eYou are now &r{0} in moderator mode.");
	public static String MAP_KIT_COMMAND = C("&eMap Kit: &7Protection I, Sharpness I, Power IV");
	public static String STAFF_LIVES_GAVE = C("&eYou have given &6{0} &e{1}x lives.");
	public static String PLAYER_LIVES_SENT = C("&eYou have sent &6{0} &e{1}x lives.");
	public static String PLAYER_LIVES_REVIVED = C("&eYou have used a life to revive the player &6{0}");
	public static String STAFF_LIVES_REVIVED = C("&eYou have revived the player &6{0}");
	public static List<String> LIST_MSG = new ArrayList<String>();
	public static List<String> FREEZE_MSG_TO_PLAYER = new ArrayList<String>();
	public static String FREEZE_UNFROZEN_PLAYER = C("&aYou have unfrozen the player &2{0}");
	public static String FREEZE_FROZEN_PLAYER = C("&cYou have frozen the player &4{0}");
	public static String END_WORLD_MUST_BE_OVERWORLD = C("&eYou must set this location only in the overworld.");
	public static String END_WORLD_MUST_BE_END = C("&eYou must set this location only in the end.");
	public static String END_WORLD_EXIT_SET = C("&eYou have set the exit of the end world to your location.");
	public static String END_WORLD_ENTRANCE_SET = C("&eYou have set the entrance of the end world to your location.");
	public static String CRAFT_WORKBENCH_OPEN = C("&eYou have opened your crafting workbench.");
	public static List<String> COORDS = new ArrayList<String>();
	public static List<String> DEATHBAN_INFO = new ArrayList<String>();
	public static String COBBLE_COMMAND = C("&eYou can &r{0} &epickup cobblestone");
	public static String BLACKLIST_DEFAULT_REASON = C("Extreme violation of our rules");
	public static String BLACKLIST_BROADCAST = C("&c{0} &7has been &4&lblacklisted &7for &c{1} &7by &c{2}&7.");
	public static String LIVES_CHECK = C("&e{0} has {1} lives.");
	public static String LIVES_QUANTITY = C("&cYou must send lives in a quantity of 1 or more.");
	public static String INVALID_NUMBER = C("&cError: Please enter a valid number.");
	public static String LIVES_NOT_ENOUGH = C("&eYou have not got enough lives to do this.");
	public static String NOT_DEATHBANNED = C("&cThat player is not deathbanned.");
	public static String DEATHBAN_KICK = C("&cYou are still deathbanned for another {0}");
	public static String LIVES_TOOK = C("&eYou have taken &6{0} &elives from &c{1}&e. They now have &a{2} &elives.");
	public static String STILL_ON_TIMER = C("&eYou are still on a &r{0} &efor another &c{1}&e!");
	public static String SMELTED_ORES = C("&eAll gold and iron ores in your inventory have been smelted into ingots.");
	public static List<String> AUTO_SAVE_BROADCAST = new ArrayList<String>();	
	public static String XRAY_STAFF_BROADCAST = C("&8[&4XRAY&8] &a{0} &eis possibly xraying. &7(&b{0} diamonds&7)");
	public static String XRAY_ALERTS_TOGGLED = C("&eYou have toggled your xray alerts to: &r{0}");
	public static String USED_BUFF = C("&eYou have used the bard buff &a{0} &ecosting you &6{1} &eenergy.");
	public static String ENERGY = C("&bEnergy: &r{0}");
	public static String EQUIPPED_CLASS = C("&aYou have equipped the {0} class.");
	public static String UNEQUIPPED_CLASS = C("&cYou have unequipped the {0} class.");
	public static String TOGGLED_CHAT_MODE = C("&cChat &6> &7The server chat status has been changed to: &c{0}&7!.");
	public static String BLACKLIST_KICK = C("&cYou are blacklisted from the network.");
	public static String DONATOR_BROADCAST = C("&eOnline &aNeon &eDonators &6» &r{0}\n&7Purchase this rank at store.neoncore.com");
	public static String DONATOR_BROADCAST_PERMISSION = "neon.donator";
	public static String MINER_UPGRADED = C("&a{0} &ehas mined a total of &b{1} diamonds &eand has been upgraded to the miner class &r{2}&e!");
	public static List<String> MINER_COMMAND = new ArrayList<String>();
	public static String ON_MINE = C("&eYou have mined a &bDiamond Ore&e. You have to mine &b{0} diamonds &ebefore you reach the next level.");
	
}