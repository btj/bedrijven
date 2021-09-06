package bedrijven.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import bedrijven.Bedrijf;
import bedrijven.Entiteit;
import bedrijven.EntiteitUtils;
import bedrijven.Persoon;

class BedrijvenTest {

	@Test
	void test() {
		Persoon larryPage = new Persoon();
		assertEquals(Set.of(), larryPage.getEigendommen());
		
		Bedrijf google = new Bedrijf(larryPage);
		assertEquals(larryPage, google.getEigenaar());
		assertEquals(Set.of(google), larryPage.getEigendommen());
		
		Bedrijf alphabet = new Bedrijf(larryPage);
		assertEquals(Set.of(alphabet, google), larryPage.getEigendommen());
		
		google.draagOverAan(alphabet);
		assertEquals(alphabet, google.getEigenaar());
		assertEquals(Set.of(alphabet), larryPage.getEigendommen());
		assertEquals(Set.of(google), alphabet.getEigendommen());
		
		assertEquals(larryPage, google.getUltiemeEigenaarA());
		assertEquals(larryPage, google.getUltiemeEigenaarB());
		
		Set<Entiteit> eigenaars = new HashSet<>();
		for (Iterator<Entiteit> i = EntiteitUtils.eigenaarsIterator(google); i.hasNext(); )
			eigenaars.add(i.next());
		assertEquals(Set.of(alphabet, larryPage), eigenaars);
		
		eigenaars.clear();
		EntiteitUtils.forEachEigenaar(google, e -> eigenaars.add(e));
		assertEquals(Set.of(alphabet, larryPage), eigenaars);
		
		Bedrijf sideProject = new Bedrijf(larryPage);
		assertEquals(Set.of(sideProject, google), EntiteitUtils.getBladEigendommen(larryPage).collect(Collectors.toSet()));
	}

}
