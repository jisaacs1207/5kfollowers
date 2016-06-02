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


*/
	public static TreeMap<Integer, TranslatedStats> translateOwnedStats(PlayerConfig fConfig){
		TreeMap<Integer, TranslatedStats> fStats = new TreeMap<Integer, TranslatedStats>();
		TranslatedStats f1 = new TranslatedStats();
		TranslatedStats f2 = new TranslatedStats();
		TranslatedStats f3 = new TranslatedStats();

		//name
		f1.followerName=fConfig.follower1Name;
		f2.followerName=fConfig.follower2Name;
		f3.followerName=fConfig.follower3Name;

		//level		
		f1.followerLevel=fConfig.follower1Level;
		f2.followerLevel=fConfig.follower2Level;
		f3.followerLevel=fConfig.follower3Level;

		//gender
		if((fConfig.follower1Gender>0)&&(fConfig.follower1Gender<5))f1.followerGender="female";
		else if((fConfig.follower1Gender>4)&&(fConfig.follower1Gender<9))f1.followerGender="male";
		else f1.followerGender="neuter";
		if((fConfig.follower1Gender>0)&&(fConfig.follower1Gender<5))f2.followerGender="female";
		else if((fConfig.follower1Gender>4)&&(fConfig.follower1Gender<9))f2.followerGender="male";
		else f2.followerGender="neuter";
		if((fConfig.follower1Gender>0)&&(fConfig.follower1Gender<5))f3.followerGender="female";
		else if((fConfig.follower1Gender>4)&&(fConfig.follower1Gender<9))f3.followerGender="male";
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
		
		if(fConfig.follower1Gene==1) f2.followerGene="gigantic";
		else if(fConfig.follower1Gene==2) f2.followerGene="ugly";
		else if(fConfig.follower1Gene==3) f2.followerGene="beautiful";
		else if(fConfig.follower1Gene==4) f2.followerGene="dwarf";
		else if(fConfig.follower1Gene==5) f2.followerGene="mutant";
		else if(fConfig.follower1Gene==6) f2.followerGene="athletic";
		else if(fConfig.follower1Gene==7) f2.followerGene="smart";
		else if(fConfig.follower1Gene==8) f2.followerGene="nimble";
		else if(fConfig.follower1Gene==9) f2.followerGene="overweight";
		else f2.followerGene="strong";
		
		if(fConfig.follower1Gene==1) f3.followerGene="gigantic";
		else if(fConfig.follower1Gene==2) f3.followerGene="ugly";
		else if(fConfig.follower1Gene==3) f3.followerGene="beautiful";
		else if(fConfig.follower1Gene==4) f3.followerGene="dwarf";
		else if(fConfig.follower1Gene==5) f3.followerGene="mutant";
		else if(fConfig.follower1Gene==6) f3.followerGene="athletic";
		else if(fConfig.follower1Gene==7) f3.followerGene="smart";
		else if(fConfig.follower1Gene==8) f3.followerGene="nimble";
		else if(fConfig.follower1Gene==9) f3.followerGene="overweight";
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

		if(fConfig.follower1Class==1) f2.followerClass="fighter";
		else if(fConfig.follower1Class==2) f2.followerClass="mage";
		else if(fConfig.follower1Class==3) f2.followerClass="thief";
		else if(fConfig.follower1Class==4) f2.followerClass="cleric";
		else if(fConfig.follower1Class==5) f2.followerClass="miner";
		else if(fConfig.follower1Class==6) f2.followerClass="idiot";
		else if(fConfig.follower1Class==7) f2.followerClass="technician";
		else if(fConfig.follower1Class==8) f2.followerClass="moderator";
		else if(fConfig.follower1Class==9) f2.followerClass="merchant";
		else f2.followerClass="vagrant";

		
		if(fConfig.follower1Class==1) f3.followerClass="fighter";
		else if(fConfig.follower1Class==2) f3.followerClass="mage";
		else if(fConfig.follower1Class==3) f3.followerClass="thief";
		else if(fConfig.follower1Class==4) f3.followerClass="cleric";
		else if(fConfig.follower1Class==5) f3.followerClass="miner";
		else if(fConfig.follower1Class==6) f3.followerClass="idiot";
		else if(fConfig.follower1Class==7) f3.followerClass="technician";
		else if(fConfig.follower1Class==8) f3.followerClass="moderator";
		else if(fConfig.follower1Class==9) f3.followerClass="merchant";
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
		
		if(fConfig.follower1Perk1==1) f2.followerPerk1="pious";
		else if(fConfig.follower1Perk1==2) f2.followerPerk1="miser";
		else if(fConfig.follower1Perk1==3) f2.followerPerk1="magical";
		else if(fConfig.follower1Perk1==4) f2.followerPerk1="addict";
		else if(fConfig.follower1Perk1==5) f2.followerPerk1="alcoholic";
		else if(fConfig.follower1Perk1==6) f2.followerPerk1="charismatic";
		else if(fConfig.follower1Perk1==7) f2.followerPerk1="educated";
		else if(fConfig.follower1Perk1==8) f2.followerPerk1="shady";
		else if(fConfig.follower1Perk1==9) f2.followerPerk1="heroic";
		else if(fConfig.follower1Perk1==10) f2.followerPerk1="lucky";
		else if(fConfig.follower1Perk1==11) f2.followerPerk1="inquisitive";
		else if(fConfig.follower1Perk1==12) f2.followerPerk1="perceptive";
		else if(fConfig.follower1Perk1==13) f2.followerPerk1="musical";
		else if(fConfig.follower1Perk1==14) f2.followerPerk1="artistic";
		else f2.followerPerk1="honest";
		
		if(fConfig.follower1Perk1==1) f3.followerPerk1="pious";
		else if(fConfig.follower1Perk1==2) f3.followerPerk1="miser";
		else if(fConfig.follower1Perk1==3) f3.followerPerk1="magical";
		else if(fConfig.follower1Perk1==4) f3.followerPerk1="addict";
		else if(fConfig.follower1Perk1==5) f3.followerPerk1="alcoholic";
		else if(fConfig.follower1Perk1==6) f3.followerPerk1="charismatic";
		else if(fConfig.follower1Perk1==7) f3.followerPerk1="educated";
		else if(fConfig.follower1Perk1==8) f3.followerPerk1="shady";
		else if(fConfig.follower1Perk1==9) f3.followerPerk1="heroic";
		else if(fConfig.follower1Perk1==10) f3.followerPerk1="lucky";
		else if(fConfig.follower1Perk1==11) f3.followerPerk1="inquisitive";
		else if(fConfig.follower1Perk1==12) f3.followerPerk1="perceptive";
		else if(fConfig.follower1Perk1==13) f3.followerPerk1="musical";
		else if(fConfig.follower1Perk1==14) f3.followerPerk1="artistic";
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
		
		if(fConfig.follower1Perk2==1) f2.followerPerk2="pious";
		else if(fConfig.follower1Perk2==2) f2.followerPerk2="miser";
		else if(fConfig.follower1Perk2==3) f2.followerPerk2="magical";
		else if(fConfig.follower1Perk2==4) f2.followerPerk2="addict";
		else if(fConfig.follower1Perk2==5) f2.followerPerk2="alcoholic";
		else if(fConfig.follower1Perk2==6) f2.followerPerk2="charismatic";
		else if(fConfig.follower1Perk2==7) f2.followerPerk2="educated";
		else if(fConfig.follower1Perk2==8) f2.followerPerk2="shady";
		else if(fConfig.follower1Perk2==9) f2.followerPerk2="heroic";
		else if(fConfig.follower1Perk2==10) f2.followerPerk2="lucky";
		else if(fConfig.follower1Perk2==11) f2.followerPerk2="inquisitive";
		else if(fConfig.follower1Perk2==12) f2.followerPerk2="perceptive";
		else if(fConfig.follower1Perk2==13) f2.followerPerk2="musical";
		else if(fConfig.follower1Perk2==14) f2.followerPerk2="artistic";
		else f2.followerPerk2="honest";
		
		if(fConfig.follower1Perk2==1) f3.followerPerk2="pious";
		else if(fConfig.follower1Perk2==2) f3.followerPerk2="miser";
		else if(fConfig.follower1Perk2==3) f3.followerPerk2="magical";
		else if(fConfig.follower1Perk2==4) f3.followerPerk2="addict";
		else if(fConfig.follower1Perk2==5) f3.followerPerk2="alcoholic";
		else if(fConfig.follower1Perk2==6) f3.followerPerk2="charismatic";
		else if(fConfig.follower1Perk2==7) f3.followerPerk2="educated";
		else if(fConfig.follower1Perk2==8) f3.followerPerk2="shady";
		else if(fConfig.follower1Perk2==9) f3.followerPerk2="heroic";
		else if(fConfig.follower1Perk2==10) f3.followerPerk2="lucky";
		else if(fConfig.follower1Perk2==11) f3.followerPerk2="inquisitive";
		else if(fConfig.follower1Perk2==12) f3.followerPerk2="perceptive";
		else if(fConfig.follower1Perk2==13) f3.followerPerk2="musical";
		else if(fConfig.follower1Perk2==14) f3.followerPerk2="artistic";
		else f3.followerPerk2="honest";
		// armor
		if(fConfig.follower1Armor==1) f1.followerArmor="defenseless";
		else if(fConfig.follower1Armor==2) f1.followerArmor="standard";
		else if(fConfig.follower1Armor==3) f1.followerArmor="masterpiece";
		else if(fConfig.follower1Armor==4) f1.followerArmor="blessed";
		else f1.followerArmor="legendary";
		
		if(fConfig.follower1Armor==1) f2.followerArmor="defenseless";
		else if(fConfig.follower1Armor==2) f2.followerArmor="standard";
		else if(fConfig.follower1Armor==3) f2.followerArmor="masterpiece";
		else if(fConfig.follower1Armor==4) f2.followerArmor="blessed";
		else f2.followerArmor="legendary";
		
		if(fConfig.follower1Armor==1) f3.followerArmor="defenseless";
		else if(fConfig.follower1Armor==2) f3.followerArmor="standard";
		else if(fConfig.follower1Armor==3) f3.followerArmor="masterpiece";
		else if(fConfig.follower1Armor==4) f3.followerArmor="blessed";
		else f3.followerArmor="legendary";
		
		// weapon
		if(fConfig.follower1Weapon==1) f1.followerWeapon="barehanded";
		else if(fConfig.follower1Weapon==2) f1.followerWeapon="standard";
		else if(fConfig.follower1Weapon==3) f1.followerWeapon="mastersword";
		else if(fConfig.follower1Weapon==4) f1.followerWeapon="arcane-infused";
		else f1.followerWeapon="legendary";
		
		if(fConfig.follower1Weapon==1) f2.followerWeapon="barehanded";
		else if(fConfig.follower1Weapon==2) f2.followerWeapon="standard";
		else if(fConfig.follower1Weapon==3) f2.followerWeapon="mastersword";
		else if(fConfig.follower1Weapon==4) f2.followerWeapon="arcane-infused";
		else f2.followerWeapon="legendary";
		
		if(fConfig.follower1Weapon==1) f3.followerWeapon="barehanded";
		else if(fConfig.follower1Weapon==2) f3.followerWeapon="standard";
		else if(fConfig.follower1Weapon==3) f3.followerWeapon="mastersword";
		else if(fConfig.follower1Weapon==4) f3.followerWeapon="arcane-infused";
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
}
