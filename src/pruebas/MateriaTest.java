package pruebas;

import org.junit.jupiter.api.Test;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;
import junit.framework.TestCase;
import mundo.Materia;
import mundo.Profesor;

class MateriaTest extends TestCase{

	private Materia materia;
	
	private void escenarioUno() {
		materia = new Materia("Apo2", 3, null);
	}
	
	private void escenarioDos() throws ObjetoYaExisteException {
		materia = new Materia("Apo2", 3, null);
		materia.agregarProfesor(new Profesor("Cristian", 26, "9999", "cristian@msn.com"));
	}
	
	@Test
	public void testDarNombre() {
		escenarioUno();
		assertEquals("Apo2", materia.getNombre());
	}
	
	@Test
	public void testDarCreditos() {
		escenarioUno();
		assertEquals(3, materia.getCreditos());
	}
	
	@Test
	public void testAgregarProfesorTrue() throws ObjetoYaExisteException{
		escenarioUno();
		int tamano1 = materia.getNumeroProfesores(materia);
		materia.agregarProfesor(new Profesor("Cristian", 26, "9999", "cristian@msn.com"));
		int tamano2 = materia.getNumeroProfesores(materia);
		assertEquals(tamano1+1, tamano2);
	}
	
	@Test
	public void testAgregarProfesorFalse() throws ObjetoYaExisteException {
		escenarioDos();
		String error = "";
		try {
			materia.agregarProfesor(new Profesor("Cristian", 26, "9999", "cristian@msn.com"));
		}catch(ObjetoYaExisteException e) {
			error =  e.getMessage();
		}
		assertEquals("El objeto con el nombre Cristian ya existe en esta lista", error);
	}
	
	@Test
	public void testEliminarProfesor() throws ObjetoYaExisteException{
		escenarioDos();
		int tamanio1 = materia.getNumeroProfesores(materia);
		try{
			materia.eliminarProfesor("Cristian");
		}catch(ObjetoNoExisteException o) {
			
		}
		int tamanio2 = materia.getNumeroProfesores(materia);
		assertEquals(tamanio1-1, tamanio2);
	}
	
	@Test
	public void testBuscarProfesorPorNombreTrue() throws ObjetoYaExisteException {
		escenarioDos();
		Profesor p2 = null;
		try {
			p2 = materia.buscarProfesorPorNombre("Cristian");
		}catch(ObjetoNoExisteException o) {
			
		}
		assertEquals("Cristian", p2.getNombre());
	}
	
	@Test
	public void testBuscarProfesorPorNombreFalse() throws ObjetoYaExisteException {
		escenarioDos();
		String error = "";
		try {
			materia.buscarProfesorPorNombre("Barrios");
		}catch(ObjetoNoExisteException o) {
			error = o.getMessage();
		}
		assertEquals("El objeto con el nombre Barrios no existe", error);
	}
	
	@Test
	public void testEstaVaciaTrue() {
		escenarioUno();
		assertTrue(materia.estaVacia());
	}
	
	@Test
	public void testEstaVaciaFalse() throws ObjetoYaExisteException {
		escenarioDos();
		assertFalse(materia.estaVacia());
	}
	
	@Test
	public void testdarInfoProfesorTrue() throws ObjetoYaExisteException{
		escenarioDos();
		String prueba = materia.darInfoProfesor();
		assertEquals("En total hay 1 profesores\nEl promedio de edad de los profesores es: 26.0 años", prueba);
	}
	
	@Test
	public void testDarInfoProfesorFalse() throws ObjetoYaExisteException{
		escenarioUno();
		String prueba = materia.darInfoProfesor();
		assertEquals("No hay profesores en esta materia", prueba);
	}

}
