package io.github.jisaacs1207.followers;

import java.util.HashMap;
import java.util.TreeMap;
import org.bukkit.event.Listener;


public class Modifiers implements Listener {
/* Modifier Explanations

	
================================================================================================	
Level Modifiers - Thanks Homidion 

public static int getChance(int playerLevel, int taskLevel)
{
    return Math.max(Math.min(100 + (playerLevel * 5) - ((taskLevel <= 10 ? taskLevel : 10 + (taskLevel-10)/5) * 15), 100), 0);
}
 ss
        [1] [2] [3] [4] [5] [6] [7] [8] [9] [10]    [15]    [20]    [25]   
[1]     90  75  60  45  30  15  0   0   0   0       0       0       0  
[2]     95  80  65  50  35  20  5   0   0   0       0       0       0  
[3]     100 85  70  55  40  25  10  0   0   0       0       0       0  
[4]     100 90  75  60  45  30  15  0   0   0       0       0       0  
[5]     100 95  80  65  50  35  20  5   0   0       0       0       0  
[6]     100 100 85  70  55  40  25  10  0   0       0       0       0  
[7]     100 100 90  75  60  45  30  15  0   0       0       0       0  
[8]     100 100 95  80  65  50  35  20  5   0       0       0       0  
[9]     100 100 100 85  70  55  40  25  10  0       0       0       0  
[10]    100 100 100 90  75  60  45  30  15  0       0       0       0  
[11]    100 100 100 95  80  65  50  35  20  5       0       0       0  
[12]    100 100 100 100 85  70  55  40  25  10      0       0       0  
[13]    100 100 100 100 90  75  60  45  30  15      0       0       0  
[14]    100 100 100 100 95  80  65  50  35  20      5       0       0  
[15]    100 100 100 100 100 85  70  55  40  25      10      0       0  
[16]    100 100 100 100 100 90  75  60  45  30      15      0       0  
[17]    100 100 100 100 100 95  80  65  50  35      20      5       0  
[18]    100 100 100 100 100 100 85  70  55  40      25      10      0  
[19]    100 100 100 100 100 100 90  75  60  45      30      15      0  
[20]    100 100 100 100 100 100 95  80  65  50      35      20      5
+5(itemlevel)
			// trading (0-1) - money 1-3
			// harvesting (2-3) - item 1-3
			// building (4-5) - money 4-6
			// exploring (6-7) - item 4-6
			// mining (8-9) - money 7-9
			// spelunking (10-11) - item 7-9
			// hunting (12-15) - money,item 10-13
			// questing (16-20) - item, money 14-16
			// netherquest (21-24) - item, money 17-19
			// enderquest (25) - item, money, voucher 20
================================================================================================

Gender

Female - Death Chance - 5% Success Chance - 5%
Male - Death Chance - +5% Success Chance + 5%
Neuter - Nothing

1-Female 
2-Female
3-Female
4-Female
5-Male
6-Male
7-Male
8-Male
9-Neuter

================================================================================================

Gene

1-Gigantic
2-Ugly 
3-Beautiful
4-Dwarf
5-Mutant
6-Athletic
7-Smart
8-Nimble
9-Overweight
10-Strong

================================================================================================

Class

1 - Fighter
2 - Mage
3 - Thief 
4 - Cleric
5 - Miner
6 - Idiot
7 - Technician
8 - Moderator
9 - Merchant
10 - Vagrant
=================================================================================================

Perk

1 - Pious
2 - Miser
3 - Magical
4 - Addict
5 - Alcoholic
6 - Charismatic
7 - Educated
8 - Shady
9 - Heroic
10 - Lucky
11 - Inquisitive
12 - Perceptive
13 - Musical
14 - Artistic
15 - Honest

=================================================================================================

Armor

1 - Defenseless
2 - Standard
3 - Masterpiece
4 - Blessed
5 - Legendary

=================================================================================================

Weapon

1 - Barehanded
2 - Standard
3 - Mastersword
4 - Arcane-Infused
5 - Legendary
	

=================================================================================================


*/	public static int getDeathChance(PlayerConfig pConfig, int followerNumber, int taskLevel){
		int finalChance=1;
		int playerLevel=Integer.valueOf(Methods.findOwnedStat(pConfig, followerNumber, "level"));
		int armor=Integer.valueOf(Methods.findOwnedStat(pConfig, followerNumber, "armor"));
		int baseChance=((taskLevel*10)-(playerLevel*8));
	    int armorChance=armor*5;
	    finalChance=baseChance-armorChance;
		return finalChance;
	}
	public static int getChance(PlayerConfig pConfig, int followerNumber, int taskLevel)
	{
		int finalChance=1;
		int playerLevel=Integer.valueOf(Methods.findOwnedStat(pConfig, followerNumber, "level"));
		int weapon=Integer.valueOf(Methods.findOwnedStat(pConfig, followerNumber, "weapon"));
		int baseChance=Math.max(Math.min(100 + (playerLevel * 5) -
				((taskLevel <= 10 ? taskLevel : 10 + (taskLevel-10)/5) * 15), 100), 0);
		int weaponChance=weapon*5; 
	    finalChance=weaponChance+baseChance;
		if(playerLevel>taskLevel) finalChance=finalChance+10*(playerLevel-taskLevel);
		if(playerLevel==taskLevel) finalChance=finalChance+10;
		if(finalChance>100) finalChance=100;
		return finalChance;
	}
	
	public static TreeMap<Integer, TranslatedStats> translateOwnedStats(PlayerConfig fConfig){
		TreeMap<Integer, TranslatedStats> fStats = new TreeMap<Integer, TranslatedStats>();
		TranslatedStats f1 = new TranslatedStats();
		TranslatedStats f2 = new TranslatedStats();
		TranslatedStats f3 = new TranslatedStats();

		//name
		f1.followerName=fConfig.follower1Name;
		f2.followerName=fConfig.follower2Name;
		f3.followerName=fConfig.follower3Name;
		
		//missionTimeLeft
		f1.followerMissionTimeLeft=fConfig.follower1MissionTimeLeft;
		f2.followerMissionTimeLeft=fConfig.follower2MissionTimeLeft;
		f3.followerMissionTimeLeft=fConfig.follower3MissionTimeLeft;
		
		//level		
		f1.followerLevel=fConfig.follower1Level;
		f2.followerLevel=fConfig.follower2Level;
		f3.followerLevel=fConfig.follower3Level;

		//gender
		if((fConfig.follower1Gender>0)&&(fConfig.follower1Gender<5))f1.followerGender="female";
		else if((fConfig.follower1Gender>4)&&(fConfig.follower1Gender<9))f1.followerGender="male";
		else f1.followerGender="neuter";
		if((fConfig.follower2Gender>0)&&(fConfig.follower2Gender<5))f2.followerGender="female";
		else if((fConfig.follower2Gender>4)&&(fConfig.follower2Gender<9))f2.followerGender="male";
		else f2.followerGender="neuter";
		if((fConfig.follower3Gender>0)&&(fConfig.follower3Gender<5))f3.followerGender="female";
		else if((fConfig.follower3Gender>4)&&(fConfig.follower3Gender<9))f3.followerGender="male";
		else f3.followerGender="neuter";
		//gene
		if(fConfig.follower1Gene==1) f1.followerGene="gigantic";
		else if(fConfig.follower1Gene==2) f1.followerGene="ugly";
		else if(fConfig.follower1Gene==3) f1.followerGene="beautiful";
		else if(fConfig.follower1Gene==4) f1.followerGene="dwarf";
		else if(fConfig.follower1Gene==5) f1.followerGene="mutant";
		else if(fConfig.follower1Gene==6) f1.followerGene="athletic";
		else if(fConfig.follower1Gene==7) f1.followerGene="smart";
		else if(fConfig.follower1Gene==8) f1.followerGene="nimble";
		else if(fConfig.follower1Gene==9) f1.followerGene="overweight";
		else f1.followerGene="strong";
		
		if(fConfig.follower2Gene==1) f2.followerGene="gigantic";
		else if(fConfig.follower2Gene==2) f2.followerGene="ugly";
		else if(fConfig.follower2Gene==3) f2.followerGene="beautiful";
		else if(fConfig.follower2Gene==4) f2.followerGene="dwarf";
		else if(fConfig.follower2Gene==5) f2.followerGene="mutant";
		else if(fConfig.follower2Gene==6) f2.followerGene="athletic";
		else if(fConfig.follower2Gene==7) f2.followerGene="smart";
		else if(fConfig.follower2Gene==8) f2.followerGene="nimble";
		else if(fConfig.follower2Gene==9) f2.followerGene="overweight";
		else f2.followerGene="strong";
		
		if(fConfig.follower3Gene==1) f3.followerGene="gigantic";
		else if(fConfig.follower3Gene==2) f3.followerGene="ugly";
		else if(fConfig.follower3Gene==3) f3.followerGene="beautiful";
		else if(fConfig.follower3Gene==4) f3.followerGene="dwarf";
		else if(fConfig.follower3Gene==5) f3.followerGene="mutant";
		else if(fConfig.follower3Gene==6) f3.followerGene="athletic";
		else if(fConfig.follower3Gene==7) f3.followerGene="smart";
		else if(fConfig.follower3Gene==8) f3.followerGene="nimble";
		else if(fConfig.follower3Gene==9) f3.followerGene="overweight";
		else f3.followerGene="strong";
		// class
		if(fConfig.follower1Class==1) f1.followerClass="fighter";
		else if(fConfig.follower1Class==2) f1.followerClass="mage";
		else if(fConfig.follower1Class==3) f1.followerClass="thief";
		else if(fConfig.follower1Class==4) f1.followerClass="cleric";
		else if(fConfig.follower1Class==5) f1.followerClass="miner";
		else if(fConfig.follower1Class==6) f1.followerClass="idiot";
		else if(fConfig.follower1Class==7) f1.followerClass="technician";
		else if(fConfig.follower1Class==8) f1.followerClass="moderator";
		else if(fConfig.follower1Class==9) f1.followerClass="merchant";
		else f1.followerClass="vagrant";

		if(fConfig.follower2Class==1) f2.followerClass="fighter";
		else if(fConfig.follower2Class==2) f2.followerClass="mage";
		else if(fConfig.follower2Class==3) f2.followerClass="thief";
		else if(fConfig.follower2Class==4) f2.followerClass="cleric";
		else if(fConfig.follower2Class==5) f2.followerClass="miner";
		else if(fConfig.follower2Class==6) f2.followerClass="idiot";
		else if(fConfig.follower2Class==7) f2.followerClass="technician";
		else if(fConfig.follower2Class==8) f2.followerClass="moderator";
		else if(fConfig.follower2Class==9) f2.followerClass="merchant";
		else f2.followerClass="vagrant";

		
		if(fConfig.follower3Class==1) f3.followerClass="fighter";
		else if(fConfig.follower3Class==2) f3.followerClass="mage";
		else if(fConfig.follower3Class==3) f3.followerClass="thief";
		else if(fConfig.follower3Class==4) f3.followerClass="cleric";
		else if(fConfig.follower3Class==5) f3.followerClass="miner";
		else if(fConfig.follower3Class==6) f3.followerClass="idiot";
		else if(fConfig.follower3Class==7) f3.followerClass="technician";
		else if(fConfig.follower3Class==8) f3.followerClass="moderator";
		else if(fConfig.follower3Class==9) f3.followerClass="merchant";
		else f3.followerClass="vagrant";

		// perk1
		if(fConfig.follower1Perk1==1) f1.followerPerk1="pious";
		else if(fConfig.follower1Perk1==2) f1.followerPerk1="miser";
		else if(fConfig.follower1Perk1==3) f1.followerPerk1="magical";
		else if(fConfig.follower1Perk1==4) f1.followerPerk1="addict";
		else if(fConfig.follower1Perk1==5) f1.followerPerk1="alcoholic";
		else if(fConfig.follower1Perk1==6) f1.followerPerk1="charismatic";
		else if(fConfig.follower1Perk1==7) f1.followerPerk1="educated";
		else if(fConfig.follower1Perk1==8) f1.followerPerk1="shady";
		else if(fConfig.follower1Perk1==9) f1.followerPerk1="heroic";
		else if(fConfig.follower1Perk1==10) f1.followerPerk1="lucky";
		else if(fConfig.follower1Perk1==11) f1.followerPerk1="inquisitive";
		else if(fConfig.follower1Perk1==12) f1.followerPerk1="perceptive";
		else if(fConfig.follower1Perk1==13) f1.followerPerk1="musical";
		else if(fConfig.follower1Perk1==14) f1.followerPerk1="artistic";
		else f1.followerPerk1="honest";
		
		if(fConfig.follower2Perk1==1) f2.followerPerk1="pious";
		else if(fConfig.follower2Perk1==2) f2.followerPerk1="miser";
		else if(fConfig.follower2Perk1==3) f2.followerPerk1="magical";
		else if(fConfig.follower2Perk1==4) f2.followerPerk1="addict";
		else if(fConfig.follower2Perk1==5) f2.followerPerk1="alcoholic";
		else if(fConfig.follower2Perk1==6) f2.followerPerk1="charismatic";
		else if(fConfig.follower2Perk1==7) f2.followerPerk1="educated";
		else if(fConfig.follower2Perk1==8) f2.followerPerk1="shady";
		else if(fConfig.follower2Perk1==9) f2.followerPerk1="heroic";
		else if(fConfig.follower2Perk1==10) f2.followerPerk1="lucky";
		else if(fConfig.follower2Perk1==11) f2.followerPerk1="inquisitive";
		else if(fConfig.follower2Perk1==12) f2.followerPerk1="perceptive";
		else if(fConfig.follower2Perk1==13) f2.followerPerk1="musical";
		else if(fConfig.follower2Perk1==14) f2.followerPerk1="artistic";
		else f2.followerPerk1="honest";
		
		if(fConfig.follower3Perk1==1) f3.followerPerk1="pious";
		else if(fConfig.follower3Perk1==2) f3.followerPerk1="miser";
		else if(fConfig.follower3Perk1==3) f3.followerPerk1="magical";
		else if(fConfig.follower3Perk1==4) f3.followerPerk1="addict";
		else if(fConfig.follower3Perk1==5) f3.followerPerk1="alcoholic";
		else if(fConfig.follower3Perk1==6) f3.followerPerk1="charismatic";
		else if(fConfig.follower3Perk1==7) f3.followerPerk1="educated";
		else if(fConfig.follower3Perk1==8) f3.followerPerk1="shady";
		else if(fConfig.follower3Perk1==9) f3.followerPerk1="heroic";
		else if(fConfig.follower3Perk1==10) f3.followerPerk1="lucky";
		else if(fConfig.follower3Perk1==11) f3.followerPerk1="inquisitive";
		else if(fConfig.follower3Perk1==12) f3.followerPerk1="perceptive";
		else if(fConfig.follower3Perk1==13) f3.followerPerk1="musical";
		else if(fConfig.follower3Perk1==14) f3.followerPerk1="artistic";
		else f3.followerPerk1="honest";
		// perk2
		if(fConfig.follower1Perk2==1) f1.followerPerk2="pious";
		else if(fConfig.follower1Perk2==2) f1.followerPerk2="miser";
		else if(fConfig.follower1Perk2==3) f1.followerPerk2="magical";
		else if(fConfig.follower1Perk2==4) f1.followerPerk2="addict";
		else if(fConfig.follower1Perk2==5) f1.followerPerk2="alcoholic";
		else if(fConfig.follower1Perk2==6) f1.followerPerk2="charismatic";
		else if(fConfig.follower1Perk2==7) f1.followerPerk2="educated";
		else if(fConfig.follower1Perk2==8) f1.followerPerk2="shady";
		else if(fConfig.follower1Perk2==9) f1.followerPerk2="heroic";
		else if(fConfig.follower1Perk2==10) f1.followerPerk2="lucky";
		else if(fConfig.follower1Perk2==11) f1.followerPerk2="inquisitive";
		else if(fConfig.follower1Perk2==12) f1.followerPerk2="perceptive";
		else if(fConfig.follower1Perk2==13) f1.followerPerk2="musical";
		else if(fConfig.follower1Perk2==14) f1.followerPerk2="artistic";
		else f1.followerPerk2="honest";
		
		if(fConfig.follower2Perk2==1) f2.followerPerk2="pious";
		else if(fConfig.follower2Perk2==2) f2.followerPerk2="miser";
		else if(fConfig.follower2Perk2==3) f2.followerPerk2="magical";
		else if(fConfig.follower2Perk2==4) f2.followerPerk2="addict";
		else if(fConfig.follower2Perk2==5) f2.followerPerk2="alcoholic";
		else if(fConfig.follower2Perk2==6) f2.followerPerk2="charismatic";
		else if(fConfig.follower2Perk2==7) f2.followerPerk2="educated";
		else if(fConfig.follower2Perk2==8) f2.followerPerk2="shady";
		else if(fConfig.follower2Perk2==9) f2.followerPerk2="heroic";
		else if(fConfig.follower2Perk2==10) f2.followerPerk2="lucky";
		else if(fConfig.follower2Perk2==11) f2.followerPerk2="inquisitive";
		else if(fConfig.follower2Perk2==12) f2.followerPerk2="perceptive";
		else if(fConfig.follower2Perk2==13) f2.followerPerk2="musical";
		else if(fConfig.follower2Perk2==14) f2.followerPerk2="artistic";
		else f2.followerPerk2="honest";
		
		if(fConfig.follower3Perk2==1) f3.followerPerk2="pious";
		else if(fConfig.follower3Perk2==2) f3.followerPerk2="miser";
		else if(fConfig.follower3Perk2==3) f3.followerPerk2="magical";
		else if(fConfig.follower3Perk2==4) f3.followerPerk2="addict";
		else if(fConfig.follower3Perk2==5) f3.followerPerk2="alcoholic";
		else if(fConfig.follower3Perk2==6) f3.followerPerk2="charismatic";
		else if(fConfig.follower3Perk2==7) f3.followerPerk2="educated";
		else if(fConfig.follower3Perk2==8) f3.followerPerk2="shady";
		else if(fConfig.follower3Perk2==9) f3.followerPerk2="heroic";
		else if(fConfig.follower3Perk2==10) f3.followerPerk2="lucky";
		else if(fConfig.follower3Perk2==11) f3.followerPerk2="inquisitive";
		else if(fConfig.follower3Perk2==12) f3.followerPerk2="perceptive";
		else if(fConfig.follower3Perk2==13) f3.followerPerk2="musical";
		else if(fConfig.follower3Perk2==14) f3.followerPerk2="artistic";
		else f3.followerPerk2="honest";
		// armor
		if(fConfig.follower1Armor==1) f1.followerArmor="defenseless";
		else if(fConfig.follower1Armor==2) f1.followerArmor="standard";
		else if(fConfig.follower1Armor==3) f1.followerArmor="masterpiece";
		else if(fConfig.follower1Armor==4) f1.followerArmor="blessed";
		else f1.followerArmor="legendary";
		
		if(fConfig.follower2Armor==1) f2.followerArmor="defenseless";
		else if(fConfig.follower2Armor==2) f2.followerArmor="standard";
		else if(fConfig.follower2Armor==3) f2.followerArmor="masterpiece";
		else if(fConfig.follower2Armor==4) f2.followerArmor="blessed";
		else f2.followerArmor="legendary";
		
		if(fConfig.follower3Armor==1) f3.followerArmor="defenseless";
		else if(fConfig.follower3Armor==2) f3.followerArmor="standard";
		else if(fConfig.follower3Armor==3) f3.followerArmor="masterpiece";
		else if(fConfig.follower3Armor==4) f3.followerArmor="blessed";
		else f3.followerArmor="legendary";
		
		// weapon
		if(fConfig.follower1Weapon==1) f1.followerWeapon="barehanded";
		else if(fConfig.follower1Weapon==2) f1.followerWeapon="standard";
		else if(fConfig.follower1Weapon==3) f1.followerWeapon="mastersword";
		else if(fConfig.follower1Weapon==4) f1.followerWeapon="arcane-infused";
		else f1.followerWeapon="legendary";
		
		if(fConfig.follower2Weapon==1) f2.followerWeapon="barehanded";
		else if(fConfig.follower2Weapon==2) f2.followerWeapon="standard";
		else if(fConfig.follower2Weapon==3) f2.followerWeapon="mastersword";
		else if(fConfig.follower2Weapon==4) f2.followerWeapon="arcane-infused";
		else f2.followerWeapon="legendary";
		
		if(fConfig.follower3Weapon==1) f3.followerWeapon="barehanded";
		else if(fConfig.follower3Weapon==2) f3.followerWeapon="standard";
		else if(fConfig.follower3Weapon==3) f3.followerWeapon="mastersword";
		else if(fConfig.follower3Weapon==4) f3.followerWeapon="arcane-infused";
		else f3.followerWeapon="legendary";
		
		fStats.put(1, f1);
		fStats.put(2, f2);
		fStats.put(3, f3);
		return fStats;
	}
	
	public static HashMap<String, String> translateStats(AvailableFollowers fConfig){
		HashMap<String, String> fStats = new HashMap<String, String>();
		//name
		fStats.put("name", fConfig.follower1Name);
		//level
		fStats.put("level", String.valueOf(fConfig.follower1Level));
		//gender
		if((fConfig.follower1Gender>0)&&(fConfig.follower1Gender<5))fStats.put("gender", "female");
		else if((fConfig.follower1Gender>4)&&(fConfig.follower1Gender<9))fStats.put("gender", "male");
		else fStats.put("gender", "neuter");
		//gene
		if(fConfig.follower1Gene==1) fStats.put("gene", "gigantic");
		else if(fConfig.follower1Gene==2) fStats.put("gene", "ugly");
		else if(fConfig.follower1Gene==3) fStats.put("gene", "beautiful");
		else if(fConfig.follower1Gene==4) fStats.put("gene", "dwarf");
		else if(fConfig.follower1Gene==5) fStats.put("gene", "mutant");
		else if(fConfig.follower1Gene==6) fStats.put("gene", "athletic");
		else if(fConfig.follower1Gene==7) fStats.put("gene", "smart");
		else if(fConfig.follower1Gene==8) fStats.put("gene", "nimble");
		else if(fConfig.follower1Gene==9) fStats.put("gene", "overweight");
		else fStats.put("gene", "strong");
		// class
		if(fConfig.follower1Class==1) fStats.put("class", "fighter");
		else if(fConfig.follower1Class==2) fStats.put("class", "mage");
		else if(fConfig.follower1Class==3) fStats.put("class", "thief");
		else if(fConfig.follower1Class==4) fStats.put("class", "cleric");
		else if(fConfig.follower1Class==5) fStats.put("class", "miner");
		else if(fConfig.follower1Class==6) fStats.put("class", "idiot");
		else if(fConfig.follower1Class==7) fStats.put("class", "technician");
		else if(fConfig.follower1Class==8) fStats.put("class", "moderator");
		else if(fConfig.follower1Class==9) fStats.put("class", "merchant");
		else fStats.put("class", "vagrant");
		// perk1
		if(fConfig.follower1Perk1==1) fStats.put("perk1", "pious");
		else if(fConfig.follower1Perk1==2) fStats.put("perk1", "miser");
		else if(fConfig.follower1Perk1==3) fStats.put("perk1", "magical");
		else if(fConfig.follower1Perk1==4) fStats.put("perk1", "addict");
		else if(fConfig.follower1Perk1==5) fStats.put("perk1", "alcoholic");
		else if(fConfig.follower1Perk1==6) fStats.put("perk1", "charismatic");
		else if(fConfig.follower1Perk1==7) fStats.put("perk1", "educated");
		else if(fConfig.follower1Perk1==8) fStats.put("perk1", "shady");
		else if(fConfig.follower1Perk1==9) fStats.put("perk1", "heroic");
		else if(fConfig.follower1Perk1==10) fStats.put("perk1", "lucky");
		else if(fConfig.follower1Perk1==11) fStats.put("perk1", "inquisitive");
		else if(fConfig.follower1Perk1==12) fStats.put("perk1", "perceptive");
		else if(fConfig.follower1Perk1==13) fStats.put("perk1", "musical");
		else if(fConfig.follower1Perk1==14) fStats.put("perk1", "artistic");
		else fStats.put("perk1", "honest");
		// perk2
		if(fConfig.follower1Perk2==1) fStats.put("perk2", "pious");
		else if(fConfig.follower1Perk2==2) fStats.put("perk2", "miser");
		else if(fConfig.follower1Perk2==3) fStats.put("perk2", "magical");
		else if(fConfig.follower1Perk2==4) fStats.put("perk2", "addict");
		else if(fConfig.follower1Perk2==5) fStats.put("perk2", "alcoholic");
		else if(fConfig.follower1Perk2==6) fStats.put("perk2", "charismatic");
		else if(fConfig.follower1Perk2==7) fStats.put("perk2", "educated");
		else if(fConfig.follower1Perk2==8) fStats.put("perk2", "shady");
		else if(fConfig.follower1Perk2==9) fStats.put("perk2", "heroic");
		else if(fConfig.follower1Perk2==10) fStats.put("perk2", "lucky");
		else if(fConfig.follower1Perk2==11) fStats.put("perk2", "inquisitive");
		else if(fConfig.follower1Perk2==12) fStats.put("perk2", "perceptive");
		else if(fConfig.follower1Perk2==13) fStats.put("perk2", "musical");
		else if(fConfig.follower1Perk2==14) fStats.put("perk2", "artistic");
		else fStats.put("perk2", "honest");
		// armor
		if(fConfig.follower1Armor==1) fStats.put("armor", "defenseless");
		else if(fConfig.follower1Armor==2) fStats.put("armor", "standard");
		else if(fConfig.follower1Armor==3) fStats.put("armor", "masterpiece");
		else if(fConfig.follower1Armor==4) fStats.put("armor", "blessed");
		else fStats.put("armor", "legendary");
		// weapon
		if(fConfig.follower1Weapon==1) fStats.put("weapon", "barehanded");
		else if(fConfig.follower1Weapon==2) fStats.put("weapon", "standard");
		else if(fConfig.follower1Weapon==3) fStats.put("weapon", "mastersword");
		else if(fConfig.follower1Weapon==4) fStats.put("weapon", "arcane-infused");
		else fStats.put("weapon", "legendary");
		
		return fStats;
	}
	
	public static int findFollowerPrice(AvailableFollowers fConfig){
		int levelPrice;
		int genderPrice=0;
		int weaponPrice;
		int armorPrice;
		int totalPrice;
		levelPrice=((fConfig.follower1Level*fConfig.follower1Level)*1000)+(fConfig.follower1Level*10000)-30000;
		if(levelPrice<10000) levelPrice=10000;
		if(levelPrice>200000) levelPrice=200000;
		if(fConfig.follower1Gender==9) genderPrice=10000;
		weaponPrice=fConfig.follower1Weapon*10000;
		armorPrice=fConfig.follower1Armor*15000;

		totalPrice=levelPrice+genderPrice+weaponPrice+armorPrice;
		return totalPrice;
	}
	
	public static String translateMission(int missionInt){
		String missionTitle;
		if((missionInt==1)||(missionInt==2)) missionTitle="trading";
		else if((missionInt==3)||(missionInt==4)) missionTitle="harvesting";
		else if((missionInt==5)||(missionInt==6)) missionTitle="building";	
		else if((missionInt==7)||(missionInt==8)) missionTitle="exploring";
		else if((missionInt==9)||(missionInt==10)) missionTitle="mining";
		else if((missionInt==10)&&(missionInt==11)) missionTitle="spelunking";
		else if((missionInt>=12)&&(missionInt<=15)) missionTitle="hunting";
		else if((missionInt>=16)&&(missionInt<=20)) missionTitle="questing";
		else if((missionInt>=21)&&(missionInt<=24)) missionTitle="netherquesting";
		else missionTitle="enderquesting";
		return missionTitle;
	}
	
	public static int reverseTranslateMission(String missionTitle){
		int missionInt=0;
		if((missionInt==1)||(missionInt==2)) missionTitle="trading";
		else if((missionInt==3)||(missionInt==4)) missionTitle="harvesting";
		else if((missionInt==5)||(missionInt==6)) missionTitle="building";	
		else if((missionInt==7)||(missionInt==8)) missionTitle="exploring";
		else if((missionInt==9)||(missionInt==10)) missionTitle="mining";
		else if((missionInt==10)&&(missionInt==11)) missionTitle="spelunking";
		else if((missionInt>=12)&&(missionInt<=15)) missionTitle="hunting";
		else if((missionInt>=16)&&(missionInt<=20)) missionTitle="questing";
		else if((missionInt>=21)&&(missionInt<=24)) missionTitle="netherquesting";
		else missionTitle="enderquesting";
		return missionInt;
	}
}
