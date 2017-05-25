package com.apjava.phokemon.screens;

import java.util.ArrayList;
import java.util.List;

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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import phokemon.BattleLabel;
import phokemon.HealthBar;

/**
 * @version 5-22-17
 * @author Jacob Murry
 *
 */
public class PickingScreen implements Screen{

	private Game game;
	private SpriteBatch batch;
	private Texture bgImg;
	private Stage stage;
	private float width, height;
	private LabelStyle labelstyle;
	private Music music;
	private Sound buttonSound;
	private BitmapFont font;
	private List<String> phokemonList;
	private int selectedPhokemon = 0;
	
	public PickingScreen(Game game) {
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
		bgImg = new Texture("menuBG.png");
		//music
		music = Gdx.audio.newMusic(Gdx.files.internal("music/battlemusic1.mp3"));//other music
		music.setLooping(true);
		music.setVolume(0.5f);
		//music.play();
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		//FONT AND LABELS
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gameboyfont.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) (height/18);
		font = generator.generateFont(parameter);
		labelstyle = new LabelStyle();
		labelstyle.font = font;
		labelstyle.fontColor = Color.BLACK;
		
		//Add every phokemon to the list
		phokemonList = new ArrayList<String>();
		phokemonList.add("Water Snake");
		phokemonList.add("Barnizard");
		phokemonList.add("Birdude");
		phokemonList.add("Blazebat");
		phokemonList.add("Uglurchin");
		//
		
		final BattleLabel phokemonLabel = new BattleLabel("Select your phokemon", labelstyle);
		phokemonLabel.setTextAnimated("Select phokemon\nPlayer 1");
		phokemonLabel.setAlignment(Align.center);
		phokemonLabel.setPosition(width/2-phokemonLabel.getWidth()/2, height/4);
		phokemonLabel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSound.play();
				//transition phokemons
				
			}
		});
		stage.addActor(phokemonLabel);
		
		Image leftImage = new Image(new Texture("leftarrow.png"));
		leftImage.setSize(width/8, height/5);
		leftImage.setOrigin(0, leftImage.getHeight()/2);
		leftImage.setPosition(width/5-leftImage.getWidth(), height/5.5f);
		leftImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSound.play();
				//transition phokemons
				if(selectedPhokemon>0) {
					selectedPhokemon--;
					phokemonLabel.setTextAnimated(phokemonList.get(selectedPhokemon));
					
				} else {
					selectedPhokemon=phokemonList.size()-1;
					phokemonLabel.setTextAnimated(phokemonList.get(selectedPhokemon));
				}
			}
		});
		stage.addActor(leftImage);
		Image rightImage = new Image(new Texture("rightarrow.png"));
		rightImage.setSize(width/8, height/5);
		rightImage.setOrigin(0, rightImage.getHeight()/2);
		rightImage.setPosition(width-width/5, height/5.5f);
		rightImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonSound.play();
				//transition phokemons
				if(selectedPhokemon<phokemonList.size()-1) {
					selectedPhokemon++;
					phokemonLabel.setTextAnimated(phokemonList.get(selectedPhokemon));		
				} else {
					selectedPhokemon=0;
					phokemonLabel.setTextAnimated(phokemonList.get(selectedPhokemon));
				}
			}
		});
		stage.addActor(rightImage);
		
		final Label battle = new Label("Battle", labelstyle);
		
    
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.8f, 0.84f, 0.84f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bgImg, 0, 0, width, height);
		
		batch.end();
		//decide and set visable and invisible what has to renderered
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
		font.dispose();
	}

}
