package a24.climoilou.web2.backend_oiseau;

import a24.climoilou.web2.backend_oiseau.Models.Oiseau;
import a24.climoilou.web2.backend_oiseau.Repositories.OiseauRepository;
import a24.climoilou.web2.backend_oiseau.Validators.OiseauValidateur;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import org.slf4j.Logger;

@SpringBootApplication
public class BackendOiseauApplication implements CommandLineRunner {

	@Autowired
	private OiseauRepository oiseauRepository;

	@Bean
	CommandLineRunner initData() {
		return this::run;
	}

	public void run(String... strings) {
		Logger logger = (Logger) LoggerFactory.getLogger(BackendOiseauApplication.class);
		logger.info("init Data*********************************");

		Oiseau oiseau = oiseauRepository.findByRace("bain");
		if (oiseau == null) {
			oiseau = Oiseau.builder().
					categorie(Oiseau.Categorie.canard)
					.race("bain")
					.prix(1)
					.origine(Oiseau.Origine.asie)
					.datePublication("2024-12-11")
					.srcImage("src/assets/images/canards/bain.jpg").build();
			oiseauRepository.save(oiseau);
		}
		Oiseau oiseau2 = oiseauRepository.findByRace("ayam cemani");
		if (oiseau2 == null) {
			oiseau2 = Oiseau.builder().
					categorie(Oiseau.Categorie.poule)
					.race("ayam cemani")
					.prix(95)
					.origine(Oiseau.Origine.asie)
					.datePublication("2024-12-02")
					.srcImage("src/assets/images/poules/ayam.jpg").build();
			oiseauRepository.save(oiseau2);
		}
		Oiseau oiseau3 = oiseauRepository.findByRace("sebastopol");
		if (oiseau3 == null) {
			oiseau3 = Oiseau.builder().
					categorie(Oiseau.Categorie.oie)
					.race("sebastopol")
					.prix(80)
					.origine(Oiseau.Origine.europe)
					.datePublication("2024-12-11")
					.srcImage("src/assets/images/oies/sebastopol.jpg").build();
			oiseauRepository.save(oiseau3);
		}
		Oiseau oiseau4 = oiseauRepository.findByRace("sauvage");
		if (oiseau4 == null) {
			oiseau4 = Oiseau.builder().
					categorie(Oiseau.Categorie.dinde)
					.race("sauvage")
					.prix(1)
					.origine(Oiseau.Origine.asie)
					.datePublication("2024-12-11")
					.srcImage("src/assets/images/dindes/sauvage.jpg").build();
			oiseauRepository.save(oiseau4);
		}
		Oiseau oiseau5 = oiseauRepository.findByRace("colvert");
		if (oiseau5 == null) {
			oiseau5 = Oiseau.builder().
					categorie(Oiseau.Categorie.canard)
					.race("colvert")
					.prix(10)
					.origine(Oiseau.Origine.amerique)
					.datePublication("2024-12-11")
					.srcImage("src/assets/images/canards/colvert.jpg").build();
			oiseauRepository.save(oiseau5);
		}

		oiseauRepository.findAll().forEach(System.out::println);
	}

	@Bean
	OiseauValidateur getValidateurOiseau() {
		return new OiseauValidateur();
	}


	public static void main(String[] args) {
		SpringApplication.run(BackendOiseauApplication.class, args);
	}

}
