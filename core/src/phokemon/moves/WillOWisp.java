package phokemon.moves;

import com.apjava.phokemon.screens.BattleScreen;

import phokemon.AttackMove;
import phokemon.Fire;
import phokemon.Phokes;

public class WillOWisp extends AttackMove{
	
	public WillOWisp(){
		super(0, 100, new Fire(), "Will-O-Wisp");
	}
	
	public void doDamage(BattleScreen battleScreen, Phokes opponent){
		opponent.burned = true;
		opponent.setAttack((int)(opponent.getAttack() * .8));
		System.out.println(opponent.getName() + " was burned");
	}

}
