package phokemon;

public class Flying implements PhokeType{
	@Override
	public String toString() {
		return "flying";
	}

	@Override
	public boolean isSuperEffective(PhokeType op) {
		return op.toString().equals("electric");
	}

	@Override
	public boolean isNotEffective(PhokeType op) {
		return  op.toString().equals("grass");
	}

}
