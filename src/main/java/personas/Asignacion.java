package personas;


import java.util.Date;

public class Asignacion {
    private Empleado empleado;
    private Date fechaInicio;
    private Date fechaFin;
    private int costoPorHora;
    private int dedicacionHoraria;

    public Asignacion(Empleado empleado, Date fechaInicio, Date fechaFin, int costoPorHora, int dedicacionHoraria) {
        this.empleado = empleado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costoPorHora = costoPorHora;
        this.dedicacionHoraria = dedicacionHoraria;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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

    public int getCostoPorHora() {
        return costoPorHora;
    }

    public void setCostoPorHora(int costoPorHora) {
        this.costoPorHora = costoPorHora;
    }

    public int getDedicacionHoraria() {
        return dedicacionHoraria;
    }

    public void setDedicacionHoraria(int dedicacionHoraria) {
        this.dedicacionHoraria = dedicacionHoraria;
    }
}
