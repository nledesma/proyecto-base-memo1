package tablero;

import java.util.ArrayList;
import java.util.List;


public class Tablero {
    List<String> estadosPosibles;
    Proyecto proyecto;

    public Tablero(Proyecto proyecto, List<String> estadosPosibles) {
        this.estadosPosibles = estadosPosibles;
        this.proyecto = proyecto;
    }

    public List<String> getEstadosPosibles() {
        return this.estadosPosibles;
    }

    public String getPrimerEstado() {
        return this.estadosPosibles.get(0);
    }

    public Proyecto getProyecto() {
        return proyecto;
    }
}
