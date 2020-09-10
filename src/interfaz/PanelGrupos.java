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

import mundo.Grupo;

/**
 * Clase que representa el panel que contiene la lista de grupos y sus botones
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class PanelGrupos extends JPanel implements ActionListener, ListSelectionListener{
	
	/**
	 * Constantes que sirven para asociar los botones al agregar, eliminar y mostrar información respectivamente
	 */
	public final static String AGREGAR = "Agregar", ELIMINAR = "Eliminar", MOSTRAR_INFORMACION = "<html>Mostrar Información</html>", BUSCAR= "Buscar grupo";
	
	/**
	 * Lista de grupos del profesor
	 */
	private JList listaGrupos;
	
	/**
	 * Scroll que permite bajar cuando la lista de grupos se llene
	 */
	private JScrollPane scroll;
	
	/**
	 * Botones que sirven para agregar, eliminar y mostrar información respectivamente
	 */
	private JButton agregar, eliminar, mostrar, buscar;
	
	/**
	 * Relacion con la clase PanelInfoPrograma
	 */
	private PanelInfoPrograma principal;
	
	/**
	 * Constructor de la clase PanelGrupos
	 * @param principal - Clase PanelInfoPrograma
	 */
	public PanelGrupos(PanelInfoPrograma principal) {
		this.principal = principal;
		Font font = new Font("Verdana", Font.BOLD, 15);
		TitledBorder border = BorderFactory.createTitledBorder("Grupos del Profesor");
		border.setTitleColor(Color.BLUE);
		border.setTitleFont(font);
		setBorder(border);
		setLayout(new BorderLayout());
		
		listaGrupos = new JList();
		scroll = new JScrollPane(listaGrupos);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listaGrupos.addListSelectionListener(this);
		
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
		
		buscar = new JButton(BUSCAR);
		buscar.addActionListener(this);
		buscar.setActionCommand(BUSCAR);
		
		aux.add(agregar); aux.add(eliminar); aux.add(mostrar); aux.add(buscar);
		
		add(scroll, BorderLayout.CENTER);
		add(aux, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Método que permite refrescar la lista de los grupos
	 * @param nuevaLista - Arreglo de grupos<br>
	 * <b>pre:</b> nuevaLista != null
	 */
	public void refrescarLista(ArrayList nuevaLista){
		listaGrupos.setListData(nuevaLista.toArray());
		listaGrupos.setSelectedIndex(0);
	}

	/**
	 * Método que devuelve el grupo seleccionado de la lista
	 * @return Grupo seleccionado
	 */
	public Grupo darGrupo() {
		Grupo p = (Grupo)listaGrupos.getSelectedValue();
		return p;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(AGREGAR)) {
			principal.agregarGrupo();
		}else if(comando.equals(ELIMINAR)) {
			principal.eliminarGrupo();
		}else if(comando.equals(MOSTRAR_INFORMACION)) {
			principal.mostrarInfoGrupos();
		}else if(comando.equals(BUSCAR)) {
			principal.buscarGrupo();
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(listaGrupos.getSelectedValue() != null) {
			principal.refrescarListaEstudiantes();
		}
	}
}
