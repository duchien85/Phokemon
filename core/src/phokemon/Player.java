package phokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.apjava.phokemon.screens.BattleScreen;

public class Player {
	private List<Phokes> phokemonList;
	private int selectedPhokemon = 0;
	private int playerNum;
	private boolean isAttack = false;
	private Scanner f = new Scanner(System.in);
	private BattleScreen battleScreen;
	
	public boolean isAttack() {
		return isAttack;
	}

	public void setAttack(boolean attack) {
		this.isAttack = attack;
	}

	/**
	 * Create a new player object
	 * @param isFirstPlayer true if on left
	 * @param phokemons any number of phokemon objects this player gets
	 */
	public Player(BattleScreen screen, int playerNum, Phokes... phokemons) {
		this.playerNum = playerNum;
		this.battleScreen = screen;
		phokemonList = new ArrayList<Phokes>();
		for(int i=0; i<phokemons.length; i++)
			phokemonList.add(phokemons[i]);
	}
	
	/**
	 * Determine if player can still play if the player has alive phokemon
	 * @return true if player has phokemon to play with.
	 */
	public boolean canPlay() {
		boolean canPlay = false;
		for(int o = 0; o < phokemonList.size(); o ++)
		if (phokemonList.get(o).isAlive){
			canPlay = true;
		}
			return canPlay;
	
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
	public Phokes getPhokemon(int target){
		return phokemonList.get(target);
	}
	public void switchPhoke(){
		int x = 0;
		//if(getCurrentPhokemon().isAlive) System.out.println(getCurrentPhokemon().getName() + " has fainted");
		do{
		System.out.println("Player " + playerNum + ", select the number slot of the phokemon you want to switch to.");
		x = f.nextInt();
		if(getPhokemon(x).isAlive){
			setSelectedPhokemon(x);
		}
		else{
			System.out.println("That phokemon is Knocked Out");
		}
		}while(!getPhokemon(x).isAlive);
	}
	
	/**
	 * 
	 * @param move index to use
	 * @param self phokemon making the move
	 * @param opponent phokemon to apply damage to
	 * @return true of attack is successful
	 */
	public boolean attack(int move, Phokes self, Phokes opponent){
		boolean hit = self.getMoves().get(move).attack(battleScreen, opponent);
		battleScreen.setNextDialog((battleScreen.getDialog()==BattleScreen.PLAYER1_ATTACK) ? BattleScreen.PLAYER2 : BattleScreen.PLAYER1);
		battleScreen.setDialog(BattleScreen.BATTLE_LOG);
		if(hit) {
			String moveName = self.getMoves().get(move).toString();
			battleScreen.updateBattleLog(moveName + " was used against " + opponent.getName());
		} else {
			battleScreen.updateBattleLog(self.getName()+"'s attack missed");
		}
		return hit;
	}
	public int getSelectedPhokemon() {
		return selectedPhokemon;
	}

	public void setSelectedPhokemon(int selectedPhokemon) {
		this.selectedPhokemon = selectedPhokemon;
	}

	/**
	 * @return if this is the player on the left
	 */
	public void decideMove(){
		System.out.println("Player " + playerNum + " select 1 to attack");
		if(f.nextInt() == 1){
			isAttack = true;
		}
	}
	public int playerNum() {
		return playerNum;
	}
	
	public List<Phokes> getPhokesList() {
		return phokemonList;
	}
}
