package com.apjava.phokemon.screens;

import java.util.ArrayList;
import java.util.List;

import com.apjava.phokemon.mechanics.HealthBar;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BattleScreen implements Screen {
	private Game game;
	private SpriteBatch batch;
	private Texture bgImg;
	private Stage stage;
	private float width, height;
	private LabelStyle labelstyle;
	private Sprite pokemon1, pokemon2;
	private boolean animatePokemon = true;
	private Music music;
	private Sound buttonSound, flameSound;
	private BitmapFont font;
	private List<ParticleEffect> particleEffects;
	private List<Actor> player1Moves, player2Moves;
	//will remove and add to phokemon
	private HealthBar hbTest, hbTest2;
	
	public BattleScreen(Game game) {
		this.game = game;
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		//background image
		bgImg = new Texture("battleTemplate.png");
		//music
		music = Gdx.audio.newMusic(Gdx.files.internal("music/battlemusic1.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		flameSound = Gdx.audio.newSound(Gdx.files.internal("sounds/flameNEEDLICENSING.mp3"));
		//FONT AND LABELS
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gameboyfont.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) (height/18);
		font = generator.generateFont(parameter);
		labelstyle = new LabelStyle();
		labelstyle.font = font;
		labelstyle.fontColor = Color.BLACK;
		//add the demo moves
		player1Moves = new ArrayList<Actor>();
		player2Moves = new ArrayList<Actor>();
		
		String[] attacks = {"Flamethrower", "Kick", "Aerial Ace", "Punch"};
		for(int i = 0; i<attacks.length; i++) {
			Label attack = new Label(attacks[i], labelstyle);
			//attack.setScale(10);
			attack.setWidth(width/10);
			attack.setHeight(height/10);
			float padding = height/60;
			float horizontalPos = (i==0||i==2) ? 0.0f: 2.5f; 
			int verticalPos = (i==2||i==3) ? 0: 1; 
			attack.setPosition(width/2+((width/10+attack.getWidth())*horizontalPos)-(width/10+attack.getWidth())*attacks.length/2, padding+height/8*verticalPos);
			//todo it will call this attack when clicked
			attack.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					buttonSound.play();
			    	addPlayer1Fire();
			    	hbTest2.setCurrentHealthSmooth(hbTest2.getCurrentHealth()-50);
			    }
			});
			player1Moves.add(attack);
			stage.addActor(attack);
		}
		//test health bar positions
		hbTest = new HealthBar(width-width/4.4f, height/2.75f, width/4.9f, height/30, 300, 300);
		stage.addActor(hbTest);
		hbTest2 = new HealthBar(width/5.2f, height-height/4.17f, width/4.9f, height/30, 300, 300);
		stage.addActor(hbTest2);
		//
		
		//add pokemon 1
		Texture pokemonTexture1 = new Texture("charizard.png");
		pokemon1 = new Sprite(pokemonTexture1);
		pokemon1.setBounds(width/8, height/3.5f, width/4, height/4);
		
		Texture pokemonTexture2 = new Texture("mewtwo.png");
		pokemon2 = new Sprite(pokemonTexture2);
		pokemon2.setBounds(width-width/3, height/2.0f, width/4, height/4);
		
		//testing particles
		particleEffects = new ArrayList<ParticleEffect>();
		
	    
	}
	
	/**
	 * Changing later
	 */
	private void addPlayer1Fire() {
		ParticleEffect fire = new ParticleEffect();
		fire.load(Gdx.files.internal("particles/bubbles.particle"), Gdx.files.internal("particles/"));
		fire.setPosition(pokemon1.getX()+pokemon1.getWidth()/2, pokemon1.getY()+pokemon1.getHeight());
		fire.start();
		for(ParticleEmitter emitter: fire.getEmitters()) {
			ScaledNumericValue angle = emitter.getAngle();
			float degrees = 20.0f;
			angle.setLow(degrees);
			angle.setHigh(degrees+90.0f, degrees-90.0f);
		}
	    particleEffects.add(fire);
	    flameSound.play(3.0f);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.8f, 0.84f, 0.84f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bgImg, 0, 0, width, height);
		pokemon1.draw(batch);
		pokemon2.draw(batch);
		if(animatePokemon) {
			//todo
		}
		//update and render particle effects
		for(int i=0; i < particleEffects.size(); i++) {
			ParticleEffect effect = particleEffects.get(i);
			effect.update(delta);
			effect.draw(batch);
			if(effect.isComplete()) {
				effect.dispose();
				particleEffects.remove(i);
			}
		}
		batch.end();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		bgImg.dispose();
		stage.dispose();
		labelstyle.font.dispose();
		music.dispose();
		buttonSound.dispose();
		flameSound.dispose();
		font.dispose();
	}

}