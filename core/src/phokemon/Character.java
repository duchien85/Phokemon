package phokemon;


import java.util.List;

/**
 * 5-10-17
 * @author Jacob Murry, Rishabh A
 *
 */
public abstract class Character {
	protected int health, maxHealth, attack, defense, speed;
	protected String name;
	//protected String status;
	protected List<AttackMove> moves;
	protected boolean isAlive = true;
	public boolean IsAlive() {
		if(health < 0){
			isAlive = false;
		}
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
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
		return moves;
	}
	public void setMoves(List<AttackMove> moves) {
		this.moves = moves;
	}
	
}