package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que representa la visualización del banner de la ventana principal
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class PanelBanner extends JPanel {

	/**
	 * Imagen del fondo, logo de la icesi y la banana
	 */
	private ImageIcon banana, fondo, logoIcesi;
	
	/**
	 * El color con el que va cambiando periodacamente en el titulo
	 */
	private Color color;
	
	/**
	 * Etiqueta que representa el titulo principal de la ventana principal
	 */
	private JLabel labTitulo;
	
	/**
	 * Constructor de la clase PanelBanner
	 */
	public PanelBanner() {
		setPreferredSize(new Dimension(200, 180));
		fondo = new ImageIcon("img/fondo.jpg");
		labTitulo = new JLabel("Facultad de Ingeniería");
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
		g.drawImage(banana.getImage(), 10, 10, 160, 160, this);
		g.drawImage(logoIcesi.getImage(), 1110, 40, 70, 70, this);
		g.drawImage(new ImageIcon("img/logo_icesi.png").getImage(), 1115, 35, 210, 100, this);
		Font font = new Font("Verdana", Font.BOLD, 70);
		g.setColor(color);
		g.setFont(font);
		g.drawString(labTitulo.getText(), 180, 120);
	}
	
	/**
	 * Método que permite escoger aleatoriamente un color
	 * @return Color aleatoriamente escogido
	 */
	public Color darColor(){
		Color[] colores= new Color[10];
		colores[0]= new Color(7,169,44);
		colores[1]= new Color(53,175,1);
		colores[2]= new Color(117,7,169);
		colores[3]= new Color(240,0,6);
		colores[4]= new Color(28,124,213);
		colores[5]= new Color(227,168,13);
		colores[6]= new Color(64,176,137);
		colores[7]= new Color(166,74,106);
		colores[8]= new Color(4,171,189);
		colores[9]= new Color(175,39,39);
		
		int numero = (int) (Math.random() * 9);
	
		return colores[numero];
	}
	
	/**
	 * Método que permite refrescar la ruta de la imagen de la bana
	 * @param ruta - Nueva ruta
	 */
	public void refrescarBanana(String ruta) {
		banana = new ImageIcon(ruta);
	}
	
	/**
	 * Método que permite refrescar la ruta del logo de la Icesi
	 * @param ruta - Nueva Ruta
	 */
	public void refrescarLogoIcesi(String ruta) {
		logoIcesi = new ImageIcon(ruta);
	}
	
	/**
	 * Método que permite refrescar el color del titulo principal
	 */
	public void refrescarTitulo() {
		color=darColor();
		labTitulo.setForeground(color);
		repaint(); 
	}
}