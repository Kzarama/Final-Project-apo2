package mundo;

import java.io.Serializable;
import java.util.ArrayList;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;

/**
 * Clase que representa la materia de un programa de la facultad de la Universidad Icesi
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class Materia implements Serializable, Comparable<Materia> {
	
	/**
	 * Atributo que representa el nombre de la materia
	 */
	private String nombre;
	
	/**
	 * Atributo que representa el número de créditos de la materia
	 */
	private int creditos;
	
	/**
	 * Atributo que representa el número de profesores de la materia
	 */
	private int numeroProfesores;
	
	/**
	 * Relación con la siguiente materia de la lista enlazada de materias
	 */
	private Materia siguienteMateria;
	
	/**
	 * Relación con la raiz del profesor del árbol binario de profesores
	 */
	private Profesor raizProfesor;
	
	/**
	 * Constructor de la clase Materia
	 * @param nombre - Nombre de la materia
	 * @param creditos - Creditos de la materia
	 * @param m - Siguiente materia
	 */
	public Materia(String nombre, int creditos, Materia m) {
		this.nombre = nombre;
		this.creditos = creditos;
		this.numeroProfesores = 0;
		this.siguienteMateria = m;
		this.raizProfesor = null;
	}
	
	/**
	 * Método que permite agregar un profesor
	 * @param p - Profesor a agregar
	 * @throws ObjetoYaExisteException Se lanza cuando el profesor ya existe en el árbol binario de profesores<br><br>
	 * <b>pre:</b> p != null
	 */
	public void agregarProfesor(Profesor p) throws ObjetoYaExisteException {
		if(raizProfesor == null) {
			raizProfesor = p;
		}else {
			raizProfesor.insertarProfesor(p);
		}
		numeroProfesores++;
	}

	/**
	 * Método que permite eliminar un profesor
	 * @param nombre - Nombre del profesor a eliminar 
	 * @throws ObjetoNoExisteException Se lanza cuando no existe ningún profesor en el ABB con ese nombre
	 * @throws ObjetoYaExisteException -
	 */
	public void eliminarProfesor(String nombre) throws ObjetoNoExisteException, ObjetoYaExisteException {
		if(estaVacia()) {
		} else {
			ArrayList<Profesor> profesores = darListaProfesores();
			boolean encontrado = false;
			for(int i = 0; i < profesores.size() && !encontrado; i++) {
				if(profesores.get(i).getNombre().equals(nombre)) {
					encontrado = true;
					profesores.remove(i);
					
				}
			}
			if(encontrado == false) {
				throw new ObjetoNoExisteException(nombre);
			} else {
				raizProfesor = null;
				numeroProfesores = 0;
				for(int i = 0; i < profesores.size(); i++) {
					Profesor nuevo = new Profesor(profesores.get(i).getNombre(), profesores.get(i).getEdad(), profesores.get(i).getIdentificacion(), profesores.get(i).geteMail()); 
					agregarProfesor(nuevo);
				}
			}
		}
	}
	
	/**
	 * Método que permite eliminar un grupo de un profesor
	 * @param profesor - Profesor al que pertenece el grupo
	 * @param nombre - Nombre del grupo a eliminar
	 * @throws ObjetoNoExisteException Se lanza cuando no existe ningún grupo con ese nombre<br><br>
	 * <b>pre:</b> profesor != null
	 */
	public void eliminarGrupo(Profesor profesor, String nombre) throws ObjetoNoExisteException {
		profesor.eliminarGrupo(nombre);
	}
	
	/**
	 * Método que permite insertar un grupo a un profesor
	 * @param profesor - Profesor al que pertenecerá el grupo
	 * @param grupo - Grupo a insertar
	 * @throws ObjetoYaExisteException Se lanza cuando el grupo a insertar ya existe en la lista<br><br>
	 * <b>pre:</b> profesor != null - grupo != null
	 */
	public void insertarGrupo(Profesor profesor, Grupo grupo) throws ObjetoYaExisteException {
		profesor.insertarGrupo(grupo);
	}
	
	/**
	 * Método que permite insertar un estudiante a un grupo
	 * @param e - Estudiante a insertar
	 * @param g - Grupo al que pertenecerá el estudiante
	 * @param p - Profesor al que pertenece el grupo
	 * @throws ObjetoYaExisteException Se lanza cuando el estudiante a insertar ya existe en la lista<br><br>
	 * <b>pre:</b> p != null g != null - e != null
	 */
	public void insertarEstudiante(Estudiante e, Grupo g, Profesor p) throws ObjetoYaExisteException{
		p.insertarEstudiante(e, g);
	}
	
	/**
	 * Método que permite buscar un profesor del ABB de profesor
	 * @param nombre - Nombre del profesor a buscar
	 * @throws ObjetoNoExisteException Se lanza cuando el profesor a buscar no existe<br><br>
	 * <b>pre:</b> raizProfesor != null
	 */
	public Profesor buscarProfesorPorNombre(String nombre) throws ObjetoNoExisteException{
		Profesor p = raizProfesor.buscarProfesorPorNombre(nombre);
		if(p == null) {
			throw new ObjetoNoExisteException(nombre);
		}else {
			return p;
		}
	}
	
	

	/**
	 * Método que permite verificar si el ABB de profesores se encuentra vacío
	 * @return True si está vacío o False si no está vacío
	 */
	public boolean estaVacia() {
		if(raizProfesor == null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Método que devuelve un arreglo de profesores
	 * @return Arreglo de profesores
	 */
	public ArrayList<Profesor> darListaProfesores() {
		if(raizProfesor == null) {
			return null;
		}else {
			ArrayList<Profesor> profesores = new ArrayList<Profesor>();
			raizProfesor.ordenarPreorden(profesores);
			return profesores;
		}
	}
	
	/**
	 * Método que devuelve un arreglo con los estudiantes de un grupo
	 * @param g - Grupo al que pertenecen los estudiantes
	 * @param p - Profesor al que pertenece el grupo
	 * @return Arreglo con los estudiantes de un grupo<br><br>
	 * <b>pre:</b> g != null - p != null
	 */
	public ArrayList<Estudiante> darListaEstudiantes(Grupo g, Profesor p) {
		if(p != null) {
			return p.darListaEstudiantes(g);
		}else {
			ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
			return estudiantes;
		}
	}
	
	/**
	 * Método que permite desconectar la siguiente materia de la lista enlazada
	 */
	public void desconectarSiguienteMateria() {
		siguienteMateria = siguienteMateria.siguienteMateria;
	}

	/**
	 * Método que devuelve el nombre de la materia
	 * @return El nombre del programa
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que devuelve los créditos de la materia
	 * @return Los créditos del programa
	 */
	public int getCreditos() {
		return creditos;
	}

	/**
	 * Método que devuelve el número de profesores de una materia
	 * @param materia - Materia que tiene los profesores
	 * @return El número de profesores que tiene una materia<br><br>
	 * <b>pre:</b> materia != null
	 */
	public int getNumeroProfesores(Materia materia) {
		return numeroProfesores;
	}

	/**
	 * Método que devuelve la siguiente materia de la lista enlazada
	 * @return Siguiente materia
	 */
	public Materia getSiguienteMateria() {
		return siguienteMateria;
	}

	/**
	 * Método que modifica la siguiente materia de una lista enlazada
	 * @param siguienteMateria - Materia nueva
	 */
	public void setSiguienteMateria(Materia siguienteMateria) {
		this.siguienteMateria = siguienteMateria;
	}

	/**
	 * Método que devuelve el profesor raiz del ABB de profesores
	 * @return El profesor raiz
	 */
	public Profesor getRaizProfesor() {
		return raizProfesor;
	}
	
	/**
	 * Método que devuelve el primero grupo de un profesor de la lista enlazada de grupos
	 * @param p Profesor que tiene los grpos
	 * @return El primer grupo<br><br>
	 * <b>pre:</b> p != null
	 */
	public Grupo darPrimerGrupo(Profesor p) {
		return p.getPrimerGrupo(p);
	}

	/**
	 * Método que devuelve una cadena de texto con la información de los profesores de la materia
	 * @return La información de los profesores de la materia
	 */
	public String darInfoProfesor() {
		String info = "";
		if(numeroProfesores != 0) {
			double promedioEdad = 0;
			for(int i = 0; i < numeroProfesores; i++) {
				promedioEdad += darListaProfesores().get(i).getEdad();
			}
			promedioEdad = promedioEdad / numeroProfesores;
			info = "En total hay " + numeroProfesores + " profesores\nEl promedio de edad de los profesores es: " + promedioEdad + " años";
		} else {
			info = "No hay profesores en esta materia";
		}
		return info;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int compararPorCreditos(Materia m) {
		int num = 0;
		if(creditos < m.getCreditos()) {
			num = -1;
		} else if(creditos > m.getCreditos()) {
			num = 1;
		}
		return num;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int compareTo(Materia arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
