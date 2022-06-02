/**
 * Name: Isayiah Lim
 * Last Updated On: 5/31/2022
 * Mrs. Kankelborg
 * APCS Period 2
 * Text Battle Project Part Two
 * 
 * This class is the represents a Mage object which is a Player. It must contain all of the fields and methods 
 * detailed in the project spec. You 
 * may add additional fields and methods if you like.
 */
public class Mage extends Player
{
	private int mana;
	private int maxMana;
	public Mage(String name, int mana) 
	{
		//creates a player with a mage's specifications
		super(name, 50, 5, 10);
		//gives it a default amount of mana
		if(mana <= 0)
			this.mana = 50;
		else
			this.mana = mana;
		maxMana = this.mana;
	}
	
	public int attack(Monster monster)
	{
		//reduces the mana amount after every attack by a random amount
		int cost = (int)(Math.random()*(maxMana/2) + 1);
		//if the player runs out of mana
		if(mana - cost < 0)
		{
			System.out.println("Not enough mana!");
			return 0;
		}
		else
		{
			mana -= cost;
			System.out.println(super.getName() + " has " + mana + " mana left.");
			return super.attack(monster);
		}
	}
	
	//adds mana back
	public void restoreMana(int restore)
	{
		if(mana + restore >= maxMana)
			mana = maxMana;
		else
			mana += restore;
	}
	
	public int getMana()
	{
		return mana;
	}
	
	//reduces the amount of mana
	public void useMana(int reduce)
	{
		if(mana - reduce > 0)
			mana -= reduce;
		else
			mana = 0;
	}

}

