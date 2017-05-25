package phokemon;

public class StatBoost implements PhokeType {
	
	@Override
	public String toString() {
		return "statboost";
	}

	@Override
	public boolean isSuperEffective(PhokeType op) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNotEffective(PhokeType op) {
		// TODO Auto-generated method stub
		return false;
	}

}
