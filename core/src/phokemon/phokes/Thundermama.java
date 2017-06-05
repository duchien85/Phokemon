package phokemon.phokes;

import phokemon.Electric;
import phokemon.Phokes;
import phokemon.moves.BulkUp;
import phokemon.moves.DoubleEdge;
import phokemon.moves.ElectricSting;
import phokemon.moves.Thunder;
/**
 * @version 5-23-17
 * @author Rishabh, Jacob, Nick
 *
 */
public class Thundermama extends Phokes{
	
	public Thundermama(boolean isPlayer1){
		super(isPlayer1, "Thundermama.png", "Thundermama", new Electric(), 135, 128, 72, 100, new Thunder(), new ElectricSting(), new BulkUp(), new DoubleEdge());
	}

}
