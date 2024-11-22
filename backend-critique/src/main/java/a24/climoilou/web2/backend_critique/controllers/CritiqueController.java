package a24.climoilou.web2.backend_critique.controllers;

import a24.climoilou.web2.backend_critique.models.Critique;
import a24.climoilou.web2.backend_critique.validators.CritiqueValidateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    private Logger logger = LoggerFactory.getLogger(CritiqueController.class);


    @GetMapping("/critiques")
    public Collection<Critique> getAllCritiques() {
        logger.info("Retourne toutes les critiques confondues");

        return null;
    }

    @GetMapping("/critiques/{id}")
    public Collection<Critique> getCritiqueParBirdId(@PathVariable Long id) {
        logger.info("Retournes toutes les critiques pour l'id " + id);


        return null;
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





}
