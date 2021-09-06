package bedrijven;

import java.util.HashSet;
import java.util.Set;

import logicalcollections.LogicalSet;

/**
 * @invar | getEigendommen() != null
 * @invar | getEigendommen().stream().allMatch(eigendom -> eigendom.getEigenaar() == this)
 */
public abstract class Entiteit {
	
	/**
	 * @invar | eigendommen != null
	 * @invar | eigendommen.stream().allMatch(eigendom -> eigendom != null)
	 * 
	 * @representationObject
	 */
	private final Set<Bedrijf> eigendommen = new HashSet<>();
	
	/**
	 * @invar | getEigendommenInternal().stream().allMatch(eigendom -> eigendom.getEigenaarInternal() == this)
	 * 
	 * @creates | result
	 * @post | result != null
	 * @post | result.stream().allMatch(eigendom -> eigendom != null)
	 * @peerObjects (package-level)
	 */
	Set<Bedrijf> getEigendommenInternal() { return Set.copyOf(eigendommen); }
	
	/**
	 * @peerObjects
	 */
	public Set<Bedrijf> getEigendommen() { return getEigendommenInternal(); }
	
	Entiteit() {}
	
	public abstract Persoon getUltiemeEigenaarA();
	
	/**
	 * @pre | eigendom != null
	 * @mutates | this
	 * @post | getEigendommenInternal().equals(LogicalSet.plus(old(getEigendommenInternal()), eigendom))
	 */
	void addEigendom(Bedrijf eigendom) {
		eigendommen.add(eigendom);
	}
	
	/**
	 * @pre | eigendom != null
	 * @mutates | this
	 * @post | getEigendommenInternal().equals(LogicalSet.minus(old(getEigendommenInternal()), eigendom))
	 */
	void removeEigendom(Bedrijf eigendom) {
		eigendommen.remove(eigendom);
	}

}
