package com.roguehcf.neon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.roguehcf.neon.chat.ChatCommand;
import com.roguehcf.neon.cmds.CobbleCommand;
import com.roguehcf.neon.cmds.CoordsCommand;
import com.roguehcf.neon.cmds.CraftCommand;
import com.roguehcf.neon.cmds.CrowbarCommand;
import com.roguehcf.neon.cmds.HelpCommand;
import com.roguehcf.neon.cmds.ListCommand;
import com.roguehcf.neon.cmds.MinerCommand;
import com.roguehcf.neon.cmds.ModCommand;
import com.roguehcf.neon.cmds.NeonCommand;
import com.roguehcf.neon.cmds.NightVisionCommand;
import com.roguehcf.neon.cmds.OresCommand;
import com.roguehcf.neon.cmds.PingCommand;
import com.roguehcf.neon.cmds.ReviveCommand;
import com.roguehcf.neon.cmds.SmeltCommand;
import com.roguehcf.neon.cmds.SotwCommand;
import com.roguehcf.neon.cmds.StaffChatCommand;
import com.roguehcf.neon.cmds.ToggleScoreboardCommand;
import com.roguehcf.neon.cmds.VanishCommand;
import com.roguehcf.neon.configuration.ConfigurationManager;
import com.roguehcf.neon.configuration.type.LocaleConfiguration;
import com.roguehcf.neon.configuration.type.OptionsConfiguration;
import com.roguehcf.neon.deathban.DeathbanListener;
import com.roguehcf.neon.deathban.commands.DeathbanCommand;
import com.roguehcf.neon.deathban.commands.LivesCommand;
import com.roguehcf.neon.donator.DonatorBroadcastTask;
import com.roguehcf.neon.event.listeners.EntityDamageByEntityListener;
import com.roguehcf.neon.factions.FactionManager;
import com.roguehcf.neon.factions.manager.FlatFileFactionManager;
import com.roguehcf.neon.listener.JoinQuitListener;
import com.roguehcf.neon.pvpclass.PvPClassListener;
import com.roguehcf.neon.scoreboard.ScoreboardWrapper;
import com.roguehcf.neon.scoreboard.provider.ScoreboardTextProvider;
import com.roguehcf.neon.security.SecurityProfile;
import com.roguehcf.neon.listener.ChatListener;
import com.roguehcf.neon.listener.CrowbarListener;
import com.roguehcf.neon.timer.TimerManager;
import com.roguehcf.neon.timer.listener.CombatTagListener;
import com.roguehcf.neon.timer.listener.TimerListener;
import com.roguehcf.neon.util.NeonPlayer;
import com.roguehcf.neon.util.NeonServer;
import com.roguehcf.neon.util.ServerUtils;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;

public class Neon extends JavaPlugin {

	@Getter private TimerManager timerManager;
	@Getter private ArrayList<UUID> scoreboardDisabled = new ArrayList<UUID>();
	@Getter private ConfigurationManager configManager;
	@Getter private PvPClassListener pvpClassListener;
	@Getter private NeonServer neonServer;
	@Getter private HashMap<UUID, SecurityProfile> securityProfiles = new HashMap<>();
	@Getter private String craftBukkitVersion; /* For reflection if needed */
	@Getter private ScoreboardWrapper scoreboardWrapper;
	@Getter private FactionManager factionManager;
	
	@Getter private static Neon instance;

	public void onEnable() {
		
		instance = this;
		
		saveResource("locale.yml", false);
		saveResource("scoreboard.yml", false);
		
		craftBukkitVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		
		registerManagers();
		registerRunnables();
		registerAll();
		
		/* send msg to console */
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "NeonCore has been enabled. (version " + getDescription().getVersion() + ")");
	}
	
	public void registerManagers() {
		configManager = new ConfigurationManager(this);
		timerManager = new TimerManager(this);
		new NeonPlayer(this);
		neonServer = new NeonServer(this);
		factionManager = new FlatFileFactionManager();
		pvpClassListener = new PvPClassListener();
		reg(pvpClassListener);
		scoreboardWrapper = new ScoreboardWrapper(this, new ScoreboardTextProvider());
	}

	public void registerAll(){
		getCommand("neon").setExecutor(new NeonCommand());
		getCommand("togglescoreboard").setExecutor(new ToggleScoreboardCommand());
		getCommand("staffchat").setExecutor(new StaffChatCommand());
		getCommand("list").setExecutor(new ListCommand());
		
		getCommand("lives").setExecutor(new LivesCommand());
		getCommand("deathban").setExecutor(new DeathbanCommand());
		
		CobbleCommand cobbleCommand = new CobbleCommand();
		
		getCommand("cobble").setExecutor(cobbleCommand);
		
		getCommand("sotw").setExecutor(new SotwCommand());
		
		getCommand("ores").setExecutor(new OresCommand());
		getCommand("craft").setExecutor(new CraftCommand());
		getCommand("ping").setExecutor(new PingCommand());
		getCommand("coords").setExecutor(new CoordsCommand());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("revive").setExecutor(new ReviveCommand());
		getCommand("vanish").setExecutor(new VanishCommand());
		getCommand("miner").setExecutor(new MinerCommand());
		getCommand("nv").setExecutor(new NightVisionCommand());
		getCommand("smelt").setExecutor(new SmeltCommand());
		
		ModCommand modCommand = new ModCommand();
		modCommand.setupItems();
		modCommand.startVanishUpdateTask(this);
		
		getCommand("mod").setExecutor(modCommand);
		getCommand("chat").setExecutor(new ChatCommand());
		getCommand("crowbar").setExecutor(new CrowbarCommand());
		
		Map<String, Map<String, Object>> commands = getDescription().getCommands();

		for (Map.Entry<String, Map<String, Object>> entry : commands.entrySet()) {
			PluginCommand command = getCommand(entry.getKey());
			command.setPermission("neon.command." + entry.getKey());
			command.setPermissionMessage(LocaleConfiguration.NO_PERM);
		}
		
		reg(new TimerListener());
		reg(new JoinQuitListener());
		reg(new EntityDamageByEntityListener());
		reg(new CombatTagListener());
		reg(new DeathbanListener());
		reg(new ChatListener());
		reg(new ModCommand());
		reg(cobbleCommand);
		reg(new CrowbarListener());
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public void registerRunnables(){
		if(OptionsConfiguration.DONATOR_BROADCAST) {
			DonatorBroadcastTask donatorBroadcast = new DonatorBroadcastTask();
			donatorBroadcast.runTaskTimer(this, ServerUtils.TICKS_PER_SECOND * OptionsConfiguration.DONATOR_BROADCAST_DELAY, ServerUtils.TICKS_PER_SECOND * OptionsConfiguration.DONATOR_BROADCAST_DELAY);
		}
	}
	
	public void reg(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}

}
