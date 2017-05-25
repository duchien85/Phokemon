package com.apjava.phokemon;

import com.apjava.phokemon.screens.BattleScreen;
import com.apjava.phokemon.screens.MenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @version 5-12-17
 * @author Jacob Murry
 *
 */
public class Phokemon extends Game {
	
	@Override
	public void create () {
		this.setScreen(new MenuScreen(this));
	}
	
	@Override
	public void dispose () {
		
	}
}
