package hilos;

import interfaz.InterfazFacultadIngenieria;

/**
 * Clase que representa el hilo del logo de la Universidad Icesi
 * @author user
 *
 */
public class HiloLogo extends Thread{
	
	/**
	 * Relación con la clase principal de la interfaz
	 */
	private InterfazFacultadIngenieria ifi;
	
	/**
	 * Constructor de la clase HiloLogo
	 * @param ifi - Clase principal de la interfaz
	 */
	public HiloLogo(InterfazFacultadIngenieria ifi) {
		this.ifi = ifi;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		int numero = 1;
		while(true) {
			if(numero == 42) {
				numero = 1;
			}
			ifi.refrescarLogoIcesi("img/l"+numero+".png");
			numero ++;
			ifi.refrescar();
			try {
				Thread.sleep(50);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
