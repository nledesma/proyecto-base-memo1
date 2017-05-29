package tablero;

import personas.Empleado;
import utils.Prioridad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nico on 29/05/17.
 */
public class FachadaTablero {
    private Tablero tablero;
    private Empleado empladoInteraccion;
    private List<Tarea> tareas;

    public FachadaTablero() {
        this.empladoInteraccion = new Empleado("Juan", "Perez");
        this.tareas = new ArrayList<Tarea>();
    }

    public void crearTablero(){
        List<String> estadosPosibles = new ArrayList<String>();
        estadosPosibles.add("Pendiente");
        estadosPosibles.add("En Progreso");
        estadosPosibles.add("En Testing");
        estadosPosibles.add("Finalizada");
        this.tablero = new Tablero(estadosPosibles);
    }

    public void crearTarea(){
        Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "Una descripcion", null, 0, 0, null);
        this.tareas.add(tarea);
    }
}
