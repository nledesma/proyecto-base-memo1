package utils;

import java.util.Date;
import java.util.Map;
import personas.Empleado;

public class Evento {

    private TipoEvento tipoEvento;
    private Map<String, String> valores;
    private Date fecha;
    private Empleado autor;

    public Evento(TipoEvento tipoEvento, Map<String, String> valores, Empleado por) {
        this.tipoEvento = tipoEvento;
        this.valores = valores;
        this.fecha = new Date();
        this.autor = por;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public Map<String, String> getValores() {
        return valores;
    }
}
