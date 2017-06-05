package phokemon;


import java.util.List;

/**
 * 5-10-17
 * @author Jacob Murry, Rishabh, Nick, Chintan, Nick
 *
 */
public abstract class Character {
	private int health, maxHealth, attack, defense, speed;
	private String name;
	private List<AttackMove> moveList;
	private boolean alive = true;
	
	/**
	 * Assign template parameters
	 * @param health
	 * @param maxHealth
	 * @param attack
	 * @param defense
	 * @param speed
	 * @param name
	 * @param moves
	 */
	public Character(int health, int maxHealth, int attack, int defense, int speed, String name, List<AttackMove> moves) {
		this.health = health;
		this.maxHealth = maxHealth;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.name = name;
		this.moveList = moves;
	}
	
	public boolean isAlive() {
		if(health < 0){
			alive = false;
		}
		return alive;
	}
	public void setAlive(boolean isAlive) {
		this.alive = isAlive;
	}
	public void doDamage(int damage) {
		
	}
	public int getHealth(){
		return health;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AttackMove> getMoves() {
		return moveList;
	}
	public void setMoves(List<AttackMove> moves) {
		this.moveList = moves;
	}
	
}