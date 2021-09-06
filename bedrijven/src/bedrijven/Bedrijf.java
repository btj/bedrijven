package bedrijven;

import logicalcollections.LogicalSet;

/**
 * @invar | getEigenaar() != null
 * @invar | getEigenaar().getEigendommen().contains(this)
 */
public class Bedrijf extends Entiteit {

	/**
	 * @invar | eigenaar != null
	 * @invar | eigenaar.eigendommen.contains(this)
	 * 
	 * @peerObject
	 */
	Entiteit eigenaar;
	
	/**
	 * @peerObject
	 */
	public Entiteit getEigenaar() { return eigenaar; }
	
	/**
	 * @pre | eigenaar != null
	 * @mutates_properties | eigenaar.getEigendommen()
	 * @post | getEigenaar() == eigenaar
	 * @post | eigenaar.getEigendommen().equals(LogicalSet.plus(old(eigenaar.getEigendommen()), this))
	 */
	public Bedrijf(Entiteit eigenaar) {
		this.eigenaar = eigenaar;
		eigenaar.eigendommen.add(this);
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
		
		eigenaar.eigendommen.remove(this);
		eigenaar = nieuweEigenaar;
		eigenaar.eigendommen.add(this);
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
