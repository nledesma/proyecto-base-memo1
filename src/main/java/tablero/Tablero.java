package tablero;

import personas.Empleado;
import utils.Alerta;
import utils.Calendario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Tablero {
    List<String> estadosPosibles;
    Proyecto proyecto;
    List<Tarea> tareas;
    List<Fase> fases;

    public Tablero(Proyecto proyecto, List<String> estadosPosibles) {
        this.estadosPosibles = estadosPosibles;
        this.proyecto = proyecto;
        this.tareas = new ArrayList<Tarea>();
        this.fases = new ArrayList<Fase>();
    }

    public int getHorasDisponiblesHastaFecha(Date fecha){
        return Calendario.diasHabilesHasta(fecha) * this.getHorasDedicablesPorDia();
    }

    public int getHorasDedicablesPorDia(){
        int horas = 0;
        for (int i = 0; i < this.proyecto.equipoAsignado.size(); i++) {
            horas += this.proyecto.equipoAsignado.get(i).getDedicacionHoraria();
        }

        return horas;
    }

    public List<Alerta> getAlertas(){

        List<Alerta> alertas = new ArrayList<Alerta>();

        for (int i = 0; i < this.fases.size(); i++) {
            alertas.addAll(this.fases.get(i).getAlertas(this));
        }
        return alertas;
    }

    public List<String> getEstadosPosibles() {
        return this.estadosPosibles;
    }

    public boolean autorizarCreacionTarea(Empleado usuario){
        return this.proyecto.esMiembro(usuario);
    }

    public boolean autorizarAsignacionTarea(Empleado usuario) { return this.proyecto.esMiembro(usuario); }

    public String getPrimerEstado() {
        return this.estadosPosibles.get(0);
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public List<Tarea> getTareas(){
        return this.tareas;
    }

    public List<Fase> getFases() { return this.fases; }

    public void addTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    public void addFase(String descripcion){
        this.fases.add(new Fase(descripcion));
    }

}
