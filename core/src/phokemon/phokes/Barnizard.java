package phokemon.phokes;

import phokemon.Fire;
import phokemon.Phokes;
import phokemon.moves.Agility;
import phokemon.moves.DoubleEdge;
import phokemon.moves.FireBlast;
import phokemon.moves.FirePunch;
import phokemon.moves.Flamethrower;
import phokemon.moves.WillOWisp;

/**
 * @version 5-23-17
 * @author Rishabh, Jacob
 *
 */
public class Barnizard extends Phokes{
	
	public Barnizard(boolean isPlayer1){
		super(isPlayer1, "barnizard.png", "Barnizard", new Fire(), 170, 128, 70, 70, new FirePunch(), new Flamethrower(), new DoubleEdge(), new WillOWisp());
	}

}
