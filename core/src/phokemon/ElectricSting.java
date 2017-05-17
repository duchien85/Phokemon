package phokemon;

public class ElectricSting extends AttackMove{
	public ElectricSting(){
		super(30, 80, new Electric());
	}
	public void doDamage(Phokes opponent){
		int damage = 0;
		for (int i = 0; i < 4; i ++){
			if(Math.random()*100 <= accuracy) {
				System.out.println("blastoise used Double Splash");
				damage += calculateDamage(opponent);
			}
		}
		opponent.setHealth(opponent.getHealth() - damage);
	}
}
