package com.apjava.phokemon.screens;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import phokemon.AttackMove;
import phokemon.BattleLabel;
import phokemon.HealthBar;
import phokemon.Phokes;
import phokemon.Player;
import phokemon.phokes.Barnizard;
import phokemon.phokes.FireBat;
import phokemon.phokes.LawnClippings;
import phokemon.phokes.Thundermama;
import phokemon.phokes.Tornado;
import phokemon.phokes.WaterSnake;

/**
 * @version 5-12-17
 * @author Jacob Murry
 *
 */
public class BattleScreen implements Screen {
	private Game game;
	private SpriteBatch batch;
	private Texture bgImg;
	private Stage stage;
	private float width, height;
	private LabelStyle labelstyle;
	private Sprite pokemon1, pokemon2;
	private boolean animatePokemon = true;
	private Music music, victoryMusic;
	private Sound buttonSound, flameSound, electricSound, dieSound, waterSound, superEffective, notEffective, statBoostSound, switchSound;
	private BitmapFont font;
	private List<ParticleEffect> particleEffects;
	private List<Actor> battleLog, player1Options, player1Moves, player2Options, player2Moves, player1Switches, player2Switches;
	//will remove and add to phokemon
	private HealthBar hbTest, hbTest2;
	public static int BATTLE_LOG = 0, PLAYER1 = 1, PLAYER1_ATTACK = 2, PLAYER2 = 3, PLAYER2_ATTACK = 4, PLAYER1_SWITCH = 5, PLAYER2_SWITCH = 6;
	private int dialogOption = 0;
	private int nextDialogOption = PLAYER1;
	private boolean isDraw = false;
	private Player player1, player2;
	private BattleLabel battleLabel;
	private boolean player1Attacking = false, player2Attacking = false;
	private int player1Move = 0, player2Move = 0, player1Switch=0, player2Switch=0;
	private Label name1, name2;
	private List<Phokes> player1Picks, player2Picks;
	
	public BattleScreen(Game game, List<Phokes> player1Picks, List<Phokes> player2Picks) {
		this.game = game;
		this.player1Picks = player1Picks;
		this.player2Picks = player2Picks;
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
		victoryMusic = Gdx.audio.newMusic(Gdx.files.internal("music/victory.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		flameSound = Gdx.audio.newSound(Gdx.files.internal("sounds/flameNEEDLICENSING.mp3"));
		electricSound = Gdx.audio.newSound(Gdx.files.internal("sounds/electricshock2.wav"));
		dieSound = Gdx.audio.newSound(Gdx.files.internal("sounds/diesound.wav"));
		waterSound = Gdx.audio.newSound(Gdx.files.internal("sounds/watermove.wav"));
		superEffective = Gdx.audio.newSound(Gdx.files.internal("sounds/supereffective1.wav"));
		notEffective = Gdx.audio.newSound(Gdx.files.internal("sounds/noteffective1.wav"));
		statBoostSound = Gdx.audio.newSound(Gdx.files.internal("sounds/statboost.wav"));
		switchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/phokeball.wav"));
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
		player1Options = new ArrayList<Actor>();
		player2Options = new ArrayList<Actor>();
		player1Switches = new ArrayList<Actor>();
		player2Switches = new ArrayList<Actor>();
		battleLog = new ArrayList<Actor>();
		
		//battle log
		battleLabel = new BattleLabel("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", labelstyle);
		battleLabel.setTextAnimated("Player 1 has challenged\nplayer 2");
		battleLabel.setOrigin(0, battleLabel.getHeight());
		battleLabel.setPosition(width/25, height/8);
		battleLabel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!battleLabel.isTyping()) {
					buttonSound.play();
					if(isDraw) {
						
					} else {
						dialogOption = nextDialogOption;
					}
			
				}
			}
		});
		battleLog.add(battleLabel);
		stage.addActor(battleLabel);
		
		//player 1 options
		final Label attack1Label = new Label("Attack", labelstyle);
		attack1Label.setPosition(width/20, height/7);
		stage.addActor(attack1Label);
		attack1Label.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSound.play();
				dialogOption = PLAYER1_ATTACK;
			}
		});
		player1Options.add(attack1Label);
		final Label switch1Label = new Label("Switch", labelstyle);
		switch1Label.setPosition(width/1.5f, height/7);
		stage.addActor(switch1Label);
		switch1Label.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSound.play();
				showSwitchOptions(true);
			}
		});
		player1Options.add(switch1Label);
		//
		//player 2 options
		final Label attack2Label = new Label("Attack", labelstyle);
		attack2Label.setPosition(width/20, height/7);
		stage.addActor(attack2Label);
		attack2Label.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSound.play();
				dialogOption = BattleScreen.PLAYER2_ATTACK;
			}
		});
		player2Options.add(attack2Label);
		final Label switch2Label = new Label("Switch", labelstyle);
		switch2Label.setPosition(width/1.5f, height/7);
		stage.addActor(switch2Label);
		switch2Label.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSound.play();
				showSwitchOptions(false);
			}
		});
		player2Options.add(switch2Label);
		//
		
		//testing particles
		particleEffects = new ArrayList<ParticleEffect>();
		
		
		//create players and phokemon
		//player 1
		player1 = new Player(this, 1, player1Picks.toArray(new Phokes[player1Picks.size()]));
		player1.setSelectedPhokemon(0);
		player1.setAttack(true);
		hbTest = new HealthBar(width-width/4.4f, height/2.75f, width/4.9f, height/30, player1.getCurrentPhokemon().getHealth(), player1.getCurrentPhokemon().getMaxHealth());
		stage.addActor(hbTest);
		//name label
		name1 = new Label(player1.getCurrentPhokemon().getName(), labelstyle);
		name1.setWidth(width/10);
		name1.setHeight(height/10);
		name1.setPosition(width/1.7f, height/2.5f);
		stage.addActor(name1);
		//button for each move player1
		for(int i = 0; i<player1.getCurrentPhokemon().getMoves().size(); i++) {
			final int attackIndex = i;
			final int listSize = 4;
			final AttackMove move = player1.getCurrentPhokemon().getMoves().get(i);
			Label attack = new Label(move.toString(), labelstyle);
			//attack.setScale(10);
			attack.setWidth(width/10);
			attack.setHeight(height/10);
			float padding = height/60;
			float horizontalPos = (i==0||i==2) ? 0.0f: 2.5f; 
			int verticalPos = (i==2||i==3) ? 0: 1; 
			attack.setPosition(width/2+((width/10+attack.getWidth())*horizontalPos)-(width/10+attack.getWidth())*listSize/2, padding+height/8*verticalPos);
			//todo it will call this attack when clicked
			attack.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					buttonSound.play();
			    	player1Attacking = true;
					player1Move = attackIndex;
					//tell log for player 2 to select move
					setDialog(BATTLE_LOG);
					setNextDialog(PLAYER2);
					updateBattleLog("Player 2 Select Move");
			    }
			});
			player1Moves.add(attack);
			stage.addActor(attack);
		}
		//
		
		//////
		//player 2
		player2 = new Player(this, 2, player2Picks.toArray(new Phokes[player2Picks.size()]));
		player2.setSelectedPhokemon(0);
		player2.setAttack(false);
		hbTest2 = new HealthBar(width/5.2f, height-height/4.17f, width/4.9f, height/30, player2.getCurrentPhokemon().getHealth(), player2.getCurrentPhokemon().getMaxHealth());
		stage.addActor(hbTest2);
		//pokemon name labels
		name2 = new Label(player2.getCurrentPhokemon().getName(), labelstyle);
		name2.setWidth(width/10);
		name2.setHeight(height/10);
		name2.setPosition(width/50.0f, height-height/4.9f);
		stage.addActor(name2);
		//
		//button for each move player2
		for(int i = 0; i<player2.getCurrentPhokemon().getMoves().size(); i++) {
			final int attackIndex = i;
			final int listSize = 4;
			final AttackMove move = player2.getCurrentPhokemon().getMoves().get(i);
			Label attack = new Label(move.toString(), labelstyle);
			//attack.setScale(10);
			attack.setWidth(width/10);
			attack.setHeight(height/10);
			float padding = height/60;
			float horizontalPos = (i==0||i==2) ? 0.0f: 2.5f; 
			int verticalPos = (i==2||i==3) ? 0: 1; 
			attack.setPosition(width/2+((width/10+attack.getWidth())*horizontalPos)-(width/10+attack.getWidth())*listSize/2, padding+height/8*verticalPos);
			//todo it will call this attack when clicked
			attack.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					buttonSound.play();
					player2Attacking = true;
					player2Move = attackIndex;
					//tell log for player 2 to select move
					setDialog(BATTLE_LOG);
					isDraw = true;
					doMoves();
				}
			});
			player2Moves.add(attack);
			stage.addActor(attack);
		}
		//
		
	    
	}
	

	/**
	 * Add particles coming from player 1
	 * @param typeStr ex fire, water, electric, grass
	 */
	private void addPlayer1Effects(String typeStr) {
		if(typeStr.equalsIgnoreCase("fire")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/firethicc.particle"), Gdx.files.internal(""));
			fire.setPosition(player1.getCurrentPhokemon().getSprite().getX()+player1.getCurrentPhokemon().getSprite().getWidth()/2, player1.getCurrentPhokemon().getSprite().getY()+player1.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 20.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			flameSound.play(6.0f);
		} else if(typeStr.equalsIgnoreCase("electric")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/electric.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player1.getCurrentPhokemon().getSprite().getX()+player1.getCurrentPhokemon().getSprite().getWidth()/2, player1.getCurrentPhokemon().getSprite().getY()+player1.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 20.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			electricSound.play(6.0f);
		} else if(typeStr.equalsIgnoreCase("water")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/bubbles.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player1.getCurrentPhokemon().getSprite().getX()+player1.getCurrentPhokemon().getSprite().getWidth()/2, player1.getCurrentPhokemon().getSprite().getY()+player1.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 20.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			waterSound.play(6.0f);
		} else if(typeStr.equalsIgnoreCase("grass")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/leaf.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player1.getCurrentPhokemon().getSprite().getX()+player1.getCurrentPhokemon().getSprite().getWidth()/2, player1.getCurrentPhokemon().getSprite().getY()+player1.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 20.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			//electricSound.play(3.0f);
		} else if(typeStr.equalsIgnoreCase("statboost")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/status.particle"), Gdx.files.internal(""));
			fire.setPosition(player1.getCurrentPhokemon().getSprite().getX()+player1.getCurrentPhokemon().getSprite().getWidth()/2, player1.getCurrentPhokemon().getSprite().getY()+player1.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 20.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			statBoostSound.play(3.0f);
		} else if(typeStr.equalsIgnoreCase("flying")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/flying.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player1.getCurrentPhokemon().getSprite().getX()+player1.getCurrentPhokemon().getSprite().getWidth()/2, player1.getCurrentPhokemon().getSprite().getY()+player1.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 20.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			//electricSound.play(3.0f);
		} else if(typeStr.equalsIgnoreCase("normal")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/normal.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player1.getCurrentPhokemon().getSprite().getX()+player1.getCurrentPhokemon().getSprite().getWidth()/2, player1.getCurrentPhokemon().getSprite().getY()+player1.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 20.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			//electricSound.play(3.0f);
		} else if(typeStr.equalsIgnoreCase("burn")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/burn.particle"), Gdx.files.internal(""));
			fire.setPosition(player1.getCurrentPhokemon().getSprite().getX()+player1.getCurrentPhokemon().getSprite().getWidth()/2, player1.getCurrentPhokemon().getSprite().getY()+player1.getCurrentPhokemon().getSprite().getHeight()/2);
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 220.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			
		}
	}
	
	/**
	 * Add particles coming from player 2
	 * @param typeStr ex fire, water, electric, grass
	 */
	private void addPlayer2Effects(String typeStr) {
		if(typeStr.equalsIgnoreCase("fire")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/firethicc2.particle"), Gdx.files.internal(""));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 220.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			flameSound.play(6.0f);
		} else if(typeStr.equalsIgnoreCase("electric")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/electric2.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 220.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			electricSound.play(6.0f);
		} else if(typeStr.equalsIgnoreCase("water")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/bubbles2.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 50.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			waterSound.play(6.0f);
		} else if(typeStr.equalsIgnoreCase("grass")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/leaf2.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 220.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			
		} else if(typeStr.equalsIgnoreCase("statboost")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/status.particle"), Gdx.files.internal(""));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 220.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			statBoostSound.play(6.0f);
		} else if(typeStr.equalsIgnoreCase("flying")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/flying2.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 220.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			
		} else if(typeStr.equalsIgnoreCase("normal")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/normal2.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 220.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			
		} else if(typeStr.equalsIgnoreCase("burn")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/burn.particle"), Gdx.files.internal(""));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight()/2);
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 220.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.8f, 0.84f, 0.84f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bgImg, 0, 0, width, height);
		//render current phokemon
		player1.getCurrentPhokemon().getSprite().draw(batch);
		player2.getCurrentPhokemon().getSprite().draw(batch);
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
		//decide and set visable and invisible what has to renderered
		for(Actor actor: player1Options) {
			if(dialogOption==BattleScreen.PLAYER1)
				actor.setVisible(true);
			else
				actor.setVisible(false);
		}
		for(Actor actor: player2Options) {
			if(dialogOption==BattleScreen.PLAYER2)
				actor.setVisible(true);
			else
				actor.setVisible(false);
		}
		for(Actor actor: player1Moves) {
			if(dialogOption==BattleScreen.PLAYER1_ATTACK)
				actor.setVisible(true);
			else
				actor.setVisible(false);
		}
		for(Actor actor: player2Moves) {
			if(dialogOption==BattleScreen.PLAYER2_ATTACK)
				actor.setVisible(true);
			else
				actor.setVisible(false);
		}
		for(Actor actor: player1Switches) {
			if(dialogOption==BattleScreen.PLAYER1_SWITCH)
				actor.setVisible(true);
			else
				actor.setVisible(false);
		}
		for(Actor actor: player2Switches) {
			if(dialogOption==BattleScreen.PLAYER2_SWITCH)
				actor.setVisible(true);
			else
				actor.setVisible(false);
		}
		for(Actor actor: battleLog) {
			if(dialogOption==BattleScreen.BATTLE_LOG)
				actor.setVisible(true);
			else
				actor.setVisible(false);
		}
		stage.act(delta);
		stage.draw();
	}
	
	/**
	 * Update battle log text and break lines properly
	 * @param text
	 */
	public void updateBattleLog(String text) {
		//line break every 28 characters
		StringBuffer textBuffer = new StringBuffer("");
		for(int k =0; k < text.length(); k++) {
			if((k+1)%29==0) {
				textBuffer.append("\n");
			}
			textBuffer.append(text.charAt(k));
		}
		//
		battleLabel.setText("");
		battleLabel.setTextAnimated(textBuffer.toString());
	}
	
	/**
	 * 
	 * @return current dialog mode
	 */
	public int getDialog() {
		return dialogOption;
	}
	
	/**
	 * Set the current dialog to show in battlescreen
	 * @param d static values in BattleScreen
	 */
	public void setDialog(int d) {
		dialogOption = d;
	}
	
	/**
	 * Set the dialog to show in battlescreen AFTER Battledialog
	 * @param d static values in BattleScreen
	 */
	public void setNextDialog(int d) {
		nextDialogOption = d;
	}
	
	/**
	 * Have each player switch phokemon or attack
	 */
	private void doMoves() {
		float extraDelay = 0.0f;//add to each because tasks are semi-asyncnorous
		checkPhokemon();
		if(!player1Attacking) {
			//switch player 1
			Timer.schedule(new Task() {
				
				@Override
				public void run() {
					switchPhokemon(true);
				}
			}, extraDelay);
		} if(!player2Attacking) {
			//switch player 2
			extraDelay+=5.0f;
			Timer.schedule(new Task() {
				
				@Override
				public void run() {
					switchPhokemon(false);
				}
			}, extraDelay);
			
		}
		if(player1.getCurrentPhokemon().getSpeed()>player2.getCurrentPhokemon().getSpeed()) {
			//player 1 attacks first
			if(player1Attacking && !checkPhokemon()) {	
				Timer.schedule(new Task() {		
					@Override
					public void run() {
						player1Attack();
					}
				}, extraDelay);
			}
			if(player2Attacking && !checkPhokemon()) {
				extraDelay+=6.0f;
				Timer.schedule(new Task() {
					
					@Override
					public void run() {
						if(!checkPhokemon()) {
							player2Attack();
							checkPhokemon();
						}
					}
				}, extraDelay);
			}
		} else {
			//player 2 attacks first
			if(player2Attacking && !checkPhokemon()) {
				Timer.schedule(new Task() {		
					@Override
					public void run() {
						player2Attack();
					}
				}, extraDelay);
			} if(player1Attacking && !checkPhokemon()){
				extraDelay+=6.0f;
				Timer.schedule(new Task() {
					
					@Override
					public void run() {
						if(!checkPhokemon()) {
							player1Attack();
							checkPhokemon();
						}
					}
				}, extraDelay);
			}
		}
		//burn effects on phokemon
		
		if(player1.getCurrentPhokemon().burned) {
			extraDelay+=5.0f;
			Timer.schedule(new Task() {
				
				@Override
				public void run() {
					updateBattleLog(player1.getCurrentPhokemon().getName()+" was burned");
					addPlayer1Effects("burn");
					player1.getCurrentPhokemon().doDamage(5);
					hbTest.setCurrentHealthSmooth(player1.getCurrentPhokemon().getHealth());
					checkPhokemon();
				}
			}, 5.0f+extraDelay);
		} if(player2.getCurrentPhokemon().burned) {
			extraDelay=+5.0f;
			Timer.schedule(new Task() {
				
				@Override
				public void run() {
					updateBattleLog(player2.getCurrentPhokemon().getName()+" was burned");
					addPlayer2Effects("burn");
					player2.getCurrentPhokemon().doDamage(5);
					hbTest2.setCurrentHealthSmooth(player2.getCurrentPhokemon().getHealth());
					checkPhokemon();
				}
			}, 5.0f+extraDelay);
			
		}
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				//while(battleLabel.isTyping()) {
					
				
				setDialog(BATTLE_LOG);
				setNextDialog(PLAYER1);
				isDraw = false;
				//updateBattleLog("Player 1 Select Move");
			}
		}, 5.0f+extraDelay);
		
	}
	
	private void player1Attack() {
		if(player1.attack(player1Move, player1.getCurrentPhokemon(), player2.getCurrentPhokemon()))
    		addPlayer1Effects(player1.getCurrentPhokemon().getMoves().get(player1Move).getPhokeType().toString());
    	hbTest2.setCurrentHealthSmooth(player2.getCurrentPhokemon().getHealth());
	}
	
	private void player2Attack() {
		if(player2.attack(player2Move, player2.getCurrentPhokemon(), player1.getCurrentPhokemon()))
			addPlayer2Effects(player2.getCurrentPhokemon().getMoves().get(player2Move).getPhokeType().toString());
		hbTest.setCurrentHealthSmooth(player1.getCurrentPhokemon().getHealth());
	}
	
	/** 
	 * checks if phokemon are alive
	 * and proceeds accordingly
	 * @return true if a phokemon dies
	 */
	private boolean checkPhokemon() {
		boolean somePhokeDied = false;
		if(player1.getCurrentPhokemon().getHealth()<=0) {
			somePhokeDied = true;
			player1.getCurrentPhokemon().setAlive(false);
			//delay death
			Timer.schedule(new Task(){
			    @Override
			    public void run() {
			    	player1.getCurrentPhokemon().getSprite().setAlpha(0.0f);
			    	//play death sound
			    	dieSound.play(5.0f);
			    	//see if there are any phokemon alive
					if(player1.canPlay()) {
						//switch to first alive phokemon
						for(int k=0; k<player1.getPhokesList().size(); k++) {
							if(player1.getPhokemon(k).isAlive()) {
								player1Switch = k;
								switchPhokemon(true);
								break;
							}
						}
					} else {
						setDialog(BATTLE_LOG);
						setNextDialog(BATTLE_LOG);
						updateBattleLog("Player 2 has won");
						music.stop();
						victoryMusic.play();
					}
			    }
			}, 5.0f);
		} if(player2.getCurrentPhokemon().getHealth()<=0) {
			somePhokeDied = true;
			player2.getCurrentPhokemon().setAlive(false);
			//delay death
			Timer.schedule(new Task(){
			    @Override
			    public void run() {
			    	player2.getCurrentPhokemon().getSprite().setAlpha(0.0f);
			    	//play death sound
			    	dieSound.play(5.0f);
			    	//see if there are any phokemon alive
					if(player2.canPlay()) {
						//switch to first alive phokemon
						for(int k=0; k<player2.getPhokesList().size(); k++) {
							if(player2.getPhokemon(k).isAlive()) {
								player2Switch = k;
								switchPhokemon(false);
								
								break;
							}
						}
					} else {
						setDialog(BATTLE_LOG);
						setNextDialog(BATTLE_LOG);
						updateBattleLog("Player 1 has won");
						music.stop();
						victoryMusic.play();
					}
			    }
			}, 5.0f);
		}
		return somePhokeDied;
	}
	
	/**
	 * Show the phokemon the player can switch to
	 * and record what they select
	 * @param isPlayer1
	 */
	private void showSwitchOptions(boolean isPlayer1) {
		//first remove existing options in case some phokemon have died.
		if(isPlayer1) {
			for(int i = player1Switches.size()-1; i>=0; i--) {
				player1Switches.remove(i).remove();
			}
		} else {
			for(int i = player2Switches.size()-1; i>=0; i--) {
				player2Switches.remove(i).remove();
			}
		}
		//add the new options
		if(isPlayer1) {
			setDialog(PLAYER1_SWITCH);
			List<Phokes> availablePhokes = new ArrayList<Phokes>();
			List<Integer> availableIndexes = new ArrayList<Integer>();
			for(int x=0; x<player1.getPhokesList().size(); x++) {
				Phokes phoke = player1.getPhokesList().get(x);
				if(phoke.isAlive() && x!=player1.getSelectedPhokemon()) {
					availablePhokes.add(phoke);
					availableIndexes.add(x);
				}
			}
			for(int i = 0; i<availablePhokes.size(); i++) {
				final int phokesoptionIndex = availableIndexes.get(i);
				final int listSize = 4;
				final Phokes phoke = availablePhokes.get(i);
				Label phokesoption = new Label(phoke.getName(), labelstyle);
				phokesoption.setColor(1.0f, 0.0f, 0.0f, 1.0f);
				
				phokesoption.setWidth(width/10);
				phokesoption.setHeight(height/10);
				float padding = height/60;
				float horizontalPos = (i==0||i==2) ? 0.0f: 2.5f; 
				int verticalPos = (i==2||i==3) ? 0: 1; 
				phokesoption.setPosition(width/2+((width/10+phokesoption.getWidth())*horizontalPos)-(width/10+phokesoption.getWidth())*listSize/2, padding+height/8*verticalPos);
				//todo it will call this phokesoption when clicked
				phokesoption.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						buttonSound.play();
						player1Attacking = false;
						player1Switch = phokesoptionIndex;
						//tell log for player 2 to select move
						setDialog(BATTLE_LOG);
						setNextDialog(PLAYER2);
						updateBattleLog("Player 2 Select Move");

					}
				});
				player1Switches.add(phokesoption);
				stage.addActor(phokesoption);
			}
			//add back button
			int i = 2;
			Label phokesoption = new Label("Back", labelstyle);
			phokesoption.setColor(1.0f, 0.0f, 0.0f, 1.0f);
			phokesoption.setWidth(width/10);
			phokesoption.setHeight(height/10);
			float padding = height/60;
			float horizontalPos = (i==0||i==2) ? 0.0f: 2.5f; 
			int verticalPos = (i==2||i==3) ? 0: 1; 
			phokesoption.setPosition(width/2+((width/10+phokesoption.getWidth())*horizontalPos)-(width/10+phokesoption.getWidth())*2, padding+height/8*verticalPos);
			//todo it will call this phokesoption when clicked
			phokesoption.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					buttonSound.play();
					setDialog(PLAYER1);
					
					

				}
			});
			player1Switches.add(phokesoption);
			stage.addActor(phokesoption);

		} else {
			//player 2 other alive phokemon
			setDialog(PLAYER2_SWITCH);
			List<Phokes> availablePhokes = new ArrayList<Phokes>();
			List<Integer> availableIndexes = new ArrayList<Integer>();
			for(int x=0; x<player2.getPhokesList().size(); x++) {
				Phokes phoke = player2.getPhokesList().get(x);
				if(phoke.isAlive() && x!=player2.getSelectedPhokemon()) {
					availablePhokes.add(phoke);
					availableIndexes.add(x);
				}
			}
			for(int i = 0; i<availablePhokes.size(); i++) {
				final int phokesoptionIndex = availableIndexes.get(i);
				final int listSize = 4;
				final Phokes phoke = availablePhokes.get(i);
				Label phokesoption = new Label(phoke.getName(), labelstyle);
				phokesoption.setColor(1.0f, 0.0f, 0.0f, 1.0f);
				
				phokesoption.setWidth(width/10);
				phokesoption.setHeight(height/10);
				float padding = height/60;
				float horizontalPos = (i==0||i==2) ? 0.0f: 2.5f; 
				int verticalPos = (i==2||i==3) ? 0: 1; 
				phokesoption.setPosition(width/2+((width/10+phokesoption.getWidth())*horizontalPos)-(width/10+phokesoption.getWidth())*listSize/2, padding+height/8*verticalPos);
				//todo it will call this phokesoption when clicked
				phokesoption.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
						buttonSound.play();
						player2Attacking = false;
						player2Switch = phokesoptionIndex;
						//tell log for player 2 to select move
						setDialog(BATTLE_LOG);
						isDraw = true;
						doMoves();

					}
				});
				player2Switches.add(phokesoption);
				stage.addActor(phokesoption);
			}
			//add player 2 back button
			int i = 2;
			Label phokesoption = new Label("Back", labelstyle);
			phokesoption.setColor(1.0f, 0.0f, 0.0f, 1.0f);
			
			phokesoption.setWidth(width/10);
			phokesoption.setHeight(height/10);
			float padding = height/60;
			float horizontalPos = (i==0||i==2) ? 0.0f: 2.5f; 
			int verticalPos = (i==2||i==3) ? 0: 1; 
			phokesoption.setPosition(width/2+((width/10+phokesoption.getWidth())*horizontalPos)-(width/10+phokesoption.getWidth())*2, padding+height/8*verticalPos);
			//todo it will call this phokesoption when clicked
			phokesoption.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					buttonSound.play();
					setDialog(PLAYER2);

				}
			});
			player2Switches.add(phokesoption);
			stage.addActor(phokesoption);
		}
	}
	
	/**
	 * Update the names of the moves shown to the player
	 * @param isPlayer1
	 */
	private void updatePlayerMoves(boolean isPlayer1) {
		if(isPlayer1) {
			for(int i=0; i<player1Moves.size(); i++) {
				try {
					Label move = (Label) player1Moves.get(i);
					move.setText(player1.getCurrentPhokemon().getMoves().get(i).toString());
				} catch(ClassCastException e) {
					e.printStackTrace();
				}
			}
		} else {
			for(int i=0; i<player2Moves.size(); i++) {
				try {
					Label move = (Label) player2Moves.get(i);
					move.setText(player2.getCurrentPhokemon().getMoves().get(i).toString());
				} catch(ClassCastException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void switchPhokemon(boolean isPlayer1) {
		if(isPlayer1) {
			switchSound.play(6.0f);
			player1.switchPhoke(player1Switch);
			updateBattleLog("Player 1 switched to "+player1.getCurrentPhokemon().getName());
			updatePlayerMoves(true);
			name1.setText(player1.getCurrentPhokemon().getName());
			hbTest.setMaxHealth(player1.getCurrentPhokemon().getMaxHealth());
			hbTest.setCurrentHealth(player1.getCurrentPhokemon().getHealth());
		} else {
			switchSound.play(6.0f);
			player2.switchPhoke(player2Switch);
			updateBattleLog("Player 2 switched to "+player2.getCurrentPhokemon().getName());
			updatePlayerMoves(false);
			name2.setText(player2.getCurrentPhokemon().getName());
			hbTest2.setMaxHealth(player2.getCurrentPhokemon().getMaxHealth());
			hbTest2.setCurrentHealth(player2.getCurrentPhokemon().getHealth());
		}
	}
	
	/**
	 * Play an actual sound
	 */
	public void playSuperEffectiveSound() {
		superEffective.play(6.0f);
	}
	
	/**
	 * Play an actual sound
	 */
	public void playNotEffectiveSound() {
		notEffective.play(6.0f);
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
		victoryMusic.dispose();
		buttonSound.dispose();
		flameSound.dispose();
		electricSound.dispose();
		dieSound.dispose();
		waterSound.dispose();
		superEffective.dispose();
		notEffective.dispose();
		statBoostSound.dispose();
		switchSound.dispose();
		font.dispose();
	}

}
