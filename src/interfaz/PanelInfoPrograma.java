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
 * Clase que contiene el panel del dialogo de la informaci�n del programa de la Facultad de Ingenier�a
 * @author Jorge Antonio Morales y Kevin Zarama
 *
 */
public class PanelInfoPrograma extends JPanel{
	
	/**
	 * Relacion con el panel de las materias del programa
	 */
	private PanelMaterias panelMaterias;
	
	/**
	 * Relaci�n con el panel de los profesores del programa
	 */
	private PanelProfesores panelProfesores;
	
	/**
	 * Relaci�n con el panel de los grupos del programa
	 */
	private PanelGrupos panelGrupos;
	
	/**
	 * Relaci�n con el panel de los estudiantes del programa
	 */
	private PanelEstudiantes panelEstudiantes;
	
	/**
	 * Relaci�n con la clase principal de la ventana de dialogo del programa
	 */
	private DialogoInfoPrograma principal;
	
	/**
	 * Constructor del panel de la informaci�n del programa
	 * @param principal - Clase principal de la ventana de dialogo del programa
	 */
	public PanelInfoPrograma(DialogoInfoPrograma principal) {
		this.principal = principal;
		setLayout(new GridLayout(1, 4));
		TitledBorder border = BorderFactory.createTitledBorder("Informaci�n del Programa");
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
	 * M�todo que sirve para refrescar las materias del programa
	 * @param materias - Arreglo de materias del programa<br>
	 * <b>pre:</b> materias != null
	 */
	public void refrescarMaterias(ArrayList<Materia> materias) {
		panelMaterias.refrescarLista(materias);
	}
	
	/**
	 * M�todo que sirve para refrescar los profesores de una materia
	 * @param profesores - Arreglo de profesores de una materia<br>
	 */
	public void refrescarProfesores(ArrayList<Profesor> profesores) {
		panelProfesores.refrescarLista(profesores);
	}
	
	/**
	 * M�todo que sirve para refrescar los grupos de un profesor
	 * @param grupos - Arreglo de grupos de un profesores<br>
	 * <b>pre:</b> grupos != null
	 */
	public void refrescarGrupos(ArrayList<Grupo> grupos) {
		panelGrupos.refrescarLista(grupos);
	}
	
	/**
	 * M�todo que sirve para refrescar los estudiantes de un grupo
	 * @param estudiantes - Arreglo de estudiantes de un grupo<br>
	 */
	public void refrescarEstudiantes(ArrayList<Estudiante> estudiantes) {
		panelEstudiantes.refrescarLista(estudiantes);
	}
	
	/**
	 * M�todo que sirve para actualizar la lista de profesores cuando se seleccione otra materia
	 */
	public void refrescarListaProfesores() {
		principal.refrescarListaProfesores();
	}
	
	/**
	 * M�todo que sirve para actualizar la lista de grupos cuando se seleccione otro profesor
	 */
	public void refrescarListaGrupos() {
		principal.refrescarListaGrupos();
	}
	
	/**
	 * M�todo que sirve para actualizar la lisa de estudiantes cuando se seleccione otro grupo
	 */
	public void refrescarListaEstudiantes() {
		principal.refrescarListaEstudiantes();
	}
	
	/**
	 * M�todo que devuelve una materia seleccionada de la lista de materias
	 * @return la materia seleccionada de la lista
	 */
	public Materia darMateria() {
		return panelMaterias.darMateria();
	}
	
	/**
	 * M�todo que devuelve un profesor seleccionado de la lista de profesores
	 * @return el profesor seleccionado de la lista
	 */
	public Profesor darProfesor() {
		return panelProfesores.darProfesor();
	}
	
	/**
	 * M�todo que devuelve un grupo seleccionado de la lista de grupos
	 * @return el grupo seleccionado de la lista
	 */
	public Grupo darGrupo() {
		return panelGrupos.darGrupo();
	}
	
	/**
	 * M�todo que devuelve un estudiante seleccionado de la lista de estudiantes
	 * @return el estudiante seleccionado de la lista
	 */
	public Estudiante darEstudiante() {
		return panelEstudiantes.darEstudiante();
	}
	
	/**
	 * M�todo que permite agregar una materia al programa
	 */
	public void agregarMateria() {
		principal.agregarMateria();
	}
	
	/**
	 * M�todo que permite eliminar una materia del programa
	 */
	public void eliminarMateria() {
		principal.eliminarMateria();
	}
	
	/**
	 * M�todo que permite eliminar un profesor del programa
	 */
	public void eliminarProfesor() {
		principal.eliminarProfesor();
	}
	
	/**
	 * M�todo que permite agregar un profesor al programa
	 */
	public void agregarProfesor() {
		principal.agregarProfesor();
	}
	
	/**
	 * M�todo que permite agregar un grupo al programa
	 */
	public void agregarGrupo() {
		principal.agregarGrupo();
	}
	
	/**
	 * M�todo que permite eliminar un grupo del programa
	 */
	public void eliminarGrupo() {
		principal.eliminarGrupo();
	}
	
	/**
	 * M�todo que permite agregar un estudiante al programa
	 */
	public void agregarEstudiante() {
		principal.agregarEstudiante();
	}
	
	/**
	 * M�todo que permite eliminar un estudiante del programa
	 */
	public void elminarEstudiante() {
		principal.eliminarEstudiante();
	}
	
	/**
	 * M�todo que permite mostrar la informaci�n de las materias presentes en el programa
	 */
	public void mostrarInfoMaterias() {
		principal.mostrarInfoMaterias();
	}
	
	/**
	 * M�todo que permite mostrar la informaci�n de los profesores presentes en el programa
	 */
	public void mostrarInfoProfesores() {
		principal.mostrarInfoProfesores();
	}
	
	/**
	 * M�todo que permite mostrar la informaci�n de los grupos presentes en el programa
	 */
	public void mostrarInfoGrupos() {
		principal.mostrarInfoGrupos();
	}
	
	/**
	 * M�todo que permite mostrar la informaci�n de los estudiantes presentes en el programa
	 */
	public void mostrarInfoEstudiantes() {
		principal.mostrarInfoEstudiantes();
	}
	
	/**
	 * M�todo que permite buscar una materia de un programa
	 */
	public void buscarMateria() {
		principal.buscarMateria();
	}
	
	/**
	 * M�todo que permite buscar un grupo de un profesor
	 */
	public void buscarGrupo() {
		principal.buscarGrupo();
	}
	
	/**
	 * M�todo que permite buscar un profesor de una materia
	 */
	public void buscarProfesorPorNombre() {
		principal.buscarProfesorPorNombre();
	}
	
	/**
	 * M�todo que permite buscar un estudiante por su promedio 
	 */
	public void buscarEstudiantePorPromedio() {
		principal.buscarEstudiantePorPromedio();
	}
	
}