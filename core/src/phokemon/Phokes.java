package phokemon;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

/**
 * 
 * @author Rishabh, Jacob, Kalpit, Chintan, Nick
 * @version 5-20-17
 */
public class Phokes extends Character implements Disposable {

	public boolean burned, paralyzed;
	private PhokeType phokeType;
	private Sprite phokesSprite;
	private Texture phokesTexture;
	
	/**
	 * Create phoke object with 
	 * @param isPlayer1
	 * @param image path to image
	 * @param name
	 * @param phokeType
	 * @param health
	 * @param attack
	 * @param defense
	 * @param speed
	 * @param hits the attacks
	 */
	public Phokes(boolean isPlayer1, String image, String name, PhokeType phokeType, int health, int attack, int defense, int speed,  AttackMove...hits){
		super(health, health, attack, defense, speed, name, createMoveList(hits));
		this.phokeType = phokeType;
		paralyzed = false;
		burned = false;
		
		
		//graphics
		phokesTexture = new Texture(image);
		phokesSprite = new Sprite(phokesTexture);
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		if(isPlayer1)
			phokesSprite.setBounds(width/8, height/3.5f, width/4, height/4);//player 1 location
		else
			phokesSprite.setBounds(width-width/3, height/2.0f, width/4, height/4);
		
	}
	
	private static List<AttackMove> createMoveList(AttackMove... attack) {
		List<AttackMove> moves = new ArrayList<AttackMove>();
		for( int m = 0; m < attack.length; m++){
			moves.add(attack[m]);
		}
		return moves;
	}
	
	/**
	 * 
	 * @return PhokeType object of this phoke
	 */
	public PhokeType getPhokeType() {
		return phokeType;
	}
	
	
	/**
	 * 
	 * @return the sprite graphic of this phokemon
	 */
	public Sprite getSprite() {
		return phokesSprite;
	}
	
	@Override
	public void dispose() {
		phokesTexture.dispose();
	}
	
}