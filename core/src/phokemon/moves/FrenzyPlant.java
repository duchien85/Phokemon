package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Grass;

/**
 * @version 5-22-17
 * @author Rishabh
 *
 */
public class FrenzyPlant extends AttackMove{
	public FrenzyPlant(){
		super(4000, 25, new Grass(), "FrenzyPlant");
	}

}
