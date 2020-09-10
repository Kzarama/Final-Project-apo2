package mundo;

import java.io.Serializable;
import java.util.ArrayList;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;

/**
 * Clase que representa el estudiante de la Facultad de Ingeniería de la Universidad Icesi
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class Estudiante extends Persona implements Comparable<Estudiante>, Serializable{
	
	/**
	 * Atributo que representa el promedio del estudiante
	 */
	private double promedio;
	
	/**
	 * Relación que representa el estudiante izquierdo y el derecho derecho del ABB de estudiantes
	 */
	private Estudiante estudianteIzquierdo, estudianteDerecho;

	/**
	 * Constructor de la clase Estudiante
	 * @param nombre - Nombre del estudiante
	 * @param identificacion - Identificación del estudiante
	 * @param edad - Edad del estudiante
	 * @param eMail - eMail del estudiante
	 * @param promedio - Promedio del estudiante
	 */
	public Estudiante(String nombre, String identificacion, int edad, String eMail, double promedio) {
		super(nombre, identificacion, edad, eMail);
		this.promedio = promedio;
		estudianteIzquierdo = null;
		estudianteDerecho = null;
	}
	
	/**
	 * Método que permite insertar un nuevo estudiante al ABB de estudiantes
	 * @param nuevo - Estudiante a insertar
	 * @throws ObjetoYaExisteException Se lanza cuando el estudiante a agregar ya existe en el ABB de estudiantes<br><br>
	 * <b>pre:</b> nuevo != null
	 */
	public void insertarEstudiante(Estudiante nuevo) throws ObjetoYaExisteException{
		if(compareTo(nuevo) == 0) {
			throw new ObjetoYaExisteException(nuevo.getNombre());
		}
		if(compareTo(nuevo) > 0) {
			if(estudianteIzquierdo == null) {
				estudianteIzquierdo = nuevo;
			}else {
				estudianteIzquierdo.insertarEstudiante(nuevo);
			}
		}else {
			if(estudianteDerecho == null) {
				estudianteDerecho = nuevo;
			}else {
				estudianteDerecho.insertarEstudiante(nuevo);
			}
		}
	}

	/**
	 * Método que verifica si un estudiante es una hoja o no
	 * @return True si el estudiante es una hoja y False si no es una hoja
	 */
	public boolean esHoja() {
		return estudianteIzquierdo == null && estudianteDerecho == null;
	}
	
	/**
	 * Método que devuelve el menor estudiante del ABB de estudiantes
	 * @return Menor estudiantes
	 */
	public Estudiante darMenorEstudiante() {
		return (estudianteIzquierdo ==  null) ? this : estudianteIzquierdo.darMenorEstudiante();
	}
	
	/**
	 * Método que permite ordenar de forma preorden el arreglo de estudiantes
	 * @param e Arreglo de estudiantes <br><br>
	 * <b>pre:</b> El arreglo de estudiantes está inicializado
	 */
	public void ordenarPreorden(ArrayList<Estudiante> e) {
		e.add(this);
		if(estudianteIzquierdo != null) {
			estudianteIzquierdo.ordenarPreorden(e);
		}
		if(estudianteDerecho != null) {
			estudianteDerecho.ordenarPreorden(e); 
		}
	}

	/**
	 * Método que devuelve el promedio del estudiante
	 * @return El promedio del estudiante
	 */
	public double getPromedio() {
		return promedio;
	}
	
	/**
	 * Método que permite eliminar un estudiante dado su nombre
	 * @param nombre - Nombre del estudiante a eliminar
	 * @return Estudiante eliminado
	 * @throws ObjetoNoExisteException Se lanza cuando el estudiante con el nombre a eliminar no existe en el ABB
	 */
	public Estudiante eliminarEstudiante(String nombre) throws ObjetoNoExisteException{
		if(esHoja()) {
			return null;
		}
		if(getNombre().compareToIgnoreCase(nombre) == 0) {
			if(estudianteIzquierdo == null) {
				return estudianteDerecho;
			}
			if(estudianteDerecho == null) {
				return estudianteIzquierdo;
			}
			Estudiante sucesor = estudianteDerecho.darMenorEstudiante();
			estudianteDerecho = estudianteDerecho.eliminarEstudiante(sucesor.getNombre());
			sucesor.estudianteIzquierdo = estudianteIzquierdo;
			sucesor.estudianteDerecho = estudianteDerecho;
			return sucesor;
		} else if(nombre.compareToIgnoreCase(nombre) > 0) {
			estudianteIzquierdo = estudianteIzquierdo.eliminarEstudiante(nombre);
		} else {
			estudianteDerecho = estudianteDerecho.eliminarEstudiante(nombre);
		}
		return this;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return super.getNombre();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Estudiante e) {
		return super.getNombre().compareToIgnoreCase(e.getNombre());
	}
	
}
