package io.github.jisaacs1207.followers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Help implements Listener {
	public static void syntaxPrinter(Player player,String syntax){
		player.sendMessage(ChatColor.DARK_PURPLE+"Syntax: '"+ChatColor.LIGHT_PURPLE+syntax+ChatColor.DARK_PURPLE+"'");
	}
	public static void warningPrinter(Player player,String warning){
		player.sendMessage(ChatColor.RED+"Warning: "+ChatColor.WHITE+warning);
	}
	public static void followers(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GREEN+"Followers "+ChatColor.WHITE+"- "+ChatColor.GRAY+"Hire followers and delegate tasks.");
		player.sendMessage(ChatColor.GREEN+"http://fivekingdoms.net/ -"+ChatColor.GRAY+" jisaacs1207");
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_PURPLE+"For help, try '"+ChatColor.LIGHT_PURPLE+"fo help"+ChatColor.DARK_PURPLE+"'");
		player.sendMessage("");
	}
	public static void help(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GRAY+"===================["+ChatColor.GREEN+"Followers Help"+ChatColor.GRAY+"]===================");
		player.sendMessage(ChatColor.GRAY+"Try one of the following topics:");
		player.sendMessage(ChatColor.GREEN+"Hire, Stats, Inspect, Mission, Upgrade, Fire, Commands");
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_PURPLE+"For example, '"+ChatColor.LIGHT_PURPLE+"help commands"+ChatColor.DARK_PURPLE+"'");
		player.sendMessage("");
	}
	public static void hire(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GRAY+"===================["+ChatColor.GREEN+"Hire Help"+ChatColor.GRAY+"]===================");
		player.sendMessage("Hiring followers from the available followers brings them");
		player.sendMessage("under your employ for a certain price. They will stay");
		player.sendMessage("with you until dead or fired.");
		player.sendMessage(ChatColor.GRAY+"Available subcommands:"+ChatColor.GREEN+" List, Inspect, <# (1-3)>");
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_PURPLE+"For example, '"+ChatColor.LIGHT_PURPLE+"fo hire 3"+ChatColor.DARK_PURPLE+"'");
		player.sendMessage("");
	}
	public static void fire(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GRAY+"===================["+ChatColor.GREEN+"Fire Help"+ChatColor.GRAY+"]===================");
		player.sendMessage("Firing a follower releases them from your control. They");
		player.sendMessage("may return to the available follower pool or simply");
		player.sendMessage("retire themselves to obscurity.");
		player.sendMessage(ChatColor.GRAY+"Available subcommands:"+ChatColor.GREEN+" <# (1-3)>");
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_PURPLE+"For example, '"+ChatColor.LIGHT_PURPLE+"fo fire 3"+ChatColor.DARK_PURPLE+"'");
		player.sendMessage("");
	}
	public static void list(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GRAY+"===================["+ChatColor.GREEN+"List Help"+ChatColor.GRAY+"]===================");
		player.sendMessage("Lists a specified group of followers or choices.");
		player.sendMessage(ChatColor.GRAY+"Available subcommands:"+ChatColor.GREEN+" [None]");
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_PURPLE+"For example, '"+ChatColor.LIGHT_PURPLE+"fo hire list"+ChatColor.DARK_PURPLE+"'");
		player.sendMessage("");
	}
	public static void inspect(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GRAY+"===================["+ChatColor.GREEN+"Inspect Help"+ChatColor.GRAY+"]===================");
		player.sendMessage("Checking out a follower before hiring or sending them");
		player.sendMessage("on a mission is always a good idea. This allows you to");
		player.sendMessage("see their stats and equipment among several other things.");
		player.sendMessage(ChatColor.GRAY+"Available subcommands: "+ChatColor.GREEN+"[None]");
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_PURPLE+"For example, '"+ChatColor.LIGHT_PURPLE+"fo inspect 1"+ChatColor.DARK_PURPLE+"'");
		player.sendMessage("");
	}
	public static void stats(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GRAY+"===================["+ChatColor.GREEN+"Stats Help"+ChatColor.GRAY+"]===================");
		player.sendMessage("Statistic for followers effect several facets of missions");
		player.sendMessage("that may include ability at finding treasure or gold, the");
		player.sendMessage("chance of dying, or even the likelihood of death. At this");
		player.sendMessage("juncture, staff will not be divulging the exact effects of");
		player.sendMessage("each, though you are free to openly talk about them and");
		player.sendMessage("share your discoveries.");
		player.sendMessage("");
	}
	public static void mission(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GRAY+"===================["+ChatColor.GREEN+"Mission Help"+ChatColor.GRAY+"]===================");
		player.sendMessage("Missions are your followers' chance at earning their keep.");
		player.sendMessage("You may send them on a myriad of different adventures for");
		player.sendMessage("different rewards. Each type carries a chance of death.");
		player.sendMessage(ChatColor.GRAY+"Types: "+ChatColor.GREEN+"trade, harvest, build, explore, mine, spelunk");
		player.sendMessage(ChatColor.GREEN+"         hunt, quest, netherquest, enderquest");
		player.sendMessage(ChatColor.GRAY+"Subcommands:"+ChatColor.GREEN+" List, <type> <# (1-3)>");
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_PURPLE+"For example, '"+ChatColor.LIGHT_PURPLE+"fo mission trade 1"+ChatColor.DARK_PURPLE+"'");
		player.sendMessage("");
	}
	public static void upgrade(Player player){
		player.sendMessage("");
		player.sendMessage(ChatColor.GRAY+"===================["+ChatColor.GREEN+"Upgrade Help"+ChatColor.GRAY+"]===================");
		player.sendMessage("The best way to ensure success and survival is to");
		player.sendMessage("appropriately upgrade your followers' equipment for");
		player.sendMessage("a fee. They can also find equipment for free on missions.");
		player.sendMessage(ChatColor.GRAY+"Available subcommands: "+ChatColor.GREEN+"<Armor, Weapon");
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_PURPLE+"For example, '"+ChatColor.LIGHT_PURPLE+"fo upgrade 1 armor"+ChatColor.DARK_PURPLE+"'");
		player.sendMessage("");
	}
}
