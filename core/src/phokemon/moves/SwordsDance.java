package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Character;
import phokemon.Normal;

public class SwordsDance extends AttackMove{
	
	public SwordsDance(){
		super(1, 100, new Normal(), "Swords Dance");
	}
	
	public void doDamage(Character self){
		self.setAttack(self.getAttack()*2);
		System.out.println(self.getName() + "used Swords Dance");
	}
}
