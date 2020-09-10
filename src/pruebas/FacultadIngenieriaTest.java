package pruebas;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import mundo.FacultadIngenieria;
import mundo.Programa;

class FacultadIngenieriaTest extends TestCase {
	
	private FacultadIngenieria mundo;
	
	private void escenarioUno() {
		mundo = new FacultadIngenieria();
	}
	
	@Test
	public void testIniciarMundo() {
		escenarioUno();
		assertEquals(6, mundo.getProgramas().size());
	}
	
	@Test
	public void testBuscarPrograma() {
		escenarioUno();
		Programa p1 = mundo.buscarPrograma(0, "Ingeniería de Sistemas");
		Programa p2 = mundo.getProgramas().get(0);
		assertEquals(p2, p1);
	}
	
	@Test
	public void testGetDecano() {
		escenarioUno();
		assertEquals("Gonzalo Ulloa", mundo.getDecano());
	}
	
}
