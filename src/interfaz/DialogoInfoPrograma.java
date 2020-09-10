package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mundo.Estudiante;
import mundo.Grupo;
import mundo.Materia;
import mundo.Profesor;
import mundo.Programa;

/**
 * Clase que representa la ventana de dialogo con la información de un programa
 * @author Kevin Zarama y Jorge Antonio Morales
 */
public class DialogoInfoPrograma extends JDialog{
	
	/**
	 * Relación con la clase principal de la interfaz
	 */
	private InterfazFacultadIngenieria principal;
	
	/**
	 * Relación con el panel que contiene la información del programa
	 */
	private PanelInfoPrograma panelInfoPrograma;
	
	/**
	 * Etiqueta que contiene la imagen del banner
	 */
	private JLabel banner;
	
	/**
	 * Imagen del banner
	 */
	private ImageIcon img;
	
	/**
	 * Relación con un programa de la facultad de ingeniería
	 */
	private Programa programa;
	
	/**
	 * Constructor de la ventana de diálogo de un programa seleccionado
	 * @param principal - Clase pricipal de la interfaz
	 * @param p - Programa a mostrar
	 */
	public DialogoInfoPrograma(InterfazFacultadIngenieria principal, Programa p) {
		super(principal, true);
		this.principal = principal;
		this.programa = p;
		setSize(1000, 650);
		setResizable(false);
		setLayout(new BorderLayout());
		setTitle("Programa de " +p.getNombrePrograma()+" de la Universidad Icesi   -   Director(a): "+programa.getDirector());
		
		panelInfoPrograma = new PanelInfoPrograma(this);
		
		JPanel auxB = new JPanel();
		auxB.setPreferredSize(new Dimension(0,150));
		banner = new JLabel();		
		img = new ImageIcon(programa.getRuta());
		banner.setIcon(img);
		auxB.add(banner);
		add(auxB, BorderLayout.NORTH);
		add(panelInfoPrograma, BorderLayout.CENTER);
		
	}
	
	/**
	 * Método que sirve para refrescar las materias del programa
	 * @param materias - Arreglo de materias del programa<br>
	 * <b>pre:</b> materias != null
	 */
	public void refrescarMaterias(ArrayList<Materia> materias) {
		panelInfoPrograma.refrescarMaterias(materias);
	}
	
	/**
	 * Método que sirve para refrescar los profesores de una materia
	 * @param profesores - Arreglo de profesores de una materia<br>
	 */
	public void refrescarProfesores(ArrayList<Profesor> profesores) {
		panelInfoPrograma.refrescarProfesores(profesores);
	}
	
	/**
	 * Método que sirve para refrescar los grupos de un profesor
	 * @param grupos - Arreglo de grupos de un profesores<br>
	 * <b>pre:</b> grupos != null
	 */
	public void refrescarGrupos(ArrayList<Grupo> grupos) {
		panelInfoPrograma.refrescarGrupos(grupos);
	}
	
	/**
	 * Método que sirve para refrescar los estudiantes de un grupo
	 * @param estudiantes - Arreglo de estudiantes de un grupo<br>
	 */
	public void refrescarEstudiantes(ArrayList<Estudiante> estudiantes) {
		panelInfoPrograma.refrescarEstudiantes(estudiantes);
	}
	
	/**
	 * Método que sirve para actualizar la lista de profesores cuando se seleccione otra materia
	 */
	public void refrescarListaProfesores() {
		principal.refrescarListaProfesores(programa);
	}
	
	/**
	 * Método que sirve para actualizar la lista de grupos cuando se seleccione otro profesor
	 */
	public void refrescarListaGrupos() {
		principal.refrescarListaGrupos(programa);
	}
	
	/**
	 * Método que sirve para actualizar la lisa de estudiantes cuando se seleccione otro grupo
	 */
	public void refrescarListaEstudiantes() {
		principal.refrescarListaEstudiantes(programa);
	}
	
	/**
	 * Método que devuelve una materia seleccionada de la lista de materias
	 * @return la materia seleccionada de la lista
	 */
	public Materia darMateria() {
		return panelInfoPrograma.darMateria();
	}
	
	/**
	 * Método que devuelve un profesor seleccionado de la lista de profesores
	 * @return el profesor seleccionado de la lista
	 */
	public Profesor darProfesor() {
		return panelInfoPrograma.darProfesor();
	}
	
	/**
	 * Método que devuelve un grupo seleccionado de la lista de grupos
	 * @return el grupo seleccionado de la lista
	 */
	public Grupo darGrupo() {
		return panelInfoPrograma.darGrupo();
	}
	
	/**
	 * Método que devuelve un estudiante seleccionado de la lista de estudiantes
	 * @return el estudiante seleccionado de la lista
	 */
	public Estudiante darEstudiante() {
		return panelInfoPrograma.darEstudiante();
	}
	
	/**
	 * Método que permite agregar una materia al programa
	 */
	public void agregarMateria() {
		principal.agregarMateria(programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite eliminar una materia del programa
	 */
	public void eliminarMateria() {
		principal.eliminarMateria(programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite eliminar un profesor del programa
	 */
	public void eliminarProfesor() {
		principal.eliminarProfesor(programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite agregar un profesor al programa
	 */
	public void agregarProfesor() {
		principal.agregarProfesor(programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite agregar un grupo al programa
	 */
	public void agregarGrupo() {
		principal.agregarGrupo(programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite eliminar un grupo del programa
	 */
	public void eliminarGrupo() {
		principal.eliminarGrupo(programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite agregar un estudiante al programa
	 */
	public void agregarEstudiante() {
		principal.agregarEstudiante(programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite eliminar un estudiante del programa
	 */
	public void eliminarEstudiante() {
		principal.eliminarEstudiante(darGrupo(), programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite mostrar la información de las materias presentes en el programa
	 */
	public void mostrarInfoMaterias() {
		principal.mostrarInfoMaterias(programa);
	}
	
	/**
	 * Método que permite mostrar la información de los profesores presentes en el programa
	 */
	public void mostrarInfoProfesores() {
		principal.mostrarInfoProfesores(darMateria());
	}
	
	/**
	 * Método que permite mostrar la información de los grupos presentes en el programa
	 */
	public void mostrarInfoGrupos() {
		principal.mostrarInfoGrupos(darProfesor());
	}
	
	/**
	 * Método que permite mostrar la información de los estudiantes en el programa
	 */
	public void mostrarInfoEstudiantes() {
		principal.mostrarInfoEstudiantes(darGrupo());
	}
	
	/**
	 * Método que permite buscar una materia de un programa
	 */
	public void buscarMateria() {
		principal.buscarMateria(programa.getNombrePrograma());
	}
	
	/**
	 * Método que permite buscar un grupo de un profesor
	 */
	public void buscarGrupo() {
		principal.buscarGrupo(darProfesor());
	}
	
	/**
	 * Método que permite buscar un profesor de una materia
	 */
	public void buscarProfesorPorNombre() {
		principal.buscarProfesorPorNombre(darMateria());
	}
	
	/**
	 * Método que permite buscar un estudiante por su promedio
	 */
	public void buscarEstudiantePorPromedio() {
		principal.buscarEstudianteBinarioPorPromedio(darGrupo());
	}
	
}