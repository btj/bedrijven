package bedrijven;

import java.util.HashSet;
import java.util.Set;

/**
 * @invar | getEigendommen() != null
 * @invar | getEigendommen().stream().allMatch(eigendom -> eigendom.getEigenaar() == this)
 */
public abstract class Entiteit {
	
	/**
	 * @invar | eigendommen != null
	 * @invar | eigendommen.stream().allMatch(eigendom -> eigendom != null && eigendom.eigenaar == this)
	 * 
	 * @representationObject
	 * @peerObjects
	 */
	final Set<Bedrijf> eigendommen = new HashSet<>();
	
	/**
	 * @peerObjects
	 */
	public Set<Bedrijf> getEigendommen() { return Set.copyOf(eigendommen); }
	
	Entiteit() {}
	
	public abstract Persoon getUltiemeEigenaarA();

}
