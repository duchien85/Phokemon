package com.apjava.phokemon.screens;

import java.util.ArrayList;
import java.util.List;

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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import phokemon.AttackMove;
import phokemon.BattleLabel;
import phokemon.HealthBar;
import phokemon.Phokes;
import phokemon.Player;
import phokemon.phokes.Barnizard;
import phokemon.phokes.WaterSnake;

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
	private Sound buttonSound, flameSound, electricSound;
	private BitmapFont font;
	private List<ParticleEffect> particleEffects;
	private List<Actor> battleLog, player1Options, player1Moves, player2Options, player2Moves;
	//will remove and add to phokemon
	private HealthBar hbTest, hbTest2;
	public static int BATTLE_LOG = 0, PLAYER1 = 1, PLAYER1_ATTACK = 2, PLAYER2 = 3, PLAYER2_ATTACK = 4;
	private int dialogOption = 0;
	private int nextDialogOption = PLAYER1;
	private Player player1, player2;
	private BattleLabel battleLabel;
	
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
		electricSound = Gdx.audio.newSound(Gdx.files.internal("sounds/electricshock2.wav"));
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
		battleLog = new ArrayList<Actor>();
		
		//battle log
		battleLabel = new BattleLabel("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", labelstyle);
		battleLabel.setTextAnimated("Player 1 has challenged\nplayer 2");
		battleLabel.setOrigin(0, battleLabel.getHeight());
		battleLabel.setPosition(width/25, height/8);
		battleLabel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSound.play();
				//battleLabel.setTextAnimated("Phokemon1 used fire punch");
				dialogOption = nextDialogOption;
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
			}
		});
		player2Options.add(switch2Label);
		//
		
		//testing particles
		particleEffects = new ArrayList<ParticleEffect>();
		
		
		//create players and phokemon
		//player 1
		Phokes barnizard = new Barnizard(true);
		player1 = new Player(this, 1, barnizard);
		player1.setSelectedPhokemon(0);
		player1.setAttack(true);
		hbTest = new HealthBar(width-width/4.4f, height/2.75f, width/4.9f, height/30, player1.getCurrentPhokemon().getHealth(), player1.getCurrentPhokemon().getMaxHealth());
		stage.addActor(hbTest);
		//name label
		Label name1 = new Label(player1.getCurrentPhokemon().getName(), labelstyle);
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
			    	if(player1.attack(attackIndex, player1.getCurrentPhokemon(), player2.getCurrentPhokemon()))
			    		addPlayer1Effects(move.getPhokeType().toString());
			    	hbTest2.setCurrentHealthSmooth(player2.getCurrentPhokemon().getHealth());
			    	checkPhokemon();
			    }
			});
			player1Moves.add(attack);
			stage.addActor(attack);
		}
		//
		
		//////
		//player 2
		Phokes watersnake = new WaterSnake(false);
		player2 = new Player(this, 2, watersnake);
		player2.setSelectedPhokemon(0);
		player2.setAttack(false);
		hbTest2 = new HealthBar(width/5.2f, height-height/4.17f, width/4.9f, height/30, player2.getCurrentPhokemon().getHealth(), player2.getCurrentPhokemon().getMaxHealth());
		stage.addActor(hbTest2);
		//pokemon name labels
		Label name2 = new Label(player2.getCurrentPhokemon().getName(), labelstyle);
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
					if(player2.attack(attackIndex, player2.getCurrentPhokemon(), player1.getCurrentPhokemon()))
						addPlayer2Effects(move.getPhokeType().toString());
					hbTest.setCurrentHealthSmooth(player1.getCurrentPhokemon().getHealth());
					checkPhokemon();
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
			//electricSound.play(3.0f);
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
		}
	}
	
	/**
	 * Add particles coming from player 2
	 * @param typeStr ex fire, water, electric, grass
	 */
	private void addPlayer2Effects(String typeStr) {
		if(typeStr.equalsIgnoreCase("fire")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/firethicc.particle"), Gdx.files.internal(""));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 200.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			flameSound.play(6.0f);
		} else if(typeStr.equalsIgnoreCase("electric")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/electric.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 200.0f;
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
			//electricSound.play(3.0f);
		} else if(typeStr.equalsIgnoreCase("grass")) {
			ParticleEffect fire = new ParticleEffect();
			fire.load(Gdx.files.internal("particles/leaf.particle"), Gdx.files.internal("particles/"));
			fire.setPosition(player2.getCurrentPhokemon().getSprite().getX()+player2.getCurrentPhokemon().getSprite().getWidth()/2, player2.getCurrentPhokemon().getSprite().getY()+player2.getCurrentPhokemon().getSprite().getHeight());
			fire.start();
			for(ParticleEmitter emitter: fire.getEmitters()) {
				ScaledNumericValue angle = emitter.getAngle();
				float degrees = 200.0f;
				angle.setLow(degrees);
				angle.setHigh(degrees+90.0f, degrees-90.0f);
			}
			particleEffects.add(fire);
			//electricSound.play(3.0f);
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
	 * Acts as game loop/logic 
	 * checks if phokemon are alive
	 * and proceeds accordingly
	 */
	private void checkPhokemon() {
		if(player1.getCurrentPhokemon().getHealth()<=0) {
			player1.getCurrentPhokemon().setAlive(false);
			//delay death
			Timer.schedule(new Task(){
			    @Override
			    public void run() {
			    	player1.getCurrentPhokemon().getSprite().setAlpha(0.0f);
			    	//play death sound
			    	
			    	//see if there are any phokemon alive
					if(player1.canPlay()) {
						switchPhokemon(true);
					} else {
						setDialog(BATTLE_LOG);
						setNextDialog(BATTLE_LOG);
						updateBattleLog("Player 2 has won");
					}
			    }
			}, 5.0f);
		} if(player2.getCurrentPhokemon().getHealth()<=0) {
			player2.getCurrentPhokemon().setAlive(false);
			//delay death
			Timer.schedule(new Task(){
			    @Override
			    public void run() {
			    	player2.getCurrentPhokemon().getSprite().setAlpha(0.0f);
			    	//play death sound
			    	
			    	//see if there are any phokemon alive
					if(player2.canPlay()) {
						switchPhokemon(false);
					} else {
						setDialog(BATTLE_LOG);
						setNextDialog(BATTLE_LOG);
						updateBattleLog("Player 1 has won");
					}
			    }
			}, 5.0f);
		}
	}
	
	public void switchPhokemon(boolean isPlayer1) {
		if(isPlayer1) {
			player1.switchPhoke();
		}
	}
	
	public void playSuperEffectiveSound() {
		
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
		electricSound.dispose();
		font.dispose();
	}

}
