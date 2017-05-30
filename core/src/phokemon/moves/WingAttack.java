package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Flying;
/**
 * @version 5-17-17
 * @author Rishabh
 *
 */
public class WingAttack extends AttackMove{
	
	public WingAttack(){
		super (60, 100, new Flying(), "Wing Attack");
	}

}
