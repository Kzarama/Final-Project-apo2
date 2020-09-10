package pruebas;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;
import junit.framework.TestCase;
import mundo.Materia;
import mundo.Programa;

class ProgramaTest extends TestCase{
	
	private Programa programa;
	
	private void escenarioUno() {
		programa = new Programa("Ingeniería de Sistemas", "Nohra Villegas", "img/sistemas.jpg");
	}
	
	private void escenarioDos() throws ObjetoYaExisteException {
		programa = new Programa("Ingeniería de Sistemas", "Nohra Villegas", "img/sistemas.jpg");
		programa.insertarMateria(new Materia("Apo1", 3, null));
	}
	
	private void escenarioTres() throws ObjetoYaExisteException {
		programa = new Programa("Ingeniería de Sistemas", "Nohra Villegas", "img/sistemas.jpg");
		programa.insertarMateria(new Materia("Apo1", 3, null));
		programa.insertarMateria(new Materia("Apo2", 3, null));
		programa.insertarMateria(new Materia("Estructuras", 3, null));
	}
	
	@Test
	public void testDarNombrePrograma() {
		escenarioUno();
		assertEquals("Ingeniería de Sistemas", programa.getNombrePrograma());
	}
	
	@Test
	public void testDarNombreDirector() {
		escenarioUno();
		assertEquals("Nohra Villegas", programa.getDirector());
	}
	
	@Test
	public void testDarRutaPrograma() {
		escenarioUno();
		assertEquals("img/sistemas.jpg", programa.getRuta());
	}
	
	@Test
	public void testEstaVaciaTrue() {
		escenarioUno();
		assertTrue(programa.estaVacia());
	}
	
	@Test
	public void testEstaVaciaFalse() throws ObjetoYaExisteException {
		escenarioDos();
		assertFalse(programa.estaVacia());
	}
	
	@Test
	public void testInsertarMateriaTrue() {
		escenarioUno();
		int tamano1 = programa.getNumeroMaterias();
		try {
			programa.insertarMateria(new Materia("Apo1", 3, null));
		}catch(ObjetoYaExisteException e) {
			e.getMessage();
		}
		int tamano2 = programa.getNumeroMaterias();
		assertEquals(tamano2, tamano1+1);
	}
	
	@Test
	public void testInsertarMateriaFalse() throws ObjetoYaExisteException {
		escenarioDos();
		String error = "";
		try {
			programa.insertarMateria(new Materia("Apo1", 3, null));
		}catch(ObjetoYaExisteException e) {
			error =  e.getMessage();
		}
		assertEquals("El objeto con el nombre Apo1 ya existe en esta lista", error);
	}
	
	@Test
	public void testLocalizarAnterior() throws ObjetoYaExisteException{
		escenarioTres();
		Materia m1 = programa.localizarAnterior("Apo2");
		Materia m2 = null;
		try {
			m2 = programa.buscarMateria("Apo1");
		}catch(ObjetoNoExisteException e) {
			
		}
		assertEquals(m2, m1);
	}
	
	@Test
	public void testEliminarMateria() throws ObjetoYaExisteException{
		escenarioTres();
		int viejo = programa.getNumeroMaterias();
		try{
			programa.eliminarMateria("Apo1");
		}catch(ObjetoNoExisteException e) {
			
		}
		int actual = programa.getNumeroMaterias();
		assertEquals(viejo-1, actual);
	}
	
	@Test
	public void testDarMaterias() throws ObjetoYaExisteException {
		escenarioTres();
		ArrayList<Materia> materias = new ArrayList<Materia>();
		materias.add(new Materia("Apo1", 3, null));
		materias.add(new Materia("Apo2", 3, null));
		materias.add(new Materia("Estructuras", 3, null));
		
		ArrayList<Materia> materias2 = programa.darMaterias();
		
		assertEquals(materias.get(0).getNombre(), materias2.get(0).getNombre());
		assertEquals(materias.get(1).getNombre(), materias2.get(1).getNombre());
		assertEquals(materias.get(2).getNombre(), materias2.get(2).getNombre());
	}
	
	@Test
	public void testBuscarMateriaTrue() throws ObjetoYaExisteException {
		escenarioTres();
		Materia m2 = null;
		try {
			m2 = programa.buscarMateria("Apo1");
		}catch(ObjetoNoExisteException o) {
			
		}
		assertEquals("Apo1", m2.getNombre());
	}
	
	@Test
	public void testBuscarMateriaFalse() throws ObjetoYaExisteException {
		escenarioTres();
		String error = "";
		try {
			programa.buscarMateria("Apo3");
		}catch(ObjetoNoExisteException o) {
			error = o.getMessage();
		}
		assertEquals("El objeto con el nombre Apo3 no existe", error);
	}
	
	@Test
	public void testMostrarInfoMateriaTrue() throws ObjetoYaExisteException{
		escenarioTres();
		String prueba = programa.mostrarInfoMateria();
		assertEquals("En total hay 3 materias del programa\nEl numero de materias con 1 credito son: 0 materia\nEl numero de materias"
				+ " con 2 creditos son: 0 materias\nEl numero de materias con 3 creditos son: 3 materias\nEl numero de materias con"
				+ " 4 creditos son: 0 materias\nEl numero de materias con mas de cuatro creditos son: 0 materias", prueba);
	}
	
	@Test
	public void testMostrarInfoMateriaFalse() throws ObjetoYaExisteException{
		escenarioUno();
		String prueba = programa.mostrarInfoMateria();
		assertEquals("No hay materias en este programa", prueba);
	}

}
