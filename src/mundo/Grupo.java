package mundo;

import java.io.Serializable;
import java.util.ArrayList;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;
import excepciones.PromedioNoEncontradoException;

/**
 * M�todo que representa el grupo de la Facultad de Ingenier�a de la Universidad Icesi
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
/**
 * @author user
 *
 */
public class Grupo implements Serializable{

	/**
	 * Atributo que representa el nombre del grupo
	 */
	private String nombreGrupo;
	
	/**
	 * Atributo que representa el horario del grupo
	 */
	private String horario;
	
	/**
	 * Atributo que representa el n�mero de estudiantes del grupo
	 */
	private int numeroEstudiantes;
	
	/**
	 * Relaci�n con el siguiente grupo de la lista enlazada de grupos
	 */
	private Grupo siguienteGrupo;
	
	/**
	 * Relaci�n con el estudiante raiz del ABB de estudiantes
	 */
	private Estudiante raizEstudiante;

	/**
	 * Constructor de la clase Grupo
	 * @param nombreGrupo - Nombre del grupo
	 * @param horario - Horario del grupo
	 * @param g - Siguiente grupo
	 */
	public Grupo(String nombreGrupo, String horario, Grupo g) {
		this.nombreGrupo = nombreGrupo;
		this.horario = horario;
		this.numeroEstudiantes = 0;
		this.siguienteGrupo = g;
		this.raizEstudiante = null;
	}
	
	/**
	 * M�todo que permite verificar si el ABB de estudiantes est� vac�o o no
	 * @return True si est� vac�o o False si no est� vac�o
	 */
	public boolean estaVacia() {
		if(raizEstudiante == null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * M�todo que permite agregar un estudiante
	 * @param e - Estudiante a agregar
	 * @throws ObjetoYaExisteException Se lanza cuando el estudiante a agregar ya existe en el ABB de estudiantes<br><br>
	 * <b>pre:</b> e != null
	 */
	public void agregarEstudiante(Estudiante e) throws ObjetoYaExisteException {
		if(raizEstudiante == null) {
			raizEstudiante = e;
		} else {
			raizEstudiante.insertarEstudiante(e);
		}
		numeroEstudiantes++;
	}
	
	/**
	 * M�todo que devuelve el arreglo de estudiantes del ABB de estudiantes
	 * @return El arreglo de estudiantes o null si su raiz no existe
	 */
	public ArrayList<Estudiante> darListaEstudiantes() {
		if(raizEstudiante == null) {
			return null;
		}else {
			ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
			raizEstudiante.ordenarPreorden(estudiantes);
			return estudiantes;
		}
	}

	/**
	 * M�todo que devuelve el nombre del grupo
	 * @return El nombre del grupo
	 */
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	
	/**
	 * M�todo que permite desconectar el siguiente grupo de la lista de grupos
	 */
	public void desconectarSiguienteGrupo() {
		siguienteGrupo = siguienteGrupo.siguienteGrupo;
	}
	
	/**
	 * M�todo que devuelve el horario del grupo
	 * @return El horario del grupo
	 */
	public String getHorario() {
		return horario;
	}
	
	/**
	 * M�todo que devuelve el siguiente grupo de la lista enlazada de grupos
	 * @return El siguiente grupo
	 */
	public Grupo getSiguienteGrupo() {
		return siguienteGrupo;
	}

	/**
	 * M�todo que sirve para modificar el siguiente grupo de la lista enlazada de grupos
	 * @param siguienteGrupo Siguiente grupo
	 */
	public void setSiguienteGrupo(Grupo siguienteGrupo) {
		this.siguienteGrupo = siguienteGrupo;
	}

	/**
	 * M�todo que permite eliminar un estudiante dado su nombre
	 * @param nombre - Nombre del estudiante a eliminar
	 * @throws ObjetoYaExisteException -
	 * @throws ObjetoNoExisteException Se lanza cuando el estudiante con dicho nombre no existe en el ABB
	 */
	public void eliminarEstudiante(String nombre) throws ObjetoYaExisteException, ObjetoNoExisteException {
		if(estaVacia()) {
			
		} else if(raizEstudiante.getNombre().equals(nombre)) {
			ArrayList<Estudiante> estudiantes = darListaEstudiantes();
			boolean encontrado = false;
			for(int i = 0; i < estudiantes.size(); i++) {
				if(estudiantes.get(i).getNombre().equals(nombre)) {
					encontrado = true;
					estudiantes.remove(i);
				}
			}
			if(encontrado == false) {
				throw new ObjetoNoExisteException(nombre);
			} else {
				raizEstudiante = null;
				numeroEstudiantes = 0;
				for(int i = 0; i < estudiantes.size(); i++) {
					Estudiante nuevo = new Estudiante(estudiantes.get(i).getNombre(), estudiantes.get(i).getIdentificacion(), estudiantes.get(i).getEdad(), estudiantes.get(i).geteMail(), estudiantes.get(i).getPromedio());
					agregarEstudiante(nuevo);
				}
			}
		}
	}
	
	/**
	 * M�todo que permite buscar de forma binaria un estudiante dado su promedio
	 * @param buscado - Promedio a buscar
	 * @param promedios - Arreglo de promedios donde se buscar�
	 * @return True si encontr� el estudiante o false si no lo encontr�
	 * @throws PromedioNoEncontradoException Se lanza cuando no se encontr� ning�n estudiante con dicho promedio
	 */
	public boolean buscarBinarioEstudiantePorPromedio(double buscado, double[] promedios) throws PromedioNoEncontradoException{
		boolean encontrado = false;
		int inicio = 0;
		int fin = promedios.length -1;
		while(inicio <= fin && !encontrado){
			int medio = (inicio + fin) / 2;
			if(promedios[medio] == buscado){
				encontrado = true;
			}else if(promedios[medio] > buscado){
				fin = medio - 1;
			}else{
				inicio = medio + 1;
			}
		}
		if(encontrado == false){
			throw new PromedioNoEncontradoException(buscado);
		}
		return encontrado;
	}
	
	/**
	 * M�todo que devuelve el n�mero de estudiantes del grupo
	 * @return El n�mero de estudiantes del grupo
	 */
	public int getNumeroEstudiantes() {
		return numeroEstudiantes;
	}

	/**
	 * M�todo que devuelve la informaci�n de los estudiantes
	 * @return Una cadena de texto con la informaci�n de los estudiantes
	 */
	public String darInfoEstudiantes() {
		double promedio = 0;
		for(int i = 0; i < numeroEstudiantes; i++) {
			promedio = darListaEstudiantes().get(i).getPromedio();
		}
		String info = "Hay " + numeroEstudiantes + " estudiantes en este grupo\nEl promedio del grupo es: " + promedio;
		return info;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return nombreGrupo;
	}
		
}
