package com.apjava.phokemon.mechanics;


import java.util.List;

public abstract class Character {
	protected int health, attack, defense, speed;
	protected String name;
	//protected String status;
	protected List<AttackMove> move1;
	
	public void doDamage(int damage) {
		
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(int health){
		this.health = health;
	}
	
}