package utils;

import personas.Empleado;

import java.util.Date;
import java.util.Map;

public class Alerta {
    private String descripcion;

    public Alerta(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
