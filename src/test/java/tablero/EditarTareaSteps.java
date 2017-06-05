package tablero;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by joaquin on 02/06/17.
 */
public class EditarTareaSteps {

    private FachadaTablero fachadaTablero;
    private Tarea tarea;
    private int indexEstadoAnterior;
    private List<String> estadosPosibles;

    @Dado("^una tarea del tablero$")
    public void una_tarea_del_tablero() throws Throwable {
        fachadaTablero = new FachadaTablero();
        fachadaTablero.crearTablero(6);
        //ToDo: solucionar problema de metodo duplicado

        fachadaTablero.crearTarea("Una descripcion");
        tarea = fachadaTablero.getPrimeraTarea();
    }

    @Cuando("^cambio su estado$")
    public void cambio_su_estado() throws Throwable {
        estadosPosibles = fachadaTablero.getEstadosPosibles();
        indexEstadoAnterior = estadosPosibles.indexOf(tarea.getEstado());
        fachadaTablero.cambiarEstadoTarea(tarea);
    }

    @Entonces("^la tarea pasa al estado indicado$")
    public void la_tarea_pasa_al_estado_indicado() throws Throwable {
        String estadoEsperado = estadosPosibles.get(indexEstadoAnterior + 1);
        assertEquals(estadoEsperado, tarea.getEstado());
    }

}
