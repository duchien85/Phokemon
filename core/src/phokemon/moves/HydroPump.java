package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Water;

/**
 * @version 5-22-17
 * @author Rishabh
 *
 */
public class HydroPump extends AttackMove {
	public HydroPump(){
		super(120, 80, new Water(), "Hydro Pump");
	}
}
