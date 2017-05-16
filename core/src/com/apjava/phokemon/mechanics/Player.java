package com.apjava.phokemon.mechanics;

import java.util.List;

public class Player {
	private List<Phokes> phokemonList;
	private int selectedPhokemon = 0;
	private boolean isFirstPlayer;
	
	/**
	 * Create a new player object
	 * @param isFirstPlayer true if on left
	 * @param phokemons any number of phokemon objects this player gets
	 */
	public Player(boolean isFirstPlayer, Phokes... phokemons) {
		this.isFirstPlayer = isFirstPlayer;
		for(int i=0; i<phokemons.length; i++)
			phokemonList.add(phokemons[i]);
	}
	
	/**
	 * Determine if player can still play if the player has alive phokemon
	 * @return true if player has phokemon to play with.
	 */
	public boolean canPlay() {
		return !phokemonList.isEmpty();
	}
	
	/**
	 * call this to get phokemon to render or call attacks on
	 * @return the players selected phokemon
	 */
	public Phokes getCurrentPhokemon() {
		try {
			return phokemonList.get(selectedPhokemon);
		} catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return if this is the player on the left
	 */
	public boolean isFirstPlayer() {
		return isFirstPlayer;
	}
}
