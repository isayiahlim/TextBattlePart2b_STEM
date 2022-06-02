/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the application class. Your main method must meet all the requirements in the 
 * specification on turn in but is otherwise for your testing purposes only. My test code will call
 * your startBattle method directly with my own version of the code you will write in main. 
 * 
 */
import java.util.Scanner;
public class Battle {
	/**
	 * Use this method for playing your game. I will bypass this in my test code, but I will
	 * be looking at its contents when I grade for internal correctness.
	 */
	public static void main(String[] args) 
	{
		//sets the name for the player
		Scanner input = new Scanner(System.in);
		System.out.print("What is your name? ");
		String name = input.nextLine();
		Player player;
		
		//variables determining whether the game continues
		Boolean quit = false;
		Boolean alive = true;
		
		//sets the class of the player
		System.out.print("Choose your class (Rogue, Warrior, or Mage) ");
		String className = input.next();
		//makes sure it is valid
		while (!className.equals("Rogue") && !className.equals("Warrior") && 
				!className.equals("Mage"))
		{
			System.out.print("Choose a valid class (Rogue, Warrior, or Mage) ");
			className = input.next();
		}
		if(className.equals("Rogue"))
			player = new Rogue(name + " the Rogue", 50);
		else if (className.equals("Warrior"))
			player = new Warrior(name + " the Warrior", 50);
		else 
			player = new Mage(name + " the Mage", 50);
		System.out.println();
		
		//chooses the monster from an array of monster
		String[] monsterList = {"Mr. Lesli", "CollegeBoard", "Tonald J. Dump", "This Project"};
		//starts the game and runs until player dies or they choose to quit
		while(!quit && alive)
		{
			//chooses a random monster to fight
			Monster monster = new Monster(monsterList[(int)(Math.random()*monsterList.length)]);
			//plays game, sees if player wins
			alive = startBattle(player, monster, input);
			if(alive)
			{
				//asks if they want to quit
				System.out.println("Continue?");
				String response = input.next().toUpperCase();
				if(response.substring(0,1).equals("N"))	
					quit = true;
			}	
		}
	}
	//gives additional loot at the end of a round
	public static Item getLoot(Player player)
	{
		//randomizes the rarity and potion chosen
		double rarityChance = Math.random();
		double potionChance = Math.random();
		String ptype;
		//determines rarity
		if(rarityChance < 0.05)
			ptype = "Epic";
		else if(rarityChance < 0.15)
			ptype = "Greater";
		else if(rarityChance < 0.35)
			ptype = "Basic";
		else
			ptype = "Lesser";
		//returns a random potion of given rarity
		if (player instanceof Mage)
		{
			if (potionChance < 0.2)
				return new ManaPotion(ptype);
			else if (potionChance < 0.4)
				return new StrengthPotion(ptype);
			else
				return new HealthPotion(ptype);
		}
		else 
		{
			if(potionChance < 0.2)
				return new StrengthPotion(ptype);
			else
				return new HealthPotion(ptype);
		}
	}
	 /**
	  * This is the method that my test cases will call directly.
	  * Do not modify the header of this method.
	  * @param player
	  * @param monster
	  */
	public static boolean startBattle(Player player, Monster monster, Scanner input) 
	{
		//intro message
		System.out.println(player.getName() + " has encountered a " + monster.getType() + "!");
		
		//variables
		int roundNum = 1;
		
		//runs until either the player or monster dies
		while(player.getHealth() > 0 && monster.getHealth() > 0)
		{
			System.out.println();
			System.out.println("********************************** ROUND " + roundNum +
					" **********************************");
			System.out.println();
			System.out.println("Your inventory holds: " + player.getInventory());
			//choose between attacking and using an item
			System.out.print("Type an inventory slot number or 0 to attack: ");
			int response = input.nextInt();
			System.out.println();
			
			//player's move
			if(response == 0)
			{
				int damage = player.attack(monster);
				if (damage > 0)
				{
					System.out.println(player.getName() + " attacks the " + monster.getType() + 
							" for " + damage + " damage.");
					System.out.println(monster);
				}
			}
			else
				player.useItem(response-1);
			System.out.println();
			//monster fights back
			if(monster.getHealth() > 0)
			{
				System.out.println("The " + monster.getType() + " attacks " + player.getName() + " for " 
						+ monster.attack(player) + " damage.");
				System.out.println(player);
				System.out.println();
			}
			roundNum ++;
		}
		
		//end message
		if(monster.getHealth() > 0)
		{
			System.out.println("The " + monster.getType() + " has defeated " + player.getName());
			return false;
		}
		else
		{
			System.out.println(player.getName() + " has defeated the " + monster.getType());
			// heal the player
			int healnum = (int)(Math.random()*20) + 1;
			System.out.println(player.getName() + " has been rewarded with " + healnum 
					+ " points of health back.");
			player.healDamage(healnum);
			//gives random loot
			Item loot = getLoot(player);
			player.receiveItem(loot);
			System.out.println(player.getName() + " has been rewarded with a " + loot);
			return true;
		}
	}		
}
