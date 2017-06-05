package tablero;

import excepciones.UnauthorizedException;
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

    public FachadaTablero() {
    }

    public void crearTablero(int horas){
        this.empladoInteraccion = new Empleado("Juan", "Perez", horas);
        this.lider = new Empleado("The", "Boss", horas);
        this.noAutorizado = new Empleado("Un", "Authorized", horas);
        this.tareas = new ArrayList<Tarea>();
        this.proyecto = new Proyecto(lider);
        this.proyecto.addMiembro(empladoInteraccion);
        List<String> estadosPosibles = new ArrayList<String>();
        estadosPosibles.add("Pendiente");
        estadosPosibles.add("En Progreso");
        estadosPosibles.add("En Testing");
        estadosPosibles.add("Finalizada");
        this.tablero = new Tablero(this.proyecto, estadosPosibles);
    }

    public void crearFaseTerminaEnNdias(int n){
        Date fechaFin = Calendario.getFechaMasNdiasHabiles(n);
        this.fase = new Fase(this.tablero, "Una fase", new Date(), fechaFin);
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
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "una descripcion", TipoTarea.NUEVO_DESARROLLO,
                    null, n, 0, null, null, null);
            this.tareaActual = tarea;
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public void asignarFaseAtarea(){
        this.tareaActual.setFase(this.fase, this.lider);
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
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "una descripcion",
                    null ,null, 0, 0, null, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSoporteSinTicket(){
        try {
            Tarea tarea = new Tarea(this.empladoInteraccion, this.tablero, "una descripcion",
                    TipoTarea.SOPORTE ,null, 0, 0, null, null, null);
            this.tareas.add(tarea);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    public String crearTareaSinAutorizacion(){
        try {
            Tarea tarea = new Tarea(this.noAutorizado, this.tablero, "una descripcion",
                    TipoTarea.NUEVO_DESARROLLO,null, 0, 0, null, null, null);
            this.tareas.add(tarea);
        } catch (UnauthorizedException e){
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

    public String getAlertaFase(){
        List<Alerta> alertas = this.fase.getAlertas();

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

    public Fase getFase() {
        return fase;
    }
}
