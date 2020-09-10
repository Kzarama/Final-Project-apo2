package excepciones;

/**
 * Clase que representa la excepción que produce al encontrar un objeto que ya existe
 * @author Jorge Antonio Morales y Kevin Zarama
 */
public class ObjetoYaExisteException extends Exception{

	/**
	 * Constructor de la clase ObjetoYaExisteException
	 * @param nombre - Nombre del objeto ya existente
	 */
	public ObjetoYaExisteException(String nombre) {
		super("El objeto con el nombre "+nombre+" ya existe en esta lista");
	}
}
