package a24.climoilou.web2.backend_oiseau.Controllers;

import a24.climoilou.web2.backend_oiseau.Models.Oiseau;
import a24.climoilou.web2.backend_oiseau.Models.Oiseau;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class OiseauControlleur {

    @GetMapping("/oiseaux")
    public Collection<Oiseau> getAllOiseaux(){

        return null;
    }

    @GetMapping("/oiseaux/{id}")
    public Collection<Oiseau> getOiseauParBirdId(@PathVariable Long id){

        return null;
    }

    @PostMapping("/ajouterOiseau")
    public void ajouterOiseau(@RequestBody Oiseau oiseau){

    }

    @DeleteMapping("/supprimerOiseau/{id}")
    public void supprimerOiseau(@PathVariable Long id){

    }

    @PatchMapping("/modifierOiseau/{id}")
    public void modifierOiseau(@PathVariable Long id, @RequestBody Oiseau oiseau){

    }


}
