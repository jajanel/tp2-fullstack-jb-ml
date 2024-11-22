package a24.climoilou.web2.backend_critique.exceptions;

public class CritiqueNotFoundException extends RuntimeException{

    public CritiqueNotFoundException(){
        super("Erreur: la critique est introuvable");
    }

}
