package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Character;
import phokemon.Normal;
import phokemon.StatBoost;

public class IronDefense extends AttackMove{
	public IronDefense(){
		super(0, 100, new StatBoost(), "Iron Defense");
	}
	public void doDamage(Character self){
		self.setDefense(self.getDefense()*2);
		System.out.println(self.getName() + "used Iron Defense");
	}
}
