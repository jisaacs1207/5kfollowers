package io.github.jisaacs1207.followers;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Commands implements Listener, CommandExecutor{
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmnd, String string, String[] args) {
		String cmd = cmnd.getName();
		if ((cmd.equalsIgnoreCase("followers"))||(cmd.equalsIgnoreCase("fo"))){
			Player player = (Player) sender;
			// no args
			//
			if (args.length==0){
				player.sendMessage("Chickenpotpie");
			}
			// help (<empty>,<admin>,<admin commands>,<commands>)
			else if (args[0].equalsIgnoreCase("help") && args.length==1){
				player.sendMessage("h0args");
			}
			else if (args[0].equalsIgnoreCase("help") && args.length==2){
				player.sendMessage("h1arg");
			}

			// hire (<empty>,<help>,<list>,<#>,<#><confirm>,<inspect>,<inspect><#>)
			else if (args[0].equalsIgnoreCase("hire") && args.length==1){
				player.sendMessage("help on hire");
			}
			else if (args[0].equalsIgnoreCase("hire") && args.length==2){
				if(args[1].equalsIgnoreCase("list")){
					Methods.shortListAvailableFollowers(player);
				}
				else if(Methods.isInt(args[1])){
					int choice = Integer.valueOf(args[1]);
					String sChoice = args[1];
					if((choice>0)&&(choice<4)){
						int curFollowers=Methods.countAvailableFollowers();
						if(choice<=curFollowers){
							AvailableFollowers fConfig = Followers.availableFollowers.get(sChoice);
							Methods.longListAvailableFollowers(player,fConfig);
							player.sendMessage(ChatColor.YELLOW+ "Type : '"+ChatColor.DARK_PURPLE+"/fo hire " + choice + " confirm"+ChatColor.YELLOW+"' to confirm purchase.");
						}
						else player.sendMessage(ChatColor.RED+"That follower doesn't exist.");
					}
					else player.sendMessage(ChatColor.RED+"That follower doesn't exist.");
				}
				else if(args[1].equalsIgnoreCase("inspect")){
					player.sendMessage("Syntax: /fo hire inspect <#>");
				}
				else player.sendMessage("hire options");
			}
			else if (args[0].equalsIgnoreCase("hire") && args.length==3){
				if(args[1].equalsIgnoreCase("inspect")){
					int inputKey;
					if(Methods.isInt(args[2])){
						inputKey=Integer.valueOf(args[2]);
						if(Methods.availableFollowerExists(inputKey)){
							AvailableFollowers fConfig = Followers.availableFollowers.get(args[2]);
							Methods.longListAvailableFollowers(player,fConfig);
						}
						else{
							player.sendMessage("That follower doesn't exist.");
							Methods.shortListAvailableFollowers(player);
							player.sendMessage("Syntax: /fo hire inspect <#>");
						}
						
					}
					else player.sendMessage("Syntax: /fo hire inspect <#>");
				}
				else if(args[2].equalsIgnoreCase("confirm")){
					if(Methods.isInt(args[1])){
						int choice=Integer.valueOf(args[1]);
						String sChoice=args[1];
						if((choice>0)&&(choice<4)){
							int curFollowers=Methods.countAvailableFollowers();
							if(choice<=curFollowers){
								double playerBalance = Followers.econ.getBalance(player.getName());
								int followerPrice = Modifiers.findFollowerPrice(Followers.availableFollowers.get(args[1]));
								if(playerBalance>=followerPrice){
									Followers.econ.withdrawPlayer(player.getName(), followerPrice);
									AvailableFollowers fConfig=Followers.availableFollowers.get(sChoice);
									PlayerConfig pConfig=Followers.playerStats.get(player.getName());
									String fName = fConfig.follower1Name;
									player.sendMessage("You've successfully hired " + fName + "!");
									player.sendMessage("You've been charged $" +followerPrice+".");
									if(pConfig.follower1Name.equalsIgnoreCase("filler")){
										pConfig.follower1Name=fConfig.follower1Name;
										pConfig.follower1Level=fConfig.follower1Level;
										pConfig.follower1Class=fConfig.follower1Class;
										pConfig.follower1Gene=fConfig.follower1Gene;
										pConfig.follower1Gender=fConfig.follower1Gender;
										pConfig.follower1Perk1=fConfig.follower1Perk1;
										pConfig.follower1Perk2=fConfig.follower1Perk2;
										pConfig.follower1Armor=fConfig.follower1Armor;
										pConfig.follower1Weapon=fConfig.follower1Weapon;
										pConfig.currentFollowers=pConfig.currentFollowers++;
										Followers.playerStats.put(player.getName(), pConfig);
										Methods.saveMapToPFile(player.getName());
									}
									else if(pConfig.follower2Name.equalsIgnoreCase("filler")){
										pConfig.follower2Name=fConfig.follower1Name;
										pConfig.follower2Level=fConfig.follower1Level;
										pConfig.follower2Class=fConfig.follower1Class;
										pConfig.follower2Gene=fConfig.follower1Gene;
										pConfig.follower2Gender=fConfig.follower1Gender;
										pConfig.follower2Perk1=fConfig.follower1Perk1;
										pConfig.follower2Perk2=fConfig.follower1Perk2;
										pConfig.follower2Armor=fConfig.follower1Armor;
										pConfig.follower2Weapon=fConfig.follower1Weapon;
										pConfig.currentFollowers=pConfig.currentFollowers++;
										Followers.playerStats.put(player.getName(), pConfig);
										Methods.saveMapToPFile(player.getName());
									}
									else if(pConfig.follower3Name.equalsIgnoreCase("filler")){
										pConfig.follower3Name=fConfig.follower1Name;
										pConfig.follower3Level=fConfig.follower1Level;
										pConfig.follower3Class=fConfig.follower1Class;
										pConfig.follower3Gene=fConfig.follower1Gene;
										pConfig.follower3Gender=fConfig.follower1Gender;
										pConfig.follower3Perk1=fConfig.follower1Perk1;
										pConfig.follower3Perk2=fConfig.follower1Perk2;
										pConfig.follower3Armor=fConfig.follower1Armor;
										pConfig.follower3Weapon=fConfig.follower1Weapon;
										pConfig.currentFollowers=pConfig.currentFollowers++;
										Followers.playerStats.put(player.getName(), pConfig);
										Methods.saveMapToPFile(player.getName());
										fName = fConfig.follower1Name;
									}
									
									else{
										player.sendMessage(ChatColor.RED+"You've no available slots for that follower!");
										player.sendMessage(ChatColor.YELLOW+"Fire someone first, and then try again.");
									}
									Methods.wipeAvailableFollower(player, choice);
								}
								else player.sendMessage("You don't have enough shards for that follower!");
							}
							else player.sendMessage(ChatColor.RED+"That follower doesn't exist.");
						}
						else player.sendMessage(ChatColor.RED+"That follower doesn't exist.");
					}
					else player.sendMessage("Syntax : /fo hire <#> confirm");
				}
			}
			// fire (<empty>,<help>,<list>,<#>,<#><confirm>,<inspect>,<inspect><#>)
			else if (args[0].equalsIgnoreCase("fire") && args.length==1){
				player.sendMessage("Syntax : /fo fire <1-3>");
			}
			else if (args[0].equalsIgnoreCase("fire") && args.length==2){
				if(Methods.isInt(args[1])){
					int folNum=Integer.valueOf(args[1]);
					PlayerConfig fConfig = Followers.playerStats.get(player.getName());
					TreeMap<Integer, TranslatedStats> translatedStats = Modifiers.translateOwnedStats(fConfig);
					int folCount=0;
					for(Integer key : translatedStats.keySet()){
						if(!translatedStats.get(key).followerName.equalsIgnoreCase("filler")) folCount++;
					}
					if(!(folNum>folCount)){
						TranslatedStats stats = translatedStats.get(folNum);
						player.sendMessage("Do you really want to fire: ");
						if(stats.followerGender.equalsIgnoreCase("female"))player.sendMessage(ChatColor.WHITE+"("+ ChatColor.GREEN+stats.followerLevel+
								ChatColor.WHITE+") "+ChatColor.LIGHT_PURPLE+stats.followerName);
						if(stats.followerGender.equalsIgnoreCase("male"))player.sendMessage(ChatColor.WHITE+"("+ ChatColor.GREEN+stats.followerLevel+
								ChatColor.WHITE+") "+ChatColor.DARK_AQUA+stats.followerName);
						if(stats.followerGender.equalsIgnoreCase("neuter"))player.sendMessage(ChatColor.WHITE+"("+ChatColor.GREEN+ stats.followerLevel+
								ChatColor.WHITE+") "+ChatColor.GRAY+stats.followerName);
						player.sendMessage("To confirm, type: '/fo fire "+folNum+" confirm'");
					}
				}
				else player.sendMessage("Syntax : /fo fire <1-3>");
			}
			else if (args[0].equalsIgnoreCase("fire") && args.length==3){
				if(Methods.isInt(args[1])){
					if(args[2].equalsIgnoreCase("confirm")){
						int folNum=Integer.valueOf(args[1]);
						PlayerConfig fConfig = Followers.playerStats.get(player.getName());
						TreeMap<Integer, TranslatedStats> translatedStats = Modifiers.translateOwnedStats(fConfig);
						int folCount=0;
						for(Integer key : translatedStats.keySet()){
							if(!translatedStats.get(key).followerName.equalsIgnoreCase("filler")) folCount++;
						}
						if(!(folNum>folCount)){
							TranslatedStats stats = translatedStats.get(folNum);
							player.sendMessage("You've successfully fired " + stats.followerName + "!");
							if(folNum==1){
								fConfig.follower1Name="filler";
							}
							else if(folNum==2){
								fConfig.follower2Name="filler";
							}
							else{
								fConfig.follower3Name="filler";
							}
							PlayerConfig reorderedConfig = Methods.reorderPlayerMap(fConfig);
							Followers.playerStats.put(player.getName().toString(), reorderedConfig);
							Methods.saveMapToPFile(player.getName().toString());
						}
						else player.sendMessage("Syntax : /fo fire <1-3> confirm"); 
					} 
					else player.sendMessage("Syntax : /fo fire <1-3> confirm");
				}
				else player.sendMessage("Syntax : /fo fire <1-3> confirm");
			}
			// sell (<empty>,<help>,<list>,<#>,<#><pname>,<#><pname><confirm>,<inspect>,<inspect><#>)
			else if (args[0].equalsIgnoreCase("sell") && args.length==1){
				player.sendMessage("help on sell");
			}
			else if (args[0].equalsIgnoreCase("sell") && args.length==2){
				player.sendMessage("sell options");
			}
			// buy (<empty>,<help>,<confirm>)
			else if (args[0].equalsIgnoreCase("buy") && args.length==1){
				player.sendMessage("help on buy");
			}
			else if (args[0].equalsIgnoreCase("buy") && args.length==2){
				player.sendMessage("buy options");
			}
			// admin (<empty>,<help>,<inspect>,<inspect><pname>,<inspect><pname><#>,
			//        <delete>,<delete><pname>,<delete><pname><#>,<create>,
			//        <create><pname>,<create><pname><fname>,<set>,<set><pname>,
			//        <set><pname><#>,<set><pname><#><attribute>)
			else if (args[0].equalsIgnoreCase("admin") && args.length==1){
				player.sendMessage("help on admin");
			}
			else if (args[0].equalsIgnoreCase("admin") && args.length==2){
				if(args[1].equalsIgnoreCase("wipelist")){
					Followers.availableFollowers.clear();	
					player.sendMessage("Available followers wiped.");
				}
				else if(args[1].equalsIgnoreCase("wipe")){
					player.sendMessage("Wipe whom?");
					Methods.shortListAvailableFollowers(player);
					player.sendMessage("/fo admin wipe <#>");
					player.sendMessage("");
				}
				else if(args[1].equalsIgnoreCase("repop")){
					Methods.populateFollowers();
					player.sendMessage("Available followers repopulated.");
				}
				else player.sendMessage("admin options");
			}
			else if (args[0].equalsIgnoreCase("admin") && args.length==3){
				if(args[1].equalsIgnoreCase("wipe")){
					if(Methods.isInt(args[2])){
						int listNumber = Integer.valueOf(args[2]);
						if((listNumber>0)&&(listNumber<4)){
							player.sendMessage("Successfully assassinated " + Followers.availableFollowers.get
									(String.valueOf(listNumber)).follower1Name+".");
							Methods.wipeAvailableFollower(player, listNumber);
						}
						else player.sendMessage("Correct syntax: /fo admin wipe <#> (1-3)");
					}
					else player.sendMessage("Correct syntax: /fo admin wipe <#>");
				}
			}
			else if (args[0].equalsIgnoreCase("admin")&& args.length==4){
				if(args[1].equalsIgnoreCase("finish")){
					if(Methods.playerFileExists(args[2])){
						String playerName = args[2];
						if(Methods.isInt(args[3])){
							int fChoice=Integer.valueOf(args[3]);
							if((fChoice>=1)&&(fChoice<=3)){
								PlayerConfig pConfig = Followers.playerStats.get(playerName);
								Boolean saveMe=false;
								if(fChoice==1){
									if(pConfig.follower1MissionTimeLeft!=0){
										pConfig.follower1MissionTimeLeft=System.currentTimeMillis();
										saveMe=true;
									}
									else player.sendMessage("That follower isn't on a mission.");
								}
								else if(fChoice==2){
									if(pConfig.follower2MissionTimeLeft!=0){
										pConfig.follower2MissionTimeLeft=System.currentTimeMillis();
										saveMe=true;
									}
									else player.sendMessage("That follower isn't on a mission.");
								}
								else{
									if(pConfig.follower3MissionTimeLeft!=0){
										pConfig.follower3MissionTimeLeft=System.currentTimeMillis();
										saveMe=true;
									}
									else player.sendMessage("That follower isn't on a mission.");
								}
								if(saveMe){
									player.sendMessage("Their mission will end within a minute.");
									Followers.playerStats.put(playerName, pConfig);
									Methods.saveMapToPFile(playerName);
								}					
							}
							else player.sendMessage("Follower choice must be 1-3.");
						}
						else player.sendMessage(args[2]+" is not a valid integer.");
					}
					else player.sendMessage("Player '" + args[2] + "' does not exist.");
				}
			}
			else if (args[0].equalsIgnoreCase("admin")&& args.length==6){
				if(args[1].equalsIgnoreCase("set")){
					String playerName=args[2];
					if(Methods.playerFileExists(playerName)){
						if(Methods.isInt(args[3])){
							int fChoice=Integer.valueOf(args[3]);
							if((fChoice>=1)&&(fChoice<=3)){
								if(Methods.ownedFollowerExists(playerName, fChoice)){
									player.sendMessage("Yatta!");
								}
								else player.sendMessage("Follower does not exist.");
							}
							else player.sendMessage("Not a valid follower number (1-3).");
						}
						else player.sendMessage(args[2]+ " is not a valid integer.");
					}
					else player.sendMessage("That player doesn't exist.");
				}
			}
			// list (<empty>,<help>,<away>,<home>,<#>
			else if (args[0].equalsIgnoreCase("list") && args.length==1){
				PlayerConfig fConfig = Followers.playerStats.get(player.getName());
				Methods.listPlayerFollowers(player, fConfig);
			}
			else if (args[0].equalsIgnoreCase("list") && args.length==2){
				player.sendMessage("list options");
			}
			// inspect
			else if (args[0].equalsIgnoreCase("inspect") && args.length==1){
				PlayerConfig fConfig = Followers.playerStats.get(player.getName());
				Methods.listPlayerFollowers(player, fConfig);
				player.sendMessage("");
				player.sendMessage("Syntax: /fo inspect <#>");
			}
			else if (args[0].equalsIgnoreCase("inspect") && args.length==2){
				if(Methods.isInt(args[1])){
					int folNum=Integer.valueOf(args[1]);
					PlayerConfig fConfig = Followers.playerStats.get(player.getName());
					TreeMap<Integer, TranslatedStats> translatedStats = Modifiers.translateOwnedStats(fConfig);
					int folCount=0;
					for(Integer key : translatedStats.keySet()){
						if(!translatedStats.get(key).followerName.equalsIgnoreCase("filler")) folCount++;
					}
					if(!(folNum>folCount)){
						if((folNum<4)&&(folNum>0)){
							TranslatedStats fStats=translatedStats.get(1);
							if(folNum==1){
								fStats=translatedStats.get(1);
							}
							else if(folNum==2){
								fStats=translatedStats.get(2);
							}
							else {
								fStats=translatedStats.get(3);
							}
							player.sendMessage("");
							player.sendMessage(ChatColor.YELLOW+"*| "+ChatColor.GREEN+fStats.followerName+ChatColor.YELLOW+" |*");
							player.sendMessage("");
							player.sendMessage(ChatColor.YELLOW+"Level: "+ChatColor.GREEN + fStats.followerLevel);
							player.sendMessage(ChatColor.YELLOW+"Gender: "+ChatColor.GREEN  + fStats.followerGender);
							player.sendMessage(ChatColor.YELLOW+"Class: "+ChatColor.GREEN  + fStats.followerClass);
							player.sendMessage(ChatColor.YELLOW+"Gene: "+ChatColor.GREEN  + fStats.followerGene);
							player.sendMessage(ChatColor.YELLOW+"Perk: "+ChatColor.GREEN  + fStats.followerPerk1);
							player.sendMessage(ChatColor.YELLOW+"Perk: "+ChatColor.GREEN  + fStats.followerPerk2);
							player.sendMessage(ChatColor.YELLOW+"Weapon: "+ChatColor.GREEN  + fStats.followerWeapon);
							player.sendMessage(ChatColor.YELLOW+"Armor: "+ChatColor.GREEN  + fStats.followerArmor);
							player.sendMessage("");
						}
						else player.sendMessage("Syntax: /fo inspect <1-3>");
					}
					else player.sendMessage("Syntax: /fo inspect <1-3>");
				}
				else player.sendMessage("Syntax: /fo inspect <#>");
			}
			// mission (<>,<type>,<type> <follower #>,<type> <follower #> <confirm>)
			// types :
			// trading (0-1)
			// harvesting (2-3)
			// building (4-5)
			// exploring (6-7)
			// mining (8-9)
			// spelunking (10-11)
			// hunting (12-15)
			// questing (16-20)
			// netherquest (21-24)
			// enderquest (25)
			else if (args[0].equalsIgnoreCase("mission") && args.length==1){
				player.sendMessage("help on mission and types");
			}
			else if (args[0].equalsIgnoreCase("mission") && args.length==2){
				player.sendMessage("mission types descriptions");
			}
			else if (args[0].equalsIgnoreCase("mission") && args.length==3){
				PlayerConfig pConfig = Followers.playerStats.get(player.getName());
				if(Methods.isInt(args[2])){
					int followerChoice=Integer.valueOf(args[2]);
					if(!Methods.isOnMission(pConfig, followerChoice)){
						int followerCount = Methods.ownedfollowerCount(pConfig);
						if((followerChoice<=followerCount)&&(followerChoice>0)){
							if((args[1].equalsIgnoreCase("trade"))||(args[1].equalsIgnoreCase("harvest"))||
									(args[1].equalsIgnoreCase("build"))||(args[1].equalsIgnoreCase("explore"))||
									(args[1].equalsIgnoreCase("mine"))||(args[1].equalsIgnoreCase("spelunk"))||
									(args[1].equalsIgnoreCase("hunt"))||(args[1].equalsIgnoreCase("quest"))||
									(args[1].equalsIgnoreCase("netherquest"))||(args[1].equalsIgnoreCase("enderquest"))){
								String missionTitle=args[1];
								int missionValue=0;
								String followerName= Methods.findOwnedStat(pConfig, followerChoice, "name");
								if(missionTitle.equalsIgnoreCase("trade")) missionValue=2;
								if(missionTitle.equalsIgnoreCase("harvest")) missionValue=3;
								if(missionTitle.equalsIgnoreCase("build")) missionValue=5;
								if(missionTitle.equalsIgnoreCase("explore")) missionValue=7;
								if(missionTitle.equalsIgnoreCase("mine")) missionValue=9;
								if(missionTitle.equalsIgnoreCase("spelunk")) missionValue=11;
								if(missionTitle.equalsIgnoreCase("hunt")) missionValue=15;
								if(missionTitle.equalsIgnoreCase("quest")) missionValue=20;
								if(missionTitle.equalsIgnoreCase("netherquest")) missionValue=24;
								if(missionTitle.equalsIgnoreCase("enderquest")) missionValue=25;
								player.sendMessage("Send " + followerName +
										" " + Modifiers.translateMission(missionValue) + "?");
								int chance = Modifiers.getChance(pConfig, followerChoice, missionValue);
								int deathChance=Modifiers.getDeathChance(pConfig, followerChoice, missionValue);
								String chanceString = "filler";
								String deathChanceString = "filler";
								if(chance<=0) chanceString = "non-existant";
								else if((chance>=1)&&(chance<=5))chanceString = "near non-existant";
								else if((chance>=6)&&(chance<=10))chanceString = "negligible";
								else if((chance>=11)&&(chance<=15))chanceString = "miniscule";
								else if((chance>=16)&&(chance<=20))chanceString = "tiny";
								else if((chance>=21)&&(chance<=30))chanceString = "very small";
								else if((chance>=31)&&(chance<=50))chanceString = "small";
								else if((chance>=51)&&(chance<=74))chanceString = "below-moderate";
								else if((chance>=75)&&(chance<=85))chanceString = "moderate";
								else if((chance>=86)&&(chance<=89))chanceString = "beyond-moderate";
								else if((chance>=90)&&(chance<=94))chanceString = "good";
								else if((chance>=95)&&(chance<=97))chanceString = "very good";
								else if((chance>=98)&&(chance<=99))chanceString = "remarkable";
								else chanceString = "perfect";
								if(deathChance<=0) deathChanceString = "non-existant";
								else if((deathChance>=1)&&(deathChance<=25)) deathChanceString = "possible";
								else if((deathChance>=26)&&(deathChance<=50)) deathChanceString = "highly possible";
								else if((deathChance>=51)&&(deathChance<=75)) deathChanceString = "likely";
								else if((deathChance>=76)&&(deathChance<=99)) deathChanceString = "highly likely";
								else deathChanceString = "certain";
								player.sendMessage("They seem to have a " + chanceString + " chance at success.");
								player.sendMessage("There is a " + deathChanceString + " chance of dying if they fail.");
								player.sendMessage("Type: /fo mission " + missionTitle.toLowerCase() + 
										" " + followerChoice+ " confirm");
							}
							else player.sendMessage("Syntax: /fo mission <type> <follower #>");
						}
						else player.sendMessage("Syntax: /fo mission <type> <follower #>");
					}
					else player.sendMessage("They are already on a mission!");
				}
				else player.sendMessage("Syntax: /fo mission <type> <follower #>");
			}
			else if (args[0].equalsIgnoreCase("mission") && args.length==4){
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				if(args[3].equalsIgnoreCase("confirm")){
					PlayerConfig pConfig = Followers.playerStats.get(player.getName());
					if(Methods.isInt(args[2])){
						int followerChoice=Integer.valueOf(args[2]);
						if(!Methods.isOnMission(pConfig, followerChoice)){
							int followerCount = Methods.ownedfollowerCount(pConfig);
							if((followerChoice<=followerCount)&&(followerChoice>0)){
								if((args[1].equalsIgnoreCase("trade"))||(args[1].equalsIgnoreCase("harvest"))||
										(args[1].equalsIgnoreCase("build"))||(args[1].equalsIgnoreCase("explore"))||
										(args[1].equalsIgnoreCase("mine"))||(args[1].equalsIgnoreCase("spelunk"))||
										(args[1].equalsIgnoreCase("hunt"))||(args[1].equalsIgnoreCase("quest"))||
										(args[1].equalsIgnoreCase("netherquest"))||(args[1].equalsIgnoreCase("enderquest"))){
									String missionTitle=args[1];
									int missionValue=0;
									long finishTime=0;
									long currentTime=System.currentTimeMillis();
									String followerName= Methods.findOwnedStat(pConfig, followerChoice, "name");
									if(missionTitle.equalsIgnoreCase("trade")){
										missionValue=Methods.randomNumber(1,2);
										finishTime=currentTime+3600000;
									}
									else if(missionTitle.equalsIgnoreCase("harvest")){
										missionValue=3;
										finishTime=currentTime+21600000;
									}
									else if(missionTitle.equalsIgnoreCase("build")){
										missionValue=Methods.randomNumber(4,5);
										finishTime=currentTime+21600000;
									}
									else if(missionTitle.equalsIgnoreCase("explore")){
										missionValue=Methods.randomNumber(6,7);
										finishTime=currentTime+43200000;
									}
									else if(missionTitle.equalsIgnoreCase("mine")){
										missionValue=Methods.randomNumber(8,9);
										finishTime=currentTime+86400000;
									}
									else if(missionTitle.equalsIgnoreCase("spelunk")){
										missionValue=Methods.randomNumber(10,11);
										finishTime=currentTime+172800000;
									}
									else if(missionTitle.equalsIgnoreCase("hunt")){
										missionValue=Methods.randomNumber(12,15);
										finishTime=currentTime+172800000;
									}
									else if(missionTitle.equalsIgnoreCase("quest")){
										missionValue=Methods.randomNumber(16,20);
										finishTime=currentTime+259200000;
									}
									else if(missionTitle.equalsIgnoreCase("netherquest")){
										missionValue=Methods.randomNumber(21,24);
										finishTime=currentTime+432000000;
									}
									else{
										missionValue=25;
										finishTime=currentTime+604800000;
									}
									player.sendMessage("You've sent " + followerName +
											" to " + missionTitle + ".");
									player.sendMessage("With luck, they will return in about " + TimeUnit.MILLISECONDS.toHours(finishTime-currentTime) +
											" hours.");
									Methods.setOwnedStat(player.getName(), followerChoice, "MissionTimeLeft", String.valueOf(finishTime));
									Methods.setOwnedStat(player.getName(), followerChoice, "MissionLevel", String.valueOf(missionValue));
									Methods.setOwnedStat(player.getName(), followerChoice, "MissionType", missionTitle);
								}
								else player.sendMessage("Syntax: /fo mission <type> <follower #> confirm");
							}
							else player.sendMessage("Syntax: /fo mission <type> <follower #> confirm");
						}
						else player.sendMessage("They are already on a mission!");
					}
					else player.sendMessage("Syntax: /fo mission <type> <follower #> confirm");
				}
				else player.sendMessage("Syntax: /fo mission <type> <follower #> confirm");
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
			// upgrade (<empty>,<help>,<list>,<list><#>,<armor/weapon>,
			//          <armor/weapon><#>,<armor/weapon><#><confirm>)
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==1){
				player.sendMessage("help on upgrade");
			}
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==2){
				if(args[1].equalsIgnoreCase("help")){
					player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
				}
				if(Methods.isInt(args[1])){
					PlayerConfig pConfig=Followers.playerStats.get(player.getName());
					int followerChoice=Integer.parseInt(args[1]);
					int followerCount=Methods.ownedfollowerCount(pConfig);
					if(followerChoice<=followerCount){
						String name;
						if(followerChoice==1) name = pConfig.follower1Name;
						else if(followerChoice==2) name = pConfig.follower2Name;
						else name = pConfig.follower3Name;
						player.sendMessage("Upgrade "+name+"'s armor or weapon?");
						player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
					}
					else player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
				}
			}
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==3){
				if(Methods.isInt(args[1])){
					PlayerConfig pConfig=Followers.playerStats.get(player.getName());
					int followerChoice=Integer.parseInt(args[1]);
					int followerCount=Methods.ownedfollowerCount(pConfig);
					if(followerChoice<=followerCount){
						String name = Methods.findOwnedStat(pConfig, followerChoice, "name");
						int weaponLevel = Integer.valueOf(Methods.findOwnedStat(pConfig, followerChoice, "weapon"));
						int armorLevel = Integer.valueOf(Methods.findOwnedStat(pConfig, followerChoice, "armor"));
						String itemChoice=args[2];
						int itemCost=0;
						if((itemChoice.equalsIgnoreCase("armor"))||(itemChoice.equalsIgnoreCase("weapon"))){
							if((itemChoice.equalsIgnoreCase("armor"))&&(armorLevel<=3)){
								itemCost=(armorLevel*15000)+15000;
								player.sendMessage("Upgrade "+name+"'s "+itemChoice.toLowerCase()+" for $" +itemCost+"?");
								player.sendMessage("Type: /fo upgrade "+followerChoice+" "+itemChoice.toLowerCase()+" confirm");
							}
							else if((itemChoice.equalsIgnoreCase("weapon"))&&(weaponLevel<=3)){
								itemCost=(weaponLevel*10000)+10000;
								player.sendMessage("Upgrade "+name+"'s "+itemChoice.toLowerCase()+" for $" +itemCost+"?");
								player.sendMessage("Type: /fo upgrade "+followerChoice+" "+itemChoice.toLowerCase()+" confirm");
							}
							else{ 
								player.sendMessage("You no longer pay to upgrade this follower.");
								player.sendMessage("Your follower must FIND their LEGENDARY pieces!");
								player.sendMessage("They can find them on top tier missions.");
							}
							
						}
						else player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
					}
					else player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
				}
				else player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
			}
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==4){
				if(Methods.isInt(args[1])){
					PlayerConfig pConfig=Followers.playerStats.get(player.getName());
					int followerChoice=Integer.parseInt(args[1]);
					int followerCount=Methods.ownedfollowerCount(pConfig);
					if(followerChoice<=followerCount){
						String name = Methods.findOwnedStat(pConfig, followerChoice, "name");
						int weaponLevel = Integer.valueOf(Methods.findOwnedStat(pConfig, followerChoice, "weapon"));
						int armorLevel = Integer.valueOf(Methods.findOwnedStat(pConfig, followerChoice, "armor"));
						String itemChoice=args[2];
						if((itemChoice.equalsIgnoreCase("armor"))||(itemChoice.equalsIgnoreCase("weapon"))){
							if((itemChoice.equalsIgnoreCase("armor"))&&(armorLevel<=3)){				
								int itemCost=(armorLevel*15000)+15000;
								if(Followers.econ.getBalance(player.getName())>itemCost){
									Followers.econ.withdrawPlayer(player.getName(), itemCost);
									String value=Methods.findOwnedStat(pConfig, followerChoice, "armor");
									int intValue=Integer.valueOf(value)+1;
									if(followerChoice==1) pConfig.follower1Armor=intValue;
									if(followerChoice==2) pConfig.follower2Armor=intValue;
									if(followerChoice==3) pConfig.follower3Armor=intValue;
									Followers.playerStats.put(player.getName(), pConfig);
									Methods.saveMapToPFile(player.getName());
									player.sendMessage(name + "'s armor has been upgraded successfully!");
									player.sendMessage("A total of $" + itemCost + " has been withdrawn from your balance.");
								}
								else{
									player.sendMessage("You don't have enough shards to do that.");
								}
							}
							else if((itemChoice.equalsIgnoreCase("weapon"))&&(weaponLevel<=3)){
								int itemCost=(weaponLevel*10000)+10000;
								if(Followers.econ.getBalance(player.getName())>itemCost){
									Followers.econ.withdrawPlayer(player.getName(), itemCost);
									String value=Methods.findOwnedStat(pConfig, followerChoice, "weapon");
									int intValue=Integer.valueOf(value)+1;
									if(followerChoice==1) pConfig.follower1Weapon=intValue;
									if(followerChoice==2) pConfig.follower2Weapon=intValue;
									if(followerChoice==3) pConfig.follower3Weapon=intValue;
									Followers.playerStats.put(player.getName(), pConfig);
									Methods.saveMapToPFile(player.getName());
									player.sendMessage(name + "'s weapon has been upgraded successfully!");
									player.sendMessage("A total of $" + itemCost + " has been withdrawn from your balance.");
								}
								else{
									player.sendMessage("You don't have enough shards to do that.");
								}
							}
							else{ 
								player.sendMessage("You no longer pay to upgrade this follower.");
								player.sendMessage("Your follower must FIND their LEGENDARY pieces!");
								player.sendMessage("They can find them on top tier missions.");
							}
							
						}
						else player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
					}
					else player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
				}
				else player.sendMessage("Syntax: /fo upgrade <1-3> <armor/weapon>");
			}
			// recall (<empty>,<help>,<list>,<list><#>,<#>,<#><confirm>)
			else if (args[0].equalsIgnoreCase("recall") && args.length==1){
				player.sendMessage("help on recall");
			}
			else if (args[0].equalsIgnoreCase("recall") && args.length==2){
				player.sendMessage(new Message().darkblue("recall help").end());
			}
			// else....
			else player.sendMessage("Huh?!");
		}
		return false;
	}
}