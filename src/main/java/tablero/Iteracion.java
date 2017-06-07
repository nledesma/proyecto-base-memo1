package tablero;

import javafx.scene.control.Tab;
import personas.Empleado;
import utils.*;

import java.util.*;

public class Iteracion {
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Tarea> tareas;

    public Iteracion(Fase fase, String descripcion, Date fechaInicio, Date fechaFin) {
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

    public List<Alerta> getAlertas(Tablero tablero) {

        List<Alerta> alertas = new ArrayList<Alerta>();
        int horasRetraso = this.getHorasRetraso(tablero);

        if (horasRetraso > 0){
            alertas.add(new Alerta("La iteracion se encuentra retrasada por "+horasRetraso+" horas."));
        }
        return alertas;
    }

    private int getHorasRetraso(Tablero tablero){
        int horasRetraso = this.getHorasPendientes() - tablero.getHorasDisponiblesHastaFecha(this.getFechaFin());

        if (horasRetraso > 0)
            return horasRetraso;
        else
            return 0;
    }

    @Override
    public String toString(){
        return this.descripcion;
    }
}