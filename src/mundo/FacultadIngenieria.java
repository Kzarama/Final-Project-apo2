package mundo;

import java.io.Serializable;
import java.util.ArrayList;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;

/**
 * Clase principal del modelo que representa la Facultad de Ingenier�a de la Universidad Icesi
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class FacultadIngenieria implements Serializable{

	/**
	 * Atributo que representa el nombre del decano de la facultad de Ingenier�a
	 */
	private String decano;
	
	/**
	 * Arreglo que contiene los programas de la facultad de Ingenier�a
	 */
	private ArrayList<Programa> programas;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private OrdenarPorNombre ordenarPorNombre;
	
	/**
	 * Constuctor de la clase principal de modelo
	 */
	public FacultadIngenieria() {
		programas = new ArrayList<Programa>();
		this.decano = "Gonzalo Ulloa";
		iniciarMundo();
	}
	
	/**
	 * M�todo que permite instanciar los seis programas que contiene la facultad de ingenier�a
	 */
	public void iniciarMundo() {
		programas.add(new Programa("Ingenier�a de Sistemas", "Nohra Villegas", "img/sistemas.jpg"));
		programas.add(new Programa("Ingenier�a Industrial", "Efra�n Pinto", "img/industrial.jpg"));
		programas.add(new Programa("Dise�o Industrial", "Doris Albear", "img/dise�oIndustrial.jpeg"));
		programas.add(new Programa("Dise�o de Medios Interactivos", "Javier Aguirre", "img/dmi.jpg"));
		programas.add(new Programa("Ingenier�a Telem�tica", "Lina Quintero", "img/telematica.jpg"));
		programas.add(new Programa("Ingenier�a Bioqu�mica", "Carolina Orozco", "img/bioquimica.jpg"));
	}

	/**
	 * M�todo que devuelve el arreglo de programas de la facultad
	 * @return Arreglo de los programas
	 */
	public ArrayList<Programa> getProgramas() {
		return programas;
	}
	
	/**
	 * M�todo que devuelve un arreglo con las materias de un programa
	 * @param pg - Programa que contiene las materias
	 * @return Arreglo de materias del programa
	 */
	public ArrayList<Materia> darListaMaterias(Programa pg){
		return pg.darMaterias();
	}
	
	/**
	 * M�todo que devuelve un arreglo con los profesores de una materia y un programa
	 * @param m Materia a la que pertenecen los profesores
	 * @param pg Programa al que pertenecen los profesores
	 * @return Arreglo con los profesores
	 */
	public ArrayList<Profesor> darListaProfesores(Materia m, Programa pg){
		return pg.darListaProfesores(m);
	}
	
	/**
	 * M�todo que permite buscar una materia de un programa
	 * @param p - Programa donde pertenece la materia
	 * @param nombre - Nombre de la materia a buscar
	 * @return Materia buscada
	 * @throws ObjetoNoExisteException Se lanza cuando no existe ninguna materia con dicho nombre
	 */
	public Materia buscarMateria(Programa p, String nombre) throws ObjetoNoExisteException{
		return p.buscarMateria(nombre);
	}
	
	/**
	 * M�todo que devuelve un arreglo con los estudiantes de un grupo, un profesor, una materia y un programa
	 * @param pg Programa al que pertenecen los estudiantes
	 * @param m Materia al que pertenecen los estudiantes
	 * @param p Profesor al que pertenecen los estudiantes
	 * @param g Grupo al que pertenecen los estudiantes
	 * @return El arreglo con los estudiantes
	 */
	public ArrayList<Estudiante> darListaEstudiantes(Programa pg, Materia m, Profesor p, Grupo g) {
		return pg.darListaEstudiantes(m, g, p);
	}
	
	/**
	 * M�todo que permite insertar una materia a un programa
	 * @param p Programa por insertar materia
	 * @param m Materia a insertar
	 * @throws ObjetoYaExisteException Se lanza cuando ya existe una materia con el mismo nombre<br><br>
	 * <b>pre:</b> materia != null
	 */
	public void insertarMateria(Programa p, Materia m) throws ObjetoYaExisteException{
		p.insertarMateria(m);
	}
	
	/**
	 * M�todo que permite eliminar una materia de un programa
	 * @param p - Programa al que pertenecer� la materia
	 * @param nombre - Nombre de la materia por eliminar
	 * @throws ObjetoNoExisteException Se lanza cuando no existe una materia con dicho nombre
	 */
	public void eliminarMateria(Programa p, String nombre) throws ObjetoNoExisteException{
		p.eliminarMateria(nombre);
	}
	
	/**
	 * M�todo que permite insertar un profesor a una materia
	 * @param m - Materia en donde se insertar� el profesor
	 * @param pg - Programa en donde pertenece la materia
	 * @param p - Profesor a insertar
	 * @throws ObjetoYaExisteException Se lanza cuando ya existe un profesor con el mismo nombre<br><br>
	 * <b>pre:</b> profesor != null - y materia != null
	 */
	public void insertarProfesor(Materia m, Programa pg, Profesor p) throws ObjetoYaExisteException {
		pg.insertarProfesor(m, p);
	}
	
	/**
	 * M�todo que permite eliminar un profesor de una materia
	 * @param m - Materia en donde se eliminar� el profesor
	 * @param pg - Programa en donde pertenece la materia
	 * @param nombreProfesor - Nombre del profesor a eliminar
	 * @throws ObjetoYaExisteException -
	 * @throws ObjetoNoExisteException Se lanza cuando no hay ningun profesor con el nombre a eliminar<br><br>
	 * <b>pre:</b> materia != null
	 */
	public void eliminarProfesor(Materia m, Programa pg, String nombreProfesor) throws ObjetoNoExisteException, ObjetoYaExisteException{
		pg.eliminarProfesor(m, nombreProfesor);
	}
	
	/**
	 * M�todo que permite insertar un grupo a un profesor
	 * @param programa - Programa al que pertenece la materia
	 * @param materia - Materia al que pertenece el profesor
	 * @param profesor - Profesor al que pertenecer� el grupo
	 * @param grupo - Grupo a insertar
	 * @throws ObjetoYaExisteException Se lanza cuando el grupo a insertar ya existe en la lista<br><br>
	 * <b>pre:</b> materia != null - profesor != null - grupo != null
	 */
	public void insertarGrupo(Programa programa, Materia materia, Profesor profesor, Grupo grupo) throws ObjetoYaExisteException{
		programa.insertarGrupo(materia, profesor, grupo);
	}
	
	/**
	 * M�todo que permite eliminar un grupo de un profesor
	 * @param programa - Programa al que pertenece la materia
	 * @param materia - Materia al que pertenece el profesor
	 * @param profesor - Profesor al que pertenece el grupo
	 * @param nombre - Nombre del grupo a eliminar
	 * @throws ObjetoNoExisteException Se lanza cuando no existe ning�n grupo con ese nombre<br><br>
	 * <b>pre:</b> materia != null - profesor != null 
	 */
	public void eliminarGrupo(Programa programa, Materia materia, Profesor profesor, String nombre) throws ObjetoNoExisteException{
		programa.eliminarGrupo(materia, profesor, nombre);
	}
	
	/**
	 * M�todo que permite insertar un estudiante a un grupo
	 * @param e - Estudiante a insertar
	 * @param g - Grupo al que pertenecer� el estudiante
	 * @param p - Profesor al que pertenece el grupo
	 * @param m - Materia al que pertenece el profesor
	 * @param pg - Programa al que pertenece la materia
	 * @throws ObjetoYaExisteException Se lanza cuando el estudiante a insertar ya existe en la lista<br><br>
	 * <b>pre:</b> m != null - p != null g != null - e != null
	 */
	public void insertarEstudiante(Estudiante e, Grupo g, Profesor p, Materia m, Programa pg) throws ObjetoYaExisteException{
		pg.insertarEstudiante(e, g, p, m);
	}
	
	/**
	 * M�todo que permite buscar un programa del arreglo del programas
	 * @param numero Nosici�n donde empezar� a buscar
	 * @param buscado Nombre del programa a buscar
	 * @return Programa encontrado<br><br>
	 * <b>pre:</b> Arreglo de programas != null
	 */
	public Programa buscarPrograma(int numero, String buscado) {
		if(programas.get(numero) != null) {
			if(buscado.equals(programas.get(numero).getNombrePrograma())) {
				return programas.get(numero);
			} else {
				return buscarPrograma(numero + 1, buscado);
			}
		}
		return null;
	}
	
	/**
	 * M�todo que devuelve el nombre del decano de la facultad de ingenier�a
	 * @return Nombre del decano
	 */
	public String getDecano() {
		return decano;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Materia> ordenarPorNombre(Programa pg){
		ArrayList<Materia> materias = darListaMaterias(pg);
		for(int i = 1; i < materias.size(); i++) {
			ordenarPorNombre = new OrdenarPorNombre();
			Materia porInsetar = materias.get(i);
			for(int j = i; j > 0; j--) {
				Materia actual = materias.get(j-1);
				if(ordenarPorNombre.compare(actual, porInsetar) > 0) {
					materias.set(j, actual);
					materias.set(j-1, porInsetar);
				}
			}
		}
		return materias;
	}
	
	public ArrayList<Materia> ordenarPorCreditos(Programa pg){
		ArrayList<Materia> materias = darListaMaterias(pg);
		for(int i = materias.size(); i > 0; i--) {
			for(int j = 0; j < (i-1); j++) {
				if(materias.get(j).compararPorCreditos(materias.get(j+1)) > 0) {
					Materia temp = materias.get(j);
					materias.set(j, materias.get(j+1));
					materias.set(j+1, temp);
				}
			}
		}
		return materias;
	}
	
}
