package a24.climoilou.web2.backend_critique.exceptions;

public class ErreurCritique {

    private String message;

    public ErreurCritique(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
