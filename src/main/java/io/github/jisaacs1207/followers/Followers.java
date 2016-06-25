package io.github.jisaacs1207.followers;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import io.github.jisaacs1207.followers.Commands;
import io.github.jisaacs1207.followers.PlayerConfig;

public final class Followers extends JavaPlugin implements Listener{
	//vault
    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
	
	//me
	public static Followers plugin;
	public static HashMap<String, PlayerConfig> playerStats = new HashMap<String, PlayerConfig>();
	public static HashMap<String, List<String>> configData = new HashMap<String, List<String>>();
	public static TreeMap<String, AvailableFollowers> availableFollowers = new TreeMap<String, AvailableFollowers>();
	
	@Override
	public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		registerEvents(this, new Commands(), new Listeners(), new Methods(), new PlayerConfig(), 
				new Schedules(), new Timers(), new RandomNames(), new Modifiers(), new TranslatedStats(),
				new Help());
		getCommand("followers").setExecutor(new Commands());
		getCommand("fo").setExecutor(new Commands());
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			Methods.populateMapFromPFile(p.getName());
		}
		Methods.populateFollowers();
		Schedules.startFollowerExchange();
		Schedules.startFollowerReturnTimer();
		Methods.configToMap();
		//vault
		if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
	}

	@Override
	public void onDisable() {
		playerStats = null;
		availableFollowers = null;
		plugin = null;
		configData=null;
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}
