package mundo;

import java.io.Serializable;

/**
 * Clase abstracta que representa una persona
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public abstract class Persona implements Serializable{
	
	/**
	 * Atributo que representa el nombre de la persona
	 */
	private String nombre;
	
	/**
	 * Atributo que representa la identificación de la persona
	 */
	private String identificacion;
	
	/**
	 * Atributo que representa la edad de la persona
	 */
	private int edad;
	
	/**
	 * Atributo que representa el eMail de la persona
	 */
	private String eMail;
	
	/**
	 * Constructor de la clase abstracta Persona
	 * @param nombre - Nombre de la persona
	 * @param identificacion - identificación de la persona
	 * @param edad - Edad de la persona
	 * @param eMail - eMail de la persona
	 */
	public Persona(String nombre, String identificacion, int edad, String eMail) {
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.edad = edad;
		this.eMail = eMail;
	}

	/**
	 * Método que devuelve el nombre de la persona
	 * @return El nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que devuelve la identificación de la persona
	 * @return La identificación de la persona
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * Método que devuelve la edad de la persona
	 * @return La edad de la persona
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Método que devuelve el eMail de la persona
	 * @return El eMail de la persona
	 */
	public String geteMail() {
		return eMail;
	}
}
