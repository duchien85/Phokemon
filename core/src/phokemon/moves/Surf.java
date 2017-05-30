package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Water;
/**
 * @version 5-22-17
 * @author Rishabh
 *
 */
public class Surf extends AttackMove{
	public Surf(){
		super(80, 90, new Water(), "Surf");
	}

}
