package io.github.jisaacs1207.followers;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.jisaacs1207.followers.Methods;

public class Listeners implements Listener{
	// Write a config file for each player
	
	@EventHandler(priority=EventPriority.LOW)
	public void onPlayerJoinEvent(PlayerJoinEvent event){
		
		String player = event.getPlayer().getName();
		//Create a reference to (playername).yml
		File playerfile = new File(Followers.plugin.getDataFolder()+"/players/"+player);

		//Check if file exists in the referenced location
		if(!playerfile.exists())
		{	
			// profile creation 
			Methods.generateNewPlayerFile(player);
		}	
		Methods.populateMapFromPFile(player);
		Methods.updateLastJoin(player);
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void onPlayerQuitEvent(PlayerQuitEvent event){
		String player = event.getPlayer().getName();
		Methods.saveMapToPFile(player);
		Followers.playerStats.put(player, null);
	}
}
