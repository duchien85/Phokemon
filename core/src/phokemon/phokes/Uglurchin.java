package phokemon.phokes;

import phokemon.Phokes;
import phokemon.Water;
import phokemon.moves.Agility;
import phokemon.moves.DoubleSplash;
import phokemon.moves.Surf;
import phokemon.moves.Tackle;

public class Uglurchin extends Phokes{
	public Uglurchin(boolean isPlayer1){
		super(isPlayer1, "Uglurchin.png", "Uglurchin", new Water(), 145, 120, 85, 55, new Surf(), new DoubleSplash(), new Tackle(), new Agility());
	}

}
