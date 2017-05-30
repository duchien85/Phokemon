package phokemon.moves;

import com.apjava.phokemon.screens.BattleScreen;

import phokemon.AttackMove;
import phokemon.Electric;
import phokemon.Phokes;

public class ThunderWave extends AttackMove{
	
	public ThunderWave(){
		super(0,100, new Electric(), "Thunder Wave");
	}
	public void doDamage(BattleScreen battleScreen, Phokes opponent){
		opponent.paralyzed = true;
		opponent.setSpeed((int)(opponent.getSpeed() * .5));
		System.out.println(opponent.getName() + " was paralyzed");

	}

}
