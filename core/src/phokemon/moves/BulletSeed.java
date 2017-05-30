package phokemon.moves;

import phokemon.AttackMove;
import phokemon.Grass;

/**
 * @version 5-22-17
 * @author Rishabh, Jacob
 *
 */
public class BulletSeed extends AttackMove{
	public BulletSeed(){
		super (60, 100, new Grass(), "Bullet Seed");
	}
}
