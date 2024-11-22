package a24.climoilou.web2.backend_oiseau.Validators;

import a24.climoilou.web2.backend_oiseau.Models.Oiseau;

public class OiseauValidateur {

    public String validateoiseau(Oiseau oiseau) {
        return "" +
                validateCategorie(oiseau.getCategorie()) +
                validateRace(oiseau.getRace()) +
                validatePrix(oiseau.getPrix()) +
                validateOrigine(oiseau.getOrigine()) +
                validateSrc(oiseau.getSrcImage());

    }

    public String validateCategorie(Oiseau.Categorie categorie) {
        return categorie != null && (
                categorie == Oiseau.Categorie.poule ||
                categorie == Oiseau.Categorie.oie ||
                categorie == Oiseau.Categorie.canard ||
                categorie == Oiseau.Categorie.dinde) ? "" : "Categorie invalide : veuillez choisir une categorie valide (poule, oie, canard ou dinde)\n";
    }

    public String validateOrigine(Oiseau.Origine origine) {
        return origine != null && (
                origine == Oiseau.Origine.amerique ||
                        origine == Oiseau.Origine.asie ||
                        origine == Oiseau.Origine.europe ||
                        origine == Oiseau.Origine.afrique ||
                        origine == Oiseau.Origine.oceanie) ? "" : "Origine invalide : veuillez choisir un continent valide (amerique, asie, europe, afrique ou oceanie)\n";
    }

    public String validateRace(String race) {
        return race != null ? "" : "race invalide\n";
    }

    public String validatePrix(double prix) {
        return prix > 0 ? "" : "prix invalide\n";
    }

    public String validateSrc(String src) {
        return src != null &&
                src.startsWith("src/assets/images/") ? "" : "src invalide : veuillez mettre une source qui commence par \"src/assets/images/\"\n";
    }
}
