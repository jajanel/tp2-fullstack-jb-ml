package a24.climoilou.web2.backend_critique.validators;

import a24.climoilou.web2.backend_critique.models.Critique;

public class CritiqueValidateur {


    public final int CARACTERES_MIN_RACE = 2;
    public final int CARACTERES_MAX_RACE = 20;

    public boolean validateNotes(Double note) {
        return note != null && note <= 100 && note >= 0;

    }

    public boolean validateRaceOiseau(String raceOiseau){
     return raceOiseau != null &&
             raceOiseau.length() >= CARACTERES_MIN_RACE &&
             raceOiseau.length() < CARACTERES_MAX_RACE;
    }




    public boolean validateCritiqueComplete(Critique critique) {
        return validateRaceOiseau(critique.getRaceOiseau()) &&
                validateNotes(critique.getTemperament()) &&
                validateNotes(critique.getBeaute()) &&
                validateNotes(critique.getUtilisation());}

}
