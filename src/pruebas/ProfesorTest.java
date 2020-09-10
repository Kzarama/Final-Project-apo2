package pruebas;


import org.junit.jupiter.api.Test;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;
import junit.framework.TestCase;
import mundo.Grupo;
import mundo.Profesor;

class ProfesorTest extends TestCase{
	
	private Profesor profesor;
	
	private void escenarioUno() {
		profesor = new Profesor("Cristian", 26, "999", "cristian@msn.com");
	}
	
	private void escenarioDos() throws ObjetoYaExisteException{
		profesor = new Profesor("Cristian", 26, "999", "cristian@msn.com");
		profesor.insertarGrupo(new Grupo("01", "Martes y Jueves de 2:00pm a 4:00pm", null));
	}
	
	
	@Test
	public void testDarNombre() {
		escenarioUno();
		assertEquals("Cristian", profesor.getNombre());
	}
	
	@Test
	public void testDarEdad() {
		escenarioUno();
		assertEquals(26, profesor.getEdad());
	}
	
	@Test
	public void testDarId() {
		escenarioUno();
		assertEquals("999", profesor.getIdentificacion());
	}
	
	@Test
	public void testDarEmail() {
		escenarioUno();
		assertEquals("cristian@msn.com", profesor.geteMail());
	}
	
	@Test
	public void testEstaVaciaTrue() {
		escenarioUno();
		assertTrue(profesor.estaVacia());
	}
	
	@Test
	public void testEstaVaciaFalse() throws ObjetoYaExisteException {
		escenarioDos();
		assertFalse(profesor.estaVacia());
	}
	
	@Test
	public void testDarNumeroGrupos() throws ObjetoYaExisteException{
		escenarioDos();
		assertEquals(1, profesor.getNumeroCursos());
	}
	
	@Test
	public void testInsertarGrupoTrue() throws ObjetoYaExisteException{
		escenarioDos();
		int tamano1 = profesor.getNumeroCursos();
		profesor.insertarGrupo(new Grupo("05", "Lunes y Miercoles de 9:00am - 11:00 am", null));
		int tamano2 = profesor.getNumeroCursos();
		assertEquals(tamano1+1, tamano2);
	}
	
	@Test
	public void testInsertarGrupoFalse() throws ObjetoYaExisteException {
		escenarioDos();
		String error = "";
		try {
			profesor.insertarGrupo(new Grupo("01", "Martes y Jueves de 2:00pm a 4:00pm", null));
		}catch(ObjetoYaExisteException e) {
			error =  e.getMessage();
		}
		assertEquals("El objeto con el nombre 01 ya existe en esta lista", error);
	}
	
	@Test
	public void testBuscarGrupoTrue() throws ObjetoYaExisteException {
		escenarioDos();
		Grupo m2 = null;
		try {
			m2 = profesor.buscarGrupo("01");
		}catch(ObjetoNoExisteException o) {
			
		}
		assertEquals("01", m2.getNombreGrupo());
	}
	
	@Test
	public void testBuscarGrupoFalse() throws ObjetoYaExisteException {
		escenarioDos();
		String error = "";
		try {
			profesor.buscarGrupo("05");
		}catch(ObjetoNoExisteException o) {
			error = o.getMessage();
		}
		assertEquals("El objeto con el nombre 05 no existe", error);
	}
	
	@Test
	public void testDarInfoGrupo() throws ObjetoYaExisteException{
		escenarioDos();
		assertEquals("El número de grupos es: 1", profesor.darInfoGrupo());
	}

}
