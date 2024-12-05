package a24.climoilou.web2.backend_critique.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Critique  {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String raceOiseau;
    private String categorieOiseau;
    private double temperament;
    private double beaute;
    private double utilisation;

    public Critique(String raceOiseau, String categorieOiseau, double temperament, double beaute, double utilisation) {
        this.raceOiseau = raceOiseau;
        this.categorieOiseau = categorieOiseau;
        this.temperament = temperament;
        this.beaute = beaute;
        this.utilisation = utilisation;
    }


    /**
     * Calcul de la note moyenne pôur calculer la note globale et la moyenne des notes globale pour chaque oiseau
     * @return
     */
    public double calculNoteMoyenne(){
        return (temperament + beaute + utilisation)/3;
    }
}
