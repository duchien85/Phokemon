package com.apjava.phokemon.mechanics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * 
 * @author Jacob Murry
 *@version 5-17-17
 */
public class BattleLabel extends Label {
	private CharSequence targetText = "";
	private int targetIndex = 0;
	private int counter = 0;
	private int frequency = 6;

	/**
	 * Create a label to track the moves
	 * @param text of the label
	 * @param style of the label
	 */
	public BattleLabel(CharSequence text, LabelStyle style) {
		super(text, style);
	}

	
	public void setTextAnimated(CharSequence text) {
		super.setText("");
		targetIndex = 0;
		targetText = text;
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		super.draw(batch, alpha);
		counter++;
		if(counter%frequency==0 && targetIndex<targetText.length()) {
			setText(getText()+""+targetText.charAt(targetIndex));
			targetIndex++;
			counter = 0;
		}
	}
}
