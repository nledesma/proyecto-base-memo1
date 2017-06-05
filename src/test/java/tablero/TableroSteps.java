package tablero;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import static org.junit.Assert.assertEquals;

public class TableroSteps {

    private FachadaTablero fachadaTablero;
    private String resultadoCreacion;
    private String descripcionTarea;

    @Dado("^un determinado tablero de proyecto con dos desarrolladores de dedicacion de \"(.*?)\" horas$")
    public void un_determinado_tablero_de_proyecto(int horas) throws Throwable {
        fachadaTablero = new FachadaTablero();
        fachadaTablero.crearTablero(horas);
    }

    @Dado("^una fase de proyecto que finaliza en \"(.*?)\" dias habiles$")
    public void una_fase_de_proyecto_que_finaliza_en(int dias) throws Throwable {
        this.fachadaTablero.crearFaseTerminaEnNdias(dias);
    }

    @Cuando("^Agrego una tarea estimada en \"(.*?)\" horas en la fase actual")
    public void agrego_una_tarea_estimada_en_horas(int horas) throws Throwable {
        this.fachadaTablero.crearTareaHorasEstimadasN(horas);
        this.fachadaTablero.asignarFaseAtarea();
    }

    @Entonces("^la fase indica que hay \"(.*?)\" horas pendientes$")
    public void la_fase_indica_que_hay_horas_pendientes(int horas) throws Throwable {
        assertEquals(horas, this.fachadaTablero.getFase().getHorasPendientes());
    }

    @Entonces("^no hay alertas de retraso en la fase$")
    public void no_hay_alertas_de_retraso_en_la_fase() throws Throwable {
        assertEquals("OK", this.fachadaTablero.getAlertaFase());
    }

    @Entonces("^existe una alerta \"(.*?)\"$")
    public void existe_una_alerta(String alerta) throws Throwable {
        assertEquals(alerta, this.fachadaTablero.getAlertaFase());
    }
}
