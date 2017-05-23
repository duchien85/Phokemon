package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Phokes;
import phokemon.Water;

public class DoubleSplash extends AttackMove{
	
	public DoubleSplash(){
		super(75, 50, new Water(), "Double Splash");
	}
	
	public void doDamage(Phokes opponent){
		int damage = 0;
		for (int i = 0; i < 2; i ++){
			if(Math.random()*100 <= accuracy) {
				System.out.println("blastoise used Double Splash");
				damage += calculateDamage(opponent);
			}
		}
		opponent.setHealth(opponent.getHealth() - damage);
	
}
}
