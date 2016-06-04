package io.github.jisaacs1207.followers;

import java.util.HashMap;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Commands implements Listener, CommandExecutor{
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
						int curFollowers=0;
						for(String key: Followers.availableFollowers.keySet()){
					        curFollowers++;
					    }
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
							int curFollowers=0;
							for(String key: Followers.availableFollowers.keySet()){
						        curFollowers++;
							}
							if(choice<=curFollowers){
								AvailableFollowers fConfig=Followers.availableFollowers.get(sChoice);
								PlayerConfig pConfig=Followers.playerStats.get(player.getName());		
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
								}
								
								else{
									player.sendMessage(ChatColor.RED+"You've no available slots for that follower!");
									player.sendMessage(ChatColor.YELLOW+"Fire someone first, and then try again.");
								}
								Methods.wipeAvailableFollower(player, choice);
								
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
			// mission (<empty>,<help>,<list>,<list><#>,<type>,<type><level>,
			//          <type><level><fname>,<type><level><fname><confirm>)
			else if (args[0].equalsIgnoreCase("mission") && args.length==1){
				player.sendMessage("help on mission");
			}
			else if (args[0].equalsIgnoreCase("mission") && args.length==2){
				player.sendMessage("mission options");
			}
			// upgrade (<empty>,<help>,<list>,<list><#>,<armor/weapon>,
			//          <armor/weapon><#>,<armor/weapon><#><confirm>)
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==1){
				player.sendMessage("help on upgrade");
			}
			else if (args[0].equalsIgnoreCase("upgrade") && args.length==2){
				player.sendMessage(new Message().darkblue("upgrade help").end());
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