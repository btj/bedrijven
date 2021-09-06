package bedrijven;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class EntiteitUtils {
	
	public static Iterator<Entiteit> eigenaarsIterator(Bedrijf bedrijf) {
		return new Iterator<Entiteit>() {
			Entiteit entiteit = bedrijf;
			@Override
			public boolean hasNext() {
				return entiteit instanceof Bedrijf;
			}
			@Override
			public Entiteit next() {
				entiteit = ((Bedrijf)entiteit).getEigenaar();
				return entiteit;
			}
		};
	}
	
	public static void forEachEigenaar(Bedrijf bedrijf, Consumer<? super Entiteit> consumer) {
		Entiteit entiteit = bedrijf;
		while (entiteit instanceof Bedrijf) {
			entiteit = ((Bedrijf)entiteit).getEigenaar();
			consumer.accept(entiteit);
		}
	}
	
	public static Stream<Entiteit> getBladEigendommen(Entiteit entiteit) {
		return entiteit.getEigendommen().stream().flatMap(eigendom ->
			eigendom.getEigendommen().isEmpty() ? Stream.of(eigendom) : getBladEigendommen(eigendom));
	}

}
