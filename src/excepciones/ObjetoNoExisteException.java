package excepciones;

/**
 * Clase que representa la excepción que produce al encontrar un objeto que inexistente
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class ObjetoNoExisteException extends Exception{

	/**
	 * Constructor de la clase ObjetoNoExisteException
	 * @param nombre - Nombre del objeto inexistente
	 */
	public ObjetoNoExisteException(String nombre) {
		super("El objeto con el nombre "+nombre+" no existe");
	}
	
}
