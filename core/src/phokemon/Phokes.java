package phokemon;

import java.util.ArrayList;

public class Phokes extends Character {

	//public String phokeType;
	public boolean burned, paralyzed;
	private PhokeType phokeType;
	
	public Phokes(String name, PhokeType phokeType, int health, int attack, int defense, int speed,  AttackMove... moves){
		this.phokeType = phokeType;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.name = name;
		paralyzed = false;
		burned = false;
		move1 = new ArrayList<AttackMove>();
		for(int i=0; i<moves.length; i++)
			move1.add(moves[i]);
		
	}
	public PhokeType getPhokeType() {
		return phokeType;
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(int health){
		this.health = health;
	}
	
	public String getName(){
		return name;
	}
}