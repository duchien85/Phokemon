package phokemon.phokes;

import phokemon.Fire;
import phokemon.Phokes;
import phokemon.moves.FireBlast;
import phokemon.moves.Flamethrower;
import phokemon.moves.Fly;
import phokemon.moves.SwordsDance;

public class FireBat extends Phokes{
	public FireBat(boolean isPlayer1){
		super(isPlayer1, "firebat.png", "Fire Bat", new Fire(), 138, 114, 90, 105, new Flamethrower(), new FireBlast(), new Fly(), new SwordsDance());
	}
	

}
