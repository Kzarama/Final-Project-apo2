package pruebas;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import mundo.Estudiante;

class EstudianteTest extends TestCase{

	private Estudiante estudiante;
	
	private void escenarioUno() {
		estudiante = new Estudiante("Jorge", "1143878270", 19, "jorantony@msn.com", 4.6);
	}
	
	@Test
	public void testDarNombre() {
		escenarioUno();
		assertEquals("Jorge", estudiante.getNombre());
	}
	
	@Test
	public void testDarIdentificacion() {
		escenarioUno();
		assertEquals("1143878270", estudiante.getIdentificacion());
	}
	
	@Test
	public void testDarEdad() {
		escenarioUno();
		assertEquals(19, estudiante.getEdad());
	}
	
	@Test
	public void testDarPromedio() {
		escenarioUno();
		assertEquals(4.6, estudiante.getPromedio());
	}

}
