package mundo;

import java.io.Serializable;
import java.util.ArrayList;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;

/**
 * Clase que representa el profesor de la Facultad de Ingeniería de la Universidad Icesi
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class Profesor extends Persona implements Comparable<Profesor>, Serializable{
	
	/**
	 * Atributo que representa el número de cursos que tiene el profesor
	 */
	private int numeroCursos;
	
	/**
	 * Atributo que representa el primer grupo de la lista enlazada de grupos que tiene el profesor
	 */
	private Grupo primerGrupo;
	
	/**
	 * Relación que representa el profesor izquierdo y el profesor derecho del ABB de profesores
	 */
	private Profesor profesorIzquierdo, profesorDerecho;
	
	/**
	 * Constructor de la clase Profesor
	 * @param nombre - Nombre del profesor
	 * @param edad - Edad del profesor
	 * @param identificacion - Identificación del profesor
	 * @param eMail - eMail del profesor
	 */
	public Profesor(String nombre, int edad, String identificacion, String eMail) {
		super(nombre, identificacion, edad, eMail);
		profesorIzquierdo = null;
		profesorDerecho = null;
		primerGrupo = null;
		numeroCursos = 0;
	}
	
	/**
	 * Método que permite insertar un estudiante a un grupo
	 * @param e - Estudiante a insertar
	 * @param g - Grupo al que pertenecerá el estudiante
	 * @throws ObjetoYaExisteException Se lanza cuando el estudiante a insertar ya existe en la lista<br><br>
	 * <b>pre:</b> g != null - e != null
	 */
	public void insertarEstudiante(Estudiante e, Grupo g) throws ObjetoYaExisteException{
		g.agregarEstudiante(e);
	}
	
	/**
	 * Método que permite verificar si el ABB de profesores está vacío
	 * @return True si se encuentra vacío o False si no
	 */
	public boolean estaVacia() {
		if(primerGrupo == null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Método que permite insertar un grupo a un profesor
	 * @param p - Grupo a insertar
	 * @throws ObjetoYaExisteException Se lanza cuando el grupo a insertar ya existe en la lista<br><br>
	 * <b>pre:</b> grupo != null
	 */
	public void insertarGrupo(Grupo p) throws ObjetoYaExisteException{
		if(estaVacia()) {
			primerGrupo = new Grupo(p.getNombreGrupo(), p.getHorario(),null);
			numeroCursos++;
		} else {
			Grupo actual = primerGrupo;
			if(actual.getNombreGrupo().equalsIgnoreCase(p.getNombreGrupo())) {
				throw new ObjetoYaExisteException(p.getNombreGrupo());
			}
			while(actual.getSiguienteGrupo() !=null) {
				actual = actual.getSiguienteGrupo();
				if(actual.getNombreGrupo().equalsIgnoreCase(p.getNombreGrupo())) {
					throw new ObjetoYaExisteException(p.getNombreGrupo());
				}
			}
			actual.setSiguienteGrupo(new Grupo(p.getNombreGrupo(), p.getHorario(), null));
			numeroCursos++;
		}
	}
	
	/**
	 * Método que permite buscar un grupo
	 * @param nombre - Nombre del grupo a buscar
	 * @return Grupo encontrado
	 * @throws ObjetoNoExisteException Se lanza cuando no se encontró ningún grupo con dicho nombre
	 */
	public Grupo buscarGrupo(String nombre) throws ObjetoNoExisteException {
		Grupo actual = primerGrupo;
		while(actual != null && (!actual.getNombreGrupo().equals(nombre))) {
			actual = actual.getSiguienteGrupo();
		}
		if(actual == null) {
			throw new ObjetoNoExisteException(nombre);
		}
		return actual;
	}
	
	/**
	 * Método que permite eliminar un grupo 
	 * @param nombre - Nombre del grupo
	 * @throws ObjetoNoExisteException Se lanza cuando no exsite ningún grupo con el nombre dado
	 */
	public void eliminarGrupo(String nombre) throws ObjetoNoExisteException {
		if(primerGrupo == null) {
			throw new ObjetoNoExisteException(nombre);
		} else if (nombre.equals(primerGrupo.getNombreGrupo())) {
			primerGrupo = primerGrupo.getSiguienteGrupo();
		} else {
			Grupo anterior = localizarAnterior(nombre);
			if( anterior == null) {
				throw new ObjetoNoExisteException(nombre);
			}
			anterior.desconectarSiguienteGrupo();
		}
		numeroCursos--;
	}
	
	/**
	 * Método que permite localizar el anterior grupo de el nombre de un grupo dado su nombre
	 * @param nombre - Nombre del grupo a buscar
	 * @return Grupo anterior 
	 */
	public Grupo localizarAnterior(String nombre) {
		Grupo anterior = null;
		Grupo actual = primerGrupo;
		while(actual != null && !(actual.getNombreGrupo().equals(nombre))) {
			anterior = actual;
			actual = actual.getSiguienteGrupo();
		}
		return actual != null ? anterior : null;
	}
	
	/**
	 * Método que permite ordenar de forma preorden el arreglo de profesores
	 * @param p Arreglo de profesores <br><br>
	 * <b>pre:</b> El arreglo de profesores está inicializado
	 */
	public void ordenarPreorden(ArrayList<Profesor> p) {
		p.add(this);
		if(profesorIzquierdo != null) {
			profesorIzquierdo.ordenarPreorden(p);
		}
		if(profesorDerecho != null) {
			profesorDerecho.ordenarPreorden(p); 
		}
	}
	
	/**
	 * Método que permite eliminar un profesor del ABB de profesores dado su nombre
	 * @param unNombre -Nombre del profesor a eliminar
	 * @return Profesor eliminado
	 */
	public Profesor eliminarProfesor(String unNombre)
	{
		if(esHoja()) {
			return null;
		}
		if(super.getNombre().compareToIgnoreCase(unNombre) == 0) {
			if(profesorIzquierdo == null) {
				return profesorDerecho;
			}
			
			if(profesorDerecho == null) {
				return profesorIzquierdo;
			}
			
			Profesor sucesor = profesorDerecho.darMenorProfesor();
			profesorDerecho = profesorDerecho.eliminarProfesor(sucesor.getNombre());
			sucesor.profesorIzquierdo = profesorIzquierdo;
			sucesor.profesorDerecho = profesorDerecho;
			return sucesor;
			
		}else if(super.getNombre().compareToIgnoreCase(unNombre) > 0) {
			profesorIzquierdo = profesorIzquierdo.eliminarProfesor(unNombre);
		}else {
			profesorDerecho = profesorDerecho.eliminarProfesor(unNombre);
		}
		
		return this;
	}
	
	/**
	 * Método que permite insertar un nuevo profesor
	 * @param nuevo - Profesor a agregar
	 * @throws ObjetoYaExisteException Se lanza cuando el profesor a agregar ya existe en el ABB<br><br>
	 * <b>pre:</b> nuevo != null
	 */
	public void insertarProfesor(Profesor nuevo) throws ObjetoYaExisteException{
		if(compareTo(nuevo) == 0) {
			throw new ObjetoYaExisteException(nuevo.getNombre());
		}
		if(compareTo(nuevo) > 0) {
			if(profesorIzquierdo == null) {
				profesorIzquierdo = nuevo;
			}else {
				profesorIzquierdo.insertarProfesor(nuevo);
			}
		}else {
			if(profesorDerecho == null) {
				profesorDerecho = nuevo;
			}else {
				profesorDerecho.insertarProfesor(nuevo);
			}
		}
	}
	
	/**
	 * Método que permite buscar un profesor dado su nombre
	 * @param nombre - Nombre del profesor a buscar
	 * @return Profesor encontrado<br><br>
	 * <b>pre:</b> raiz de ABB de profesores != null
	 */
	public Profesor buscarProfesorPorNombre(String nombre) {
		if(super.getNombre().compareToIgnoreCase(nombre) == 0) {
			return this;
		}else if(super.getNombre().compareToIgnoreCase(nombre) > 0) {
			return (profesorIzquierdo == null) ? null : profesorIzquierdo.buscarProfesorPorNombre(nombre);
		}else {
			return (profesorDerecho == null) ? null : profesorDerecho.buscarProfesorPorNombre(nombre);
		}	
	}
	
	/**
	 * Método que verifica si un profesor es una hoja o no
	 * @return True si el profesor es una hoja y False si no es una hoja
	 */
	public boolean esHoja() {
		return profesorIzquierdo == null && profesorDerecho == null;
	}
	
	/**
	 * Método que devuelve el menor profesor del ABB de profesores 
	 * @return Menor profesor
	 */
	public Profesor darMenorProfesor() {
		return (profesorIzquierdo ==  null) ? this : profesorIzquierdo.darMenorProfesor();
	}
	
	/**
	 * Método que devuelve el arreglo de estudiantes de un grupo
	 * @param g - Grupo al que peretenecen los estudiantes
	 * @return Arreglo de estudiantes<br><br>
	 */
	public ArrayList<Estudiante> darListaEstudiantes(Grupo g) {
		if(g != null) {
			return g.darListaEstudiantes();
		}else {
			ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
			return estudiantes;
		}
	}
	
	/**
	 * Método que devuelve el primer grupo de la lista enlazada de grupos de un profesor
	 * @param p - Profesor que tiene los grupos
	 * @return El primer grupo
	 */
	public Grupo getPrimerGrupo(Profesor p) {
		return primerGrupo;
	}
	
	/**
	 * Método que devuelve el número de cursos que tiene el profesor
	 * @return El número de cursos del profesor
	 */
	public int getNumeroCursos() {
		return numeroCursos;
	}

	/**
	 * Método que devuelve la información del grupo
	 * @return Una cadena de texto con la información del grupo
	 */
	public String darInfoGrupo() {
		String info = "El número de grupos es: " + numeroCursos;
		return info;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Profesor p) {
		if(super.getNombre().compareToIgnoreCase(p.getNombre()) == 0) {
			return 0;
		}else if(super.getNombre().compareToIgnoreCase(p.getNombre()) < 0) {
			return -1;
		}
		return 1;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return super.getNombre();
	}
}
