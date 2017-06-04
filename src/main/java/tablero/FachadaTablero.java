package tablero;

import excepciones.UnauthorizedException;
import personas.Empleado;
import utils.Prioridad;
import utils.TipoTarea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nico on 29/05/17.
 */
public class FachadaTablero {
    private Proyecto proyecto;
    private Tablero tablero;
    private Empleado empladoInteraccion;
    private Empleado lider;
    private Empleado noAutorizado;
    private List<Tarea> tareas;

    public FachadaTablero() {
        this.empladoInteraccion = new Empleado("Juan", "Perez", 8);
        this.lider = new Empleado("The", "Boss", 8);
        this.noAutorizado = new Empleado("Un", "Authorized", 8);
        this.tareas = new ArrayList<Tarea>();
        this.proyecto = new Proyecto(lider);
        this.proyecto.addMiembro(empladoInteraccion);
    }

    public void crearTablero(){
        List<String> estadosPosibles = new ArrayList<String>();
        estadosPosibles.add("Pendiente");
        estadosPosibles.add("En Progreso");
        estadosPosibles.add("En Testing");
        estadosPosibles.add("Finalizada");
        this.tablero = new Tablero(this.proyecto, estadosPosibles);
    }

    public String crearTarea(String descripcion){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, descripcion, TipoTarea.NUEVO_DESARROLLO,null, 0, 0, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSinDescripcion(){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "", TipoTarea.NUEVO_DESARROLLO ,null, 0, 0, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSinTipoTarea(){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "una descripcion", null ,null, 0, 0, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSoporteSinTicket(){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "una descripcion", TipoTarea.SOPORTE ,null, 0, 0, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSinAutorizacion(){
        try {
            Tarea tarea = new Tarea(this.noAutorizado, this.tablero, "una descripcion", TipoTarea.NUEVO_DESARROLLO,null, 0, 0, null, null);
            this.tareas.add(tarea);
        } catch (UnauthorizedException e){
            return e.getMessage();
        }
        return "OK";
    }

    public void cambiarEstadoTarea(Tarea tarea){
        //ToDo crear objeto Estados para evitar este tipo de manejos?
        List<String> estadosPosibles = tablero.getEstadosPosibles();
        int indexSiguienteEstado = estadosPosibles.indexOf(tarea.getEstado()) + 1;
        if (indexSiguienteEstado >= estadosPosibles.size()) throw new RuntimeException();
        String nuevoEstado = estadosPosibles.get(indexSiguienteEstado);
        tarea.setEstado(nuevoEstado, empladoInteraccion);
    }

    public List<String> getEstadosPosibles(){
        return tablero.getEstadosPosibles();
    }

    public Tarea getPrimeraTarea(){
        return this.tareas.get(0);
    }

    public Proyecto getProyecto() {
        return proyecto;
    }
}
