package phokemon.phokes;

import phokemon.Grass;
import phokemon.Phokes;
import phokemon.moves.DoubleEdge;
import phokemon.moves.FrenzyPlant;
import phokemon.moves.IronDefense;
import phokemon.moves.RazorLeaf;

public class Mossy extends Phokes{
	public Mossy(boolean isPlayer1){
		super(isPlayer1, "mossy.png", "Mossy", new Grass(), 155, 114, 110, 61, new RazorLeaf(), new FrenzyPlant(), new DoubleEdge(), new IronDefense());
	}

}
