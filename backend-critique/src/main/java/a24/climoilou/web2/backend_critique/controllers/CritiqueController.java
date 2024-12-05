package a24.climoilou.web2.backend_critique.controllers;
import a24.climoilou.web2.backend_critique.exceptions.CritiqueInvalideException;
import a24.climoilou.web2.backend_critique.exceptions.CritiqueNotFoundException;
import a24.climoilou.web2.backend_critique.exceptions.ErreurCritique;
import a24.climoilou.web2.backend_critique.models.Critique;
import a24.climoilou.web2.backend_critique.repositories.CritiqueRepository;
import a24.climoilou.web2.backend_critique.validators.CritiqueValidateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CritiqueController implements CommandLineRunner {
    @Autowired
    private CritiqueValidateur critiqueValidateur;
    @Autowired
    private CritiqueRepository critiqueRepository;



    private Logger logger = LoggerFactory.getLogger(CritiqueController.class);

    @Override
    public void run(String... args) throws Exception {
        //TEST DES FONCTIONS AVEC QUELQUES CRITIQUES EN BASE DE DONNÉES:
        supprimerToutesCritiques();
        logger.info("Ajout de quelques critiques pour les tests: ");
        ajouterCritique(new Critique("chantecler", "Poule",50, 50, 50));
        ajouterCritique(new Critique("rousse","Poule", 60, 60, 60));
    }


    /**
     * Retourne toutes les critiques contenues dans le repository des critiques
     * @return toutes les critiques
     * @throws InterruptedException si le serveur prend trop de temps à répondre
     */
    @GetMapping("/critiques")
    public Collection<Critique> getAllCritiques() throws InterruptedException {
        logger.info("Retourne toutes les critiques confondues");
        return (Collection<Critique>) critiqueRepository.findAll();
    }

    /**
     * Retourne toutes les critiques pour le nom de l'oiseau donné en param
     * @param raceOiseau le nom de l'oiseau pour lequel on cherche les critiques
     * @return toutes les critiques pour cet oiseau
     * @throws InterruptedException si le serveur prend trop de temps à répondre
     */
    @GetMapping("/critiques/{raceOiseau}")
    public Collection<Critique> getCritiqueParNomOiseau(@PathVariable String raceOiseau) throws InterruptedException {
            logger.info("Retourne toutes les critiques pour l'oiseau: {}", raceOiseau);
            return (Collection<Critique>) critiqueRepository.findAllByRaceOiseau(raceOiseau);

    }


    /**
     * Méthode permettant d'ajouter une nouvelle critique dans la base de données
     * @param critique la critique passée par le frontend à ajouter dans la BD
     * @return l'ID de la nouvelle critique qui a été créée
     */
    @PostMapping("/ajouterCritique")
    public Long ajouterCritique(@RequestBody Critique critique) {
        Long id = 0L;
        if (critique != null) {
            if (critiqueValidateur.validateCritiqueComplete(critique)) {
                id = critiqueRepository.save(critique).getId();
                logger.info("Crée une nouvelle critique avec l'id: {} pour l'oiseau {}", id, critique.getRaceOiseau());
            } else {
                logger.warn("La critique n'a pas pu être crée");
                throw new CritiqueInvalideException();
            }
        }
        return id;
    }


    /**
     * Fonction qui permet de supprimer une critique de par son ID passée en paramètres
     * @param id l'id de la critique à supprimer
     */
    @DeleteMapping("/supprimerCritique/{id}")
    public void supprimerCritique(@PathVariable Long id) {
        if (critiqueRepository.existsById(id)) {
            critiqueRepository.deleteById(id);
            logger.info("Suppression de la critique avec l'id: {}", id);
        } else {
            logger.warn("La critique demandée: {} est inexistante. La suppression n'a pas eu lieu.", id);
            throw new CritiqueNotFoundException();
        }
    }


    /**
     * Fonction qui permet de supprimer toutes les critiques pour un oiseau donné
     * @param raceOiseau la race de l'oiseau (par exemple: chantecler, rousse, oscellated,etc.)
     */
    @DeleteMapping("/supprimerToutesCritiquesParOiseau/{raceOiseau}")
    public void supprimerToutesCritiquesParOiseau(@PathVariable String raceOiseau) {
        Collection<Critique> critiques = (Collection<Critique>) critiqueRepository.findAllByRaceOiseau(raceOiseau);
        if (critiques != null && !critiques.isEmpty()) {
            logger.info("Suppression de toutes les critiques pour {}", raceOiseau);
            critiqueRepository.deleteAll(critiques);
        } else {
            logger.warn("Il n'y a  plus aucune critique à supprimer pour {}", raceOiseau);

        }
    }

    /**
     * Méthode non utilisée par le front qui permet de vider complètement la base de données.
     * PLus utilisée pour des tests
     */
    public void supprimerToutesCritiques() {
        logger.info("Suppression de toutes les critiques");
        critiqueRepository.deleteAll();
    }


    /**
     * Retourne la note globale pour une seule critique, donc la moyenne du tempérament, beauté et utilisationd d'un oiseau.
     * @param idCritique l'id de la critique d'un oiseau que l'on veut obtenir la note globale
     * @return la note globale de la critique ou une exception si la critique avec cet id est inexistante.
     */
    @GetMapping("/getNoteGlobale/{idCritique}")
    public double getNoteGlobale(@PathVariable Long idCritique) {
        if (critiqueRepository.findById(idCritique).isPresent()) {
            double noteGlobale = Math.round(critiqueRepository.calculNoteGlobale(idCritique) * 100)/100;
            logger.info("La note moyenne de la critique {} est de: {}", idCritique, noteGlobale);
            return noteGlobale;
        } else {
            logger.warn("La critique demandée {} n'existe pas", idCritique);
            throw new CritiqueNotFoundException();
        }
    }


    /**
     * Méthode permettant de calculer la moyenne des notes globales de toutes les critiques d'un seul oiseau.
     * @param raceOiseau la race de l'oiseau pour lequel on veut obtenir la moyenne des notes globales (nom de l'oiseau: chantecler, rousse, oscellated, etc.)
     * @return la moyenne des notes globales de toutes les critiques pour cet oiseau
     */
    @GetMapping("/getMoyenneParOiseau/{raceOiseau}")
    public double getMoyenneParOiseau(@PathVariable String raceOiseau){
        double moyenneOiseau = 0;
        Collection<Critique> critiques = (Collection<Critique>) critiqueRepository.findAllByRaceOiseau(raceOiseau);
        if (!critiques.isEmpty()) {
            for (Critique critique : critiques) {
                moyenneOiseau += critique.calculNoteMoyenne();
            }
            moyenneOiseau /= critiques.size();
            moyenneOiseau = Math.round(moyenneOiseau * 100.0) / 100.0;
        }
        return moyenneOiseau;
    }


    /**
     * Méthode permettant de calculer la moyenne des notes globales de toutes les critiques pour tout les oiseaux d'une catégorie donnée
     * @param categorieOiseau la catgorie de l'oiseau (par exemple: Poule, Canard, Oie, etc.)
     * @return la moyenne des notes globales de toutes les critiques pour cette catégorie d'oiseau en double
     */
    @GetMapping("/getMoyenneParCategorie/{categorieOiseau}")
    public double getMoyenneParCategorie(@PathVariable String categorieOiseau) {
        double moyenneCategorie = 0;
        Collection<Critique> critiques = (Collection<Critique>) critiqueRepository.findAllByCategorieOiseau(categorieOiseau);
        if (critiques != null && !critiques.isEmpty()) {
            for (Critique critique : critiques) {
                moyenneCategorie += critique.calculNoteMoyenne();
            }
            moyenneCategorie /= critiques.size();
            moyenneCategorie = Math.round(moyenneCategorie * 100.0) / 100.0;
        }
        return moyenneCategorie;
    }



//    /**
//     * Retourne la note la plus haute de toute la liste de critique
//     *
//     * @param listeCritique la liste de critique données
//     * @return la note la plus haute
//     */
//    @GetMapping("/getNotePlusHaute/")
//    public double notePlusHaute(@RequestBody List<Critique> listeCritique) {
//        return listeCritique.stream()
//                .mapToDouble(critique -> {
//                    if (critiqueRepository.findById(critique.getId()).isPresent()) {
//                        return critiqueRepository.calculNoteGlobale(critique.getId());
//                    } else {
//                        throw new CritiqueNotFoundException();
//                    }
//                })
//                .max()
//                .orElseThrow(CritiqueNotFoundException::new);
//    }
//
//    /**
//     * Retourne la note la plus basse de toute la liste de critique
//     *
//     * @param listeCritique la liste de critique données
//     * @return la note la plus basse
//     */
//    @GetMapping("/getNotePlusBasse/")
//    public double notePlusBasse(@RequestBody List<Critique> listeCritique) {
//        return listeCritique.stream()
//                .mapToDouble(critique -> {
//                    if (critiqueRepository.findById(critique.getId()).isPresent()) {
//                        return critiqueRepository.calculNoteGlobale(critique.getId());
//                    } else {
//                        throw new CritiqueNotFoundException();
//                    }
//                })
//                .min()
//                .orElseThrow(CritiqueNotFoundException::new);
//    }




    /* ------------- Gestion des exceptions si une critique est non trouvée ou invalide -------------*/

    @ExceptionHandler(CritiqueInvalideException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErreurCritique handleCritiqueInvalideException(CritiqueInvalideException ex) {
        return new ErreurCritique(ex.getMessage());
    }


    //Exception fonctionnelle
    @ExceptionHandler(CritiqueInvalideException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErreurCritique handleCritiqueNotFoundException(CritiqueNotFoundException ex) {
        return new ErreurCritique(ex.getMessage());
    }


}
