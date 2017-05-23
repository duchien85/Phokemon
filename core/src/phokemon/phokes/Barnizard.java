package phokemon.phokes;

import phokemon.Fire;
import phokemon.Phokes;
import phokemon.moves.Agility;
import phokemon.moves.FireBlast;
import phokemon.moves.FirePunch;
import phokemon.moves.Flamethrower;

public class Barnizard extends Phokes{
	
	public Barnizard(boolean isPlayer1){
		super(isPlayer1, "barnizard.png", "Barnizard", new Fire(), 170, 128, 70, 70, new FirePunch(), new FireBlast(), new Flamethrower(), new Agility());
	}

}
