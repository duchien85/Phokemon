package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Character;
import phokemon.Normal;

public class Agility extends AttackMove {
	public Agility(){
		super(0, 100, new Normal(), "Agility");
	}
	public void doDamage(Character self){
		self.setSpeed(self.getSpeed()*2);
		System.out.println(self.getName() + " used Agility");
	}
}
