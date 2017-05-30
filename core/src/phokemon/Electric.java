package phokemon;
/**
 * @version 5-12-17
 * @author Rishabh
 *
 */
public class Electric implements PhokeType{
	@Override
	public String toString() {
		return "electric";
	}

	@Override
	public boolean isSuperEffective(PhokeType op) {
		return op.toString().equals("nothing");
	}

	@Override
	public boolean isNotEffective(PhokeType op) {
		return  op.toString().equals("water");
	}
}
