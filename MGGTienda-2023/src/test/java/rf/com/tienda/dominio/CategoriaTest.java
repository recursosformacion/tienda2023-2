package rf.com.tienda.dominio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import rf.com.tienda.exception.DomainException;

class CategoriaTest {

	final String NOMBRE_OK="Esto esta bien para categoria";
	final String NOMBRE_LARGO51="Esto esta bien para categoria gdtsmdkcidpolaqwermsi";
	final String NOMBRE_LARGO52="Esto esta bien para categoria  gdtsmdkcidpolaqwermsi  dsflksjdfhsdlf lsd";

	static Categoria cat;
	
	@BeforeAll
	public static void inicio() {
		cat = new Categoria();
	}
	
	
	@Disabled
	void testIsValid() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCat_nombre() throws DomainException {
		cat.setCat_nombre(NOMBRE_OK);
		assertEquals(cat.getCat_nombre(), NOMBRE_OK);
	}
	@Test
	void testSetCat_nombre1() throws DomainException {
		Assertions.assertThrows(DomainException.class, () -> {
			cat.setCat_nombre(NOMBRE_LARGO51);
		});		
	}
	@Test
	void testSetCat_nombre2() throws DomainException {
		Assertions.assertThrows(DomainException.class, () -> {
			cat.setCat_nombre(NOMBRE_LARGO52);
		});	
		
	}

	@Test
	void testSetCat_descripcion() {
		cat.setCat_descripcion(NOMBRE_OK);
		assertEquals(cat.getCat_descripcion(), NOMBRE_OK);
	}


}
