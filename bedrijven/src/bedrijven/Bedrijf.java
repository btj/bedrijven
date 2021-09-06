package bedrijven;

import logicalcollections.LogicalSet;

/**
 * @invar | getEigenaar() != null
 * @invar | getEigenaar().getEigendommen().contains(this)
 */
public class Bedrijf extends Entiteit {

	/**
	 * @invar | eigenaar != null
	 */
	private Entiteit eigenaar;
	
	/**
	 * @invar getEigenaarInternal().getEigendommenInternal().contains(this)
	 * 
	 * @post | result != null
	 * @peerObject (package-level)
	 */
	Entiteit getEigenaarInternal() { return eigenaar; }
	
	/**
	 * @peerObject
	 */
	public Entiteit getEigenaar() { return getEigenaarInternal(); }
	
	/**
	 * @pre | eigenaar != null
	 * @mutates_properties | eigenaar.getEigendommen()
	 * @post | getEigenaar() == eigenaar
	 * @post | eigenaar.getEigendommen().equals(LogicalSet.plus(old(eigenaar.getEigendommen()), this))
	 */
	public Bedrijf(Entiteit eigenaar) {
		this.eigenaar = eigenaar;
		eigenaar.addEigendom(this);
	}
	
	/**
	 * @throws IllegalArgumentException | nieuweEigenaar == null
	 * @throws IllegalArgumentException | nieuweEigenaar == getEigenaar()
	 * @mutates_properties | getEigenaar(), getEigenaar().getEigendommen(), nieuweEigenaar.getEigendommen()
	 * @post | getEigenaar() == nieuweEigenaar
	 * @post | old(getEigenaar()).getEigendommen().equals(LogicalSet.minus(old(getEigenaar().getEigendommen()), this))
	 * @post | nieuweEigenaar.getEigendommen().equals(LogicalSet.plus(old(nieuweEigenaar.getEigendommen()), this)) 
	 */
	public void draagOverAan(Entiteit nieuweEigenaar) {
		if (nieuweEigenaar == null)
			throw new IllegalArgumentException("`nieuweEigenaar` is null");
		if (nieuweEigenaar == eigenaar)
			throw new IllegalArgumentException("`nieuweEigenaar` is al eigenaar");
		
		eigenaar.removeEigendom(this);
		eigenaar = nieuweEigenaar;
		eigenaar.addEigendom(this);
	}
	
	@Override
	public Persoon getUltiemeEigenaarA() {
		return eigenaar.getUltiemeEigenaarA();
	}
	
	public Persoon getUltiemeEigenaarB() {
		Entiteit eigenaar = this.eigenaar;
		while (eigenaar instanceof Bedrijf) {
			eigenaar = ((Bedrijf)eigenaar).eigenaar;
		}
		return (Persoon)eigenaar;
	}
	
}
