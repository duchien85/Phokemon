package phokemon.phokes;

import phokemon.Flying;
import phokemon.Phokes;
import phokemon.moves.BraveBird;
import phokemon.moves.HyperBeam;
import phokemon.moves.SwordsDance;
import phokemon.moves.WingAttack;
/**
 * @version 5-23-17
 * @author Rishabh, Jacob, Kalpit
 *
 */
public class Tornado extends Phokes{
	
	public Tornado(boolean isPlayer1){
		super(isPlayer1, "tornado.png", "Tornado", new Flying(), 135, 100, 85, 125, new WingAttack(), new BraveBird(), new SwordsDance(), new HyperBeam());
	}

}
