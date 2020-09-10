package mundo;

import java.io.Serializable;
import java.util.ArrayList;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;

/**
 * Clase que representa el programa perteneciente a la Facultad de Ingeniería de la Universidad Icesi
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class Programa implements Serializable {
	
	/**
	 * Atributo que representa la ruta de la imagen del programa
	 */
	private String ruta;
	
	/**
	 * Atributo que representa el nombre del programa
	 */
	private String nombrePrograma;
	
	/**
	 * Atributo que representa el nombre del director del programa
	 */
	private String director;
	
	/**
	 * Relación con la primer materia de programa
	 */
	private Materia primerMateria;
	
	/**
	 * Atributo que representa el número de materias presentes en el programa
	 */
	private int numeroMaterias;
	
	/**
	 * Constructor de la clase Programa
	 * @param nombrePrograma - Nombre del programa
	 * @param director - Nombre del director del programa
	 * @param rut - Ruta de la imagen del programa
	 */
	public Programa(String nombrePrograma, String director, String rut) {
		this.nombrePrograma = nombrePrograma;
		this.director = director;
		this.numeroMaterias = 0;
		this.ruta = rut;
		this.primerMateria = null;
	}
	
	/**
	 * Método que permite saber cuando la lista de materias de programa se encuentra vacia o no
	 * @return True si la lista está vacía y False si no está vacía
	 */
	public boolean estaVacia() {
		if(primerMateria == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Método que permite insertar una materia al programa
	 * @param m - Materia por insertar
	 * @throws ObjetoYaExisteException Se lanaza cuando la materia por insertar ya existe en el programa<br><br>
	 * <b>pre:</b> m != null
	 */
	public void insertarMateria(Materia m) throws ObjetoYaExisteException {
		if(estaVacia()) {
			primerMateria = new Materia(m.getNombre(), m.getCreditos(),null);
			numeroMaterias++;
		} else {
			Materia actual = primerMateria;
			if(actual.getNombre().equalsIgnoreCase(m.getNombre())) {
				throw new ObjetoYaExisteException(m.getNombre());
			}
			while(actual.getSiguienteMateria() !=null) {
				actual = actual.getSiguienteMateria();
				if(actual.getNombre().equalsIgnoreCase(m.getNombre())) {
					throw new ObjetoYaExisteException(m.getNombre());
				}
			}
			actual.setSiguienteMateria(new Materia(m.getNombre(), m.getCreditos(),null));
			numeroMaterias++;
		}
	}

	/**
	 * Método que devuelve un arreglo con los profesores de la materia
	 * @param m - Materia al que pertenecen los profesores
	 * @return Arreglo con los profesores de la materia<br><br>
	 * <b>pre:</b> m != null
	 */
	public ArrayList<Profesor> darListaProfesores(Materia m) {
		if(m!= null) {
			return m.darListaProfesores();
		}else {
			ArrayList<Profesor> profesores = new ArrayList<Profesor>();
			return profesores;
		}
	}
	
	/**
	 * Método que devuelve un arreglo con los estudiantes de un grupo
	 * @param m - Materia al que pertenece el profesor
	 * @param g - Grupo al que pertenecen los estudiantes
	 * @param p - Profesor al que pertenece el grupo
	 * @return Arreglo con los estudiantes de un grupo<br><br>
	 * <b>pre:</b> m != null - g != null - p != null
	 */
	public ArrayList<Estudiante> darListaEstudiantes(Materia m, Grupo g, Profesor p) {
		if(m != null) {
			return m.darListaEstudiantes(g, p);
		}else {
			ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
			return estudiantes;
		}
	}
	
	/**
	 * Método que permite insertar un profesor a una materia
	 * @param m - Materia donde pertenecerá el profesor
	 * @param pro - Programa de la materia
	 * @throws ObjetoYaExisteException Se lanza cuando el profesor a insertar ya existe en la lista de profesores<br><br>
	 * <b>pre:</b> m != null - p != null
	 */
	public void insertarProfesor(Materia m, Profesor pro) throws ObjetoYaExisteException {
		m.agregarProfesor(pro);
	}

	/**
	 * Método que permite eliminar un profesor de una materia
	 * @param m - Materia en donde se eliminará el profesor
	 * @param nombreProfesor - Nombre del profesor a eliminar
	 * @throws ObjetoYaExisteException -
	 * @throws ObjetoNoExisteException Se lanza cuando no hay ningun profesor con el nombre a eliminar<br><br>
	 * <b>pre:</b> materia != null
	 */
	public void eliminarProfesor(Materia m, String nombreProfesor) throws ObjetoYaExisteException, ObjetoNoExisteException {
		m.eliminarProfesor(nombreProfesor);
	}
	
	/**
	 * Método que permite insertar un grupo a un profesor
	 * @param materia - Materia al que pertenece el profesor
	 * @param profesor - Profesor al que pertenecerá el grupo
	 * @param grupo - Grupo a insertar
	 * @throws ObjetoYaExisteException Se lanza cuando el grupo a insertar ya existe en la lista<br><br>
	 * <b>pre:</b> materia != null - profesor != null - grupo != null
	 */
	public void insertarGrupo(Materia materia, Profesor profesor, Grupo grupo) throws ObjetoYaExisteException{
		materia.insertarGrupo(profesor, grupo);
	}
	
	/**
	 * Método que permite eliminar un grupo de un profesor
	 * @param materia - Materia al que pertenece el profesor
	 * @param profesor - Profesor al que pertenece el grupo
	 * @param nombre - Nombre del grupo a eliminar
	 * @throws ObjetoNoExisteException Se lanza cuando no existe ningún grupo con ese nombre<br><br>
	 * <b>pre:</b> materia != null - profesor != null 
	 */
	public void eliminarGrupo(Materia materia, Profesor profesor, String nombre) throws ObjetoNoExisteException{
		materia.eliminarGrupo(profesor, nombre);
	}
	
	/**
	 * Método que permite insertar un estudiante a un grupo
	 * @param e - Estudiante a insertar
	 * @param g - Grupo al que pertenecerá el estudiante
	 * @param p - Profesor al que pertenece el grupo
	 * @param m - Materia al que pertenece el profesor
	 * @throws ObjetoYaExisteException Se lanza cuando el estudiante a insertar ya existe en la lista<br><br>
	 * <b>pre:</b> m != null - p != null g != null - e != null
	 */
	public void insertarEstudiante(Estudiante e, Grupo g, Profesor p, Materia m) throws ObjetoYaExisteException{
		m.insertarEstudiante(e, g, p);
	}
	
	/**
	 * Método que devuelve la raiz de arbol binario de profesores
	 * @param m Materia donde pertenece la raiz
	 * @return El profesor raiz del arbol binario<br><br>
	 * <b>pre:</b> m != null
	 */
	public Profesor darProfesorRaiz(Materia m) {
		return m.getRaizProfesor();
	}
	
	/**
	 * Método que permite localizar la materia anterior a una dado su nombre
	 * @param nombre - Nombre de la materia a buscar
	 * @return Si se halló la materia retorna el anterior y si no returna null
	 */
	public Materia localizarAnterior(String nombre) {
		Materia anterior = null;
		Materia actual = primerMateria;
		while(actual != null && !(actual.getNombre().equals(nombre))) {
			anterior = actual;
			actual = actual.getSiguienteMateria();
		}
		return actual != null ? anterior : null;
	}

	/**
	 * Método que permite eliminar una materia con base en su nombre
	 * @param nombre - Nombre de la materia a eliminar
	 * @throws ObjetoNoExisteException Se lanza cuando la materia con dicho nombre no existe
	 */
	public void eliminarMateria(String nombre) throws ObjetoNoExisteException {
		if(primerMateria == null) {
			throw new ObjetoNoExisteException(nombre);
		} else if (nombre.equals(primerMateria.getNombre())) {
			primerMateria = primerMateria.getSiguienteMateria();
		} else {
			Materia anterior = localizarAnterior(nombre);
			if( anterior == null) {
				throw new ObjetoNoExisteException(nombre);
			}
			anterior.desconectarSiguienteMateria();
		}
		numeroMaterias--;
	}
	
	/**
	 * Método que permite buscar una materia con base en su nombre
	 * @param nombre - Nombre de la materia a buscar
	 * @return Materia buscada
	 * @throws ObjetoNoExisteException Se lanza cuando no existe ninguna materia con dicho nombre
	 */
	public Materia buscarMateria(String nombre) throws ObjetoNoExisteException {
		Materia actual = primerMateria;
		while(actual != null && (!actual.getNombre().equals(nombre))) {
			actual = actual.getSiguienteMateria();
		}
		if(actual == null) {
			throw new ObjetoNoExisteException(nombre);
		}
		return actual;
	}
	
	/**
	 * Método que devuelve la primer materia de la lista enlazada de materias
	 * @param p - Programa donde pertenece la primer materia
	 * @return La primer materia de la lista
	 */
	public Materia getPrimerMateria(Programa p) {
		return primerMateria;
	}
	
	/**
	 * Método que devuelve el número de materias del programa
	 * @return El número de materias del programa
	 */
	public int getNumeroMaterias() {
		return numeroMaterias;
	}
	
	/**
	 * Método que devuelve el nombre del director del programa
	 * @return El nombre del director del programa
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * Método que duelve la ruta de la imagen del programa
	 * @return Ruta de la imagen del programa
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Método que devuelve el nombre del programa
	 * @return Nombre de programa
	 */
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	
	/**
	 * Método que devuelve el primer grupo de la lista enlazada de grupos
	 * @param profesor - Profesor al que pertenece el grupo
	 * @param m - Materia al que pertenece el profesor
	 * @return El primero grupo de la lista<br><br>
	 * <b>pre:</b> m != null y profesor != nul
	 */
	public Grupo getPrimerGrupo(Profesor profesor, Materia m) {
		return m.darPrimerGrupo(profesor);
	}
	
	/**
	 * Método que devuelve un arreglo con las materias del programa
	 * @return Arreglo con las materias del programa
	 */
	public ArrayList<Materia> darMaterias(){
		ArrayList<Materia> materias = new ArrayList<Materia>();
		Materia m = primerMateria;
		while(m != null) {
			materias.add(m);
			m = m.getSiguienteMateria();
		}
		return materias;
	}

	/**
	 * Método que devuelve una cadena de texto con la información de una materia
	 * @return Cadena de texto con la información de la materia
	 */
	public String mostrarInfoMateria() {
		String info = "";
		int uno = 0;
		int dos = 0;
		int tres = 0;
		int cuatro = 0;
		int mas = 0;
		if(numeroMaterias != 0) {
			ArrayList<Materia> informacion = darMaterias();
			for (int i = 0; i < informacion.size(); i++) {
				if(informacion.get(i).getCreditos() == 1) {
					uno++;
				} else if(informacion.get(i).getCreditos() == 2) {
					dos++;
				} else if(informacion.get(i).getCreditos() == 3) {
					tres++;
				} else if(informacion.get(i).getCreditos() == 4) {
					cuatro++;
				} else {
					mas++;
				}
			}
			info = "En total hay " + numeroMaterias + " materias del programa\nEl numero de materias con 1 credito son: " + uno + " materia\nEl numero de materias con 2 creditos son: " + dos + " materias\nEl numero de materias con 3 creditos son: " + tres + " materias\nEl numero de materias con 4 creditos son: " + cuatro + " materias\nEl numero de materias con mas de cuatro creditos son: " + mas + " materias";
		} else {
			info = "No hay materias en este programa";
		}
		return info;
	}
	
}