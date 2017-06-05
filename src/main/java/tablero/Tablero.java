package tablero;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
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

    public Tablero(Proyecto proyecto, List<String> estadosPosibles) {
        this.estadosPosibles = estadosPosibles;
        this.proyecto = proyecto;
        this.tareas = new ArrayList<Tarea>();
    }

    private int getHorasDisponiblesHastaFecha(Date fecha){
        return Calendario.diasHabilesHasta(fecha) * this.getHorasDedicablesPorDia();
    }

    private int getHorasDedicablesPorDia(){
        int horas = 0;
        for (int i = 0; i < this.proyecto.miembros.size(); i++) {
            horas += this.proyecto.miembros.get(i).getDedicacionHoraria();
        }

        return horas;
    }

    public List<Alerta> getAlertasPorFase(Fase fase){
        List<Alerta> alertas = new ArrayList<Alerta>();
        int horasRetraso = this.getHorasRetrasoPorFase(fase);

        if (horasRetraso > 0){
            alertas.add(new Alerta("La fase se encuentra retrasada por "+horasRetraso+" horas."));
        }

        return alertas;
    }

    private int getHorasRetrasoPorFase(Fase fase){
        int horasRetraso = fase.getHorasPendientes() - this.getHorasDisponiblesHastaFecha(fase.getFechaFin());

        if (horasRetraso > 0)
            return horasRetraso;
        else
            return 0;
    }

    public List<String> getEstadosPosibles() {
        return this.estadosPosibles;
    }

    public boolean autorizarCreacionTarea(Empleado usuario){
        return this.proyecto.esMiembro(usuario);
    }

    public String getPrimerEstado() {
        return this.estadosPosibles.get(0);
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public List<Tarea> getTareas(){
        return this.tareas;
    }

    public void addTarea(Tarea tarea){
        this.tareas.add(tarea);
    }
}
