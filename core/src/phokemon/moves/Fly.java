package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Flying;
/**
 * @version 5-22-17
 * @author Rishabh
 *
 */
public class Fly extends AttackMove{
	public Fly(){
		super(90, 80, new Flying(), "Fly");
	}

}
