package tablero;

import excepciones.AssignationException;
import excepciones.UnauthorizedException;
import personas.Asignacion;
import personas.Empleado;
import utils.Alerta;
import utils.Calendario;
import utils.Prioridad;
import utils.TipoTarea;

import java.util.ArrayList;
import java.util.Date;
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
    private Tarea tareaActual;
    private Fase fase;
    private Iteracion iteracion;

    public FachadaTablero() {
    }

    public void crearTablero(int horas){
        this.empladoInteraccion = new Empleado("Juan", "Perez");
        Asignacion asignacionEmpleado = new Asignacion(this.empladoInteraccion, new Date(), Calendario.getFechaMasNdiasHabiles(200), 300,horas);
        this.lider = new Empleado("The", "Boss");
        Asignacion asignacionlider = new Asignacion(this.lider, new Date(), Calendario.getFechaMasNdiasHabiles(200), 350,horas);
        this.noAutorizado = new Empleado("Un", "Authorized");
        this.tareas = new ArrayList<Tarea>();
        this.proyecto = new Proyecto(asignacionlider);
        this.proyecto.addMiembro(asignacionEmpleado);

        List<String> estadosPosibles = new ArrayList<String>();
        estadosPosibles.add("Pendiente");
        estadosPosibles.add("En Progreso");
        estadosPosibles.add("En Testing");
        estadosPosibles.add("Finalizada");
        this.tablero = new Tablero(this.proyecto, estadosPosibles);
        this.tablero.addFase("Desarrollo");
        this.fase = this.tablero.getFases().get(0);
    }

    public void crearIteracionTerminaEnNdias(int n){
        Date fechaFin = Calendario.getFechaMasNdiasHabiles(n);
        this.iteracion = new Iteracion(this.fase, "Iteracion 1", new Date(), fechaFin);
        this.fase.addIteracion(iteracion);
    }

    public String crearTarea(String descripcion){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, descripcion, TipoTarea.NUEVO_DESARROLLO,
                    null, 0, 0, null, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaHorasEstimadasN(int n){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "Una descripcion", TipoTarea.NUEVO_DESARROLLO,
                    null, n, 0, null, null, null);
            this.tareaActual = tarea;
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public void asignarIteracionAtarea(){
        this.tareaActual.setIteracion(this.iteracion, this.lider);
    }

    public String crearTareaSinDescripcion(){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "", TipoTarea.NUEVO_DESARROLLO ,
                    null, 0, 0, null, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSinTipoTarea(){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "Una descripcion",
                    null ,null, 0, 0, null, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSoporteSinTicket(){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "Una descripcion",
                    TipoTarea.SOPORTE ,null, 0, 0, null, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSinAutorizacion(){
        try {
            Tarea tarea = new Tarea(this.noAutorizado, this.tablero, "Una descripcion",
                    TipoTarea.NUEVO_DESARROLLO,null, 0, 0, null, null, null);
            this.tareas.add(tarea);
        } catch (UnauthorizedException e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaResponsableNoMiembro() {
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "Una descripcion",
                    TipoTarea.NUEVO_DESARROLLO,null, 0, 0, this.noAutorizado, null, null);
            this.tareas.add(tarea);
        } catch (AssignationException e){
            return e.getMessage();
        }
        return "OK";
    }

    public void cambiarEstadoTarea(Tarea tarea){
        List<String> estadosPosibles = tablero.getEstadosPosibles();
        int indexSiguienteEstado = estadosPosibles.indexOf(tarea.getEstado()) + 1;
        if (indexSiguienteEstado >= estadosPosibles.size()) throw new RuntimeException("La tarea ya se encuentra en el ultimo estado");
        String nuevoEstado = estadosPosibles.get(indexSiguienteEstado);
        tarea.setEstado(nuevoEstado, empladoInteraccion);
    }

    public String getAlertaIteracion(){
        List<Alerta> alertas = this.tablero.getAlertas();

        if (alertas.size() > 0){
            return alertas.get(0).getDescripcion();
        } else {
            return "OK";
        }
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

    public Iteracion getIteracion() {
        return iteracion;
    }
}
