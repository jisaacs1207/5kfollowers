package io.github.jisaacs1207.followers;

import java.util.HashMap;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.jisaacs1207.followers.Commands;
import io.github.jisaacs1207.followers.PlayerConfig;

public final class Followers extends JavaPlugin implements Listener{
	public static Followers plugin;
	public static HashMap<String, PlayerConfig> playerStats = new HashMap<String, PlayerConfig>();
	public static TreeMap<String, AvailableFollowers> availableFollowers = new TreeMap<String, AvailableFollowers>();
	
	@Override
	public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		registerEvents(this, new Commands(), new Listeners(), new Methods(), new PlayerConfig(), 
				new Schedules(), new Timers(), new RandomNames(), new Modifiers());
		getCommand("followers").setExecutor(new Commands());
		getCommand("fo").setExecutor(new Commands());
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			Methods.populateMapFromPFile(p.getName());
		}
		Methods.populateFollowers();
		Schedules.startFollowerExchange();
	}

	@Override
	public void onDisable() {
		playerStats = null;
		availableFollowers = null;
		plugin = null;
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
	}
}
