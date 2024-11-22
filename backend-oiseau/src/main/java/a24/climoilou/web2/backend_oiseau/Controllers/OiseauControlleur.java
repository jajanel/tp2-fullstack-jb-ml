package a24.climoilou.web2.backend_oiseau.Controllers;

import a24.climoilou.web2.backend_oiseau.Exceptions.OiseauNotFoundException;
import a24.climoilou.web2.backend_oiseau.Models.Oiseau;
import a24.climoilou.web2.backend_oiseau.Repositories.OiseauRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OiseauControlleur {

    @Autowired
    private OiseauRepository oiseauRepository;
    private Logger logger = (Logger) LoggerFactory.getLogger(OiseauControlleur.class);

    public OiseauControlleur() {
    }

    @GetMapping(value = "/oiseaux", produces = "application/json")
    public Collection<Oiseau> getAllOiseaux() {
        logger.info("affichage des oiseaux");
        return (Collection<Oiseau>) oiseauRepository.findAll();

    }

    @GetMapping("/oiseaux/{id}")
    public Collection<Oiseau> getOiseauParBirdId(@PathVariable Long id) {
        logger.info("affichage de l'oiseau #" + id);
        return (Collection<Oiseau>) oiseauRepository.findOiseauById(id);
    }

    @PostMapping("/ajouterOiseau")
    public Long ajouterOiseau(@RequestBody Oiseau oiseau) {
        logger.info("ajout de l'oiseau " + oiseau);
        Long id = -1L;

        id = oiseauRepository.save(oiseau).getId();
        logger.info("le nouvel id est : " + id);

        return id;
    }

    @DeleteMapping("/supprimerOiseau/{id}")
    public void supprimerOiseau(@PathVariable Long id) {
        logger.info("deleteOiseau " + id);

        if (oiseauRepository.existsById(id)) {
            oiseauRepository.deleteById(id);

        } else {
            logger.warn("le id demandé n'existe pas");
            throw new OiseauNotFoundException();
        }
    }

    @PatchMapping("/modifierOiseau/{id}")
    public void modifierOiseau(@PathVariable Long id, @RequestBody Oiseau oiseau) {

    }


}
