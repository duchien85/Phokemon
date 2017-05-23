package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Flying;

public class WingAttack extends AttackMove{
	
	public WingAttack(){
		super (60, 100, new Flying(), "Wing Attack");
	}

}
