package a24.climoilou.web2.backend_critique.exceptions;

public class CritiqueInvalideException extends RuntimeException{
    public CritiqueInvalideException(){
        super("Erreur: la critique est invalide");
    }


}
