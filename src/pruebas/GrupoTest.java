package pruebas;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;
import excepciones.PromedioNoEncontradoException;
import junit.framework.TestCase;
import mundo.Estudiante;
import mundo.Grupo;

class GrupoTest extends TestCase{

	private Grupo grupo;
	
	private void escenarioUno() {
		grupo = new Grupo("01", "Martes y Jueves de 9:00am - 11:00 am", null);
	}
	
	private void escenarioDos() throws ObjetoYaExisteException{
		grupo = new Grupo("01", "Martes y Jueves de 9:00am - 11:00 am", null);
		grupo.agregarEstudiante(new Estudiante("Jorge", "1143878270", 19, "jorantony@msn.com", 4.6));
	}
	
	private void escenarioTres() throws ObjetoYaExisteException{
		grupo = new Grupo("01", "Martes y Jueves de 9:00am - 11:00 am", null);
		grupo.agregarEstudiante(new Estudiante("Camilo", "1143878270", 20, "camilo@msn.com", 4.2));
		grupo.agregarEstudiante(new Estudiante("Zarna", "1143878270", 18, "sarna@msn.com", 4.0));
		grupo.agregarEstudiante(new Estudiante("Armando", "1143878270", 19, "jorantony@msn.com", 4.6));
	}
	
	@Test
	public void testDarNombre() {
		escenarioUno();
		assertEquals("01", grupo.getNombreGrupo());
	}
	
	@Test
	public void testDarHorario() {
		escenarioUno();
		assertEquals("Martes y Jueves de 9:00am - 11:00 am", grupo.getHorario());
	}
	
	@Test
	public void testDarNumeroEstudiantes() throws ObjetoYaExisteException{
		escenarioDos();
		assertEquals(1, grupo.getNumeroEstudiantes());
	}
	
	@Test
	public void testEstaVaciaTrue() {
		escenarioUno();
		assertTrue(grupo.estaVacia());
	}
	
	@Test
	public void testEstaVaciaFalse() throws ObjetoYaExisteException {
		escenarioDos();
		assertFalse(grupo.estaVacia());
	}
	
	@Test
	public void testAgregarEstudianteTrue() throws ObjetoYaExisteException {
		escenarioDos();
		int tamanio1 = grupo.getNumeroEstudiantes();
		grupo.agregarEstudiante(new Estudiante("Kevin", "119282434", 19, "kevin.com", 2.1));
		int tamanio2 = grupo.getNumeroEstudiantes();
		assertEquals(tamanio1+1, tamanio2);
	}
	
	@Test
	public void testAgregarEstudianteFalse() throws ObjetoYaExisteException {
		escenarioDos();
		String error = "";
		try {
			grupo.agregarEstudiante(new Estudiante("Jorge", "1143878270", 19, "jorantony@msn.com", 4.6));
		}catch(ObjetoYaExisteException e) {
			error = e.getMessage();
		}
		assertEquals("El objeto con el nombre Jorge ya existe en esta lista", error);
	}
	
	@Test
	public void testDarListaEstudiantesPreOrden() throws ObjetoYaExisteException{
		escenarioTres();
		ArrayList<Estudiante> estudiantes1 = new ArrayList<Estudiante>();
		estudiantes1.add(new Estudiante("Camilo", "1143878270", 20, "camilo@msn.com", 4.2));
		estudiantes1.add(new Estudiante("Armando", "1143878270", 19, "jorantony@msn.com", 4.6));
		estudiantes1.add(new Estudiante("Zarna", "1143878270", 18, "sarna@msn.com", 4.0));
		
		ArrayList<Estudiante> estudiantes2 = grupo.darListaEstudiantes();
		
		assertEquals(estudiantes1.get(0).getNombre(), estudiantes2.get(0).getNombre());
		assertEquals(estudiantes1.get(1).getNombre(), estudiantes2.get(1).getNombre());
		assertEquals(estudiantes1.get(2).getNombre(), estudiantes2.get(2).getNombre());
	}
	
	@Test
	public void testBuscarEstudiantePorPromedioTrue() throws ObjetoYaExisteException {
		escenarioTres();
		double[] promedios = new double[grupo.getNumeroEstudiantes()];
		for(int i = 0; i<grupo.darListaEstudiantes().size(); i++){
			promedios[i] = grupo.darListaEstudiantes().get(i).getPromedio();
		}
		try{
			assertTrue(grupo.buscarBinarioEstudiantePorPromedio(4.2, promedios));
		}catch(PromedioNoEncontradoException e){
			e.getMessage();
		}
	}
	
	@Test
	public void testBuscarEstudiantePorPromedioFalse() throws ObjetoYaExisteException {
		escenarioTres();
		String error = "";
		double[] promedios = new double[grupo.getNumeroEstudiantes()];
		for(int i = 0; i<grupo.darListaEstudiantes().size(); i++){
			promedios[i] = grupo.darListaEstudiantes().get(i).getPromedio();
		}
		try{
			grupo.buscarBinarioEstudiantePorPromedio(3.2, promedios);
		}catch(PromedioNoEncontradoException e){
			error = e.getMessage();
		}
		assertEquals("No se ha encontrado ningún estudiante con el promedio 3.2", error);
	}
	
	@Test
	public void testEliminarEstudiante() throws ObjetoYaExisteException{
		escenarioTres();
		int tamanio1 = grupo.getNumeroEstudiantes();
		try {
			grupo.eliminarEstudiante("Camilo");
		}catch(ObjetoNoExisteException e) {
			e.printStackTrace();
		}
		int tamanio2 = grupo.getNumeroEstudiantes();
		assertEquals(tamanio1-1, tamanio2);
	}
	
	@Test
	public void testDarInfoEstudiantes() throws ObjetoYaExisteException{
		escenarioTres();
		assertEquals("Hay 3 estudiantes en este grupo\nEl promedio del grupo es: 4.0", grupo.darInfoEstudiantes());
	}

}
