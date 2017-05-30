package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Fire;

/**
 * @version 5-22-17
 * @author Rishabh
 *
 */
public class Flamethrower extends AttackMove{
	
	public Flamethrower(){
		super(100, 80, new Fire(), "Flamethrower");
	}

}
