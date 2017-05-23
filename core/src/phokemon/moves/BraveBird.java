package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Flying;

public class BraveBird extends AttackMove{
	public BraveBird(){
		super(4000, 25, new Flying(), "Brave Bird");
	}

}
