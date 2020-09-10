package hilos;

import interfaz.InterfazFacultadIngenieria;

/**
 * Clase que representa el hilo de la banana
 * @author Kevin Zarama y Jorge Antonio Morales
 *
 */
public class HiloBanana extends Thread{
	
	/**
	 * Relación con la clase principal de la interfaz
	 */
	private InterfazFacultadIngenieria ifi;
	
	/**
	 * Constructor de la clase HiloBanana
	 * @param ifi - Clase principal de la interfaz
	 */
	public HiloBanana(InterfazFacultadIngenieria ifi) {
		this.ifi = ifi;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		int numero = 1;
		while(true) {
			if(numero == 8) {
				numero = 1;
			}
			ifi.refrescarBanana("img/b"+numero+".png");
			numero ++;
			ifi.refrescar();
			try {
				Thread.sleep(125);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
