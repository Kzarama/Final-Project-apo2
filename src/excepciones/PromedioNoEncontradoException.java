package excepciones;

/**
 * Clase que representa la excepción que se produce cuando no se encuentró ningún estudiante con el promedio dado
 * @author user
 *
 */
public class PromedioNoEncontradoException extends Exception{

	/**
	 * Constructor de la clase PromedioNoEncontradoException
	 * @param promedio - promedio no encontrado
	 */
	public PromedioNoEncontradoException(double promedio) {
		super("No se ha encontrado ningún estudiante con el promedio "+promedio);
	}
}
