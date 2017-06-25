package com.roguehcf.neon.cmds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.roguehcf.neon.Neon;
import com.roguehcf.neon.util.NeonPlayer;

import org.bukkit.ChatColor;

public class ModCommand implements CommandExecutor, Listener{

	private HashMap<String, ItemStack> modModeItems = new HashMap<String, ItemStack>();
	private ArrayList<UUID> modModePlayers = new ArrayList<UUID>();
	private HashMap<UUID, ItemStack[]> inventoryContents = new HashMap<UUID, ItemStack[]>();
	private HashMap<UUID, ItemStack[]> armorContents = new HashMap<UUID, ItemStack[]>();


	public ArrayList<UUID> getPlayers(){
		return modModePlayers;
	}

	public HashMap<String, ItemStack> getItems(){
		return modModeItems;
	}

	public boolean isInStaffMode(Player p){
		return getPlayers().contains(p.getUniqueId());
	}

	public void startVanishUpdateTask(Neon core){
		core.getServer().getScheduler().scheduleSyncRepeatingTask(core, new Runnable(){

			public void run(){
				for(UUID uuid : getPlayers()){
					if(Bukkit.getServer().getPlayer(uuid) != null) {
						updateVanishItem(Bukkit.getServer().getPlayer(uuid));
					}
				}
			}

		}, 5L, 5L);
	}

	@EventHandler
	public void attack(EntityDamageByEntityEvent event){
		if(event.getEntity().getType() == EntityType.PLAYER){
			Player player = (Player) event.getEntity();
			if(isInStaffMode(player)){
				if(event.getDamager().getType() == EntityType.PLAYER){
					Player damager = (Player) event.getDamager();
					damager.sendMessage(ChatColor.RED + "You cannot attack players who are in staff mode.");
				}
				event.setCancelled(true);
			}
		}

		if(event.getEntity().getType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.PLAYER){
			Player damager = (Player) event.getDamager();
			if(isInStaffMode(damager)){
				event.setCancelled(true);
				damager.sendMessage(ChatColor.RED + "You cannot attack players whilst in staff mode.");
			}
		}
	}

	public void setupItems(){

		/*
		 * Creates items
		 */
		ItemStack co,o,ss,i,cr,v,voff,rand;
		ItemMeta a,b,c,d,e,f,g,h;

		co = new ItemStack(Material.COMPASS);
		o = new ItemStack(Material.DIAMOND_PICKAXE);
		ss = new ItemStack(Material.WEB);
		i = new ItemStack(Material.BOOK);
		cr = new ItemStack(Material.CARPET);
		v = new ItemStack(Material.INK_SACK);
		voff = new ItemStack(Material.INK_SACK);
		rand = new ItemStack(Material.WATCH);

		a = co.getItemMeta();
		b = o.getItemMeta();
		c = ss.getItemMeta();
		d = i.getItemMeta();
		e = cr.getItemMeta();
		f = v.getItemMeta();
		g = voff.getItemMeta();
		h = rand.getItemMeta();

		v.setDurability((short)10);
		voff.setDurability((short)8);

		a.setDisplayName("§eZoom Compass");
		b.setDisplayName("§eView player's ore statistics");
		c.setDisplayName("§eFreeze Player");
		d.setDisplayName("§eInspect Player");
		e.setDisplayName("§eCarpet");
		f.setDisplayName("§eVanish: §aEnabled");
		g.setDisplayName("§eVanish: §cDisabled");
		h.setDisplayName("§eRandom Teleport");

		a.setLore(Arrays.asList("§7Whoosh..."));
		b.setLore(Arrays.asList("§7Views target's ore statistics."));
		c.setLore(Arrays.asList("§7Freezes the target player."));
		d.setLore(Arrays.asList("§7Views the target's inventory."));
		e.setLore(Arrays.asList("§7Looks good?"));
		f.setLore(Arrays.asList("§7Toggles vanish (Status: §aEnabled§7)"));
		g.setLore(Arrays.asList("§7Toggles vanish (Status: §cDisabled§7)"));
		h.setLore(Arrays.asList("§7Teleports you to a random player."));

		co.setItemMeta(a);
		o.setItemMeta(b);
		ss.setItemMeta(c);
		i.setItemMeta(d);
		cr.setItemMeta(e);
		v.setItemMeta(f);
		voff.setItemMeta(g);
		rand.setItemMeta(h);

		/*
		 * Adds items to mod mode list
		 */
		getItems().put("compass", co);
		getItems().put("ores", o);
		getItems().put("freeze", ss);
		getItems().put("inspect", i);
		getItems().put("carpet", cr);
		getItems().put("vanishon", v);
		getItems().put("vanishoff", voff);
		getItems().put("random", rand);

	}

	public void setModInventory(Player p, boolean status){
		if(status){
			inventoryContents.put(p.getUniqueId(), p.getInventory().getContents());
			armorContents.put(p.getUniqueId(), p.getInventory().getArmorContents());
			p.getInventory().clear();
			p.getInventory().setItem(0, getItems().get("compass"));
			p.getInventory().setItem(1, getItems().get("ores"));
			p.getInventory().setItem(3, getItems().get("freeze"));
			p.getInventory().setItem(4, getItems().get("inspect"));
			p.getInventory().setItem(5, getItems().get("carpet"));
			p.getInventory().setItem(7, NeonPlayer.getByPlayer(p).isVanished() ? getItems().get("vanishon") : getItems().get("vanishoff"));
			p.getInventory().setItem(8, getItems().get("random"));
			p.updateInventory();
			return;
		}

		p.getInventory().clear();
		
		if(inventoryContents.containsKey(p.getUniqueId())) {
			p.getInventory().setContents(this.inventoryContents.get(p.getUniqueId()));
		}
		
		if(armorContents.containsKey(p.getUniqueId())) {
			p.getInventory().setContents(this.armorContents.get(p.getUniqueId()));
		}
		
		p.updateInventory();
	}

	public void updateVanishItem(Player p){
		p.getInventory().setItem(7, NeonPlayer.getByPlayer(p).isVanished() ? getItems().get("vanishon") : getItems().get("vanishoff"));
		p.updateInventory();
	}

	public boolean isItem(ItemStack i, String name){
		return i.equals(getItems().get(name));
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void interactWithItem(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(isInStaffMode(p)){

			if(getItems().containsValue(p.getItemInHand())){

				if(isItem(p.getItemInHand(), "vanishon")){
					p.performCommand("v");
					e.setCancelled(true);
				}

				if(isItem(p.getItemInHand(), "vanishoff")){
					p.performCommand("v");
					e.setCancelled(true);
				}

				if(isItem(p.getItemInHand(), "random")){

					int randomint = new Random().nextInt(Bukkit.getServer().getOnlinePlayers().length);

					Player random = (Player) Bukkit.getServer().getOnlinePlayers()[randomint];

					if (Bukkit.getOnlinePlayers().length == 1) {
						p.sendMessage(ChatColor.YELLOW + "Sorry but it looks like there are not enough players to use this.");
					    e.setCancelled(true);
					}

					if (Bukkit.getOnlinePlayers().length > 1) {
						if (p != random) {
							p.teleport(random);
							p.sendMessage("You were teleported randomly to §c" + random.getName() + "§r.");
							e.setCancelled(true);
						}
						if (p == random) {
							p.sendMessage(ChatColor.YELLOW + "Oops, seems like we accidently found you. Please try again!");
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void interactWithPlayer(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		if(isInStaffMode(p)){
			if(!(e.getRightClicked() instanceof Player))return; //cancels/stops if it is not a player

			Player t = (Player) e.getRightClicked();

			if(isItem(p.getItemInHand(), "freeze")){
				p.performCommand("/ss " + t.getName());
			}

			if(isItem(p.getItemInHand(), "inspect")){
				p.sendMessage(ChatColor.YELLOW + "Opening inventory of player " + ChatColor.RESET + t.getName());
				Inventory i = Bukkit.getServer().createInventory(p, 36, ChatColor.RESET + "Inventory of " + ChatColor.YELLOW + t.getName());
				i.setContents(t.getInventory().getContents());
				p.openInventory(i);
			}

			if(isItem(p.getItemInHand(), "ores")){
				p.sendMessage("§6§m---*------------------------------------------*---");
				p.sendMessage("                     §rOres mined by: §e" + t.getDisplayName());
				p.sendMessage("§bDiamond Ore: §f" + t.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE));
				p.sendMessage("§aEmerald Ore: §f" + t.getStatistic(Statistic.MINE_BLOCK, Material.EMERALD_ORE));
				p.sendMessage("§7Iron Ore: §f" + t.getStatistic(Statistic.MINE_BLOCK, Material.IRON_ORE));
				p.sendMessage("§6Gold Ore: §f" + t.getStatistic(Statistic.MINE_BLOCK, Material.GOLD_ORE));
				p.sendMessage("§cRedstone Ore: §f" + t.getStatistic(Statistic.MINE_BLOCK, Material.REDSTONE_ORE));
				p.sendMessage("§8Coal Ore: §f" + t.getStatistic(Statistic.MINE_BLOCK, Material.COAL_ORE));
				p.sendMessage("§9Lapis Ore: §f" + t.getStatistic(Statistic.MINE_BLOCK, Material.LAPIS_ORE));
				p.sendMessage("§6§m---*------------------------------------------*---");
			}
		}
	}

	@EventHandler
	public void invInteract(InventoryClickEvent e){
		if(isInStaffMode((Player) e.getWhoClicked())){
			((Player) e.getWhoClicked()).sendMessage(ChatColor.RED + "You cannot edit inventories whilst in moderator mode.");
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();

		if(isInStaffMode(p)){
			p.sendMessage(ChatColor.RED + "You cannot break blocks in staff mode.");
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();

		if(isInStaffMode(p)){
			p.sendMessage(ChatColor.RED + "You cannot place blocks in staff mode.");
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void pickup(PlayerPickupItemEvent e){
		if(isInStaffMode(e.getPlayer())){
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void drop(PlayerDropItemEvent e){
		if(isInStaffMode(e.getPlayer())){
			e.getPlayer().sendMessage(ChatColor.RED + "You cannot drop items whilst in moderator mode.");
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void join(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(!isInStaffMode(p)) {
			toggleMod(p, !getPlayers().contains(p.getUniqueId()));
		}
	}
	
	public void toggleMod(Player p, boolean status) {
		
		if(!status) {
			getPlayers().remove(p.getUniqueId());
			p.sendMessage(ChatColor.RED + "You have left staff mode.");
			p.setGameMode(GameMode.SURVIVAL);
			p.setFlying(false);
			setModInventory(p, false);
			return;
		}
		
		setModInventory(p, true);
		p.setGameMode(GameMode.CREATIVE);
		p.setFlying(true);
		
		if(!getPlayers().contains(p.getUniqueId())) {
			getPlayers().add(p.getUniqueId());
		}
		
		p.sendMessage(ChatColor.GREEN + "You have entered staff mode.");
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "Won't work... (your in console)");
			return true;
		}

		Player p = (Player) sender;

		if(!(p.hasPermission("neon.command.mod.toggle"))){
			p.sendMessage(ChatColor.RED + "You are locked in staff mode. You require a higher rank to leave staff mode. Please contact a Staff Manager or Owner if you need to leave staff mode.");
			return true;
		}

		toggleMod(p, !getPlayers().contains(p.getUniqueId()));

		return true;
	}
}
