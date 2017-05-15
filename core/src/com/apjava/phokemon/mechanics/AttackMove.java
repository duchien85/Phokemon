package com.apjava.phokemon.mechanics;

public abstract class AttackMove {
	protected int power, accuracy;
	protected PhokeType type;
	
	public AttackMove(int power, int accuracy, PhokeType type) {
		this.power = power;
		this.accuracy = accuracy;
		this.type = type;
	}
	
	/**
	 * 
	 * @param self phokemon making the move
	 * @param opponent phokemon being attacked null if status update
	 * @return true if attack is successful
	 */
	public boolean attack(Phokes self, Phokes opponent) {
		if(Math.random()*100 <= accuracy) {
			
			int damage = calculateDamage(opponent);
			opponent.doDamage(damage);
		}
		return false;
	}
	public void doDamage(Phokes opponent){
		if(Math.random()*100 <= accuracy) {
		
		int damage = calculateDamage(opponent);
		opponent.setHealth(opponent.getHealth() - damage);
	}
}
	
	/**
	 * Calculate the damge of an attack move
	 * @return damage
	 * @param opponent
	*/
	public int calculateDamage(Phokes opponent) {
		System.out.println(type.toString()+" against "+opponent.getPhokeType().toString());
		double multiplyer = 1.0;
		if(opponent.getPhokeType().isSuperEffective(type)) {
			multiplyer = 2.0;
			System.out.println("Super effective");
		} else if(opponent.getPhokeType().isNotEffective(type))
			multiplyer = 0.5;
		int damage = (int) (((22*power*((double)power/opponent.defense))/50+2)*multiplyer);
		
		return damage;
	}
	
}