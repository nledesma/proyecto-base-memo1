package tablero;

import java.util.ArrayList;
import java.util.List;


public class Tablero {
    List<String> estadosPosibles;

    public Tablero(List<String> estadosPosibles) {
        this.estadosPosibles = estadosPosibles;
    }

    public List<String> getEstadosPosibles() {
        return this.estadosPosibles;
    }

    public String getPrimerEstado() {
        return this.estadosPosibles.get(0);
    }

}
