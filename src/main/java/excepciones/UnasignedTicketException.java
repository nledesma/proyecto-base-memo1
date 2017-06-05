package excepciones;


public class UnasignedTicketException extends RuntimeException{
    public UnasignedTicketException(String message) {
        super(message);
    }
}