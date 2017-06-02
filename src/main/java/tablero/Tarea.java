package tablero;

import personas.Empleado;
import utils.Evento;
import utils.Historial;
import utils.Prioridad;
import utils.TipoEvento;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Tarea {
    private Empleado creador;
    private Tablero tablero;
    private String descripcion;
    private String estado;
    private Prioridad prioridad;
    private int horasEstimadas;
    private int horasTrabajadas;
    private Empleado responsable;
    private Historial historial;
    private boolean inicializado;

    public Tarea(Empleado creador, Tablero tablero, String descripcion, Prioridad prioridad, int horasEstimadas, int horasTrabajadas, Empleado responsable) {
        this.creador = creador;
        this.tablero = tablero;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = tablero.getPrimerEstado(); // Obtiene por default el estado base del tablero
        this.horasEstimadas = horasEstimadas == 0 ? -1 : horasEstimadas; // -1 En caso de no haber sido inicializada
        this.horasTrabajadas = horasEstimadas == 0 ? -1 : horasEstimadas; // -1 En caso de no haber sido inicializada
        this.responsable = responsable;
        this.inicializado = false;
    }

    public void init(){
        Evento eventoCreacion = this.crearEventoCreacion();
        this.historial = new Historial(eventoCreacion);
        this.inicializado = true;
    }

    private Evento crearEventoCreacion() {
        Map<String, String> valores = new HashMap<String, String>();
        valores.put("descripcion", this.descripcion);
        if (this.horasEstimadas != -1) valores.put("horas estimadas", String.valueOf(this.horasEstimadas));
        if (this.horasTrabajadas != -1) valores.put("horas trabajadas", String.valueOf(this.horasTrabajadas));
        if (this.responsable != null) valores.put("responsable", this.responsable.toString());
        if (this.responsable != null) valores.put("prioridad", this.prioridad.toString());
        if (this.responsable != null) valores.put("responsable", this.responsable.toString());

        valores.put("estado", this.estado);

        return new Evento(TipoEvento.NUEVO, valores, new Date() , this.creador);
    }

    private void agregarEventoEdicion(String nombreAtributo, String valorAtributo, Empleado autor) {
        Map<String, String> valores = Collections.emptyMap();
        valores.put(nombreAtributo, valorAtributo);
        this.historial.addEvento(new Evento(TipoEvento.EDICION, valores, new Date() , autor));
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

    public void setDescripcion(String descripcion, Empleado autor) {
        this.agregarEventoEdicion("descripcion", this.descripcion, autor);
        this.descripcion = descripcion;
    }

    public void setEstado(String estado, Empleado autor) {
        this.agregarEventoEdicion("estado", this.descripcion, autor);
        this.estado = estado;
    }
}
