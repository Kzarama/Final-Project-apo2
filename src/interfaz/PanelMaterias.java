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

import mundo.Materia;

/**
 * Clase que representa el panel que contiene la lista de las materias y sus botones
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class PanelMaterias extends JPanel implements ActionListener, ListSelectionListener{
	
	/**
	 * Constantes que sirven para asociar los botones al agregar, eliminar, mostrar información, ordenar por nombre, ordenar por creditos y buscar por nombre respectivamente
	 */
	public final static String AGREGAR = "Agregar", ELIMINAR = "Eliminar", MOSTRAR_INFORMACION = "<html>Mostrar Información</html>", ORDENAR_POR_NOMBRE = "<html>Ordenar por Nombre</html>", ORDENAR_POR_CREDITOS = "<html>Ordenar por Créditos</html>", BUSCAR_POR_NOMBRE = "<html>Buscar por Nombre<html>";
	
	/**
	 * Lista de las materias del programa
	 */
	private JList listaMaterias;
	
	/**
	 * Scroll que permite bajar cuando las materias llenen la lista
	 */
	private JScrollPane scroll;
	
	/**
	 * Botones que permiten agregar, eliminar, mostrar información, ordenar por nombre, ordenar por creditos y buscar por nombre respectivamente
	 */
	private JButton agregar, eliminar, mostrar, ordenarNombre, ordenarCreditos, buscar;
	
	/**
	 * Relación con la clase PanelInfoPrograma
	 */
	private PanelInfoPrograma principal;
	
	/**
	 * Constructor de la clase PanelMaterias
	 * @param principal - Clase InfoPrograma
	 */
	public PanelMaterias(PanelInfoPrograma principal) {
		this.principal = principal;
		Font font = new Font("Verdana", Font.BOLD, 15);
		TitledBorder border = BorderFactory.createTitledBorder("Materias del Programa");
		border.setTitleColor(Color.BLUE);
		border.setTitleFont(font);
		setBorder(border);
		setLayout(new BorderLayout());
		
		listaMaterias = new JList();
		scroll = new JScrollPane(listaMaterias);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listaMaterias.addListSelectionListener(this);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(2, 3));
		
		agregar = new JButton(AGREGAR);
		agregar.addActionListener(this);
		agregar.setActionCommand(AGREGAR);
		
		eliminar = new JButton(ELIMINAR);
		eliminar.addActionListener(this);
		eliminar.setActionCommand(ELIMINAR);
		
		mostrar = new JButton(MOSTRAR_INFORMACION);
		mostrar.addActionListener(this);
		mostrar.setActionCommand(MOSTRAR_INFORMACION);
		
		ordenarNombre = new JButton(ORDENAR_POR_NOMBRE);
		ordenarNombre.addActionListener(this);
		ordenarNombre.setActionCommand(ORDENAR_POR_NOMBRE);
		
		ordenarCreditos = new JButton(ORDENAR_POR_CREDITOS);
		ordenarCreditos.addActionListener(this);
		ordenarCreditos.setActionCommand(ORDENAR_POR_CREDITOS);
		
		buscar = new JButton(BUSCAR_POR_NOMBRE);
		buscar.addActionListener(this);
		buscar.setActionCommand(BUSCAR_POR_NOMBRE);
		
		aux.add(agregar); aux.add(eliminar); aux.add(mostrar); aux.add(ordenarNombre); aux.add(ordenarCreditos); aux.add(buscar);
		
		add(scroll, BorderLayout.CENTER);
		add(aux, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Método que permite refrescar la lista de las materias
	 * @param nuevaLista - Arreglo de materias<br>
	 * <b>pre:</b> nuevaLista != null
	 */
	public void refrescarLista(ArrayList nuevaLista){
		listaMaterias.setListData(nuevaLista.toArray());
		listaMaterias.setSelectedIndex(0);
	}

	/**
	 * Método que devuelve la materia seleccionada de la lista
	 * @return Materia seleccionada
	 */
	public Materia darMateria() {
		Materia m = (Materia)listaMaterias.getSelectedValue();
		return m;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(AGREGAR)) {
			principal.agregarMateria();
		}else if(comando.equals(ELIMINAR)) {
			principal.eliminarMateria();
		}else if(comando.equals(MOSTRAR_INFORMACION)) {
			principal.mostrarInfoMaterias();
		}else if(comando.equals(ORDENAR_POR_NOMBRE)) {
			
		}else if(comando.equals(ORDENAR_POR_CREDITOS)) {
			
		}else if(comando.equals(BUSCAR_POR_NOMBRE)) {
			principal.buscarMateria();
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(listaMaterias.getSelectedValue() != null) {
			principal.refrescarListaProfesores();
			principal.refrescarListaGrupos();
			principal.refrescarListaEstudiantes();
		}
	}
}
