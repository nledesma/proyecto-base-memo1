package tablero;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import static org.junit.Assert.*;

/**
 * Created by nicopaez on 5/16/17.
 */
public class CrearTareaSteps {

    private FachadaBanco fachadaBanco;
    private boolean resultadoExitoso;
    private final String CBU = "38479234879453495872423";

    @Dado("^un determinado tablero de proyecto$")
    public void un_determinado_tablero_de_proyecto() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Cuando("^creo una tarea$")
    public void creo_una_tarea() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Entonces("^se crea una tarea con estado inicial pendiente$")
    public void se_crea_una_tarea_con_estado_inicial_pendiente() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }


}