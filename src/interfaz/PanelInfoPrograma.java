package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import mundo.Estudiante;
import mundo.Grupo;
import mundo.Materia;
import mundo.Profesor;

/**
 * Clase que contiene el panel del dialogo de la información del programa de la Facultad de Ingeniería
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class PanelInfoPrograma extends JPanel{
	
	/**
	 * Relacion con el panel de las materias del programa
	 */
	private PanelMaterias panelMaterias;
	
	/**
	 * Relación con el panel de los profesores del programa
	 */
	private PanelProfesores panelProfesores;
	
	/**
	 * Relación con el panel de los grupos del programa
	 */
	private PanelGrupos panelGrupos;
	
	/**
	 * Relación con el panel de los estudiantes del programa
	 */
	private PanelEstudiantes panelEstudiantes;
	
	/**
	 * Relación con la clase principal de la ventana de dialogo del programa
	 */
	private DialogoInfoPrograma principal;
	
	/**
	 * Constructor del panel de la información del programa
	 * @param principal - Clase principal de la ventana de dialogo del programa
	 */
	public PanelInfoPrograma(DialogoInfoPrograma principal) {
		this.principal = principal;
		setLayout(new GridLayout(1, 4));
		TitledBorder border = BorderFactory.createTitledBorder("Información del Programa");
		border.setTitleColor(Color.RED);
		setBorder(border);
		
		panelMaterias = new PanelMaterias(this);
		panelProfesores = new PanelProfesores(this);
		panelEstudiantes = new PanelEstudiantes(this);
		panelGrupos = new PanelGrupos(this);
		
		add(panelMaterias);
		add(panelProfesores);
		add(panelGrupos);
		add(panelEstudiantes);
		
	}
	
	/**
	 * Método que sirve para refrescar las materias del programa
	 * @param materias - Arreglo de materias del programa<br>
	 * <b>pre:</b> materias != null
	 */
	public void refrescarMaterias(ArrayList<Materia> materias) {
		panelMaterias.refrescarLista(materias);
	}
	
	/**
	 * Método que sirve para refrescar los profesores de una materia
	 * @param profesores - Arreglo de profesores de una materia<br>
	 */
	public void refrescarProfesores(ArrayList<Profesor> profesores) {
		panelProfesores.refrescarLista(profesores);
	}
	
	/**
	 * Método que sirve para refrescar los grupos de un profesor
	 * @param grupos - Arreglo de grupos de un profesores<br>
	 * <b>pre:</b> grupos != null
	 */
	public void refrescarGrupos(ArrayList<Grupo> grupos) {
		panelGrupos.refrescarLista(grupos);
	}
	
	/**
	 * Método que sirve para refrescar los estudiantes de un grupo
	 * @param estudiantes - Arreglo de estudiantes de un grupo<br>
	 */
	public void refrescarEstudiantes(ArrayList<Estudiante> estudiantes) {
		panelEstudiantes.refrescarLista(estudiantes);
	}
	
	/**
	 * Método que sirve para actualizar la lista de profesores cuando se seleccione otra materia
	 */
	public void refrescarListaProfesores() {
		principal.refrescarListaProfesores();
	}
	
	/**
	 * Método que sirve para actualizar la lista de grupos cuando se seleccione otro profesor
	 */
	public void refrescarListaGrupos() {
		principal.refrescarListaGrupos();
	}
	
	/**
	 * Método que sirve para actualizar la lisa de estudiantes cuando se seleccione otro grupo
	 */
	public void refrescarListaEstudiantes() {
		principal.refrescarListaEstudiantes();
	}
	
	/**
	 * Método que devuelve una materia seleccionada de la lista de materias
	 * @return la materia seleccionada de la lista
	 */
	public Materia darMateria() {
		return panelMaterias.darMateria();
	}
	
	/**
	 * Método que devuelve un profesor seleccionado de la lista de profesores
	 * @return el profesor seleccionado de la lista
	 */
	public Profesor darProfesor() {
		return panelProfesores.darProfesor();
	}
	
	/**
	 * Método que devuelve un grupo seleccionado de la lista de grupos
	 * @return el grupo seleccionado de la lista
	 */
	public Grupo darGrupo() {
		return panelGrupos.darGrupo();
	}
	
	/**
	 * Método que devuelve un estudiante seleccionado de la lista de estudiantes
	 * @return el estudiante seleccionado de la lista
	 */
	public Estudiante darEstudiante() {
		return panelEstudiantes.darEstudiante();
	}
	
	/**
	 * Método que permite agregar una materia al programa
	 */
	public void agregarMateria() {
		principal.agregarMateria();
	}
	
	/**
	 * Método que permite eliminar una materia del programa
	 */
	public void eliminarMateria() {
		principal.eliminarMateria();
	}
	
	/**
	 * Método que permite eliminar un profesor del programa
	 */
	public void eliminarProfesor() {
		principal.eliminarProfesor();
	}
	
	/**
	 * Método que permite agregar un profesor al programa
	 */
	public void agregarProfesor() {
		principal.agregarProfesor();
	}
	
	/**
	 * Método que permite agregar un grupo al programa
	 */
	public void agregarGrupo() {
		principal.agregarGrupo();
	}
	
	/**
	 * Método que permite eliminar un grupo del programa
	 */
	public void eliminarGrupo() {
		principal.eliminarGrupo();
	}
	
	/**
	 * Método que permite agregar un estudiante al programa
	 */
	public void agregarEstudiante() {
		principal.agregarEstudiante();
	}
	
	/**
	 * Método que permite eliminar un estudiante del programa
	 */
	public void elminarEstudiante() {
		principal.eliminarEstudiante();
	}
	
	/**
	 * Método que permite mostrar la información de las materias presentes en el programa
	 */
	public void mostrarInfoMaterias() {
		principal.mostrarInfoMaterias();
	}
	
	/**
	 * Método que permite mostrar la información de los profesores presentes en el programa
	 */
	public void mostrarInfoProfesores() {
		principal.mostrarInfoProfesores();
	}
	
	/**
	 * Método que permite mostrar la información de los grupos presentes en el programa
	 */
	public void mostrarInfoGrupos() {
		principal.mostrarInfoGrupos();
	}
	
	/**
	 * Método que permite mostrar la información de los estudiantes presentes en el programa
	 */
	public void mostrarInfoEstudiantes() {
		principal.mostrarInfoEstudiantes();
	}
	
	/**
	 * Método que permite buscar una materia de un programa
	 */
	public void buscarMateria() {
		principal.buscarMateria();
	}
	
	/**
	 * Método que permite buscar un grupo de un profesor
	 */
	public void buscarGrupo() {
		principal.buscarGrupo();
	}
	
	/**
	 * Método que permite buscar un profesor de una materia
	 */
	public void buscarProfesorPorNombre() {
		principal.buscarProfesorPorNombre();
	}
	
	/**
	 * Método que permite buscar un estudiante por su promedio 
	 */
	public void buscarEstudiantePorPromedio() {
		principal.buscarEstudiantePorPromedio();
	}
	
}