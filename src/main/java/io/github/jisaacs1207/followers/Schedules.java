package io.github.jisaacs1207.followers;

import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

public class Schedules implements Listener {
	public static void startFollowerExchange(){
		BukkitScheduler scheduler = Followers.plugin.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(Followers.plugin, new Runnable(){

			@Override
			public void run() {
				Methods.populateFollowers();
			}
			
		}, 300L, (long) 60*60*20);
	}
}
