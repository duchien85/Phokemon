package phokemon;

/**
 * @version 5-13-17
 * @author Jacob Murry
 *
 */
public interface PhokeType {
	public String toString();
	
	/**
	 * Determines if an attack is effective against this phokemon
	 * @param op
	 * @return whether passed in type is effective against child
	 */
	public boolean isSuperEffective(PhokeType op);
	
	/**
	 * Determines if an attack is not effective against this phokemon
	 * @param op
	 * @return whether passed in type is not effective against child
	 */
	public boolean isNotEffective(PhokeType op);
}
