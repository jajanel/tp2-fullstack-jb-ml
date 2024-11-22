package a24.climoilou.web2.backend_oiseau.Validators;

public class OiseauValidateur {

    public String validateCategorie(String categorie) {
        return categorie != null && (
                categorie == "poule" ||
                categorie == "oie" ||
                categorie == "canard" ||
                categorie == "dinde") ? "" : "Categorie invalide : veuillez choisir une categorie valide (poule, oie, canard ou dinde)\n";
    }

    public String validateOrigine(String origine) {
        return origine != null && (
                origine == "amerique" ||
                        origine == "asie" ||
                        origine == "europe" ||
                        origine == "afrique" ||
                        origine == "oceanie") ? "" : "Origine invalide : veuillez choisir un continent valide (amerique, asie, europe, afrique ou oceanie)\n";
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
