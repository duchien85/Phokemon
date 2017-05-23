package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Water;

public class HydroPump extends AttackMove {
	public HydroPump(){
		super(120, 80, new Water(), "Hydro Pump");
	}
}
