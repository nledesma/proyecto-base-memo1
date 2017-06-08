package tablero;


import personas.Asignacion;
import personas.Empleado;

import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    List<Asignacion> equipoAsignado;
    Asignacion lider;

    public Proyecto(Asignacion lider) {
        this.lider = lider;
        this.equipoAsignado = new ArrayList<Asignacion>();
        this.equipoAsignado.add(lider);
    }

    public boolean esMiembro(Empleado empleado){
        int encontrado = -1;

        int i = 0;
        while (i < this.equipoAsignado.size() && encontrado == -1) {
            if (this.equipoAsignado.get(i).getEmpleado() == empleado)
                encontrado = i;
            i++;
        }
        return encontrado != -1;
    }

    public Empleado getLider() {
        return lider.getEmpleado();
    }

    public void setLider(Asignacion lider) {
        this.lider = lider;
    }

    public List<Asignacion> getequipoAsignado() {
        return equipoAsignado;
    }

    public void addMiembro(Asignacion asignacion) {
        this.equipoAsignado.add(asignacion);
    }
}
