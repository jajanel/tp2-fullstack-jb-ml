package a24.climoilou.web2.backend_oiseau.Controllers;

import a24.climoilou.web2.backend_oiseau.Exceptions.OiseauNotFoundException;
import a24.climoilou.web2.backend_oiseau.Models.Oiseau;
import a24.climoilou.web2.backend_oiseau.Repositories.OiseauRepository;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OiseauControlleur {

    private final OiseauRepository oiseauRepository;
    private Logger logger = (Logger) LoggerFactory.getLogger(OiseauControlleur.class);

    public OiseauControlleur(OiseauRepository oiseauRepository) {
        this.oiseauRepository = oiseauRepository;
    }

    @GetMapping(value = "/oiseaux", produces = "application/json")
    public String getAllOiseaux(){

        return """ 
                    [{
                    "idOiseau": 1,
                    "categorie": "poule",
                    "race": "chantecler",
                    "prix": 50,
                    "origine": "amerique",
                    "datePublication": "2024-12-12",
                    "srcImage": "src/assets/images/poules/chantecler.jpg"
                    },
                    {
                    "idOiseau": 2,
                    "categorie": "poule",
                    "race": "ayam cemami",
                    "prix": 95,
                    "origine": "asie",
                    "datePublication": "2024-12-02",
                    "srcImage": "src/assets/images/poules/ayam.jpg"
                    }]
               """;
    }

    @GetMapping("/oiseaux/{id}")
    public Collection<Oiseau> getOiseauParBirdId(@PathVariable Long id){

        return null;
    }

    @PostMapping("/ajouterOiseau")
    public Long ajouterOiseau(@RequestBody Oiseau oiseau){
        logger.info("ajout de l'oiseau " + oiseau);
        Long id = -1L;

        id = oiseauRepository.save(oiseau).getId();
        logger.info("le nouvel id est : " + id);

        return id;
    }

    @DeleteMapping("/supprimerOiseau/{id}")
    public void supprimerOiseau(@PathVariable Long id){
        logger.info("deleteOiseau " + id);

        if (oiseauRepository.existsById(id)) {
            oiseauRepository.deleteById(id);

        } else {
            logger.warning("le id demandé n'existe pas");
            throw new OiseauNotFoundException();
        }
    }

    @PatchMapping("/modifierOiseau/{id}")
    public void modifierOiseau(@PathVariable Long id, @RequestBody Oiseau oiseau){

    }


}
