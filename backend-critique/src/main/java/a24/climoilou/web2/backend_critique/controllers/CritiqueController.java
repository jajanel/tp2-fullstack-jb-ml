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

    }

    @PostMapping("/ajouterCritique")
    public Long ajouterCritique(@RequestBody Critique critique) {
        if (critique != null){
            if (critiqueValidateur.validateCritiqueComplete(critique)){

                logger.info("Crée une nouvelle critique avec l'id: "+ critique.getId());

            } else {
                logger.warn("La critique n'a pas pu être crée");

            }
        }

        Long id = 2L;
        return id;
    }

    @DeleteMapping("/supprimerCritique/{id}")
    public void supprimerCritique(@PathVariable Long id) {
        logger.info("Suppression de la critique avec l'id: "+ id);


    }

    @PatchMapping("/modifierCritique/{id}")
    public void modifierCritique(@PathVariable Long id, @RequestBody Critique critique) {

    }

    /**
     * Retourne la note la plus haute de toute la liste de critique
     * @param listeCritique la liste de critique données
     * @return la note la plus haute
     */
    @GetMapping("/getNotePlusHaute/")
    public double notePlusHaute(@RequestBody List<Critique> listeCritique) {
        Optional<Critique> notePlusHaute = listeCritique.stream()
                .max(Comparator.comparingDouble(Critique::getNoteGlobale));

        return notePlusHaute.get().getNoteGlobale();
    }

    /**
     * Retourne la note la plus basse de toute la liste de critique
     * @param listeCritique la liste de critique données
     * @return la note la plus basse
     */
    @GetMapping("/getNotePlusBasse/")
    public double notePlusBasse(@RequestBody List<Critique> listeCritique) {
        Optional<Critique> minNumber = listeCritique.stream()
                .min(Comparator.comparingDouble(Critique::getNoteGlobale));

                return minNumber.get().getNoteGlobale();
    }


    /**
     *
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
