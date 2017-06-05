package tablero;

import personas.Empleado;
import utils.*;

import java.util.*;

public class Fase {
    private Tablero tablero;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Tarea> tareas;

    public Fase(Tablero tablero, String descripcion, Date fechaInicio, Date fechaFin) {
        this.tablero = tablero;
        this.descripcion = descripcion;
        this.fechaFin = fechaInicio;
        this.fechaFin = fechaFin;
        this.tareas = new ArrayList<Tarea>();
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Tarea> getTareas(){
        return this.tareas;
    }

    public void addTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    public int getHorasPendientes(){
        int horas = 0;
        for (int i = 0; i < this.tareas.size(); i++) {
            horas += this.tareas.get(i).getHorasPendientes();
        }

        return horas;
    }

    public List<Alerta> getAlertas(){
        return this.tablero.getAlertasPorFase(this);
    }

    @Override
    public String toString(){
        return this.descripcion;
    }
}
