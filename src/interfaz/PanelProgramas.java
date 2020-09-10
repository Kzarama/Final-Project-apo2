package interfaz;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelProgramas extends JPanel implements ActionListener{
	
	public final static String INGENIERIA_DE_SISTEMAS = "Ingeniería de Sistemas";
	public final static String INGENIERIA_INDUSTRIAL = "Ingeniería Industrial";
	public final static String DISEÑO_INDUSTRIAL = "Diseño Industrial";
	public final static String DISEÑO_DE_MEDIOS_INTERACTIVOS = "Diseño de Medios Interactivos";
	public final static String INGENIERIA_TELEMATICA = "Ingeniería Telemática";
	public final static String INGENIERIA_BIOQUIMICA = "Ingeniería Bioquímica";
	
	private JButton sistemas, ingIndustrial, diseñoIndustrial, dmi, telematica, bioquimica;
	
	private InterfazFacultadIngenieria principal;
	
	public PanelProgramas(InterfazFacultadIngenieria principal) {
		this.principal = principal;
		setLayout(new GridLayout(3, 3));
		
		Font font = new Font("Verdana", Font.BOLD, 37);
		
		ImageIcon img1 = new ImageIcon("img/sistemas.jpg");
		sistemas = new JButton(INGENIERIA_DE_SISTEMAS);
		sistemas.addActionListener(this);
		sistemas.setActionCommand(INGENIERIA_DE_SISTEMAS);
		sistemas.setIcon(img1);
		sistemas.setFont(font);
		
		ImageIcon img2 = new ImageIcon("img/industrial.jpg");
		ingIndustrial = new JButton(INGENIERIA_INDUSTRIAL);
		ingIndustrial.addActionListener(this);
		ingIndustrial.setActionCommand(INGENIERIA_INDUSTRIAL);
		ingIndustrial.setFont(font);
		ingIndustrial.setIcon(img2);
		
		ImageIcon img3 = new ImageIcon("img/diseñoIndustrial.jpeg");
		diseñoIndustrial = new JButton(DISEÑO_INDUSTRIAL);
		diseñoIndustrial.addActionListener(this);
		diseñoIndustrial.setActionCommand(DISEÑO_INDUSTRIAL);
		diseñoIndustrial.setFont(font);
		diseñoIndustrial.setIcon(img3);
		
		ImageIcon img4 = new ImageIcon("img/dmi.jpg");
		dmi = new JButton(DISEÑO_DE_MEDIOS_INTERACTIVOS);
		dmi.addActionListener(this);
		dmi.setActionCommand(DISEÑO_DE_MEDIOS_INTERACTIVOS);
		dmi.setFont(font);
		dmi.setIcon(img4);
		
		ImageIcon img5 = new ImageIcon("img/telematica.jpg");
		telematica = new JButton(INGENIERIA_TELEMATICA);
		telematica.addActionListener(this);
		telematica.setActionCommand(INGENIERIA_TELEMATICA);
		telematica.setFont(font);
		telematica.setIcon(img5);
		
		ImageIcon img6 = new ImageIcon("img/bioquimica.jpg");
		bioquimica = new JButton(INGENIERIA_BIOQUIMICA);
		bioquimica.addActionListener(this);
		bioquimica.setActionCommand(INGENIERIA_BIOQUIMICA);
		bioquimica.setFont(font);
		bioquimica.setIcon(img6);
		
		add(sistemas); add(ingIndustrial); add(diseñoIndustrial); add(dmi); add(telematica); add(bioquimica);

	}
	

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(INGENIERIA_DE_SISTEMAS)) {
			principal.mostrarDialogoInformacionPrograma(INGENIERIA_DE_SISTEMAS);
		}else if(comando.equals(INGENIERIA_INDUSTRIAL)) {
			principal.mostrarDialogoInformacionPrograma(INGENIERIA_INDUSTRIAL);
		}else if(comando.equals(DISEÑO_INDUSTRIAL)) {
			principal.mostrarDialogoInformacionPrograma(DISEÑO_INDUSTRIAL);
		}else if(comando.equals(DISEÑO_DE_MEDIOS_INTERACTIVOS)) {
			principal.mostrarDialogoInformacionPrograma(DISEÑO_DE_MEDIOS_INTERACTIVOS);
		}else if(comando.equals(INGENIERIA_TELEMATICA)) {
			principal.mostrarDialogoInformacionPrograma(INGENIERIA_TELEMATICA);
		}else if(comando.equals(INGENIERIA_BIOQUIMICA)) {
			principal.mostrarDialogoInformacionPrograma(INGENIERIA_BIOQUIMICA);
		}
	}

}
