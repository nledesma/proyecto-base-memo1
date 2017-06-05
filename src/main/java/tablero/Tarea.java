package tablero;

import excepciones.UnauthorizedException;
import personas.Empleado;
import utils.*;
import java.util.*;

public class Tarea {
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
    private Fase fase;

    public Tarea(Empleado creador, Tablero tablero, String descripcion, TipoTarea tipoTarea, Prioridad prioridad,
                 int horasEstimadas, int horasTrabajadas, Empleado responsable, Ticket ticket, Fase fase) {
        this.validarCamposObligatorios(creador, tablero, descripcion, tipoTarea, ticket);

        this.creador = creador;
        this.tablero = tablero;
        this.tablero.addTarea(this);
        this.descripcion = descripcion;
        this.tipoTarea = tipoTarea;
        this.prioridad = prioridad;
        this.estado = tablero.getPrimerEstado(); // Obtiene por default el estado base del tablero
        this.horasEstimadas = horasEstimadas == 0 ? -1 : horasEstimadas; // -1 En caso de no haber sido inicializada
        this.horasTrabajadas = horasTrabajadas;
        this.responsable = responsable;
        this.responsable = (responsable != null) ? responsable : this.getTablero().getProyecto().getLider();
        this.ticket = ticket;
        Evento eventoCreacion = this.crearEventoCreacion();
        this.historial = new Historial(eventoCreacion);
        this.fase = fase;
    }

    private void validarCamposObligatorios(Empleado creador, Tablero tablero, String descripcion, TipoTarea tipoTarea, Ticket ticket) {
        if(!tablero.autorizarCreacionTarea(creador)) throw new UnauthorizedException("El usuario no puede crear esta tarea");
        if (descripcion == "") throw new RuntimeException("El campo descripcion es obligatorio");
        if (tipoTarea == null) throw new RuntimeException("El campo tipo tarea es obligatorio");
        if (tipoTarea == TipoTarea.SOPORTE && ticket == null) throw new RuntimeException("La tarea de soporte debe estar vinculada a un ticket");
    }

    private Evento crearEventoCreacion() {
        Map<String, String> valores = new HashMap<String, String>();
        valores.put("descripcion", this.descripcion);
        valores.put("tipo de tarea", this.tipoTarea.toString());
        valores.put("responsable", this.responsable.toString());
        if (this.horasEstimadas != -1) valores.put("horas estimadas", String.valueOf(this.horasEstimadas));
        if (this.horasTrabajadas != 0) valores.put("horas trabajadas", String.valueOf(this.horasTrabajadas));
        if (this.prioridad != null) valores.put("prioridad", this.prioridad.toString());

        valores.put("estado", this.estado);

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

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
        this.fase.addTarea(this);
    }

    public void setDescripcion(String descripcion, Empleado autor) {
        this.agregarEventoEdicion("descripcion", this.descripcion, autor);
        this.descripcion = descripcion;
    }

    public void setEstado(String estado, Empleado autor) {
        this.agregarEventoEdicion("estado", this.descripcion, autor);
        this.estado = estado;
    }

    public int getHorasPendientes(){
        if (this.horasEstimadas != -1){
            return this.horasEstimadas - this.horasTrabajadas;
        }
        return 0;
    }
}
