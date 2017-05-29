package utils;

import java.util.Date;
import java.util.Map;
import personas.Empleado;

public class Evento {
    private TipoEvento tipoEvento;
    private Map<String, String> valores;
    private Date fecha;
    private Empleado autor;

    public Evento(TipoEvento tipoEvento, Map<String, String> valores, Date cuando, Empleado por) {
        this.tipoEvento = tipoEvento;
        this.valores = valores;
        this.fecha = cuando;
        this.autor = por;
    }
}
