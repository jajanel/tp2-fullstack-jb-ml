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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import java.util.Optional;

@CrossOrigin(origins = "")
@RestController
public class CritiqueController {

    @Autowired
    private CritiqueValidateur critiqueValidateur;
    @Autowired
    private CritiqueRepository critiqueRepository;


    private Logger logger = LoggerFactory.getLogger(CritiqueController.class);

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
     * @param nomOiseau le nom de l'oiseau pour lequel on cherche les critiques
     * @return toutes les critiques pour cet oiseau
     * @throws InterruptedException si le serveur prend trop de temps à répondre
     */
    @GetMapping("/critiques/{raceOiseau}")
    public Collection<Critique> getCritiqueParNomOiseau(@PathVariable String raceOiseau) throws InterruptedException {
        if (critiqueRepository.existsByRaceOiseau(raceOiseau)) {
            logger.info("Retourne toutes les critiques pour l'oiseau: {}", raceOiseau);
            return (Collection<Critique>) critiqueRepository.findAllByRaceOiseau(raceOiseau);
        } else {
            throw new CritiqueNotFoundException();
        }
    }

    @PostMapping("/ajouterCritique")
    public Long ajouterCritique(@RequestBody Critique critique) {
        Long id = 0L;

        if (critique != null) {
            if (critiqueValidateur.validateCritiqueComplete(critique)) {
                id = critiqueRepository.save(critique).getId();
                logger.info("Crée une nouvelle critique avec l'id: {}", id);
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
            logger.warn("La critique demandée est inexistante. La suppression n'a pas eu lieu.");
            throw new CritiqueNotFoundException();
        }
    }

    /**
     *Fonction qui supprime toutes les critiques associées à un produit (oiseau) de par son nom.
     * @param nomOiseau le nom de l'oiseau pour lequel on souhaite supprimer toutes les critiques.
     */
    @DeleteMapping("/supprimerToutesCritiquesParOiseau/{nomOiseau}")
    public void supprimerToutesCritiquesParOiseau(@PathVariable String nomOiseau){
        if (critiqueRepository.findAllByNomOiseau(nomOiseau) != null) {
            logger.info("Suppression de toutes les critiques pour {}", nomOiseau);
          critiqueRepository.deleteAll( critiqueRepository.findAllByNomOiseau(nomOiseau));
        }
        logger.warn("Il n'y a aucune critique à supprimer pour {}", nomOiseau);
        throw new CritiqueNotFoundException();
    }


    /**
     * Retourne la note la plus haute de toute la liste de critique
     *
     * @param listeCritique la liste de critique données
     * @return la note la plus haute
     */
    @GetMapping("/getNotePlusHaute/")
    public double notePlusHaute(@RequestBody List<Critique> listeCritique) {
        Optional<Critique> notePlusHaute = listeCritique.stream().max(Comparator.comparingDouble(Critique::getNoteGlobale));
        return notePlusHaute.get().getNoteGlobale();
    }

    /**
     * Retourne la note la plus basse de toute la liste de critique
     *
     * @param listeCritique la liste de critique données
     * @return la note la plus basse
     */
    @GetMapping("/getNotePlusBasse/")
    public double notePlusBasse(@RequestBody List<Critique> listeCritique) {
        Optional<Critique> minNumber = listeCritique.stream().min(Comparator.comparingDouble(Critique::getNoteGlobale));

        return minNumber.get().getNoteGlobale();
    }


    /**
     * @param listeCritique
     * @return
     */
    @GetMapping("/getNoteMoyenne/")
    public double noteMoyenne(@RequestBody List<Critique> listeCritique) {
        double noteMoyenne = 2.90;


        return noteMoyenne;

    }


    @ExceptionHandler(CritiqueInvalideException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErreurCritique handleCritiqueInvalideException(CritiqueInvalideException ex) {
        return new ErreurCritique(ex.getMessage());
    }

    @ExceptionHandler(CritiqueInvalideException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErreurCritique handleCritiqueNotFoundException(CritiqueNotFoundException ex) {
        return new ErreurCritique(ex.getMessage());
    }


}
