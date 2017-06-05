package com.apjava.phokemon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Jacob Murry
 * @version 5-18-17
 */
public class MenuScreen implements Screen {
	private Game game;
	private SpriteBatch batch;
	private Texture bgImg;
	private Stage stage;
	private float width, height;
	private LabelStyle labelstyle;
	private Music music;
	private BitmapFont font;
	private Sound buttonSound;
	
	public MenuScreen(Game game1) {
		this.game = game1;
		batch = new SpriteBatch();
		stage = new Stage();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		//background image
		bgImg = new Texture("menuBG.png");
		//music
		music = Gdx.audio.newMusic(Gdx.files.internal("music/mainmenumusic.mp3"));
		music.setLooping(true);
		music.play();
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		//FONT AND LABELS
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gameboyfont.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) (height/8);
		font = generator.generateFont(parameter);
		labelstyle = new LabelStyle();
		labelstyle.font = font;
		labelstyle.fontColor = Color.BLACK;
		Label playbtn = new Label("Play", labelstyle);
		playbtn.setPosition(width/2-playbtn.getWidth()/2, height/2-playbtn.getHeight()/2);
		playbtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				music.stop();
				buttonSound.play();
		    	game.setScreen(new PickingScreen(game));	
		    	
		    }
		});
		stage.addActor(playbtn);
		final Screen currentScreen = this;
		Label aboutbtn = new Label("About", labelstyle);
		aboutbtn.setPosition(width/2-aboutbtn.getWidth()/2, height/2-aboutbtn.getHeight()/2-playbtn.getHeight()-width/50);
		aboutbtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//music.stop();
				buttonSound.play();
				game.setScreen(new AboutScreen(game, currentScreen));	
		    	
		    }
		});
		stage.addActor(aboutbtn);
		
	}

	@Override
	public void show() {
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.8f, 0.84f, 0.84f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bgImg, 0, 0, width, height);
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
		font.dispose();
	}


}
