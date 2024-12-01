package a24.climoilou.web2.backend_critique.models;

import a24.climoilou.web2.backend_critique.repositories.CritiqueRepository;
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
@Builder
public class Critique  {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //nom oiseau (Chantelcler, SIlkie, Rousse, Bain, Ocellated, etc)
    private String raceOiseau;
    private double temperament;
    private double beaute;
    private double utilisation;



    public Critique(String raceOiseau, double temperament, double beaute, double utilisation) {
        this.raceOiseau = raceOiseau;
        this.temperament = temperament;
        this.beaute = beaute;
        this.utilisation = utilisation;
    }

}
