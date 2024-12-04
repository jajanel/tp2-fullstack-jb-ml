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
import java.util.List;

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
        ajouterCritique(new Critique("chantecler", "Poule",50, 50, 50));
        ajouterCritique(new Critique("rousse","Poule", 60, 60, 60));
    }


    /**
     * Retourne toutes les critiques contenues dans le repository des critiques
     *
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
     *
     * @param raceOiseau le nom de l'oiseau pour lequel on cherche les critiques
     * @return toutes les critiques pour cet oiseau
     * @throws InterruptedException si le serveur prend trop de temps à répondre
     */
    @GetMapping("/critiques/{raceOiseau}")
    public Collection<Critique> getCritiqueParNomOiseau(@PathVariable String raceOiseau) throws InterruptedException {
            logger.info("Retourne toutes les critiques pour l'oiseau: {}", raceOiseau);
            return (Collection<Critique>) critiqueRepository.findAllByRaceOiseau(raceOiseau);

    }

    //MÉTHODE TESTÉE
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

    //MÉTHODE TESTÉE
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

    //MÉTHODE TESTÉE
    /**
     * Fonction qui supprime toutes les critiques associées à un produit (oiseau) de par son nom.
     *
     * @param raceOiseau le nom de l'oiseau pour lequel on souhaite supprimer toutes les critiques.
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

    //Méthode testée
    public void supprimerToutesCritiques() {
        logger.info("Suppression de toutes les critiques");
        critiqueRepository.deleteAll();
    }


    /**
     * Retourne la note la plus haute de toute la liste de critique
     *
     * @param listeCritique la liste de critique données
     * @return la note la plus haute
     */
    @GetMapping("/getNotePlusHaute/")
    public double notePlusHaute(@RequestBody List<Critique> listeCritique) {
        return listeCritique.stream()
                .mapToDouble(critique -> {
                    if (critiqueRepository.findById(critique.getId()).isPresent()) {
                        return critiqueRepository.calculNoteGlobale(critique.getId());
                    } else {
                        throw new CritiqueNotFoundException();
                    }
                })
                .max()
                .orElseThrow(CritiqueNotFoundException::new);
    }

    /**
     * Retourne la note la plus basse de toute la liste de critique
     *
     * @param listeCritique la liste de critique données
     * @return la note la plus basse
     */
    @GetMapping("/getNotePlusBasse/")
    public double notePlusBasse(@RequestBody List<Critique> listeCritique) {
        return listeCritique.stream()
                .mapToDouble(critique -> {
                    if (critiqueRepository.findById(critique.getId()).isPresent()) {
                        return critiqueRepository.calculNoteGlobale(critique.getId());
                    } else {
                        throw new CritiqueNotFoundException();
                    }
                })
                .min()
                .orElseThrow(CritiqueNotFoundException::new);
    }


    //MÉTHODE TESTÉE
    /**
     * Retourne la note moyenne de la critique qui est passé en paramètre
     *
     * @param idCritique l'id de la critique pour laquelle on souhaite obtenir la note moyenne
     * @return la note moyenne de la critique
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

    @GetMapping("/getMoyenneParCategorie/{categorieOiseau}")
    public double getMoyenneParCategorie(@PathVariable String categorieOiseau) {
        double moyenneCategorie = 0;
        if (critiqueRepository.existsByCategorieOiseau(categorieOiseau)) {
            Collection<Critique> toutesCritiquesDeCategorie = (Collection<Critique>) critiqueRepository.findAllByCategorieOiseau(categorieOiseau);
            for (Critique critique : toutesCritiquesDeCategorie ) {
                logger.info("Calcul des moyennes pour les oiseaux {}", categorieOiseau);
                moyenneCategorie += critique.calculNoteMoyenne();
                moyenneCategorie /= toutesCritiquesDeCategorie.size();
            }
        } else {
            logger.warn("Aucun oiseau dans la catégorie: {}", categorieOiseau);
            throw new CritiqueNotFoundException();
        }
        return moyenneCategorie;
    }



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
