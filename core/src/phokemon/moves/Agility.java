package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Character;
import phokemon.Normal;
import phokemon.StatBoost;

/**
 * @version 5-22-17
 * @author Rishabh, Jacob
 *
 */
public class Agility extends AttackMove {
	public Agility(){
		super(0, 100, new StatBoost(), "Agility");
	}
	public void doDamage(Character self){
		self.setSpeed(self.getSpeed()*2);
		System.out.println(self.getName() + " used Agility");
	}
}
