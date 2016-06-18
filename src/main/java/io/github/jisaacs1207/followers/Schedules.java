package io.github.jisaacs1207.followers;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
	
	public static void startFollowerReturnTimer(){
		BukkitScheduler scheduler = Followers.plugin.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(Followers.plugin, new Runnable(){

			@Override
			public void run() {
				for(Player player:Followers.plugin.getServer().getOnlinePlayers()){
					PlayerConfig pConfig = Followers.playerStats.get(player.getName());
					boolean f1=false;
					boolean f2=false;
					boolean f3=false;
					for(Field field:pConfig.getClass().getDeclaredFields()){
						if(field.getName().toString().contains("MissionTimeLeft")){
							long timeLeft=0;
							
							try {
								timeLeft = field.getLong(pConfig);
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if((timeLeft<=System.currentTimeMillis())&&(timeLeft!=0)){
								if(field.getName().toString().contains("follower1")){
									f1=true;
								}
								else if(field.getName().toString().contains("follower2")){
									f2=true;
								}
								else if(field.getName().toString().contains("follower3")){
									f3=true;
								}
							}
						}
					}
					if((f1==true)||(f2==true)||(f3==true)){
						if(f1==true){
							int chance = Modifiers.getChance(pConfig, 1, pConfig.follower1MissionLevel);
							int deathChance=Modifiers.getDeathChance(pConfig, 1, pConfig.follower1MissionLevel);
							int randomNumberChance = Methods.randomNumber(1, 100);
							if(chance>=randomNumberChance){
								//success
								player.sendMessage("");
								player.sendMessage(ChatColor.GOLD+pConfig.follower1Name+ChatColor.YELLOW
										+" has returned from their mission!");
								player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
										+ pConfig.follower1Name+ ChatColor.YELLOW +" to " 
										+ChatColor.BLUE+pConfig.follower1MissionType+ChatColor.YELLOW+".");
								player.sendMessage(ChatColor.GREEN+"Congratulations!"+ChatColor.YELLOW
										+" The mission was a complete"+ChatColor.GREEN+" SUCCESS"+ChatColor.YELLOW+"!");
								player.sendMessage("");
								pConfig.completedAdventures++;
								pConfig.follower1Successes++;
								if((Methods.randomNumber(1, 5)==2)&&(pConfig.follower1Level<20)){
									player.sendMessage(ChatColor.GREEN+"Your follower has LEVELED UP!");
									pConfig.follower1Level++;
								}
							}
							else{
								//fail
								randomNumberChance = Methods.randomNumber(1, 100);
								if(deathChance>=randomNumberChance){
									//dead
									player.sendMessage("");
									player.sendMessage(ChatColor.GOLD+pConfig.follower1Name+ChatColor.YELLOW
											+" never returned from their mission.");
									player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
											+ pConfig.follower1Name+ ChatColor.YELLOW +" to " 
											+ChatColor.BLUE+pConfig.follower1MissionType+ChatColor.YELLOW+".");
									player.sendMessage(ChatColor.RED+"Sadly"+ChatColor.YELLOW
											+", the mission was a complete"+ChatColor.RED+" FAILURE"+ChatColor.YELLOW+"!");
									player.sendMessage(ChatColor.GOLD+pConfig.follower1Name+
											ChatColor.YELLOW+" is either "+ChatColor.RED+"missing or dead"+ChatColor.YELLOW
											+".");
									player.sendMessage("");
									pConfig.follower1Name="filler";
									pConfig.failedAdventures++;
								}
								else{
									//alive
									player.sendMessage("");
									player.sendMessage(ChatColor.GOLD+pConfig.follower1Name+ChatColor.YELLOW
											+"'s mission has come to an end.");
									player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
											+ pConfig.follower1Name+ ChatColor.YELLOW +" to " 
											+ChatColor.BLUE+pConfig.follower1MissionType+ChatColor.YELLOW+".");
									player.sendMessage(ChatColor.RED+"Unfortunately"+ChatColor.YELLOW
											+", the mission was a"+ChatColor.RED+" FAILURE"+ChatColor.YELLOW+"!");
									player.sendMessage(ChatColor.GOLD+pConfig.follower1Name+
											ChatColor.YELLOW+" has returned "+ChatColor.GRAY+"in humiliation"+ChatColor.YELLOW
											+".");
									player.sendMessage("");
									pConfig.failedAdventures++;
									pConfig.follower1Failures++;
									if((Methods.randomNumber(1, 10)==2)&&(pConfig.follower1Level<20)){
										player.sendMessage(ChatColor.GREEN+"Your follower has LEVELED UP!");
										pConfig.follower1Level++;
									}
								}
							}
							pConfig.follower1MissionLevel=0;
							pConfig.follower1MissionTimeLeft=0;
							pConfig.follower1MissionType="filler";
							PlayerConfig reorderedConfig = Methods.reorderPlayerMap(pConfig);
							Followers.playerStats.put(player.getName().toString(), reorderedConfig);
							Methods.saveMapToPFile(player.getName().toString());
						}
						if(f2==true){
							int chance = Modifiers.getChance(pConfig, 1, pConfig.follower2MissionLevel);
							int deathChance=Modifiers.getDeathChance(pConfig, 1, pConfig.follower2MissionLevel);
							int randomNumberChance = Methods.randomNumber(1, 100);
							if(chance>=randomNumberChance){
								//success
								player.sendMessage("");
								player.sendMessage(ChatColor.GOLD+pConfig.follower2Name+ChatColor.YELLOW
										+" has returned from their mission!");
								player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
										+ pConfig.follower2Name+ ChatColor.YELLOW +" to " 
										+ChatColor.BLUE+pConfig.follower2MissionType+ChatColor.YELLOW+".");
								player.sendMessage(ChatColor.GREEN+"Congratulations!"+ChatColor.YELLOW
										+" The mission was a complete"+ChatColor.GREEN+" SUCCESS"+ChatColor.YELLOW+"!");
								player.sendMessage("");
								pConfig.completedAdventures++;
								pConfig.follower2Successes++;
								if((Methods.randomNumber(1, 5)==2)&&(pConfig.follower2Level<20)){
									player.sendMessage(ChatColor.GREEN+"Your follower has LEVELED UP!");
									pConfig.follower2Level++;
								}
							}
							else{
								//fail
								randomNumberChance = Methods.randomNumber(1, 100);
								if(deathChance>=randomNumberChance){
									//dead
									player.sendMessage("");
									player.sendMessage(ChatColor.GOLD+pConfig.follower2Name+ChatColor.YELLOW
											+" never returned from their mission.");
									player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
											+ pConfig.follower2Name+ ChatColor.YELLOW +" to " 
											+ChatColor.BLUE+pConfig.follower2MissionType+ChatColor.YELLOW+".");
									player.sendMessage(ChatColor.RED+"Sadly"+ChatColor.YELLOW
											+", the mission was a complete"+ChatColor.RED+" FAILURE"+ChatColor.YELLOW+"!");
									player.sendMessage(ChatColor.GOLD+pConfig.follower2Name+
											ChatColor.YELLOW+" is either "+ChatColor.RED+"missing or dead"+ChatColor.YELLOW
											+".");
									player.sendMessage("");
									pConfig.follower2Name="filler";
									pConfig.failedAdventures++;
								}
								else{
									//alive
									player.sendMessage("");
									player.sendMessage(ChatColor.GOLD+pConfig.follower2Name+ChatColor.YELLOW
											+"'s mission has come to an end.");
									player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
											+ pConfig.follower2Name+ ChatColor.YELLOW +" to " 
											+ChatColor.BLUE+pConfig.follower2MissionType+ChatColor.YELLOW+".");
									player.sendMessage(ChatColor.RED+"Unfortunately"+ChatColor.YELLOW
											+", the mission was a"+ChatColor.RED+" FAILURE"+ChatColor.YELLOW+"!");
									player.sendMessage(ChatColor.GOLD+pConfig.follower2Name+
											ChatColor.YELLOW+" has returned "+ChatColor.GRAY+"in humiliation"+ChatColor.YELLOW
											+".");
									player.sendMessage("");
									pConfig.failedAdventures++;
									pConfig.follower2Failures++;
									if((Methods.randomNumber(1, 10)==2)&&(pConfig.follower2Level<20)){
										player.sendMessage(ChatColor.GREEN+"Your follower has LEVELED UP!");
										pConfig.follower1Level++;
									}
								}
							}
							pConfig.follower2MissionLevel=0;
							pConfig.follower2MissionTimeLeft=0;
							pConfig.follower2MissionType="filler";
							PlayerConfig reorderedConfig = Methods.reorderPlayerMap(pConfig);
							Followers.playerStats.put(player.getName().toString(), reorderedConfig);
							Methods.saveMapToPFile(player.getName().toString());
						}
						if(f3==true){
							int chance = Modifiers.getChance(pConfig, 1, pConfig.follower3MissionLevel);
							int deathChance=Modifiers.getDeathChance(pConfig, 1, pConfig.follower3MissionLevel);
							int randomNumberChance = Methods.randomNumber(1, 100);
							if(chance>=randomNumberChance){
								//success
								player.sendMessage("");
								player.sendMessage(ChatColor.GOLD+pConfig.follower3Name+ChatColor.YELLOW
										+" has returned from their mission!");
								player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
										+ pConfig.follower3Name+ ChatColor.YELLOW +" to " 
										+ChatColor.BLUE+pConfig.follower3MissionType+ChatColor.YELLOW+".");
								player.sendMessage(ChatColor.GREEN+"Congratulations!"+ChatColor.YELLOW
										+" The mission was a complete"+ChatColor.GREEN+" SUCCESS"+ChatColor.YELLOW+"!");
								player.sendMessage("");
								pConfig.completedAdventures++;
								pConfig.follower3Successes++;
								if((Methods.randomNumber(1, 5)==2)&&(pConfig.follower3Level<20)){
									player.sendMessage(ChatColor.GREEN+"Your follower has LEVELED UP!");
									pConfig.follower3Level++;
								}
							}
							else{
								//fail
								randomNumberChance = Methods.randomNumber(1, 100);
								if(deathChance>=randomNumberChance){
									//dead
									player.sendMessage("");
									player.sendMessage(ChatColor.GOLD+pConfig.follower3Name+ChatColor.YELLOW
											+" never returned from their mission.");
									player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
											+ pConfig.follower3Name+ ChatColor.YELLOW +" to " 
											+ChatColor.BLUE+pConfig.follower3MissionType+ChatColor.YELLOW+".");
									player.sendMessage(ChatColor.RED+"Sadly"+ChatColor.YELLOW
											+", the mission was a complete"+ChatColor.RED+" FAILURE"+ChatColor.YELLOW+"!");
									player.sendMessage(ChatColor.GOLD+pConfig.follower3Name+
											ChatColor.YELLOW+" is either "+ChatColor.RED+"missing or dead"+ChatColor.YELLOW
											+".");
									player.sendMessage("");
									pConfig.follower3Name="filler";
									pConfig.failedAdventures++;
								}
								else{
									//alive
									player.sendMessage("");
									player.sendMessage(ChatColor.GOLD+pConfig.follower3Name+ChatColor.YELLOW
											+"'s mission has come to an end.");
									player.sendMessage(ChatColor.YELLOW+"You'd sent " +ChatColor.GOLD
											+ pConfig.follower3Name+ ChatColor.YELLOW +" to " 
											+ChatColor.BLUE+pConfig.follower3MissionType+ChatColor.YELLOW+".");
									player.sendMessage(ChatColor.RED+"Unfortunately"+ChatColor.YELLOW
											+", the mission was a"+ChatColor.RED+" FAILURE"+ChatColor.YELLOW+"!");
									player.sendMessage(ChatColor.GOLD+pConfig.follower3Name+
											ChatColor.YELLOW+" has returned "+ChatColor.GRAY+"in humiliation"+ChatColor.YELLOW
											+".");
									player.sendMessage("");
									pConfig.failedAdventures++;
									pConfig.follower3Failures++;
									if((Methods.randomNumber(1, 10)==2)&&(pConfig.follower3Level<20)){
										player.sendMessage(ChatColor.GREEN+"Your follower has LEVELED UP!");
										pConfig.follower1Level++;
									}
								}
							}
							pConfig.follower3MissionLevel=0;
							pConfig.follower3MissionTimeLeft=0;
							pConfig.follower3MissionType="filler";
							PlayerConfig reorderedConfig = Methods.reorderPlayerMap(pConfig);
							Followers.playerStats.put(player.getName().toString(), reorderedConfig);
							Methods.saveMapToPFile(player.getName().toString());
						}
					}
				}
			}
			
		}, 300L, (long) 60*20);
	}
}
