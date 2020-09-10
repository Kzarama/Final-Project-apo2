package mundo;

import java.util.Comparator;

public class OrdenarPorNombre implements Comparator<Materia>{

	@Override
	public int compare(Materia m, Materia m1) {
		return m.getNombre().compareToIgnoreCase(m1.getNombre());
	}
	
}
