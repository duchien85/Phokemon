package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Flying;

public class Fly extends AttackMove{
	public Fly(){
		super(90, 80, new Flying(), "Fly");
	}

}
