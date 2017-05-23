package phokemon;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

public class Phokes extends Character implements Disposable{

	public boolean burned, paralyzed;
	private PhokeType phokeType;
	private Sprite phokesSprite;
	private Texture phokesTexture;
	
	public Phokes(boolean isPlayer1, String image, String name, PhokeType phokeType, int health, int attack, int defense, int speed,  AttackMove...hits){
		this.phokeType = phokeType;
		this.health = health;
		this.maxHealth = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.name = name;
		paralyzed = false;
		burned = false;
		moves = new ArrayList<AttackMove>();
		for( int m = 0; m < hits.length; m++){
			moves.add(hits[m]);
		}
		
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