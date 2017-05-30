package phokemon;
/**
 * @version 5-12-17
 * @author Rishabh
 *
 */
public class Fire implements PhokeType{
	@Override
	public String toString() {
		return "fire";
	}

	@Override
	public boolean isSuperEffective(PhokeType op) {
		return op.toString().equals("water");
	}

	@Override
	public boolean isNotEffective(PhokeType op) {
		return  op.toString().equals("grass");
	}

}
