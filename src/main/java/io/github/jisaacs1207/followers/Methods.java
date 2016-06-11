package io.github.jisaacs1207.followers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import io.github.jisaacs1207.followers.Followers;
import io.github.jisaacs1207.followers.Methods;
import io.github.jisaacs1207.followers.PlayerConfig;

public class Methods implements Listener{
	public static void generateNewPlayerFile(String playerName){
		String player = playerName;
		File playerfile = new File(Followers.plugin.getDataFolder()+"/players/"+player);
		YamlConfiguration playerfileyaml = YamlConfiguration.loadConfiguration(playerfile);
		playerfileyaml.set("infofirstjoined", System.currentTimeMillis());
		playerfileyaml.set("infolastjoined", 1);
		playerfileyaml.set("maxFollowers", 1);
		playerfileyaml.set("currentFollowers", 0);
		playerfileyaml.set("followersDead", 0);
		playerfileyaml.set("completedAdventures", 0);
		playerfileyaml.set("failedAdventures", 0);
		playerfileyaml.set("successfulAdventures", 0);
		playerfileyaml.set("follower1Name", "filler");
		playerfileyaml.set("follower1Class", 0);
		playerfileyaml.set("follower1Gene", 0);
		playerfileyaml.set("follower1Gender", 0);
		playerfileyaml.set("follower1Perk1", 0);
		playerfileyaml.set("follower1Perk2", 0);
		playerfileyaml.set("follower1Level", 0);
		playerfileyaml.set("follower1Armor", 0);
		playerfileyaml.set("follower1Weapon", 0);
		playerfileyaml.set("follower1Insured", false);
		playerfileyaml.set("follower1Successes", 0);
		playerfileyaml.set("follower1Failures", 0);
		playerfileyaml.set("follower1MissionType", 0);
		playerfileyaml.set("follower1MissionLevel", 0);
		playerfileyaml.set("follower1MissionTimeLeft", 0);
		playerfileyaml.set("follower2Name", "filler");
		playerfileyaml.set("follower2Class", 0);
		playerfileyaml.set("follower2Gene", 0);
		playerfileyaml.set("follower2Gender", 0);
		playerfileyaml.set("follower2Perk1", 0);
		playerfileyaml.set("follower2Perk2", 0);
		playerfileyaml.set("follower2Level", 0);
		playerfileyaml.set("follower2Armor", 0);
		playerfileyaml.set("follower2Weapon", 0);
		playerfileyaml.set("follower2Insured", false);
		playerfileyaml.set("follower2Successes", 0);
		playerfileyaml.set("follower2Failures", 0);
		playerfileyaml.set("follower2MissionType", 0);
		playerfileyaml.set("follower2MissionLevel", 0);
		playerfileyaml.set("follower2MissionTimeLeft", 0);
		playerfileyaml.set("follower3Name", "filler");
		playerfileyaml.set("follower3Class", 0);
		playerfileyaml.set("follower3Gene", 0);
		playerfileyaml.set("follower3Gender", 0);
		playerfileyaml.set("follower3Perk1", 0);
		playerfileyaml.set("follower3Perk2", 0);
		playerfileyaml.set("follower3Level", 0);
		playerfileyaml.set("follower3Armor", 0);
		playerfileyaml.set("follower3Weapon", 0);
		playerfileyaml.set("follower3Insured", false);
		playerfileyaml.set("follower3Successes", 0);
		playerfileyaml.set("follower3Failures", 0);
		playerfileyaml.set("follower3MissionType", 0);
		playerfileyaml.set("follower3MissionLevel", 0);
		playerfileyaml.set("follower3MissionTimeLeft", 0);

		
		try {
			  playerfileyaml.save(playerfile);
			} catch(IOException e) {
			  e.printStackTrace();
			}
	}
	
	public static boolean playerFileExists(String playerName){
		boolean playerExists = false;
		File folder = new File(Followers.plugin.getDataFolder()+"/players/");
		File[] listOfFiles = folder.listFiles();
		for(File file : listOfFiles){
	    	String fileName= file.getName().toString();
	    	if(playerName.equalsIgnoreCase(fileName)){
	    		playerExists=true;
	    	}
		}
		return playerExists;
	}
	
	public static PlayerConfig populateObjectFromPfile(String playerName){
		PlayerConfig pConfig = new PlayerConfig();
		if(Methods.playerFileExists(playerName)){
			File playerfile = new File(Followers.plugin.getDataFolder()+"/players/"+playerName);
			YamlConfiguration playerfileyaml = YamlConfiguration.loadConfiguration(playerfile);
			pConfig.infofirstjoined=playerfileyaml.getLong("infofirstjoined");
			pConfig.infolastjoined=playerfileyaml.getLong("infolastjoined");
			pConfig.maxFollowers=playerfileyaml.getInt("maxFollowers");
			pConfig.followersDead=playerfileyaml.getInt("followersDead");
			pConfig.completedAdventures=playerfileyaml.getInt("completedAdventures");
			pConfig.failedAdventures=playerfileyaml.getInt("failedAdventures");
			pConfig.successfulAdventures=playerfileyaml.getInt("successfulAdventures");
			pConfig.follower1Name=playerfileyaml.getString("follower1Name");
			pConfig.follower1Class=playerfileyaml.getInt("follower1Class");
			pConfig.follower1Gene=playerfileyaml.getInt("follower1Gene");
			pConfig.follower1Gender=playerfileyaml.getInt("follower1Gender");
			pConfig.follower1Perk1=playerfileyaml.getInt("follower1Perk1");
			pConfig.follower1Perk2=playerfileyaml.getInt("follower1Perk2");
			pConfig.follower1Level=playerfileyaml.getInt("follower1Level");
			pConfig.follower1Armor=playerfileyaml.getInt("follower1Armor");
			pConfig.follower1Weapon=playerfileyaml.getInt("follower1Weapon");
			pConfig.follower1Insured=playerfileyaml.getBoolean("follower1Insured");
			pConfig.follower1Successes=playerfileyaml.getInt("follower1Successes");
			pConfig.follower1Failures=playerfileyaml.getInt("follower1Failures");
			pConfig.follower1MissionType=playerfileyaml.getInt("follower1MissionType");
			pConfig.follower1MissionLevel=playerfileyaml.getInt("follower1MissionLevel");
			pConfig.follower1MissionTimeLeft=playerfileyaml.getInt("follower1MissionTimeLeft");
			pConfig.follower2Name=playerfileyaml.getString("follower2Name");
			pConfig.follower2Class=playerfileyaml.getInt("follower2Class");
			pConfig.follower2Gene=playerfileyaml.getInt("follower2Gene");
			pConfig.follower2Gender=playerfileyaml.getInt("follower2Gender");
			pConfig.follower2Perk1=playerfileyaml.getInt("follower2Perk1");
			pConfig.follower2Perk2=playerfileyaml.getInt("follower2Perk2");
			pConfig.follower2Level=playerfileyaml.getInt("follower2Level");
			pConfig.follower2Armor=playerfileyaml.getInt("follower2Armor");
			pConfig.follower2Weapon=playerfileyaml.getInt("follower2Weapon");
			pConfig.follower2Insured=playerfileyaml.getBoolean("follower2Insured");
			pConfig.follower2Successes=playerfileyaml.getInt("follower2Successes");
			pConfig.follower2Failures=playerfileyaml.getInt("follower2Failures");
			pConfig.follower2MissionType=playerfileyaml.getInt("follower2MissionType");
			pConfig.follower2MissionLevel=playerfileyaml.getInt("follower2MissionLevel");
			pConfig.follower2MissionTimeLeft=playerfileyaml.getInt("follower2MissionTimeLeft");
			pConfig.follower3Name=playerfileyaml.getString("follower3Name");
			pConfig.follower3Class=playerfileyaml.getInt("follower3Class");
			pConfig.follower3Gene=playerfileyaml.getInt("follower3Gene");
			pConfig.follower3Gender=playerfileyaml.getInt("follower3Gender");
			pConfig.follower3Perk1=playerfileyaml.getInt("follower3Perk1");
			pConfig.follower3Perk2=playerfileyaml.getInt("follower3Perk2");
			pConfig.follower3Level=playerfileyaml.getInt("follower3Level");
			pConfig.follower3Armor=playerfileyaml.getInt("follower3Armor");
			pConfig.follower3Weapon=playerfileyaml.getInt("follower3Weapon");
			pConfig.follower3Insured=playerfileyaml.getBoolean("follower3Insured");
			pConfig.follower3Successes=playerfileyaml.getInt("follower3Successes");
			pConfig.follower3Failures=playerfileyaml.getInt("follower3Failures");
			pConfig.follower3MissionType=playerfileyaml.getInt("follower3MissionType");
			pConfig.follower3MissionLevel=playerfileyaml.getInt("follower3MissionLevel");
			pConfig.follower3MissionTimeLeft=playerfileyaml.getInt("follower3MissionTimeLeft");
			
		}
		return pConfig;
	}
	
	public static void populateMapFromPFile(String playerName){
		PlayerConfig pConfig = new PlayerConfig();
		pConfig = populateObjectFromPfile(playerName);
		Followers.playerStats.put(playerName, pConfig);
	}
	
	public static void saveMapToPFile(String playerName){
		File playerfile = new File(Followers.plugin.getDataFolder()+"/players/"+playerName);
		YamlConfiguration playerfileyaml = YamlConfiguration.loadConfiguration(playerfile);
		PlayerConfig pConfig = new PlayerConfig();
		pConfig = Followers.playerStats.get(playerName);
		playerfileyaml.set("infofirstjoined", pConfig.infofirstjoined);
		playerfileyaml.set("infolastjoined", pConfig.infolastjoined);
		playerfileyaml.set("maxFollowers", pConfig.maxFollowers);
		playerfileyaml.set("followersDead", pConfig.followersDead);
		playerfileyaml.set("completedAdventures", pConfig.completedAdventures);
		playerfileyaml.set("failedAdventures", pConfig.failedAdventures);
		playerfileyaml.set("successfulAdventures", pConfig.successfulAdventures);
		playerfileyaml.set("follower1Name", pConfig.follower1Name);
		playerfileyaml.set("follower1Class", pConfig.follower1Class);
		playerfileyaml.set("follower1Gene", pConfig.follower1Gene);
		playerfileyaml.set("follower1Gender", pConfig.follower1Gender);
		playerfileyaml.set("follower1Perk1", pConfig.follower1Perk1);
		playerfileyaml.set("follower1Perk2", pConfig.follower1Perk2);
		playerfileyaml.set("follower1Level", pConfig.follower1Level);
		playerfileyaml.set("follower1Armor", pConfig.follower1Armor);
		playerfileyaml.set("follower1Weapon", pConfig.follower1Weapon);
		playerfileyaml.set("follower1Insured", pConfig.follower1Insured);
		playerfileyaml.set("follower1Successes", pConfig.follower1Successes);
		playerfileyaml.set("follower1Failures", pConfig.follower1Failures);
		playerfileyaml.set("follower1MissionType", pConfig.follower1MissionType);
		playerfileyaml.set("follower1MissionLevel", pConfig.follower1MissionLevel);
		playerfileyaml.set("follower1MissionTimeLeft", pConfig.follower1MissionTimeLeft);
		playerfileyaml.set("follower2Name", pConfig.follower2Name);
		playerfileyaml.set("follower2Class", pConfig.follower2Class);
		playerfileyaml.set("follower2Gene", pConfig.follower2Gene);
		playerfileyaml.set("follower2Gender", pConfig.follower2Gender);
		playerfileyaml.set("follower2Perk1", pConfig.follower2Perk1);
		playerfileyaml.set("follower2Perk2", pConfig.follower2Perk2);
		playerfileyaml.set("follower2Level", pConfig.follower2Level);
		playerfileyaml.set("follower2Armor", pConfig.follower2Armor);
		playerfileyaml.set("follower2Weapon", pConfig.follower2Weapon);
		playerfileyaml.set("follower2Insured", pConfig.follower2Insured);
		playerfileyaml.set("follower2Successes", pConfig.follower2Successes);
		playerfileyaml.set("follower2Failures", pConfig.follower2Failures);
		playerfileyaml.set("follower2MissionType", pConfig.follower2MissionType);
		playerfileyaml.set("follower2MissionLevel", pConfig.follower2MissionLevel);
		playerfileyaml.set("follower2MissionTimeLeft", pConfig.follower2MissionTimeLeft);
		playerfileyaml.set("follower3Name", pConfig.follower3Name);
		playerfileyaml.set("follower3Class", pConfig.follower3Class);
		playerfileyaml.set("follower3Gene", pConfig.follower3Gene);
		playerfileyaml.set("follower3Gender", pConfig.follower3Gender);
		playerfileyaml.set("follower3Perk1", pConfig.follower3Perk1);
		playerfileyaml.set("follower3Perk2", pConfig.follower3Perk2);
		playerfileyaml.set("follower3Level", pConfig.follower3Level);
		playerfileyaml.set("follower3Armor", pConfig.follower3Armor);
		playerfileyaml.set("follower3Weapon", pConfig.follower3Weapon);
		playerfileyaml.set("follower3Insured", pConfig.follower3Insured);
		playerfileyaml.set("follower3Successes", pConfig.follower3Successes);
		playerfileyaml.set("follower3Failures", pConfig.follower3Failures);
		playerfileyaml.set("follower3MissionType", pConfig.follower3MissionType);
		playerfileyaml.set("follower3MissionLevel", pConfig.follower3MissionLevel);
		playerfileyaml.set("follower3MissionTimeLeft", pConfig.follower3MissionTimeLeft);
		try{
			playerfileyaml.save(playerfile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	public static void updateLastJoin(String playerName){
		PlayerConfig pConfig = new PlayerConfig();
		pConfig = populateObjectFromPfile(playerName);
		pConfig.infolastjoined=System.currentTimeMillis();
		Followers.playerStats.put(playerName, pConfig);
		Methods.saveMapToPFile(playerName); 
	}
	
	public static int countAvailableFollowers(){
		int curFollowers=0;
		for(String key: Followers.availableFollowers.keySet()){
	        curFollowers++;
	    }
		return curFollowers;
	}
	public static void populateFollowers(){
		int curFollowers = countAvailableFollowers();
		int maxFollowers = 3;
		int needFollowers;
		needFollowers = maxFollowers-curFollowers;
		AvailableFollowers fConfig = new AvailableFollowers();	
		if(needFollowers>0){
			if(needFollowers==1){
				fConfig = new AvailableFollowers();	
				fConfig = populateFollowersObject();
				String objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
				Followers.availableFollowers.put(objectKey, fConfig);
				reorderAvailableFollowers();
			}
			else if(needFollowers==2){
				fConfig = new AvailableFollowers();	
				fConfig = populateFollowersObject();
				String objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
				Followers.availableFollowers.put(objectKey, fConfig);
				fConfig = populateFollowersObject();
				objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
				Followers.availableFollowers.put(objectKey, fConfig);
				reorderAvailableFollowers();
			}
			else{
				fConfig = new AvailableFollowers();	
				fConfig = populateFollowersObject();
				String objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
				Followers.availableFollowers.put(objectKey, fConfig);
				fConfig = populateFollowersObject();
				objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
				Followers.availableFollowers.put(objectKey, fConfig);
				fConfig = populateFollowersObject();
				objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
				Followers.availableFollowers.put(objectKey, fConfig);
				reorderAvailableFollowers();
			}
		}
		else if(needFollowers<0){ // something went wrong, rebuild 
			fConfig = new AvailableFollowers();	
			fConfig = populateFollowersObject();
			String objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
			Followers.availableFollowers.put(objectKey, fConfig);
			fConfig = populateFollowersObject();
			objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
			Followers.availableFollowers.put(objectKey, fConfig);
			fConfig = populateFollowersObject();
			objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
			Followers.availableFollowers.put(objectKey, fConfig);
			reorderAvailableFollowers();
		}
		else{
			fConfig = new AvailableFollowers();
			List<String> keyList = new ArrayList<String>(); 
			for(String key: Followers.availableFollowers.keySet()){
			    keyList.add(key);
			}
			int randSelector = 0 + (int)(Math.random() * 2);
			String randomKey = (String) keyList.get(randSelector); 
			Followers.availableFollowers.remove(randomKey);
			fConfig = populateFollowersObject();
			String objectKey = String.valueOf(0 + (int)(Math.random() * 10000));
			Followers.availableFollowers.put(objectKey, fConfig);
			reorderAvailableFollowers();
		}
	}
	public static AvailableFollowers populateFollowersObject(){
		AvailableFollowers fConfig = new AvailableFollowers();	
		fConfig.follower1Name=RandomNames.randomName();
		fConfig.follower1Level=1 + (int)(Math.random() * 10);
		fConfig.follower1Gene=1 + (int)(Math.random() * 5);
		fConfig.follower1Gender=1 + (int)(Math.random() * 9);
		fConfig.follower1Class=1 + (int)(Math.random() * 10);
		fConfig.follower1Armor=1 + (int)(Math.random() * 3);
		fConfig.follower1Weapon=1 + (int)(Math.random() * 3);
		fConfig.follower1Perk1=1 + (int)(Math.random() * 15);
		fConfig.follower1Perk2=1 + (int)(Math.random() * 15);
		while(fConfig.follower1Perk1==fConfig.follower1Perk2){
			fConfig.follower1Perk2=1 + (int)(Math.random() * 15);
		}
		return fConfig;
	}
	
	public static void longListAvailableFollowers(Player player, AvailableFollowers fConfig){
		player.sendMessage(ChatColor.YELLOW+"*| "+ChatColor.GREEN+Modifiers.translateStats(fConfig).get("name")+ChatColor.YELLOW+" |*");
		player.sendMessage("");
		player.sendMessage(ChatColor.YELLOW+"Level: "+ChatColor.GREEN + Modifiers.translateStats(fConfig).get("level"));
		player.sendMessage(ChatColor.YELLOW+"Gender: "+ChatColor.GREEN  + Modifiers.translateStats(fConfig).get("gender"));
		player.sendMessage(ChatColor.YELLOW+"Class: "+ChatColor.GREEN  + Modifiers.translateStats(fConfig).get("class"));
		player.sendMessage(ChatColor.YELLOW+"Gene: "+ChatColor.GREEN  + Modifiers.translateStats(fConfig).get("gene"));
		player.sendMessage(ChatColor.YELLOW+"Perk: "+ChatColor.GREEN  + Modifiers.translateStats(fConfig).get("perk1"));
		player.sendMessage(ChatColor.YELLOW+"Perk: "+ChatColor.GREEN  + Modifiers.translateStats(fConfig).get("perk2"));
		player.sendMessage(ChatColor.YELLOW+"Weapon: "+ChatColor.GREEN  + Modifiers.translateStats(fConfig).get("weapon"));
		player.sendMessage(ChatColor.YELLOW+"Armor: "+ChatColor.GREEN  + Modifiers.translateStats(fConfig).get("armor"));
		player.sendMessage(ChatColor.RED +"Price: "+ChatColor.DARK_GREEN  + Modifiers.findFollowerPrice(fConfig));
		player.sendMessage("");
	}
	
	public static void shortListAvailableFollowers(Player player){
		List<String> keyList = new ArrayList<String>(); 
		int followerCount=0;
		for(String key: Followers.availableFollowers.keySet()){
		    keyList.add(key);
		    followerCount++;
		}
		player.sendMessage("");
		if(followerCount<1){
			player.sendMessage(ChatColor.YELLOW + "Sorry, there are no available followers!");
		}
		else{
			player.sendMessage(ChatColor.GOLD+"|*"+ChatColor.BLUE+"Available Followers" +ChatColor.GOLD+"*|");
			player.sendMessage("");
		}
		if(followerCount>0){
			String k1 = (String) keyList.get(0);
			AvailableFollowers fol1 = Followers.availableFollowers.get(k1);
			if((fol1.follower1Gender>0)&&(fol1.follower1Gender<5))player.sendMessage(ChatColor.YELLOW+k1 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol1)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol1.follower1Level +ChatColor.WHITE+") "+ ChatColor.LIGHT_PURPLE+fol1.follower1Name);
			if((fol1.follower1Gender>4)&&(fol1.follower1Gender<9))player.sendMessage(ChatColor.YELLOW+k1 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol1)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol1.follower1Level +ChatColor.WHITE+") "+ ChatColor.DARK_AQUA+fol1.follower1Name);
			if(fol1.follower1Gender==9)player.sendMessage(ChatColor.YELLOW+k1 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol1)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol1.follower1Level +ChatColor.WHITE+") "+ ChatColor.GRAY+fol1.follower1Name);
		}
		if(followerCount>1){
			String k2 = (String) keyList.get(1);
			AvailableFollowers fol2 = Followers.availableFollowers.get(k2);
			if((fol2.follower1Gender>0)&&(fol2.follower1Gender<5))player.sendMessage(ChatColor.YELLOW+k2 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol2)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol2.follower1Level +ChatColor.WHITE+") "+ ChatColor.LIGHT_PURPLE+fol2.follower1Name);
			if((fol2.follower1Gender>4)&&(fol2.follower1Gender<9))player.sendMessage(ChatColor.YELLOW+k2 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol2)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol2.follower1Level +ChatColor.WHITE+") "+ ChatColor.DARK_AQUA+fol2.follower1Name);
			if(fol2.follower1Gender==9)player.sendMessage(ChatColor.YELLOW+k2 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol2)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol2.follower1Level +ChatColor.WHITE+") "+ ChatColor.GRAY+fol2.follower1Name);
		}
		if(followerCount>2){
			String k3 = (String) keyList.get(2);
			AvailableFollowers fol3 = Followers.availableFollowers.get(k3);
			if((fol3.follower1Gender>0)&&(fol3.follower1Gender<5))player.sendMessage(ChatColor.YELLOW+k3 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol3)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol3.follower1Level +ChatColor.WHITE+") "+ ChatColor.LIGHT_PURPLE+fol3.follower1Name);
			if((fol3.follower1Gender>4)&&(fol3.follower1Gender<9))player.sendMessage(ChatColor.YELLOW+k3 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol3)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol3.follower1Level +ChatColor.WHITE+") "+ ChatColor.DARK_AQUA+fol3.follower1Name);
			if(fol3.follower1Gender==9)player.sendMessage(ChatColor.YELLOW+k3 + ": " +ChatColor.DARK_RED+"$"+Modifiers.findFollowerPrice(fol3)+ " "+ ChatColor.WHITE+"(" + ChatColor.GREEN+fol3.follower1Level +ChatColor.WHITE+") "+ ChatColor.GRAY+fol3.follower1Name);
		}
		player.sendMessage("");
		player.sendMessage(ChatColor.RED + "NOTE: New followers may show up every hour or so.");
		player.sendMessage("");
	}
	public static void wipeAvailableFollower(Player player, int place){
		Followers.availableFollowers.remove(String.valueOf(place));
		reorderAvailableFollowers();
	}
	public static boolean isInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
	public static void reorderAvailableFollowers(){
		int listPlace=1;
		TreeMap<String, AvailableFollowers> availableFollowersTemp = new TreeMap<String, AvailableFollowers>();
		for(String key: Followers.availableFollowers.keySet()){
		    AvailableFollowers follower = Followers.availableFollowers.get(key);
		    availableFollowersTemp.put(String.valueOf(listPlace), follower);
		    listPlace++;
		}
		Followers.availableFollowers=availableFollowersTemp;
	}
	public static boolean availableFollowerExists(int toCheck){
		String sToCheck=String.valueOf(toCheck);
		for(String key: Followers.availableFollowers.keySet()){
	        if(sToCheck.equalsIgnoreCase(key))return true;
	    }
		return false;
	}
	public static void listPlayerFollowers(Player player,PlayerConfig fConfig){
		TreeMap<Integer, TranslatedStats> fStats = Modifiers.translateOwnedStats(fConfig);
		int cur = 0;
		for(Integer key : fStats.keySet()){
			TranslatedStats stats = fStats.get(key);
			if(!stats.followerName.equalsIgnoreCase("filler")){
				cur++;
				if(stats.followerGender.equalsIgnoreCase("female"))player.sendMessage(ChatColor.YELLOW+
						String.valueOf(cur)+ ": "+ChatColor.WHITE+"("+ ChatColor.GREEN+stats.followerLevel+
						ChatColor.WHITE+") "+ChatColor.LIGHT_PURPLE+stats.followerName);
				if(stats.followerGender.equalsIgnoreCase("male"))player.sendMessage(ChatColor.YELLOW+
						String.valueOf(cur)+ ": "+ChatColor.WHITE+"("+ ChatColor.GREEN+stats.followerLevel+
						ChatColor.WHITE+") "+ChatColor.DARK_AQUA+stats.followerName);
				if(stats.followerGender.equalsIgnoreCase("neuter"))player.sendMessage(ChatColor.YELLOW+
						String.valueOf(cur)+ ": "+ChatColor.WHITE+"("+ChatColor.GREEN+ stats.followerLevel+
						ChatColor.WHITE+") "+ChatColor.GRAY+stats.followerName);
			}
		}
	}
	public static PlayerConfig reorderPlayerMap(PlayerConfig pConfig){
		int p1=0;
		int p2=0;
		int p3=0;
		String follower1Name=pConfig.follower1Name;
		int follower1Class=pConfig.follower1Class;
		int follower1Gene=pConfig.follower1Gene;
		int follower1Gender=pConfig.follower1Gender;
		int follower1Perk1=pConfig.follower1Perk1;
		int follower1Perk2=pConfig.follower1Perk2;
		int follower1Level=pConfig.follower1Level;
		int follower1Armor=pConfig.follower1Armor;
		int follower1Weapon=pConfig.follower1Weapon;
		boolean follower1Insured=pConfig.follower1Insured;
		int follower1Successes=pConfig.follower1Successes;
		int follower1Failures=pConfig.follower1Failures;
		int follower1MissionType=pConfig.follower1MissionType;
		int follower1MissionLevel=pConfig.follower1MissionLevel;
		int follower1MissionTimeLeft=pConfig.follower1MissionTimeLeft;
		String follower2Name=pConfig.follower2Name;
		int follower2Class=pConfig.follower2Class;
		int follower2Gene=pConfig.follower2Gene;
		int follower2Gender=pConfig.follower2Gender;
		int follower2Perk1=pConfig.follower2Perk1;
		int follower2Perk2=pConfig.follower2Perk2;
		int follower2Level=pConfig.follower2Level;
		int follower2Armor=pConfig.follower2Armor;
		int follower2Weapon=pConfig.follower2Weapon;
		boolean follower2Insured=pConfig.follower2Insured;
		int follower2Successes=pConfig.follower2Successes;
		int follower2Failures=pConfig.follower2Failures;
		int follower2MissionType=pConfig.follower2MissionType;
		int follower2MissionLevel=pConfig.follower2MissionLevel;
		int follower2MissionTimeLeft=pConfig.follower2MissionTimeLeft;
		String follower3Name=pConfig.follower3Name;
		int follower3Class=pConfig.follower3Class;
		int follower3Gene=pConfig.follower3Gene;
		int follower3Gender=pConfig.follower3Gender;
		int follower3Perk1=pConfig.follower3Perk1;
		int follower3Perk2=pConfig.follower3Perk2;
		int follower3Level=pConfig.follower3Level;
		int follower3Armor=pConfig.follower3Armor;
		int follower3Weapon=pConfig.follower3Weapon;
		boolean follower3Insured=pConfig.follower3Insured;
		int follower3Successes=pConfig.follower3Successes;
		int follower3Failures=pConfig.follower3Failures;
		int follower3MissionType=pConfig.follower3MissionType;
		int follower3MissionLevel=pConfig.follower3MissionLevel;
		int follower3MissionTimeLeft=pConfig.follower3MissionTimeLeft;
		pConfig.follower1Name="filler";
		pConfig.follower1Armor=0;
		pConfig.follower1Class=0;
		pConfig.follower1Gender=0;
		pConfig.follower1Gene=0;
		pConfig.follower1Level=0;
		pConfig.follower1Perk1=0;
		pConfig.follower1Perk2=0;
		pConfig.follower1Weapon=0;
		pConfig.follower1Insured=false;
		pConfig.follower1Successes=0;
		pConfig.follower1Failures=0;
		pConfig.follower1MissionType=0;
		pConfig.follower1MissionLevel=0;
		pConfig.follower1MissionTimeLeft=0;
		
		pConfig.follower2Name="filler";
		pConfig.follower2Armor=0;
		pConfig.follower2Class=0;
		pConfig.follower2Gender=0;
		pConfig.follower2Gene=0;
		pConfig.follower2Level=0;
		pConfig.follower2Perk1=0;
		pConfig.follower2Perk2=0;
		pConfig.follower2Weapon=0;
		pConfig.follower2Insured=false;
		pConfig.follower2Successes=0;
		pConfig.follower2Failures=0;
		pConfig.follower2MissionType=0;
		pConfig.follower2MissionLevel=0;
		pConfig.follower2MissionTimeLeft=0;
		
		pConfig.follower3Name="filler";
		pConfig.follower3Armor=0;
		pConfig.follower3Class=0;
		pConfig.follower3Gender=0;
		pConfig.follower3Gene=0;
		pConfig.follower3Level=0;
		pConfig.follower3Perk1=0;
		pConfig.follower3Perk2=0;
		pConfig.follower3Weapon=0;
		pConfig.follower3Insured=false;
		pConfig.follower3Successes=0;
		pConfig.follower3Failures=0;
		pConfig.follower3MissionType=0;
		pConfig.follower3MissionLevel=0;
		pConfig.follower3MissionTimeLeft=0;
		
		if(!follower1Name.equalsIgnoreCase("filler")) {
			p1=1;
		}
		
		if(!follower2Name.equalsIgnoreCase("filler")){
			if(p1==0){
				p1=2;
			}
			else{
				p2=2;
			}
		}

		if(!follower3Name.equalsIgnoreCase("filler")){
			if(p1==0){
				p1=3;
			}
			else if(p2==0){
				p2=3;
			}
			else p3=3;
		}
		
		if(p1==1){
			pConfig.follower1Name=follower1Name;
			pConfig.follower1Armor=follower1Armor;
			pConfig.follower1Class=follower1Class;
			pConfig.follower1Gender=follower1Gender;
			pConfig.follower1Gene=follower1Gene;
			pConfig.follower1Level=follower1Level;
			pConfig.follower1Perk1=follower1Perk1;
			pConfig.follower1Perk2=follower1Perk2;
			pConfig.follower1Weapon=follower1Weapon;
			pConfig.follower1Insured=follower1Insured;
			pConfig.follower1Successes=follower1Successes;
			pConfig.follower1Failures=follower1Failures;
			pConfig.follower1MissionType=follower1MissionType;
			pConfig.follower1MissionLevel=follower1MissionLevel;
			pConfig.follower1MissionTimeLeft=follower1MissionTimeLeft;
		}
		else if(p1==2){
			pConfig.follower1Name=follower2Name;
			pConfig.follower1Armor=follower2Armor;
			pConfig.follower1Class=follower2Class;
			pConfig.follower1Gender=follower2Gender;
			pConfig.follower1Gene=follower2Gene;
			pConfig.follower1Level=follower2Level;
			pConfig.follower1Perk1=follower2Perk1;
			pConfig.follower1Perk2=follower2Perk2;
			pConfig.follower1Weapon=follower2Weapon;
			pConfig.follower1Insured=follower2Insured;
			pConfig.follower1Successes=follower2Successes;
			pConfig.follower1Failures=follower2Failures;
			pConfig.follower1MissionType=follower2MissionType;
			pConfig.follower1MissionLevel=follower2MissionLevel;
			pConfig.follower1MissionTimeLeft=follower2MissionTimeLeft;
		}
		else if(p1==3){
			pConfig.follower1Name=follower3Name;
			pConfig.follower1Armor=follower3Armor;
			pConfig.follower1Class=follower3Class;
			pConfig.follower1Gender=follower3Gender;
			pConfig.follower1Gene=follower3Gene;
			pConfig.follower1Level=follower3Level;
			pConfig.follower1Perk1=follower3Perk1;
			pConfig.follower1Perk2=follower3Perk2;
			pConfig.follower1Weapon=follower3Weapon;
			pConfig.follower1Insured=follower3Insured;
			pConfig.follower1Successes=follower3Successes;
			pConfig.follower1Failures=follower3Failures;
			pConfig.follower1MissionType=follower3MissionType;
			pConfig.follower1MissionLevel=follower3MissionLevel;
			pConfig.follower1MissionTimeLeft=follower3MissionTimeLeft;
		}
		
		if(p2==1){
			pConfig.follower2Name=follower1Name;
			pConfig.follower2Armor=follower1Armor;
			pConfig.follower2Class=follower1Class;
			pConfig.follower2Gender=follower1Gender;
			pConfig.follower2Gene=follower1Gene;
			pConfig.follower2Level=follower1Level;
			pConfig.follower2Perk1=follower1Perk1;
			pConfig.follower2Perk2=follower1Perk2;
			pConfig.follower2Weapon=follower1Weapon;
			pConfig.follower2Insured=follower1Insured;
			pConfig.follower2Successes=follower1Successes;
			pConfig.follower2Failures=follower1Failures;
			pConfig.follower2MissionType=follower1MissionType;
			pConfig.follower2MissionLevel=follower1MissionLevel;
			pConfig.follower2MissionTimeLeft=follower1MissionTimeLeft;
		}
		else if(p2==2){
			pConfig.follower2Name=follower2Name;
			pConfig.follower2Armor=follower2Armor;
			pConfig.follower2Class=follower2Class;
			pConfig.follower2Gender=follower2Gender;
			pConfig.follower2Gene=follower2Gene;
			pConfig.follower2Level=follower2Level;
			pConfig.follower2Perk1=follower2Perk1;
			pConfig.follower2Perk2=follower2Perk2;
			pConfig.follower2Weapon=follower2Weapon;
			pConfig.follower2Insured=follower2Insured;
			pConfig.follower2Successes=follower2Successes;
			pConfig.follower2Failures=follower2Failures;
			pConfig.follower2MissionType=follower2MissionType;
			pConfig.follower2MissionLevel=follower2MissionLevel;
			pConfig.follower2MissionTimeLeft=follower2MissionTimeLeft;
		}
		else if(p2==3){
			pConfig.follower2Name=follower3Name;
			pConfig.follower2Armor=follower3Armor;
			pConfig.follower2Class=follower3Class;
			pConfig.follower2Gender=follower3Gender;
			pConfig.follower2Gene=follower3Gene;
			pConfig.follower2Level=follower3Level;
			pConfig.follower2Perk1=follower3Perk1;
			pConfig.follower2Perk2=follower3Perk2;
			pConfig.follower2Weapon=follower3Weapon;
			pConfig.follower2Insured=follower3Insured;
			pConfig.follower2Successes=follower3Successes;
			pConfig.follower2Failures=follower3Failures;
			pConfig.follower2MissionType=follower3MissionType;
			pConfig.follower2MissionLevel=follower3MissionLevel;
			pConfig.follower2MissionTimeLeft=follower3MissionTimeLeft;
		}
		
		if(p3==1){
			pConfig.follower3Name=follower1Name;
			pConfig.follower3Armor=follower1Armor;
			pConfig.follower3Class=follower1Class;
			pConfig.follower3Gender=follower1Gender;
			pConfig.follower3Gene=follower1Gene;
			pConfig.follower3Level=follower1Level;
			pConfig.follower3Perk1=follower1Perk1;
			pConfig.follower3Perk2=follower1Perk2;
			pConfig.follower3Weapon=follower1Weapon;
			pConfig.follower3Insured=follower1Insured;
			pConfig.follower3Successes=follower1Successes;
			pConfig.follower3Failures=follower1Failures;
			pConfig.follower3MissionType=follower1MissionType;
			pConfig.follower3MissionLevel=follower1MissionLevel;
			pConfig.follower3MissionTimeLeft=follower1MissionTimeLeft;
		}
		else if(p3==2){
			pConfig.follower3Name=follower2Name;
			pConfig.follower3Armor=follower2Armor;
			pConfig.follower3Class=follower2Class;
			pConfig.follower3Gender=follower2Gender;
			pConfig.follower3Gene=follower2Gene;
			pConfig.follower3Level=follower2Level;
			pConfig.follower3Perk1=follower2Perk1;
			pConfig.follower3Perk2=follower2Perk2;
			pConfig.follower3Weapon=follower2Weapon;
			pConfig.follower3Insured=follower2Insured;
			pConfig.follower3Successes=follower2Successes;
			pConfig.follower3Failures=follower2Failures;
			pConfig.follower3MissionType=follower2MissionType;
			pConfig.follower3MissionLevel=follower2MissionLevel;
			pConfig.follower3MissionTimeLeft=follower2MissionTimeLeft;
		}
		else if(p3==3){
			pConfig.follower3Name=follower3Name;
			pConfig.follower3Armor=follower3Armor;
			pConfig.follower3Class=follower3Class;
			pConfig.follower3Gender=follower3Gender;
			pConfig.follower3Gene=follower3Gene;
			pConfig.follower3Level=follower3Level;
			pConfig.follower3Perk1=follower3Perk1;
			pConfig.follower3Perk2=follower3Perk2;
			pConfig.follower3Weapon=follower3Weapon;
			pConfig.follower3Insured=follower3Insured;
			pConfig.follower3Successes=follower3Successes;
			pConfig.follower3Failures=follower3Failures;
			pConfig.follower3MissionType=follower3MissionType;
			pConfig.follower3MissionLevel=follower3MissionLevel;
			pConfig.follower3MissionTimeLeft=follower3MissionTimeLeft;
		}
		return pConfig;
	}
	public static int ownedfollowerCount(PlayerConfig pConfig){
		Object value = "test";
		int followerCount=0;
		for(Field field : pConfig.getClass().getDeclaredFields()){
			try {
				value = field.get(pConfig);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if((field.getName().toString().contains("Name"))&&(!value.toString().equalsIgnoreCase("filler"))){
				followerCount++;
			}
		}
		return followerCount;
	}
	public static String findOwnedStat(PlayerConfig pConfig, int followerChoice, String stat){
		String value="filler";
		for(Field field: pConfig.getClass().getDeclaredFields()){
			
			if(stat.equalsIgnoreCase("level")){
				if(field.getName().toString().equalsIgnoreCase("follower"+followerChoice+"Level")){
					try {
						value=field.get(pConfig).toString();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(stat.equalsIgnoreCase("name")){
				if(field.getName().toString().equalsIgnoreCase("follower"+followerChoice+"Name")){
					try {
						value=field.get(pConfig).toString();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(stat.equalsIgnoreCase("armor")){
				if(field.getName().toString().contains("Armor")){
					try {
						value = field.get(pConfig).toString();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
			if(stat.equalsIgnoreCase("weapon")){
				if(field.getName().toString().contains("Weapon")){
					try {
						value = field.get(pConfig).toString();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		}
		
		
		
		return value;
		
	}
}
