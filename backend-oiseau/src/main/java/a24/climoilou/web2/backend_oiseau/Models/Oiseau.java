package a24.climoilou.web2.backend_oiseau.Models;

import jakarta.persistence.*;
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

    public enum Origine {asie, amerique, oceanie, europe, afrique}
    public enum Categorie {poule, dinde, oie, canard}
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    Categorie categorie;
    @Column(unique = true)
    String race;
    @Column
    double prix;
    @Column
    @Enumerated(EnumType.STRING)
    Origine origine;
    @Column
    String datePublication;
    @Column
    String srcImage;

    public Oiseau(Categorie categorie, String race, double prix, Origine origine, String datePublication, String srcImage) {
        this.categorie = categorie;
        this.race = race;
        this.prix = prix;
        this.origine = origine;
        this.datePublication = datePublication;
        this.srcImage = srcImage;
    }
}
