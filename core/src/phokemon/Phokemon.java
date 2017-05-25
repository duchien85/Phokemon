package phokemon;


public class Phokemon {

	public static void main(String[] args) {
		
		Player Player1 = new Player(1,new Barnizard(), new Mossy());
		Player Player2 = new Player(2, new WaterSnake(), new LawnClippings());
		Player1.setSelectedPhokemon(0);
		Player2.setSelectedPhokemon(0);
		do{
			Player1.decideMove();
			if(Player1.isAttack()) Player1.pickAttack();
			Player2.decideMove();
			if(Player2.isAttack()) Player2.pickAttack();

			if(Player1.isAttack() == false && Player2.isAttack() == false){
				if(Player1.getCurrentPhokemon().getSpeed() > Player2.getCurrentPhokemon().getSpeed()){
					Player1.switchPhoke();
					Player2.switchPhoke();
				}
				else{
					Player2.switchPhoke();
					Player1.switchPhoke();
				}
			}
			else if(Player1.isAttack() == false && Player2.isAttack() == true){
				Player1.switchPhoke();
				Player2.attack(Player2.getCurrentPhokemon(), Player1.getCurrentPhokemon());
			}
			else if(Player1.isAttack() == true && Player2.isAttack() == false){
				Player2.switchPhoke();
				Player1.attack(Player1.getCurrentPhokemon(), Player2.getCurrentPhokemon());
			}
			else{
				if(Player1.getCurrentPhokemon().getSpeed() > Player2.getCurrentPhokemon().getSpeed()){
					Player1.attack(Player1.getCurrentPhokemon(), Player2.getCurrentPhokemon());
					if(Player2.getCurrentPhokemon().IsAlive()){
						Player2.attack(Player2.getCurrentPhokemon(), Player1.getCurrentPhokemon());
					}
				}
				else{
					Player2.attack(Player2.getCurrentPhokemon(), Player1.getCurrentPhokemon());
					if(Player1.getCurrentPhokemon().IsAlive()){
						Player1.attack(Player1.getCurrentPhokemon(), Player2.getCurrentPhokemon());
					}
				}
			}

			if(Player1.getCurrentPhokemon().IsAlive()){
			if (Player1.getCurrentPhokemon().burned){
				Player1.getCurrentPhokemon().setHealth((int)(Player1.getCurrentPhokemon().getHealth() * .85));
				System.out.println(Player1.getCurrentPhokemon().getName() + " took burn damage");
			}
			}
			if(Player2.getCurrentPhokemon().IsAlive()){
			if (Player2.getCurrentPhokemon().burned){
				Player2.getCurrentPhokemon().setHealth((int)(Player2.getCurrentPhokemon().getHealth() * .85));
				System.out.println(Player2.getCurrentPhokemon().getName() + " took burn damage");
			}
			}
			if(Player1.getCurrentPhokemon().IsAlive()){
				System.out.println("Player 1 current phoke " + Player1.getCurrentPhokemon().getName());
				System.out.println(Player1.getCurrentPhokemon().getName() + "Health: " + Player1.getCurrentPhokemon().getHealth());
			}
			else{
				System.out.println(Player1.getCurrentPhokemon().getName() + " has fainted");
				if(Player1.canPlay()){
				Player1.switchPhoke();
				}
			}
			if(Player2.getCurrentPhokemon().IsAlive()){
				System.out.println("Player 2 current phoke " + Player2.getCurrentPhokemon().getName());
				System.out.println(Player2.getCurrentPhokemon().getName() + "Health: " + Player2.getCurrentPhokemon().getHealth());
			}
			else{
				System.out.println(Player2.getCurrentPhokemon().getName() + " has fainted");
				if(Player2.canPlay()){
				Player2.switchPhoke();
				}
			}
			
		}while(Player1.canPlay() && Player2.canPlay());
	}
}
