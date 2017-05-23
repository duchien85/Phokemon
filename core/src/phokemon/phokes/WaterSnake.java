package phokemon.phokes;

import phokemon.Phokes;
import phokemon.Water;
import phokemon.moves.DoubleSplash;
import phokemon.moves.HydroPump;
import phokemon.moves.Surf;

public class WaterSnake extends Phokes {
	public WaterSnake(boolean isPlayer1){
		super(isPlayer1, "watersnake.png", "Water Snake", new Water(), 144, 116, 106, 65, new HydroPump(), new Surf(), new DoubleSplash());
	}

}
