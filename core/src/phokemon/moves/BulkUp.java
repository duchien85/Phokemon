package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Character;
import phokemon.Normal;

public class BulkUp extends AttackMove{
	public BulkUp(){
		super(0, 100, new Normal(), "Bulk Up");
	}
	public void doDamage(Character self){
		self.setDefense((int)(self.getDefense()*1.5));
		self.setAttack((int)(self.getAttack()*1.5));
		System.out.println(self.getName() + " used Bulk Up");
	}
}
