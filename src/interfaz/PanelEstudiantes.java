package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mundo.Estudiante;

/**
 * Clase que representa el panel que contiene la lista de las estudiantes y sus botones
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class PanelEstudiantes extends JPanel implements ActionListener, ListSelectionListener{
	
	/**
	 * Constantes que sirven para asociar los botones al agregar, eliminar, mostrar información  y buscar por nombre respectivamente
	 */
	public final static String AGREGAR = "Agregar", ELIMINAR = "Eliminar", MOSTRAR_INFORMACION = "<html>Mostrar Información</html>", BUSCAR_POR_PROMEDIO = "<html>Buscar por promedio<html>";
	
	/**
	 * Lista de los estudiantes del programa
	 */
	private JList listaEstudiantes;
	
	/**
	 * Scroll que permite bajar cuando las materias llenen la lista
	 */
	private JScrollPane scroll;
	
	/**
	 * Botones que permiten agregar, eliminar, mostrar información y buscar por nombre respectivamente
	 */
	private JButton agregar, eliminar, mostrar, buscar;
	
	/**
	 * Relación con la clase PanelInfoPrograma
	 */
	private PanelInfoPrograma principal;
	
	/**
	 * Constructor de la clase PanelEstudiantes
	 * @param principal - Clase PanelInfoPrograma
	 */
	public PanelEstudiantes(PanelInfoPrograma principal) {
		this.principal = principal;
		Font font = new Font("Verdana", Font.BOLD, 15);
		TitledBorder border = BorderFactory.createTitledBorder("Estudiantes del Grupo");
		border.setTitleColor(Color.BLUE);
		border.setTitleFont(font);
		setBorder(border);
		setLayout(new BorderLayout());
		
		listaEstudiantes = new JList();
		scroll = new JScrollPane(listaEstudiantes);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listaEstudiantes.addListSelectionListener(this);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(2, 2));
		
		agregar = new JButton(AGREGAR);
		agregar.addActionListener(this);
		agregar.setActionCommand(AGREGAR);
		
		eliminar = new JButton(ELIMINAR);
		eliminar.addActionListener(this);
		eliminar.setActionCommand(ELIMINAR);
		
		mostrar = new JButton(MOSTRAR_INFORMACION);
		mostrar.addActionListener(this);
		mostrar.setActionCommand(MOSTRAR_INFORMACION);
		
		buscar = new JButton(BUSCAR_POR_PROMEDIO);
		buscar.addActionListener(this);
		buscar.setActionCommand(BUSCAR_POR_PROMEDIO);
		
		aux.add(agregar); aux.add(eliminar); aux.add(mostrar); aux.add(buscar);
		
		add(scroll, BorderLayout.CENTER);
		add(aux, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Método que permite refrescar la lista de los estudiantes
	 * @param nuevaLista - Arreglo de estudiantes
	 */
	public void refrescarLista(ArrayList nuevaLista){
		if(nuevaLista != null) {
			listaEstudiantes.setListData(nuevaLista.toArray());
			listaEstudiantes.setSelectedIndex(0);
		}else {
			nuevaLista = new ArrayList<Estudiante>();
			listaEstudiantes.setListData(nuevaLista.toArray());
			listaEstudiantes.setSelectedIndex(0);
		}
	}

	/**
	 * Método que devuelve el estudiante seleccionado de la lista
	 * @return Estudiante seleccionado
	 */
	public Estudiante darEstudiante() {
		Estudiante e = (Estudiante)listaEstudiantes.getSelectedValue();
		return e;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(AGREGAR)) {
			principal.agregarEstudiante();
		}else if(comando.equals(ELIMINAR)) {
			principal.elminarEstudiante();
		}else if(comando.equals(MOSTRAR_INFORMACION)) {
			principal.mostrarInfoEstudiantes();
		}else if(comando.equals(BUSCAR_POR_PROMEDIO)) {
			principal.buscarEstudiantePorPromedio();
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	}
}

