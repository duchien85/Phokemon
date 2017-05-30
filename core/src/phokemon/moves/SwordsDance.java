package phokemon.moves;

import com.apjava.phokemon.screens.BattleScreen;

import phokemon.AttackMove;
import phokemon.Character;
import phokemon.Normal;

public class SwordsDance extends AttackMove{
	/**
	 * @version 5-23-17
	 * @author Rishabh
	 *
	 */
	public SwordsDance(){
		super(1, 100, new Normal(), "Swords Dance");
	}
	
	public void doDamage(BattleScreen battleScreen, Character self){
		self.setAttack(self.getAttack()*2);
		System.out.println(self.getName() + "used Swords Dance");
	}
}
