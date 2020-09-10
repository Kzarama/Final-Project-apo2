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

import mundo.Profesor;

/**
 * Clase que representa el panel que contiene la lista de profesores y sus botones
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class PanelProfesores extends JPanel implements ActionListener, ListSelectionListener{
	
	/**
	 * Constantes que sirven para asociar los botones al agregar, eliminar, mostrar información y buscar por nombre respectivamente
	 */
	public final static String AGREGAR = "Agregar", ELIMINAR = "Eliminar", MOSTRAR_INFORMACION = "<html>Mostrar Información</html>", BUSCAR_POR_NOMBRE = "<html>Buscar por Nombre</html>", BUSCAR_POR_EDAD = "Buscar por edad";
	
	/**
	 * Lista de los profesores de la materia
	 */
	private JList listaProfesores;
	
	/**
	 * Scroll que permite bajar cuando la lista de los profesores se llene
	 */
	private JScrollPane scroll;
	
	/**
	 * Botones que sirven para agregar, eliminar, mostrar información y buscar por nombre respectivamente
	 */
	private JButton agregar, eliminar, mostrar, buscarNombre, buscarEdad;
	
	/**
	 * Relación con la clase PanelInfoPrograma
	 */
	private PanelInfoPrograma principal;
	
	/**
	 * Constructor dela clase PanelProfesores
	 * @param principal - El panel con la información del programa
	 */
	public PanelProfesores(PanelInfoPrograma principal) {
		this.principal = principal;
		Font font = new Font("Verdana", Font.BOLD, 15);
		TitledBorder border = BorderFactory.createTitledBorder("Profesores de la Materia");
		border.setTitleColor(Color.BLUE);
		border.setTitleFont(font);
		setBorder(border);
		setLayout(new BorderLayout());
		
		listaProfesores = new JList();
		scroll = new JScrollPane(listaProfesores);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listaProfesores.addListSelectionListener(this);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(3,2));
				
		agregar = new JButton(AGREGAR);
		agregar.addActionListener(this);
		agregar.setActionCommand(AGREGAR);
		
		eliminar = new JButton(ELIMINAR);
		eliminar.addActionListener(this);
		eliminar.setActionCommand(ELIMINAR);
		
		mostrar = new JButton(MOSTRAR_INFORMACION);
		mostrar.addActionListener(this);
		mostrar.setActionCommand(MOSTRAR_INFORMACION);
		
		buscarNombre = new JButton(BUSCAR_POR_NOMBRE);
		buscarNombre.addActionListener(this);
		buscarNombre.setActionCommand(BUSCAR_POR_NOMBRE);
		
		buscarEdad = new JButton(BUSCAR_POR_EDAD);
		buscarEdad.addActionListener(this);
		buscarEdad.setActionCommand(BUSCAR_POR_EDAD);
		
		aux.add(agregar); aux.add(eliminar); aux.add(mostrar); aux.add(buscarNombre); aux.add(buscarEdad);
		
		add(scroll, BorderLayout.CENTER);
		add(aux, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Método que permite refrescar la lista de los profesores
	 * @param nuevaLista - Arreglo de profesores
	 */
	public void refrescarLista(ArrayList nuevaLista){
		if(nuevaLista!= null) {
			listaProfesores.setListData(nuevaLista.toArray());
			listaProfesores.setSelectedIndex(0);
		}else {
			nuevaLista = new ArrayList<Profesor>();
			listaProfesores.setListData(nuevaLista.toArray());
			listaProfesores.setSelectedIndex(0);
		}
	}

	/**
	 * Método que devuelve el profesor seleccionado de la lista
	 * @return Profesor seleccionado
	 */
	public Profesor darProfesor() {
		Profesor p = (Profesor)listaProfesores.getSelectedValue();
		return p;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(AGREGAR)) {
			principal.agregarProfesor();
		}else if(comando.equals(ELIMINAR)) {
			principal.eliminarProfesor();
		}else if(comando.equals(MOSTRAR_INFORMACION)) {
			principal.mostrarInfoProfesores();
		}else if(comando.equals(BUSCAR_POR_NOMBRE)) {
			principal.buscarProfesorPorNombre();
		}else if(comando.equals(BUSCAR_POR_EDAD)) {
			
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(listaProfesores.getSelectedValue() != null) {
			principal.refrescarListaGrupos();
			principal.refrescarListaEstudiantes();
		}
	}
}
