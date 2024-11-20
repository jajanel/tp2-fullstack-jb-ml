package a24.climoilou.web2.backend_critique.validators;

import a24.climoilou.web2.backend_critique.models.Critique;

public class CritiqueValidateur {

    public final int CARACTERES_MIN_REVIEW = 10;
    public final int CARACTERES_MAX_REVIEW = 1000;
    public final int CARACTERES_MIN_RACE = 2;
    public final int CARACTERES_MAX_RACE = 20;

    public boolean validateNoteGlobale(Double noteGlobale) {
        return noteGlobale != null ;

    }

    public boolean validateRaceOiseau(String raceOiseau){
     return raceOiseau != null &&
             raceOiseau.length() >= CARACTERES_MIN_RACE &&
             raceOiseau.length() < CARACTERES_MAX_RACE;
    }
    public boolean validateNomOiseau(String nomOiseau){
        return nomOiseau != null &&
                nomOiseau.length() >= CARACTERES_MIN_RACE &&
                nomOiseau.length() < CARACTERES_MAX_RACE;
    }



    public boolean validateReviewTexte(String reviewDonne) {
        return reviewDonne != null &&
                reviewDonne.length() > CARACTERES_MIN_REVIEW &&
                reviewDonne.length() < CARACTERES_MAX_REVIEW;

    }

    public boolean validateCritiqueComplete(Critique critique) {
        return validateRaceOiseau(critique.getRaceOiseau()) &&
                validateReviewTexte(critique.getTemperament()) &&
                validateReviewTexte(critique.getBeaute()) &&
                validateReviewTexte(critique.getUtilisation()) &&
                validateNoteGlobale(critique.getNoteGlobale()) &&
                validateNomOiseau(critique.getNomOiseau());
    }

}
