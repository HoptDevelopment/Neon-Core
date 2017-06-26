package com.roguehcf.neon.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.roguehcf.neon.Neon;

import net.md_5.bungee.api.ChatColor;

public class SaveDataTask extends BukkitRunnable implements Listener
{

	public SaveDataTask()
	{

	}

	protected static double tps;
	protected static long saved;

	@Override
	public void run()
	{
		saved = System.currentTimeMillis();
		saveRedisData();

		Bukkit.broadcastMessage(ChatColor.GREEN + "Redis stats are saving!");
	}

	public synchronized static void saveRedisData()
	{
		synchronized (Neon.getInstance())
		{
			
		}
	}
}
