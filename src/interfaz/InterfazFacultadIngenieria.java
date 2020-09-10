package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoYaExisteException;
import excepciones.PromedioNoEncontradoException;
import hilos.HiloBanana;
import hilos.HiloLogo;
import hilos.HiloTitulo;
import mundo.Estudiante;
import mundo.FacultadIngenieria;
import mundo.Grupo;
import mundo.Materia;
import mundo.Profesor;
import mundo.Programa;
/**
 * Interfaz de la Facultad de Ingenieria de la Universidad Icesi
 * @author Kevin Zarama - Jorge Morales
 * @version 1.0 32/12/2020
 */
public class InterfazFacultadIngenieria extends JFrame{
	
	/**
	 * Relación con el banner de la interfaz
	 */
	private PanelBanner banner;
	
	/**
	 * Relación con el panel de los programas del mundo
	 */
	private PanelProgramas panelProgramas;
	
	/**
	 * Relación con el dialogo de informacion del programa
	 */
	private DialogoInfoPrograma dip;
	
	/**
	 * Relación con la clase principal del modelo
	 */
	private FacultadIngenieria facultad;
	
	/**
	 *Constructor de la clase principal de la interfaz
	 */
	public InterfazFacultadIngenieria() {
		facultad = new FacultadIngenieria();
		setIconImage(new ImageIcon("img/l1.png").getImage());
		setTitle("Facultad de Ingeniería de la Universidad Icesi   -   Decano: "+facultad.getDecano());
		setLayout(new BorderLayout());
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(1000, 700));
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.BLACK);
		
		banner = new PanelBanner();
		panelProgramas = new PanelProgramas(this);
		
		add(banner, BorderLayout.NORTH);
		add(panelProgramas, BorderLayout.CENTER);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){	
			
		}
		
		cargar();
		
		arrancarHilos();

	}
	
	/**
	 * Método que sirve para refrescar la lista de las materias de cada programa<br>
	 * @param p - Programa seleccionado<br>
	 * <b>pre:</b> El programa seleccionado es != null<br>
	 * <b>pos:</b> La lista de materias se refresca correctamente
	 */
	public void refrescarListaMaterias(Programa p) {
		dip.refrescarMaterias(facultad.darListaMaterias(p));
	}
	
	/**
	 * Método que sirve para refrescar la lista de los profesores de cada materia<br>
	 * @param prog - Programa previamente seleccionado<br>
	 * <b>pre:</b> El programa seleccionado es != null y Materia != null<br>
	 * <b>pos:</b> La lista de profesores se refresca correctamente
	 */
	public void refrescarListaProfesores(Programa prog) {
		Materia m = dip.darMateria();
		dip.refrescarProfesores(facultad.darListaProfesores(m, prog));
	}
	
	/**
	 * Método que sirve para refrescar la lista de los grupos de cada profesor<br>
	 * @param p Programa previamente seleccionado<br>
	 * <b>pre:</b> El programa seleccionado es != null, Materia != null y Profesor != null<br>
	 * <b>pos:</b> La lista de grupos se refresca correctamente
	 */
	public void refrescarListaGrupos(Programa p) {
		Materia m = dip.darMateria();
		Profesor profesor = dip.darProfesor();
		if(profesor != null) {
			Grupo grupo = p.getPrimerGrupo(profesor, m);
			ArrayList<Grupo> grupos = new ArrayList<Grupo>();
			while(grupo!=null) {
				grupos.add(grupo);
				grupo=grupo.getSiguienteGrupo();
			}
			dip.refrescarGrupos(grupos);
		}else {
			ArrayList<Grupo> grupos = new ArrayList<Grupo>();
			dip.refrescarGrupos(grupos);
		}
	}
	
	/**
	 * Método que sirve para refrescar la lista de los estudiantes de cada grupo<br>
	 * @param programa - Programa previamente seleccionado<br>
	 * <b>pre:</b> El programa seleccionado es != null, Materia != null, Profesor != null y Grupo != null<br>
	 * <b>pos:</b> La lista de estdiantes se refresca correctamente
	 */
	public void refrescarListaEstudiantes(Programa programa) {
		Materia m = dip.darMateria();
		Profesor p = dip.darProfesor();
		Grupo g = dip.darGrupo();
		dip.refrescarEstudiantes(facultad.darListaEstudiantes(programa, m, p, g));
		
	}
	
	/**
	 * Agrega una materia a un programa seleccionado previamente
	 * @param programa - El nombre del programa<br>
	 * <b>pre:</b> Programa != null<br>
	 * <b>pos:</b> Agrega correctamente la materia o lanza ObjetoYaExiseException al encontrarse ésta repetida. También puede lanzar NumberFormatException
	 * al escribir erróneamente los créditos
	 */
	public void agregarMateria(String programa) {
		Programa p = facultad.buscarPrograma(0, programa);
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la materia que desee agregar");
		if(nombre!=null) {
			try {
				int creditos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los creditos de la materia"));
				Materia m = new Materia(nombre, creditos, null);
				facultad.insertarMateria(p, m);
				refrescarListaMaterias(p);
			}catch(ObjetoYaExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}catch(NumberFormatException n) {
				JOptionPane.showMessageDialog(this, "Error en los creditos", "Error", JOptionPane.ERROR_MESSAGE);
			}catch(NullPointerException s) {
				
			}
		}
	}
	
	/**
	 * Agrega un profesor a una materia y programa seleccionados previamente
	 * @param programa - El nombre del programa<br>
	 * <b>pre:</b> Programa != null<br>
	 * <b>pos:</b> Agrega correctamente el profesor o lanza ObjetoYaExiseException al encontrarse éste repetido. También puede lanzar NumberFormatException
	 * al escribir erróneamente la edad
	 */
	public void agregarProfesor(String programa) {
		Programa pg = facultad.buscarPrograma(0, programa);
		Materia materia = dip.darMateria();
		if(materia!=null) {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor que desee agregar");
			if(nombre!=null) {
				String id = JOptionPane.showInputDialog("Ingrese la identificación del profesor que desee agregar");
				if(id!=null) {
					try {
						int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del profesor que desee agregar"));
						String eMail = JOptionPane.showInputDialog("Ingrese el eMail de profesor que desee agregar");
						if(eMail != null) {
							Profesor profesor = new Profesor(nombre, edad, id, eMail);
							facultad.insertarProfesor(materia, pg, profesor);
							refrescarListaProfesores(pg);
						}
					}catch(ObjetoYaExisteException s) {
						JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}catch(NumberFormatException n) {
						JOptionPane.showMessageDialog(this, "Error en la edad", "Error", JOptionPane.ERROR_MESSAGE);
					}catch(NullPointerException k) {
								
					}
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "Debes de agregar una materia primero", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Agrega un estudiante a un programa, una materia, un profesor y un grupo seleccionados previamente
	 * @param nombrePrograma El nombre del programa<br>
	 * <b>pre:</b> Programa != null<br>
	 * <b>pos:</b> Agrega correctamente el estudiante o lanza ObjetoYaExiseException al encontrarse éste repetido. También puede lanzar NumberFormatException
	 * al escribir erróneamente la edad y el promedio
	 */
	public void agregarEstudiante(String nombrePrograma) {
		Programa programa = facultad.buscarPrograma(0, nombrePrograma);
		Materia materia = dip.darMateria();
		Profesor profesor = dip.darProfesor();
		Grupo grupo = dip.darGrupo();
		if(materia != null && profesor != null && grupo != null) {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante que desee agregar");
			if(nombre != null) {
				String id = JOptionPane.showInputDialog("Ingrese la identificación del estudiante que desee agregar");
				if(id != null) {
					String eMail = JOptionPane.showInputDialog("Ingrese el eMail de estudiante que desee agregar");
					if(eMail != null) {
						try {
							int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del estudiante que desee agregar"));
							double promedio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el promedio del estudiante que desee agregar"));
							Estudiante estudiante = new Estudiante(nombre, id, edad, eMail, promedio);
							facultad.insertarEstudiante(estudiante, grupo, profesor, materia, programa);
							refrescarListaEstudiantes(programa);
						}catch(ObjetoYaExisteException s) {
							JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}catch(NumberFormatException n) {
							JOptionPane.showMessageDialog(this, "Error en la edad o promedio", "Error", JOptionPane.ERROR_MESSAGE);
						}catch(NullPointerException k) {
									
						}
					}
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "Debes de agregar una materia, un profesor y un grupo primero", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Agrega un grupo a un programa, una materia y un profesor seleccionados previamente
	 * @param pg - El nombre del programa<br>
	 * <b>pre:</b> Programa != null<br>
	 * <b>pos:</b> Agrega correctamente el grupo o lanza ObjetoYaExiseException al encontrarse éste repetido
	 */
	public void agregarGrupo(String pg) {
		Programa programa = facultad.buscarPrograma(0, pg);
		Materia materia = dip.darMateria();
		Profesor profesor = dip.darProfesor();
		if(materia != null && profesor != null) {
			String nombre = JOptionPane.showInputDialog("Ingrese el número del grupo que desee agregar");
			if(nombre!=null) {
				String horario = JOptionPane.showInputDialog("Ingrese el horario de grupo que desee agregar");
				if(horario!=null) {
					try {
						Grupo grupo = new Grupo(nombre, horario, null);
						facultad.insertarGrupo(programa, materia, profesor, grupo);
						refrescarListaGrupos(programa);
					}catch(ObjetoYaExisteException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "Debes de agregar una materia o un profesor primero", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Elimina un profesor de un programa y una materia seleccionados previamente
	 * @param programa - Nombre del programa seleccionado<br>
	 * <b>pre:</b> Programa != null<br>
	 * <b>pos:</b> Se elimina correctamente el profesor o lanza ObjetoNoExiseException al no encontrar un profesor con dicho nombre
	 */
	public void eliminarProfesor(String programa) {
		Programa pg = facultad.buscarPrograma(0, programa);
		Materia materia = dip.darMateria();
		if(materia != null && dip.darProfesor() != null) {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor que desee eliminar");
			if(nombre != null) {
				try {
					facultad.eliminarProfesor(materia, pg, nombre);
					refrescarListaProfesores(pg);
					refrescarListaGrupos(pg);
					refrescarListaEstudiantes(pg);
				}catch(ObjetoYaExisteException e) {
				
				}catch(ObjetoNoExisteException s) {
					JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "No hay nada para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Elimina un grupo de un programa, una materia y un profesor seleccionados previamente
	 * @param programa - Nombre del programa seleccionado<br>
	 * <b>pre:</b> Programa != null<br>
	 * <b>pos:</b> Se elimina correctamente el grupo o lanza ObjetoNoExiseException al no encontrar un grupo con dicho nombre
	 */
	public void eliminarMateria(String programa) {
		Programa p = facultad.buscarPrograma(0, programa);
		if(dip.darMateria() != null) {
			try {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la materia que desee borrar");
				facultad.eliminarMateria(p, nombre);
				refrescarListaMaterias(p);
				refrescarListaProfesores(p);
				refrescarListaGrupos(p);
				refrescarListaEstudiantes(p);
			}catch(ObjetoNoExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "No hay nada para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Elimina un grupo de un programa, una materia y un profesor seleccionados previamente
	 * @param nombrePrograma - Nombre del programa seleccionado<br>
	 * <b>pre:</b> Programa != null<br>
	 * <b>pos:</b> Se elimina correctamente el grupo o lanza ObjetoNoExiseException al no encontrar un grupo con dicho nombre
	 */
	public void eliminarGrupo(String nombrePrograma) {
		Programa programa = facultad.buscarPrograma(0, nombrePrograma);
		Materia materia = dip.darMateria();
		Profesor profesor = dip.darProfesor();
		if(materia != null && profesor !=null) {
			String grupo = JOptionPane.showInputDialog("Ingrese el nombre del grupo que desee eliminar");
			try {
				if(grupo!=null) {
					facultad.eliminarGrupo(programa, materia, profesor, grupo);
					refrescarListaGrupos(programa);
					refrescarListaEstudiantes(programa);
				}
			}catch(ObjetoNoExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "No hay nada para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Elimina un estudiante de un programa, una materia, un profesor y un grupo seleccionado previamente
	 * @param g - Grupo donde pertenece el estudiante
	 * @param nombrePrograma - nombre del programa<br>
	 * <b>pre:</b> Programa != null<br>
	 * <b>pos:</b> Se elimina correctamente el estudiante o lanza ObjetoNoExiseException al no encontrar un estudiante con dicho nombre
	 */
	public void eliminarEstudiante(Grupo g, String nombrePrograma) {
		Programa programa = facultad.buscarPrograma(0, nombrePrograma);
		Materia materia = dip.darMateria();
		Profesor profesor = dip.darProfesor();
		if(materia != null && profesor != null && g != null) {
			String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre del estudiante a eliminar");
			try {
				g.eliminarEstudiante(nombre);
				refrescarListaEstudiantes(programa);
			} catch(ObjetoYaExisteException e) {
			
			} catch(ObjetoNoExisteException s) {
				JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "No hay nada para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Guarda la información de las materias de un programa en un archivo de texto plano
	 * @param info - Cadena de texto con la información del programa
	 */
	public void guardarInfoMaterias(String info) {
		File file = new File("archivos/materias.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(info);
		} catch(Exception e) {
			
		} finally{
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
			} catch(Exception ex) {
				
			}
		}
	}
	
	/**
	 * Método que sirve para mostrar la información de las materias de un programa seleccionado
	 * @param pg - Programa seleccionado<br>
	 * <b>pos:</b> Se muestra correctamente la información de las materias o lanza NullPointerException diciendo que no hay materias en el programa
	 */
	public void mostrarInfoMaterias(Programa pg) {
		String info = pg.mostrarInfoMateria();
		try {
			JOptionPane.showMessageDialog(null, info);
		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No hay materias");
		}
		guardarInfoMaterias(info);
	}
	
	/**
	 * Método que sirve para mostrar la información de los profesores de una materia seleccionados
	 * @param m - Materia seleccionada<br>
	 * <b>pos:</b> Se muestra correctamente la información de los profesores o lanza NullPointerException diciendo que no hay profesores en la materia
	 */
	public void mostrarInfoProfesores(Materia m) {
		try {
			JOptionPane.showMessageDialog(null, m.darInfoProfesor());
		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No hay Profesores");
		}
	}
	
	
	/**
	 * Método que sirve para mostrar la información de los grupos de un profesor seleccionado
	 * @param p - Profesor seleccionado<br>
	 * <b>pos:</b> Se muestra correctamente la información de los grupos o lanza NullPointerException diciendo que no hay grupos con el profesor
	 */
	public void mostrarInfoGrupos(Profesor p) {
		try {
			JOptionPane.showMessageDialog(null, p.darInfoGrupo());
		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No hay grupos");
		}
	}
	
	/**
	 * Método que sirve para mostrar la información de los estudiantes de un grupo seleccionado
	 * @param g - Grupo seleccionado<br>
	 * <b>pos:</b> Se muestra correctamente la información de los estudiantes o lanza NullPointerException diciendo que no hay estudiantes en el grupo
	 */
	public void mostrarInfoEstudiantes(Grupo g) {
		try {
			JOptionPane.showMessageDialog(null, g.darInfoEstudiantes());
		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No hay estudiantes");
		}
	}
	
	/**
	 * Método que permite buscar una materia de un programa
	 * @param nombrePrograma Nombre del programa donde pertenece la materia<br><br>
	 * <b>pos:</b> Se encuentra correctamente la materia o lanza ObjetoNoExisteException diciendo que no existe una materia con el nombre dado
	 */
	public void buscarMateria(String nombrePrograma) {
		Programa programa = facultad.buscarPrograma(0, nombrePrograma);
		if(facultad.darListaMaterias(programa).size() != 0) {
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la materia que desee buscar");
			try {
				Materia m = facultad.buscarMateria(programa, nombre);
				JOptionPane.showMessageDialog(this, "Materia: "+ m.getNombre()+"\nCreditos: "+m.getCreditos());
			}catch(ObjetoNoExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "No hay materias para buscar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método que permite buscar un grupo de un profesor
	 * @param profesor Profesor en donde se encuentra el grupo a buscar<br><br>
	 * <b>pos:</b> Se encuentra correctamente el grupo o lanza ObjetoNoExisteException diciendo que no existe un grupo con el nombre dado
	 */
	public void buscarGrupo(Profesor profesor) {
		if(dip.darMateria() != null && dip.darProfesor() != null) {
			if(dip.darGrupo() != null) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del grupo que desee buscar");
				try {
					Grupo grupo = profesor.buscarGrupo(nombre);
					JOptionPane.showMessageDialog(this, "Grupo: "+grupo.getNombreGrupo()+"\nHorario: "+grupo.getHorario()+"\nCantidad Estudiantes: "+grupo.getNumeroEstudiantes());
				}catch(ObjetoNoExisteException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "No hay grupos para buscar", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Debes de agregar una materia o un profesor primero", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método que permite buscar un profesor de una materia
	 * @param materia Materia en donde se encuentra el profesor a buscar<br><br>
	 * <b>pos:</b> Se encuentra correctamente el profesor o lanza ObjetoNoExisteException diciendo que no existe un profesor con el nombre dado
	 */
	public void buscarProfesorPorNombre(Materia materia) {
		if(dip.darMateria() != null) {
			if(dip.darProfesor() != null) {
				String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor que desee buscar");
				try {
					Profesor profesor = materia.buscarProfesorPorNombre(nombre);
					JOptionPane.showMessageDialog(this, "Profesor: "+profesor.getNombre()+"\nIdentificación: "+profesor.getIdentificacion()+"\nEdad: "+profesor.getEdad()+"\nEmail: "+profesor.geteMail()+"\nNumero grupos: "+profesor.getNumeroCursos());
				}catch(ObjetoNoExisteException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "No hay profesores para buscar", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Debes de agregar una materia primero", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método que permite buscar un estudiante de forma binario dado su promedio en un grupo
	 * @param grupo - Grupo en donde pertenecen los estudiantes<br><br>
	 * <b>pos:</b> Se encuentran correctamente los estudiantes o se lanza PromedioNoEncontradoException al no haber ninguno con dicho promedio
	 */
	public void buscarEstudianteBinarioPorPromedio(Grupo grupo) {
		if(dip.darMateria() != null && dip.darProfesor() != null && dip.darGrupo() != null) {
			if(dip.darEstudiante() != null ) {
				ArrayList<Estudiante> estudiantes = grupo.darListaEstudiantes();
				String nombres = "";
				String promedio = "";
				double[] promedios = new double[estudiantes.size()];
				int pos=0;
				for(int i = estudiantes.size()-1 ; i>=0; i--){
					Estudiante e = estudiantes.get(i);
					promedios[pos] = e.getPromedio();
					pos++;
				}
				try {
					promedio = JOptionPane.showInputDialog("Ingrese el promedio del estudiante que desee buscar");
					double promedioABuscar = Double.parseDouble(promedio);
					if(grupo.buscarBinarioEstudiantePorPromedio(promedioABuscar, promedios)) {
						for(int i = 0; i<estudiantes.size(); i++){
							if(estudiantes.get(i).getPromedio() == promedioABuscar){
								nombres+=estudiantes.get(i).getNombre()+"\n    ";
							}
						}
						JOptionPane.showMessageDialog(this, "Los jugadores con "+promedioABuscar+" de promedio son:\n    "+nombres);
					}
				}catch(PromedioNoEncontradoException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException s){
					JOptionPane.showMessageDialog(this, "Dijite un dato númerico","Error",JOptionPane.ERROR_MESSAGE);
				}catch(NullPointerException c){
					
				}
			}
		}
	}
	
	/**
	 * Método que sirve para mostrar la ventana de dialogo de la información de un programa seleccionado y refrescar sus respectivas listas
	 * @param programa - Nombre de programa seleccionado<br>
	 * <b>pre:</b> Programa != null<br>
	 */
	public void mostrarDialogoInformacionPrograma(String programa) {
		Programa p = facultad.buscarPrograma(0, programa);
		Materia m = p.getPrimerMateria(p);
		dip = new DialogoInfoPrograma(this, facultad.buscarPrograma(0, programa));
		refrescarListaMaterias(p);
		if(m != null) {
			if(facultad.darListaProfesores(m, p) !=null) {
				refrescarListaProfesores(p);
				refrescarListaGrupos(p);
				refrescarListaEstudiantes(p);
			}
		}
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	
	/**
	 * Método que permite poner en funcionamiento los hilos de banner
	 */
	public void arrancarHilos() {
		banner.repaint();
		HiloBanana hl = new HiloBanana(this);
		hl.start();
		
		HiloTitulo  ht = new HiloTitulo(this);
		ht.start();
		
		HiloLogo  hm = new HiloLogo(this);
		hm.start();
	}
	
	/**
	 * Método que sirve para refrescar el logo de la banana
	 * @param ruta - Nueva ruta
	 */
	public void refrescarBanana(String ruta) {
		banner.refrescarBanana(ruta);
	}
	
	/**
	 * Método que sirve para refrescar el logo de la Icesi
	 * @param ruta - Nueva ruta
	 */
	public void refrescarLogoIcesi(String ruta) {
		banner.refrescarLogoIcesi(ruta);
	}
	
	/**
	 * Método que sirve para refrescar el color del título principals
	 */
	public void refrescarTitulo() {
		banner.refrescarTitulo();
	}
	
	/**
	 * Método que sirve para refrescar el paintComponent del panelBanner
	 */
	public void refrescar() {
		banner.repaint();
	}
	
	/**
	 * Método que sirve para guardar un serializado del estado de la Facultad de Ingeniería
	 */
	public void guardar() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("archivos/archivo.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(facultad);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException s) {
			s.printStackTrace();
		}finally {
			try {
				if(oos != null) {
					oos.close();
				}
				if(fos != null) {
					fos.close();
				}
			} catch(IOException s) {
				s.printStackTrace();
			}
		}
	}
	
	/**
	 * Método que permite cargar el serializado del estado de la Facultad de Ingeniería
	 */
	public void cargar() {
		FileInputStream fis = null;
		ObjectInputStream oos = null;
		try {
			fis = new FileInputStream("archivos/archivo.dat");
			oos = new ObjectInputStream(fis);
			facultad = (FacultadIngenieria)oos.readObject();
			
		} catch (FileNotFoundException e) {
		       e.printStackTrace();
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        
		    } finally {
		        try {
		             if (oos != null) {
		            	 oos.close();
		             }
		             if (fis != null) {
		            	 fis.close();
		             } 
		        } catch (IOException e) {
		             e.printStackTrace();
		        }
		    }
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Window#dispose()
	 */
	public void dispose() {
		int op = JOptionPane.showConfirmDialog(this, "¿Desea guardar?");
		if(op == 0) {
			guardar();
			System.exit(0);
		}else if(op == 1) {
			System.exit(0);
		}
	}
	
	/**
	 * Método que permite poner en funcionamiento el programa
	 * @param args - 
	 */
	public static void main(String[] args) {
		InterfazFacultadIngenieria ifi = new InterfazFacultadIngenieria();
		ifi.setVisible(true);
	}
	
}