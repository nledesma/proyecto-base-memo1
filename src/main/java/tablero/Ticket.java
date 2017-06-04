package tablero;

import excepciones.UnauthorizedException;
import personas.Empleado;
import utils.*;
import java.util.*;

public class Ticket {
    private Empleado creador;
    private String descripcion;
    private EstadoTicket estado;
    private Prioridad prioridad;
    private Date fechaCreacion;
    private Date fechaEstimadaResolucion;
    private Date fechaResolucion;
    private Historial historial;

    public Ticket(Empleado creador, String descripcion, Prioridad prioridad) {
        this.validarCamposObligatorios(descripcion);

        this.creador = creador;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = EstadoTicket.PENDIENTE; // Obtiene por default el estado base del tablero
        this.fechaCreacion = new Date();
        Evento eventoCreacion = this.crearEventoCreacion();
        this.historial = new Historial(eventoCreacion);
    }

    private void validarCamposObligatorios(String descripcion) {
        if (descripcion == "") throw new RuntimeException("El campo descripcion es obligatorio");
    }

    private Evento crearEventoCreacion() {
        Map<String, String> valores = new HashMap<String, String>();
        valores.put("descripcion", this.descripcion);
        if (this.prioridad != null) valores.put("prioridad", this.prioridad.toString());
        valores.put("estado", this.estado.toString());

        return new Evento(TipoEvento.NUEVO, valores , this.creador);
    }

    private void agregarEventoEdicion(String nombreAtributo, String valorAtributo, Empleado autor) {
        Map<String, String> valores = new HashMap<String, String>();
        valores.put(nombreAtributo, valorAtributo);
        this.historial.addEvento(new Evento(TipoEvento.EDICION, valores, autor));
    }


    public Empleado getCreador() {
        return creador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setDescripcion(String descripcion, Empleado autor) {
        this.agregarEventoEdicion("descripcion", this.descripcion, autor);
        this.descripcion = descripcion;
    }

    public void setEstado(EstadoTicket estado, Empleado autor) {
        this.agregarEventoEdicion("estado", this.descripcion, autor);
        this.estado = estado;
    }

    public Date getFechaEstimadaResolucion() {
        return fechaEstimadaResolucion;
    }

    public void setFechaEstimadaResolucion(Date fechaEstimadaResolucion) {
        this.fechaEstimadaResolucion = fechaEstimadaResolucion;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
}
