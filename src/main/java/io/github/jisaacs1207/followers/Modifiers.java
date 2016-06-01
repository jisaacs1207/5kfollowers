package io.github.jisaacs1207.followers;

import java.util.HashMap;

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
