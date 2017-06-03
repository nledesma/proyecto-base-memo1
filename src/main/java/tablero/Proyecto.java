package tablero;


import personas.Empleado;

import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    List<Empleado> miembros;
    Empleado lider;

    public Proyecto(Empleado lider) {
        this.lider = lider;
        this.miembros = new ArrayList<Empleado>();
        this.miembros.add(lider);
    }

    public Empleado getLider() {
        return lider;
    }

    public void setLider(Empleado lider) {
        this.lider = lider;
    }

    public List<Empleado> getMiembros() {
        return miembros;
    }

    public void addMiembro(Empleado empleado) {
        this.miembros.add(empleado);
    }
}
