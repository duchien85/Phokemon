package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Flying;
/**
 * @version 5-30-17
 * @author Rishabh, Jacob
 *
 */
public class BraveBird extends AttackMove{
	public BraveBird(){
		super(4000, 25, new Flying(), "Brave Bird");
	}

}
