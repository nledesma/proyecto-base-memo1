package utils;

import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<Evento> eventos;

    public Historial(Evento eventoCreacion) {
        this.eventos = new ArrayList<Evento>();
        this.eventos.add(eventoCreacion);
    }

    public void addEvento(Evento evento){
        this.eventos.add(evento);
    }

    public Evento getUltimoEvento(){
        return eventos.get(eventos.size() - 1);
    }
}
