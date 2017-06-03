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
    private String descripcionTarea;

    @Dado("^un determinado tablero de proyecto$")
    public void un_determinado_tablero_de_proyecto() throws Throwable {
        fachadaTablero = new FachadaTablero();
        fachadaTablero.crearTablero();
    }

    @Cuando("^creo una tarea con descripcion \"(.*?)\" y sin responsable$")
    public void creo_una_tarea(String descripcion) throws Throwable {
        this.descripcionTarea = descripcion;
        resultadoCreacion = fachadaTablero.crearTarea(this.descripcionTarea);
    }

    @Entonces("^se crea una tarea con estado inicial pendiente$")
    public void se_crea_una_tarea_con_estado_inicial_pendiente() throws Throwable {
        assertTrue(resultadoCreacion);
        assertEquals("Pendiente", fachadaTablero.getPrimeraTarea().getEstado());
    }

    @Cuando("^creo una tarea sin descripcion$")
    public void creo_una_tarea_sin_descripcion() throws Throwable {
        resultadoCreacion = fachadaTablero.crearTareaSinDescripcion();
    }

    @Entonces("^la tarea creada tiene la descripcion \"(.*?)\"$")
    public void la_tarea_creada_tiene_la_descripcion(String descripcion) throws Throwable {
        assertEquals(this.fachadaTablero.getPrimeraTarea().getDescripcion(), descripcion);
    }

    @Entonces("^el lider de proyecto es el responsable de la tarea$")
    public void el_lider_de_proyecto_es_el_responsable_de_la_tarea() throws Throwable {
        assertEquals(this.fachadaTablero.getProyecto().getLider(),this.fachadaTablero.getPrimeraTarea().getResponsable());
    }

    @Cuando("^un empleado sin autorizar crea una tarea$")
    public void un_empleado_sin_autorizar_crea_una_tarea() throws Throwable {
        resultadoCreacion = fachadaTablero.crearTareaSinAutorizacion();
    }

    @Entonces("^se lanza una excepcion$")
    public void se_lanza_una_excepcion() throws Throwable {
        assertFalse(resultadoCreacion);
    }

}
