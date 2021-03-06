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
				Help.followers(player);
			}
			// help (<empty>,<admin>,<admin commands>,<commands>)
			else if (args[0].equalsIgnoreCase("help") && args.length==1){
				Help.help(player);
			}
			else if (args[0].equalsIgnoreCase("help") && args.length==2){
				if(args[1].equalsIgnoreCase("hire")){
					Help.hire(player);
				}
				else if(args[1].equalsIgnoreCase("list")){
					Help.list(player);
				}
				else if(args[1].equalsIgnoreCase("inspect")){
					Help.inspect(player);
				}
				else if(args[1].equalsIgnoreCase("stats")){
					Help.stats(player);
				}
				else if(args[1].equalsIgnoreCase("mission")){
					Help.mission(player);
				}
				else if(args[1].equalsIgnoreCase("upgrade")){
					Help.upgrade(player);
				}
				else if(args[1].equalsIgnoreCase("fire")){
					Help.fire(player);
				}
				else if((args[1].equalsIgnoreCase("admin"))&&((player.isOp())||(player.hasPermission("followers.admin")))){
					Help.admin(player);
				}
				else if((args[1].equalsIgnoreCase("set"))&&((player.isOp())||(player.hasPermission("followers.admin")))){
					Help.set(player);
				}
				else if((args[1].equalsIgnoreCase("wipelist"))&&((player.isOp())||(player.hasPermission("followers.admin")))){
					Help.wipelist(player);
				}
				else if((args[1].equalsIgnoreCase("repop"))&&((player.isOp())||(player.hasPermission("followers.admin")))){
					Help.repop(player);
				}
				else if((args[1].equalsIgnoreCase("finish"))&&((player.isOp())||(player.hasPermission("followers.admin")))){
					Help.finish(player);
				}
				else if((args[1].equalsIgnoreCase("wipe"))&&((player.isOp())||(player.hasPermission("followers.admin")))){
					Help.wipe(player);
				}
			}

			// hire (<empty>,<help>,<list>,<#>,<#><confirm>,<inspect>,<inspect><#>)
			else if (args[0].equalsIgnoreCase("hire") && args.length==1){
				Help.hire(player);
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
						else{
							Methods.shortListAvailableFollowers(player);
							Help.warningPrinter(player, "That follower does not exist!");
							Help.syntaxPrinter(player, "fo hire inspect <1-3>");
						}
					}
					else{
						Methods.shortListAvailableFollowers(player);
						Help.warningPrinter(player, "That follower does not exist!");
						Help.syntaxPrinter(player, "fo hire inspect <1-3>");
					}
				}
				else if(args[1].equalsIgnoreCase("inspect")){
					Methods.shortListAvailableFollowers(player);
					Help.syntaxPrinter(player, "fo hire inspect <1-3>");
				}
				else Help.hire(player);
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
							Methods.shortListAvailableFollowers(player);
							Help.warningPrinter(player, "That follower does not exist!");
							Help.syntaxPrinter(player, "fo hire inspect <1-3>");
						}
						
					}
					else{
						Methods.shortListAvailableFollowers(player);
						Help.syntaxPrinter(player, "fo hire inspect <1-3>");
					}
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
									AvailableFollowers fConfig=Followers.availableFollowers.get(sChoice);
									PlayerConfig pConfig=Followers.playerStats.get(player.getName());
									String fName = fConfig.follower1Name;
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
										Methods.wipeAvailableFollower(player, choice);
										player.sendMessage(ChatColor.GREEN+"You've successfully hired " +ChatColor.GOLD+ fName + ChatColor.GREEN+"!");
										player.sendMessage(ChatColor.RED+"You've been charged "+ChatColor.GREEN+"$" +followerPrice+ChatColor.RED+".");
										Followers.econ.withdrawPlayer(player.getName(), followerPrice);
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
										Methods.wipeAvailableFollower(player, choice);
										player.sendMessage(ChatColor.GREEN+"You've successfully hired " +ChatColor.GOLD+ fName + ChatColor.GREEN+"!");
										player.sendMessage(ChatColor.RED+"You've been charged "+ChatColor.GREEN+"$" +followerPrice+ChatColor.RED+".");
										Followers.econ.withdrawPlayer(player.getName(), followerPrice);
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
										player.sendMessage(ChatColor.GREEN+"You've successfully hired " +ChatColor.GOLD+ fName + ChatColor.GREEN+"!");
										player.sendMessage(ChatColor.RED+"You've been charged "+ChatColor.GREEN+"$" +followerPrice+ChatColor.RED+".");
										Followers.econ.withdrawPlayer(player.getName(), followerPrice);
									}
									else{
										Help.warningPrinter(player, "You've no available slots, fire someone first!");
										Help.learnPrinter(player, "fo help fire");
									}
								}
								else Help.warningPrinter(player, "You don't have enough shards for that follower!");
							}
							else{
								Methods.shortListAvailableFollowers(player);
								Help.warningPrinter(player, "That follower does not exist!");
							}
						}
						else{
							Methods.shortListAvailableFollowers(player);
							Help.warningPrinter(player, "That follower does not exist!");
						}
					}
					else Help.hire(player);
				}
			}
			// fire (<empty>,<help>,<list>,<#>,<#><confirm>,<inspect>,<inspect><#>)
			else if (args[0].equalsIgnoreCase("fire") && args.length==1){
				Help.fire(player);
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
					else{
						Methods.listPlayerFollowers(player, fConfig);
						Help.warningPrinter(player, String.valueOf(folNum)+" isn't a valid follower.");
					}
				}
				else{
					Help.fire(player);
					Help.warningPrinter(player, "Invalid argument.");
				}
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
						else{
							Methods.listPlayerFollowers(player, fConfig);
							Help.warningPrinter(player, String.valueOf(folNum)+" isn't a valid follower.");
						}
					} 
					else Help.syntaxPrinter(player, "fo fire "+args[1]+" confirm");
				}
				else{
					Help.fire(player);
					Help.warningPrinter(player, "Invalid argument.");
				}
			}
			/*// sell (<empty>,<help>,<list>,<#>,<#><pname>,<#><pname><confirm>,<inspect>,<inspect><#>)
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
			 */
			else if ((args[0].equalsIgnoreCase("admin")) && (args.length==1)&&((player.hasPermission("followers.admin"))||(player.isOp()))){
				Help.admin(player);
			}
			else if ((args[0].equalsIgnoreCase("admin")) && (args.length==2)&&((player.hasPermission("followers.admin"))||(player.isOp()))){
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
				else Help.admin(player);
			}
			else if ((args[0].equalsIgnoreCase("admin")) && (args.length==3)&&((player.hasPermission("followers.admin"))||(player.isOp()))){
				if(args[1].equalsIgnoreCase("wipe")){
					if(Methods.isInt(args[2])){
						int listNumber = Integer.valueOf(args[2]);
						if((listNumber>0)&&(listNumber<4)){
							player.sendMessage("Successfully assassinated " + Followers.availableFollowers.get
									(String.valueOf(listNumber)).follower1Name+".");
							Methods.wipeAvailableFollower(player, listNumber);
						}
						else{
							Help.warningPrinter(player, "Incorrect argument.");
							Help.syntaxPrinter(player, "fo admin wipe <#> (1-3)");
						}
					}
					else{
						Help.warningPrinter(player, "Incorrect argument.");
						Help.syntaxPrinter(player, "fo admin wipe <#> (1-3)");
					}
				}
			}
			else if ((args[0].equalsIgnoreCase("admin"))&& (args.length==4)&&((player.hasPermission("followers.admin"))||(player.isOp()))){
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
									else Help.warningPrinter(player, "That follower isn't on a mission.");
								}
								else if(fChoice==2){
									if(pConfig.follower2MissionTimeLeft!=0){
										pConfig.follower2MissionTimeLeft=System.currentTimeMillis();
										saveMe=true;
									}
									else Help.warningPrinter(player, "That follower isn't on a mission.");
								}
								else{
									if(pConfig.follower3MissionTimeLeft!=0){
										pConfig.follower3MissionTimeLeft=System.currentTimeMillis();
										saveMe=true;
									}
									else Help.warningPrinter(player, "That follower isn't on a mission.");
								}
								if(saveMe){
									player.sendMessage("Their mission will end within a minute.");
									Followers.playerStats.put(playerName, pConfig);
									Methods.saveMapToPFile(playerName);
								}					
							}
							else{
								Help.warningPrinter(player, "Incorrect argument.");
								Help.syntaxPrinter(player, "fo admin finish <player> <follower #>");
							}
						}
						else{
							Help.warningPrinter(player, "Incorrect argument.");
							Help.syntaxPrinter(player, "fo admin finish <player> <follower #>");
						}
					}
					else{
						Help.warningPrinter(player, "Player '" + args[2] + "' does not exist.");
						Help.syntaxPrinter(player, "fo admin finish <player> <follower #>");
					}
				}
			}
			// admin set pname follower stat value
			else if ((args[0].equalsIgnoreCase("admin"))&& (args.length==6)&&((player.hasPermission("followers.admin"))||(player.isOp()))){
				if(args[1].equalsIgnoreCase("set")){
					String playerName=args[2];
					if(Methods.playerFileExists(playerName)){
						if(Methods.isInt(args[3])){
							int fChoice=Integer.valueOf(args[3]);
							if((fChoice>=1)&&(fChoice<=3)){
								if(Methods.ownedFollowerExists(playerName, fChoice)){
									String stat=args[4];
									String statValue=args[5];
									if(Methods.setStatValidCheck(stat, statValue)){
										Methods.setOwnedStat(playerName, fChoice, args[4], args[5]);
										player.sendMessage("Successfully set " + stat + " to " + statValue + ".");
									}
									else{
										Help.warningPrinter(player, "Invalid stat/value combination.");
										Help.learnPrinter(player, "fo help set");
									}
								}
								else{
									Help.warningPrinter(player, "Follower does not exist.");
									Help.learnPrinter(player, "fo help set");
								}
							}
							else{
								Help.warningPrinter(player, "Not a valid follower number (1-3).");
								Help.learnPrinter(player, "fo help set");
							}
						}
						else{
							Help.warningPrinter(player, args[2]+ " is not a valid integer.");
							Help.learnPrinter(player, "fo help set");
						}
					}
					else{
						Help.warningPrinter(player, "That player doesn't exist.");
						Help.learnPrinter(player, "fo help set");
					}
				}
			}
			else if ((args[0].equalsIgnoreCase("admin"))&& (args.length==7)&&((player.hasPermission("followers.admin"))||(player.isOp()))){
				if(args[1].equalsIgnoreCase("set")){
					String playerName=args[2];
					if(Methods.playerFileExists(playerName)){
						if(Methods.isInt(args[3])){
							int fChoice=Integer.valueOf(args[3]);
							if((fChoice>=1)&&(fChoice<=3)){
								if(Methods.ownedFollowerExists(playerName, fChoice)){
									String stat=args[4];
									String statValue=args[5]+" "+args[6];
									if(Methods.setStatValidCheck(stat, statValue)){
										Methods.setOwnedStat(playerName, fChoice, stat, statValue);
										player.sendMessage("Successfully set " + stat + " to " + statValue + ".");
									}
									else{
										Help.warningPrinter(player, "Invalid stat/value combination.");
										Help.learnPrinter(player, "fo help set");
									}
								}
								else{
									Help.warningPrinter(player, "Follower does not exist.");
									Help.learnPrinter(player, "fo help set");
								}
							}
							else{
								Help.warningPrinter(player, "Not a valid follower number (1-3).");
								Help.learnPrinter(player, "fo help set");
							}
						}
						else{
							Help.warningPrinter(player, args[2]+ " is not a valid integer.");
							Help.learnPrinter(player, "fo help set");
						}
					}
					else{
						Help.warningPrinter(player, "That player doesn't exist.");
						Help.learnPrinter(player, "fo help set");
					}
				}
			}
			// list (<empty>,<help>,<away>,<home>,<#>
			else if (args[0].equalsIgnoreCase("list") && args.length==1){
				PlayerConfig fConfig = Followers.playerStats.get(player.getName());
				Methods.listPlayerFollowers(player, fConfig);
			}
			else if (args[0].equalsIgnoreCase("list") && args.length==2){
				Help.list(player);
			}
			// inspect
			else if (args[0].equalsIgnoreCase("inspect") && args.length==1){
				Help.inspect(player);
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
							String awayString;
							if(fStats.followerMissionTimeLeft!=0){
								int hoursLeft= (int) (((fStats.followerMissionTimeLeft-System.currentTimeMillis()) / (1000*60*60)) % 24);
								awayString="away: "+hoursLeft+"h";
							}
							else awayString="home";
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
							player.sendMessage(ChatColor.YELLOW+"Location: "+ChatColor.GREEN  + awayString);
							player.sendMessage("");
						}
						else{
							Methods.listPlayerFollowers(player, fConfig);
							Help.warningPrinter(player, "Invalid follower!");
							Help.syntaxPrinter(player, "fo inspect <#>");
						}
					}
					else{
						Methods.listPlayerFollowers(player, fConfig);
						Help.warningPrinter(player, "Invalid follower!");
						Help.syntaxPrinter(player, "fo inspect <#>");
					}
				}
				else{
					Help.warningPrinter(player, "Invalid argument.");
					Help.syntaxPrinter(player, "fo inspect <#>");
				}
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
				Help.mission(player);
			}
			else if (args[0].equalsIgnoreCase("mission") && args.length==2){
				Help.mission(player);
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
								player.sendMessage("");
								player.sendMessage("They seem to have a "+ChatColor.RED + chanceString +ChatColor.WHITE +" chance at success.");
								player.sendMessage("There is a "+ChatColor.RED + deathChanceString + ChatColor.WHITE+" chance of dying if they fail.");
								player.sendMessage(ChatColor.DARK_PURPLE+"Type: "+ChatColor.LIGHT_PURPLE+"'/fo mission " + missionTitle.toLowerCase() + 
										" " + followerChoice+ " confirm"+ChatColor.DARK_PURPLE+"'");
								player.sendMessage("");
							}
							else{
								Help.mission(player);
								Help.warningPrinter(player, "Invalid mission type.");
							}
						}
						else{
							Help.warningPrinter(player, "Invalid follower!");
							Help.syntaxPrinter(player, "fo mission <type> <follower #>");
							Help.learnPrinter(player, "fo help mission");
						}
					}
					else{
						Methods.listPlayerFollowers(player, pConfig);
						Help.warningPrinter(player, "Follower is already on a mission!");
					}
				}
				else{
					Help.warningPrinter(player, "Invalid integer!");
					Help.syntaxPrinter(player, "fo mission <type> <follower #>");
					Help.learnPrinter(player, "fo help mission");
				}
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
									player.sendMessage("");
									player.sendMessage(ChatColor.YELLOW+ "You've sent " + ChatColor.GREEN+followerName +
											ChatColor.YELLOW+	" to " +ChatColor.GREEN+ missionTitle + ChatColor.YELLOW+".");
									player.sendMessage(ChatColor.YELLOW+"With luck, they will return in about "+ChatColor.GREEN + TimeUnit.MILLISECONDS.toHours(finishTime-currentTime) +
											" hours"+ChatColor.YELLOW+".");
									Methods.setOwnedStat(player.getName(), followerChoice, "MissionTimeLeft", String.valueOf(finishTime));
									Methods.setOwnedStat(player.getName(), followerChoice, "MissionLevel", String.valueOf(missionValue));
									Methods.setOwnedStat(player.getName(), followerChoice, "MissionType", missionTitle);
									player.sendMessage("");
								}
								else{
									Help.mission(player);
									Help.warningPrinter(player, "Invalid mission type.");
								}
							}
							else{
								Help.warningPrinter(player, "Invalid follower!");
								Help.syntaxPrinter(player, "fo mission <type> <follower #> confirm");
								Help.learnPrinter(player, "fo help mission");
							}
						}
						else{
							Methods.listPlayerFollowers(player, pConfig);
							Help.warningPrinter(player, "Follower is already on a mission!");
						}
					}
					else{
						Help.warningPrinter(player, "Invalid integer!");
						Help.syntaxPrinter(player, "fo mission <type> <follower #> confirm");
						Help.learnPrinter(player, "fo help mission");
					}
				}
				else{
					Help.warningPrinter(player, "You must confirm your mission!");
					Help.syntaxPrinter(player, "fo mission <type> <follower #> confirm");
				}
			}
			// upgrade (<empty>,<help>,<list>,<list><#>,<armor/weapon>,
			//          <armor/weapon><#>,<armor/weapon><#><confirm>)
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==1){
				Help.upgrade(player);
			}
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==2){
				if(args[1].equalsIgnoreCase("help")){
					Help.upgrade(player);
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
						player.sendMessage("");
						Help.warningPrinter(player, "Upgrade "+name+"'s armor or weapon?");
						Help.syntaxPrinter(player, "fo upgrade <1-3> <armor/weapon>");
						player.sendMessage("");
					}
					else{
						Help.warningPrinter(player, "Invalid follower!");
						Help.syntaxPrinter(player, "fo mission <type> <follower #> confirm");
						Help.learnPrinter(player, "fo help mission");
					}
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
								player.sendMessage("");
								player.sendMessage(ChatColor.YELLOW+"Upgrade "+ChatColor.GREEN+name+"'s "+itemChoice.toLowerCase()+ChatColor.YELLOW+" for "+ChatColor.RED+"$" +itemCost+ChatColor.YELLOW+"?");
								player.sendMessage(ChatColor.DARK_PURPLE+"Type: '"+ChatColor.LIGHT_PURPLE+"/fo upgrade "+followerChoice+" "+itemChoice.toLowerCase()+" confirm"+ChatColor.LIGHT_PURPLE+"'");
								player.sendMessage("");
							}
							else if((itemChoice.equalsIgnoreCase("weapon"))&&(weaponLevel<=3)){
								itemCost=(weaponLevel*10000)+10000;
								player.sendMessage("");
								player.sendMessage(ChatColor.YELLOW+"Upgrade "+ChatColor.GREEN+name+"'s "+itemChoice.toLowerCase()+ChatColor.YELLOW+" for "+ChatColor.RED+"$" +itemCost+ChatColor.YELLOW+"?");
								player.sendMessage(ChatColor.DARK_PURPLE+"Type: '"+ChatColor.LIGHT_PURPLE+"/fo upgrade "+followerChoice+" "+itemChoice.toLowerCase()+" confirm"+ChatColor.LIGHT_PURPLE+"'");
								player.sendMessage("");
							}
							else{ 
								player.sendMessage("");
								player.sendMessage("You no longer can pay to upgrade this follower.");
								player.sendMessage("Your follower must "+ChatColor.RED+"FIND"+ChatColor.WHITE+" their LEGENDARY pieces!");
								player.sendMessage("They can find them on "+ChatColor.RED+"top tier"+ChatColor.WHITE+" missions.");
								player.sendMessage("");
							}
							
						}
						else{
							Help.warningPrinter(player, "Item choice must be armor or weapon.");
							Help.syntaxPrinter(player, "fo upgrade <#> <item choice>");
							Help.learnPrinter(player, "fo help upgrade");
						}
					}
					else{
						Help.warningPrinter(player, "Invalid follower!");
						Help.syntaxPrinter(player, "fo upgrade <#> <item choice>");
						Help.learnPrinter(player, "fo help upgrade");
					}
				}
				else{
					Help.warningPrinter(player, "Invalid integer!");
					Help.syntaxPrinter(player, "fo mission <type> <follower #> confirm");
					Help.learnPrinter(player, "fo help mission");
				}
			}
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==4){
				if(args[3].equalsIgnoreCase("confirm")){
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
										player.sendMessage("");
										player.sendMessage(ChatColor.GREEN+name + "'s armor has been upgraded successfully!");
										player.sendMessage("A total of "+ChatColor.RED+"$" + itemCost + ChatColor.WHITE+" has been withdrawn from your balance.");
										player.sendMessage("");
									}
									else{
										player.sendMessage("");
										Help.warningPrinter(player, "You don't have enough shards for that.");
										player.sendMessage("");
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
										player.sendMessage("");
										player.sendMessage(ChatColor.GREEN+name + "'s weapon has been upgraded successfully!");
										player.sendMessage("A total of "+ChatColor.RED+"$" + itemCost + ChatColor.WHITE+" has been withdrawn from your balance.");
										player.sendMessage("");
									}
									else{
										player.sendMessage("");
										Help.warningPrinter(player, "You don't have enough shards for that.");
										player.sendMessage("");
									}
								}
								else{ 
									player.sendMessage("");
									player.sendMessage("You no longer can pay to upgrade this follower.");
									player.sendMessage("Your follower must "+ChatColor.RED+"FIND"+ChatColor.WHITE+" their LEGENDARY pieces!");
									player.sendMessage("They can find them on "+ChatColor.RED+"top tier"+ChatColor.WHITE+" missions.");
									player.sendMessage("");
								}
								
							}
							else{
								Help.warningPrinter(player, "Item choice must be armor or weapon.");
								Help.syntaxPrinter(player, "fo upgrade <#> <item choice>");
								Help.learnPrinter(player, "fo help upgrade");
							}
						}
						else{
							Help.warningPrinter(player, "Invalid follower!");
							Help.syntaxPrinter(player, "fo upgrade <#> <item choice>");
							Help.learnPrinter(player, "fo help upgrade");
						}
					}
					else{
						Help.warningPrinter(player, "Invalid integer!");
						Help.syntaxPrinter(player, "fo mission <type> <follower #> confirm");
						Help.learnPrinter(player, "fo help mission");
					}
				}
				else{
					Help.upgrade(player);
				}
			}
			// recall (<empty>,<help>,<list>,<list><#>,<#>,<#><confirm>)
			else if (args[0].equalsIgnoreCase("recall") && args.length==1){
				Help.recall(player);
			}
			else if (args[0].equalsIgnoreCase("recall") && args.length==2){
				if(Methods.isInt(args[1])){
					PlayerConfig pConfig=Followers.playerStats.get(player.getName());
					int followerChoice=Integer.parseInt(args[1]);
					int followerCount=Methods.ownedfollowerCount(pConfig);
					if(followerChoice<=followerCount){
						if(Methods.isOnMission(pConfig, followerChoice)){
							player.sendMessage("");
							player.sendMessage(ChatColor.RED+"Are you sure you would like to recall "+ 
									ChatColor.GREEN+Methods.findOwnedStat(pConfig, followerChoice, "name")
									+ChatColor.RED+"?");
							player.sendMessage("They are currently on a mission to "
									+ChatColor.GREEN+Methods.findOwnedStat(pConfig, followerChoice, "missiontype")+".");
							player.sendMessage(ChatColor.RED+"The fee is 1000 shards to recall them.");
							player.sendMessage(ChatColor.DARK_PURPLE+"Type: '"+ChatColor.LIGHT_PURPLE+"/fo recall "+followerChoice+" confirm"+ChatColor.LIGHT_PURPLE+"'");
							player.sendMessage("");
						}
						else{
							Methods.listPlayerFollowers(player, pConfig);
							Help.warningPrinter(player, "That follower is not on a mission!");
						}
					}
					else{
						Help.warningPrinter(player, "Invalid follower!");
						Help.syntaxPrinter(player, "fo upgrade <#> <item choice>");
						Help.learnPrinter(player, "fo help upgrade");
					}
				}
				else{
					Help.warningPrinter(player, "Invalid integer!");
					Help.syntaxPrinter(player, "fo mission <type> <follower #> confirm");
					Help.learnPrinter(player, "fo help mission");
				}
			}
			else if (args[0].equalsIgnoreCase("recall") && args.length==3){
				if(args[2].equalsIgnoreCase("confirm")){
					if(Methods.isInt(args[1])){
						PlayerConfig pConfig=Followers.playerStats.get(player.getName());
						int followerChoice=Integer.parseInt(args[1]);
						int followerCount=Methods.ownedfollowerCount(pConfig);
						if(followerChoice<=followerCount){
							if(Methods.isOnMission(pConfig, followerChoice)){
								double playerBalance = Followers.econ.getBalance(player.getName());
								if(playerBalance>=1000){
									Followers.econ.withdrawPlayer(player, 1000);
									Methods.setOwnedStat(player.getName(), followerChoice, "missiontype", "filler");
									Methods.setOwnedStat(player.getName(), followerChoice, "missionlevel", "0");
									Methods.setOwnedStat(player.getName(), followerChoice, "missiontimeleft", "0");
									player.sendMessage("");
									player.sendMessage(ChatColor.YELLOW+ "You've successfully recalled " +ChatColor.GREEN+Methods.findOwnedStat(pConfig, followerChoice, "name")+ ChatColor.YELLOW+".");
									player.sendMessage(ChatColor.RED+"You've been charged $1000.");
									player.sendMessage("");
								}
								else{
									Help.warningPrinter(player, "You don't have enough shards to do that!");
								}
							}
							else{
								Methods.listPlayerFollowers(player, pConfig);
								Help.warningPrinter(player, "That follower is not on a mission!");
							}
						}
						else{
							Help.warningPrinter(player, "Invalid follower!");
							Help.syntaxPrinter(player, "fo upgrade <#> <item choice>");
							Help.learnPrinter(player, "fo help upgrade");
						}
					}
					else{
						Help.warningPrinter(player, "Invalid integer!");
						Help.syntaxPrinter(player, "fo mission <type> <follower #> confirm");
						Help.learnPrinter(player, "fo help mission");
					}
				}
				else{
					Help.recall(player);
				}
			}
			// else....
			else player.sendMessage("Huh?!");
		}
		return false;
	}
}