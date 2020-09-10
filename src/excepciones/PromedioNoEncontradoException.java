package excepciones;

/**
 * Clase que representa la excepci�n que se produce cuando no se encuentr� ning�n estudiante con el promedio dado
 * @author user
 *
 */
public class PromedioNoEncontradoException extends Exception{

	/**
	 * Constructor de la clase PromedioNoEncontradoException
	 * @param promedio - promedio no encontrado
	 */
	public PromedioNoEncontradoException(double promedio) {
		super("No se ha encontrado ning�n estudiante con el promedio "+promedio);
	}
}
