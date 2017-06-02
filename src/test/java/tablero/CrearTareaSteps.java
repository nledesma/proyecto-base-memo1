package tablero;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CrearTareaSteps {

    private FachadaTablero fachadaTablero;
    private boolean resultadoCreacion;

    @Dado("^un determinado tablero de proyecto$")
    public void un_determinado_tablero_de_proyecto() throws Throwable {
        fachadaTablero = new FachadaTablero();
        fachadaTablero.crearTablero();
    }

    @Cuando("^creo una tarea$")
    public void creo_una_tarea() throws Throwable {
        resultadoCreacion = fachadaTablero.crearTarea();
    }

    @Entonces("^se crea una tarea con estado inicial pendiente$")
    public void se_crea_una_tarea_con_estado_inicial_pendiente() throws Throwable {
        assertTrue(resultadoCreacion);
        assertEquals("Pendiente", fachadaTablero.getPrimeraTarea().getEstado());
    }


}
