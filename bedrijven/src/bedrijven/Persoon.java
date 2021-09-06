package bedrijven;

public class Persoon extends Entiteit {
	
	/**
	 * @post | getEigendommen().isEmpty()
	 */
	public Persoon() {}
	
	@Override
	public Persoon getUltiemeEigenaarA() {
		return this;
	}

}
