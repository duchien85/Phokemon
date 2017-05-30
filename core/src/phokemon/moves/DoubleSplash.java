package phokemon.moves;

import com.apjava.phokemon.screens.BattleScreen;

import phokemon.AttackMove;
import phokemon.Phokes;
import phokemon.Water;
/**
 * @version 5-22-17
 * @author Rishabh, Jacob
 *
 */
public class DoubleSplash extends AttackMove{
	
	public DoubleSplash(){
		super(75, 50, new Water(), "Double Splash");
	}
	
	public void doDamage(BattleScreen battleScreen, Phokes opponent){
		int damage = 0;
		for (int i = 0; i < 2; i ++){
			if(Math.random()*100 <= accuracy) {
				System.out.println("blastoise used Double Splash");
				damage += calculateDamage(battleScreen, opponent);
			}
		}
		opponent.setHealth(opponent.getHealth() - damage);
	
	}
}
