package a24.climoilou.web2.backend_critique.controllers;

import a24.climoilou.web2.backend_critique.models.Critique;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CritiqueController {

    @GetMapping("/critiques")
    public Collection<Critique> getAllCritiques(){

        return null;
    }

    @GetMapping("/critiques/{id}")
    public Collection<Critique> getCritiqueParBirdId(@PathVariable Long id){

        return null;
    }

    @PostMapping("/ajouterCritique")
    public void ajouterCritique(@RequestBody Critique critique){

    }

    @DeleteMapping("/supprimerCritique/{id}")
    public void supprimerCritique(@PathVariable Long id){

    }

   @PatchMapping("/modifierCritique/{id}")
    public void modifierCritique(@PathVariable Long id, @RequestBody Critique critique){

    }


}
