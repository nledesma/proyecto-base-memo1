package tablero;

import personas.Empleado;
import utils.*;

import java.util.*;

public class Fase {
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Iteracion> iteraciones;

    public Fase(String descripcion) {
        this.descripcion = descripcion;
        this.iteraciones = new ArrayList<Iteracion>();
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Iteracion> getIteraciones(){
        return this.iteraciones;
    }

    public void addIteracion(Iteracion iteracion){
        this.iteraciones.add(iteracion);
    }

    public List<Alerta> getAlertas(Tablero tablero){

        List<Alerta> alertas = new ArrayList<Alerta>();

        for (int i = 0; i < this.iteraciones.size(); i++) {
            alertas.addAll(this.iteraciones.get(i).getAlertas(tablero));
        }

        return alertas;
    }

    @Override
    public String toString(){
        return this.descripcion;
    }
}
