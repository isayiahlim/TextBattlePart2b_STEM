/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the represents a Player object. It must contain all of the fields and
 * methods detailed in the project spec. You may add additional fields and methods if you
 * like.
 */
import java.util.ArrayList;
public class Player 
{
	//fields of the player
	private String name;
	private int health;
	private ArrayList<Item> inventory;
	private int maxHealth;
	private int minDmg;
	private int maxDmg;
	
	//constructor that initializes the player's fields to a default
	public Player(String name)
	{
		//changes the name to the given name
		this.name = name;
		
		//sets default statistics
		health = 100;
		maxHealth = 100;
		minDmg = 1;
		maxDmg = 10;
		
		//declares and fills the inventory
		inventory = new ArrayList<Item>();
		inventory.add(new HealthPotion("Lesser"));
		inventory.add(new HealthPotion("Lesser"));
		inventory.add(new StrengthPotion("Lesser"));
		inventory.add(new StrengthPotion("Lesser"));
		inventory.add(new ManaPotion("Lesser"));
	}
	
	//constructor that initializes the player's fields to a default
	public Player(String name, int health, int minDmg, int maxDmg)
	{
		//changes the stats to the provided stats
		this.name = name;
		if(health > 0)	
			this.health = health;
		else 
			this.health = 100;
		maxHealth = this.health;
		
		if(minDmg > 0)
			this.minDmg = minDmg;
		else
			this.minDmg = 1;
		
		if(maxDmg > this.minDmg)	
			this.maxDmg = maxDmg;
		else
			this.maxDmg = 10 + this.minDmg;
		
		//declares and fills the inventory
		inventory = new ArrayList<Item>();
		inventory.add(new HealthPotion("Lesser"));
		inventory.add(new HealthPotion("Lesser"));
		inventory.add(new StrengthPotion("Lesser"));
		inventory.add(new StrengthPotion("Lesser"));
		inventory.add(new ManaPotion("Lesser"));
	}
	
	//constructor that initializes the player's fields to a default
	public Player(String name, ArrayList<Item> inventory)
	{
		//changes the name to the given name
		this.name = name;
		
		//sets default statistics
		health = 100;
		maxHealth = 100;
		minDmg = 1;
		maxDmg = 10;
		
		//declares and fills the inventory
		if(inventory == null)
		{
			inventory = new ArrayList<Item>();
			inventory.add(new HealthPotion("Lesser"));
			inventory.add(new HealthPotion("Lesser"));
			inventory.add(new StrengthPotion("Lesser"));
			inventory.add(new StrengthPotion("Lesser"));
			inventory.add(new ManaPotion("Lesser"));
		}
		else 
		{
			this.inventory = inventory;
		}
	}
	
	//accessor methods to see all of the fields
	public String getName()
	{
		return name;
	}
	public int getHealth()
	{
		return health;
	}
	public int getMinDamage()
	{
		return minDmg;
	}
	public int getMaxDamage()
	{
		return maxDmg;
	}
	
	//attacks the monster
	public int attack(Monster monster)
	{
		int attackDmg = (int)((Math.random()*(maxDmg-minDmg) + minDmg));
		monster.takeDamage(attackDmg);
		return attackDmg;
	}
	
	//takes damage
	public void takeDamage(int damage)
	{
		if(health - damage > 0)
			health -= damage;
		else
			health = 0;
	}
	
	//uses an item
	public void useItem(int index)
	{
		if(index < 0 || index >= inventory.size() || inventory.get(index) == null)
			System.out.println("Invalid Selection - Missed Turn");
		else
		{
			inventory.get(index).use(this);
			inventory.remove(index);
		}
	}
	
	//adds an idem
	public void receiveItem(Item item)
	{
		inventory.add(item);
	}
	
	//heals the player
	public void healDamage(int hp)
	{
		if(health + hp < maxHealth)	
			health += hp;
		else
			health = maxHealth;
	}
	
	//increases the damage the player does
	public void getStronger(int dmg)
	{
		minDmg += dmg;
		maxDmg += dmg;
	}
	
	//accesses the inventory
	public String getInventory()
	{
		String returnString = "";
		for(int i = 0; i < inventory.size(); i++)
		{
			//makes sure that something exists at the inventory slot. If not, it prints out empty
			if(inventory.get(i) == null)
				returnString += (i+1) + ": empty slot ";
			else
				returnString += (i+1) + ": " + inventory.get(i) + " ";
		}
		if(inventory.size() == 0)
			return "No Items";
		return returnString;
	}
	
	//prints out the status of the player
	public String toString()
	{
		if(health > 0)
			return name + " has " + health + " health left.";
		return name + " is dead.";
	}

}