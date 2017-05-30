package phokemon.moves;

import com.apjava.phokemon.screens.BattleScreen;

import phokemon.AttackMove;
import phokemon.Electric;
import phokemon.Phokes;
/**
 * @version 5-22-17
 * @author Rishabh, Jacob
 *
 */
public class ElectricSting extends AttackMove{
	public ElectricSting(){
		super(30, 80, new Electric(), "Electric Sting");
	}
	
	public void doDamage(BattleScreen battleScreen, Phokes opponent){
		int damage = 0;
		for (int i = 0; i < 4; i ++){
			if(Math.random()*100 <= accuracy) {
				System.out.println("Electric Sting was used against " +opponent.getName());
				damage += calculateDamage(battleScreen, opponent);
			}
		}
		opponent.setHealth(opponent.getHealth() - damage);
	}
}
