package hilos;

import interfaz.InterfazFacultadIngenieria;

/**
 * Clase que representa el hilo del titulo principal del programa
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class HiloTitulo extends Thread{
	
	/**
	 * Relación con la clase principal de la interfaz
	 */
	private InterfazFacultadIngenieria ifi;
	
	/**
	 * Constructor de la clase HiloTitulos
	 * @param ifi - Clase principal de la interfaz
	 */
	public HiloTitulo(InterfazFacultadIngenieria ifi) {
		this.ifi = ifi;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while(true) {
			ifi.refrescarTitulo();
			try {
				Thread.sleep(1500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
