package com.apjava.phokemon.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class HealthBar extends Actor implements Disposable{
	private ShapeRenderer bgRenderer, fgRenderer;
	private int currentHealth, maxHealth, targetHealth;
	private Music lowHealthMusic;
	/**
	 * Create a new health bar located at (x,y) sized w by h
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param w width of the bar
	 * @param h height of the bar
	 * @param currentHealth current health value
	 * @param maxHealth the full health of the phokemon
	 */
	public HealthBar(float x, float y,float w, float h, int currentHealth, int maxHealth) {
		bgRenderer = new ShapeRenderer();
		fgRenderer = new ShapeRenderer();
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.targetHealth = currentHealth;
		this.setX(x);
		this.setY(y);
		this.setWidth(w);
		this.setHeight(h);
		lowHealthMusic = Gdx.audio.newMusic(Gdx.files.internal("music/lowhp.wav"));
		lowHealthMusic.setVolume(8.0f);
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		//calculate how much the health can change
		int healthDifference = currentHealth-targetHealth;
		if(healthDifference<0) {
			currentHealth++;
		} else if(healthDifference>0) {
			currentHealth--;
		}
		batch.end();
		bgRenderer.begin(ShapeType.Filled);
		bgRenderer.setColor(Color.GRAY);
		bgRenderer.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		bgRenderer.end();
		fgRenderer.begin(ShapeType.Filled);
		if(((double) currentHealth/ maxHealth)<0.2) {
			fgRenderer.setColor(Color.RED);
			if(!lowHealthMusic.isPlaying())
				lowHealthMusic.play();
		} else if(((double) currentHealth/ maxHealth)<0.4)
			fgRenderer.setColor(Color.YELLOW);
		else
			fgRenderer.setColor(Color.GREEN);
		fgRenderer.rect(this.getX(), this.getY(), this.getWidth()/((float)maxHealth/currentHealth), this.getHeight());
		fgRenderer.end();
		batch.begin();
	}
	
	/**
	 * Adjust health right away
	 * @param health
	 */
	public void setCurrentHealth(int health) {
		this.currentHealth = (health>0) ? health : 1;
		this.targetHealth = getCurrentHealth();
	}
	
	/**
	 * Adjust health bar so that is slowly changes
	 * @param health
	 */
	public void setCurrentHealthSmooth(int health) {
		this.targetHealth = (health>0) ? health : 1;
	}
	
	/**
	 * @return current health value
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}

	@Override
	public void dispose() {
		lowHealthMusic.dispose();
	}
}
