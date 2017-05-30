package phokemon.phokes;

import phokemon.Phokes;
import phokemon.Water;
import phokemon.moves.DoubleEdge;
import phokemon.moves.DoubleSplash;
import phokemon.moves.ElectricSting;
import phokemon.moves.HydroPump;
import phokemon.moves.Surf;

/**
 * @version 5-23-17
 * @author Rishabh, Jacob
 *
 */
public class WaterSnake extends Phokes {
	public WaterSnake(boolean isPlayer1){
		super(isPlayer1, "watersnake.png", "Water Snake", new Water(), 144, 116, 106, 65, new Surf(), new HydroPump(), new ElectricSting(), new DoubleEdge());
	}

}
