package phokemon;

import com.apjava.phokemon.screens.BattleScreen;

public abstract class AttackMove {
	protected int power, accuracy;
	protected PhokeType type;
	protected String name;
	/**
	 * 
	 * @param power
	 * @param accuracy
	 * @param type
	 * @param name of move
	 */
	public AttackMove(int power, int accuracy, PhokeType type, String name) {
		this.power = power;
		this.accuracy = accuracy;
		this.type = type;
		this.name = name;
	}
	
	/**
	 * @param battleScreen references battle screen to play sound
	 * @param opponent phokemon being attacked null if status update
	 * @return true if attack is successful
	 */
	public boolean attack(BattleScreen battleScreen, Phokes opponent) {
		int random = (int) (Math.random()*100);
		if(random<= accuracy) {
			doDamage(battleScreen, opponent);
			return true;
		}
		return false;
	}
	/**
	 * subtracts damage of an attack from opponent's hp
	 * @param battleScreen used to play sound
	 * @param opponent
	 */
	public void doDamage(BattleScreen battleScreen, Phokes opponent){
		int damage = calculateDamage(battleScreen, opponent);
		opponent.setHealth(opponent.getHealth() - damage);
		System.out.println(opponent.getHealth()+" damage: "+opponent);
		System.out.println(name + " was used against " + opponent.getName());
		
	}
	
	/**
	 * Calculate the damge of an attack move
	 * @return damage
	 * @param battleScreen used to play audio
	 * @param opponent
	*/
	public int calculateDamage(BattleScreen battleScreen, Phokes opponent) {
		System.out.println(type.toString()+" against "+opponent.getPhokeType().toString());
		double multiplyer = 1.0;
		if(opponent.getPhokeType().isSuperEffective(type)) {
			multiplyer = 2.0;
			System.out.println("Super effective");
			if(battleScreen!=null)
				battleScreen.playSuperEffectiveSound();
		} else if(opponent.getPhokeType().isNotEffective(type)) {
			multiplyer = 0.5;
			System.out.println("Not Effective");
			if(battleScreen!=null)
				battleScreen.playNotEffectiveSound();
		}
		int damage = (int) (((22*power*((double)power/opponent.defense))/50+2)*multiplyer);
		
		return damage;
	}
	
	/**
	 * 
	 * @return the type of this move
	 */
	public PhokeType getPhokeType() {
		return type;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}