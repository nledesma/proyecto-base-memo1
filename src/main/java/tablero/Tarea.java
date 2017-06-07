package tablero;

import excepciones.AssignationException;
import excepciones.RequiredFieldException;
import excepciones.UnasignedTicketException;
import excepciones.UnauthorizedException;
import personas.Empleado;
import utils.*;
import java.util.*;

public class Tarea {
    private static String DESCRIPCION_STR = "Descripcion";
    private static String TIPO_TAREA_STR= "Tipo tarea";
    private static String ESTADO_STR = "Estado";
    private static String PRIORIDAD_STR = "Prioridad";
    private static String HORAS_ESTIMADAS_STR = "Horas estimadas";
    private static String HORAS_TRABAJADAS_STR = "Horas trabajadas";
    private static String RESPONSABLE_STR= "Responsable";
    private static String TICKET_STR = "Ticket";
    private static String ITERACION_STR = "Iteracion";

    private static String MSG_DESAUTORIZADO_TAREA = "El usuario no puede crear esta tarea";
    private static String MSG_RESPONSABLE_NO_VALIDO = "El responsable no forma parte del proyecto";
    private static String MSG_DESCRIPCION_OBLIGATORIO = "El campo descripcion es obligatorio";
    private static String MSG_TIPO_TAREA_OBLIGATORIO = "El campo tipo de tarea es obligatorio";
    private static String MSG_TICKET_SOPORTE_NO_ESPECIFICADO = "La tarea de soporte debe estar vinculada a un ticket";

    private Empleado creador;
    private Tablero tablero;
    private String descripcion;
    private TipoTarea tipoTarea;
    private String estado;
    private Prioridad prioridad;
    private int horasEstimadas;
    private int horasTrabajadas;
    private Empleado responsable;
    private Historial historial;
    private Ticket ticket;
    private Iteracion iteracion;

    Tarea(Empleado creador, Tablero tablero, String descripcion, TipoTarea tipoTarea, Prioridad prioridad,
                 int horasEstimadas, int horasTrabajadas, Empleado responsable, Ticket ticket, Iteracion iteracion) {
        this.validarCamposObligatorios(creador, tablero, descripcion, tipoTarea, ticket, responsable);

        this.creador = creador;
        this.tablero = tablero;
        this.tablero.addTarea(this);
        this.descripcion = descripcion;
        this.tipoTarea = tipoTarea;
        this.prioridad = prioridad;
        this.estado = tablero.getPrimerEstado(); // Obtiene por default el estado base del tablero
        this.horasEstimadas = horasEstimadas == 0 ? -1 : horasEstimadas; // -1 En caso de no haber sido inicializada
        this.horasTrabajadas = horasTrabajadas;
        this.responsable = (responsable != null) ? responsable : this.getTablero().getProyecto().getLider();
        this.ticket = ticket;
        Evento eventoCreacion = this.crearEventoCreacion();
        this.historial = new Historial(eventoCreacion);
        this.iteracion = iteracion;
    }

    private void validarCamposObligatorios(Empleado creador, Tablero tablero, String descripcion, TipoTarea tipoTarea, Ticket ticket, Empleado responsable) {
        if (!tablero.autorizarCreacionTarea(creador)) throw new UnauthorizedException(Tarea.MSG_DESAUTORIZADO_TAREA);
        if (responsable != null && !tablero.autorizarAsignacionTarea(responsable)) throw new AssignationException(Tarea.MSG_RESPONSABLE_NO_VALIDO);
        if (descripcion == "") throw new RequiredFieldException(Tarea.MSG_DESCRIPCION_OBLIGATORIO);
        if (tipoTarea == null) throw new RequiredFieldException(Tarea.MSG_TIPO_TAREA_OBLIGATORIO);
        if (tipoTarea == TipoTarea.SOPORTE && ticket == null) throw new UnasignedTicketException(Tarea.MSG_TICKET_SOPORTE_NO_ESPECIFICADO);
    }

    private Evento crearEventoCreacion() {
        Map<String, String> valores = new HashMap<String, String>();
        valores.put(Tarea.DESCRIPCION_STR, this.descripcion);
        valores.put(Tarea.TIPO_TAREA_STR, this.tipoTarea.toString());
        valores.put(Tarea.RESPONSABLE_STR, this.responsable.toString());
        if (this.horasEstimadas != -1) valores.put(Tarea.HORAS_ESTIMADAS_STR, String.valueOf(this.horasEstimadas));
        if (this.horasTrabajadas != 0) valores.put(Tarea.HORAS_TRABAJADAS_STR, String.valueOf(this.horasTrabajadas));
        if (this.prioridad != null) valores.put(Tarea.PRIORIDAD_STR, this.prioridad.toString());
        if (this.iteracion != null) valores.put(Tarea.ITERACION_STR, this.iteracion.getDescripcion());

        valores.put(Tarea.ESTADO_STR, this.estado);

        return new Evento(TipoEvento.NUEVO, valores, this.creador);
    }

    private void agregarEventoEdicion(String nombreAtributo, String valorAtributo, Empleado autor) {
        Map<String, String> valores = new HashMap<String, String>();
        valores.put(nombreAtributo, valorAtributo);
        this.historial.addEvento(new Evento(TipoEvento.EDICION, valores , autor));
    }


    public Empleado getCreador() {
        return creador;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public Historial getHistorial() {
        return historial;
    }

    public Iteracion getIteracion() {
        return iteracion;
    }

    public void setIteracion(Iteracion iteracion, Empleado autor) {
        this.agregarEventoEdicion(Tarea.ITERACION_STR, iteracion.getDescripcion(), autor);
        this.iteracion = iteracion;
        this.iteracion.addTarea(this);
    }

    public void setDescripcion(String descripcion, Empleado autor) {
        this.agregarEventoEdicion(Tarea.DESCRIPCION_STR, descripcion, autor);
        this.descripcion = descripcion;
    }

    public void setEstado(String estado, Empleado autor) {
        this.agregarEventoEdicion(Tarea.ESTADO_STR, estado, autor);
        this.estado = estado;
    }

    public int getHorasPendientes(){
        if (this.horasEstimadas != -1){
            return this.horasEstimadas - this.horasTrabajadas;
        }
        return 0;
    }
}
