package a24.climoilou.web2.backend_oiseau.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Entity
public class Oiseau {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String categorie;
    String race;
    double prix;
    String origine;
    String datePublication;
    String srcImage;

    public Oiseau(String categorie, String race, double prix, String origine, String datePublication, String srcImage) {
        this.categorie = categorie;
        this.race = race;
        this.prix = prix;
        this.origine = origine;
        this.datePublication = datePublication;
        this.srcImage = srcImage;
    }
}
