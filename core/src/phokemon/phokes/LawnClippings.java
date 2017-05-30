package phokemon.phokes;

import phokemon.Grass;
import phokemon.Phokes;
import phokemon.moves.BulletSeed;
import phokemon.moves.FrenzyPlant;
import phokemon.moves.HyperBeam;
import phokemon.moves.SwordsDance;

public class LawnClippings extends Phokes{
	
	public LawnClippings(boolean isPlayer1){
		super(isPlayer1, "lawnclippings.png", "Lawn Clippings", new Grass(), 130, 110, 70, 125, new BulletSeed(), new FrenzyPlant(), new SwordsDance(), new HyperBeam());
	}

}
