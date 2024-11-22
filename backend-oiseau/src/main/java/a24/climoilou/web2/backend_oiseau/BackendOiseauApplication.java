package a24.climoilou.web2.backend_oiseau;

import a24.climoilou.web2.backend_oiseau.Models.Oiseau;
import a24.climoilou.web2.backend_oiseau.Repositories.OiseauRepository;
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

		Oiseau oiseau = oiseauRepository.findByRace("chantecler");
		if (oiseau == null) {
			oiseauRepository.save(new Oiseau("poule", "chantecler", 50, "amerique", "2024-12-12", "src/assets/images/poules/chantecler.jpg"));
		}
		Oiseau oiseau2 = oiseauRepository.findByRace("ayam cemani");
		if (oiseau2 == null) {
			oiseauRepository.save(new Oiseau("poule", "ayam cemani", 95, "asie", "2024-12-2", "src/assets/images/poules/ayam.jpg"));
		}
		Oiseau oiseau3 = oiseauRepository.findByRace("silkie");
		if (oiseau3 == null) {
			oiseauRepository.save(new Oiseau("poule", "silkie", 32, "asie", "2024-12-3", "src/assets/images/poules/silkie.jpg"));
		}
		Oiseau oiseau4 = oiseauRepository.findByRace("faverolle");
		if (oiseau4 == null) {
			oiseauRepository.save(new Oiseau("poule", "faverolle", 56, "europe", "2024-12-1", "src/assets/images/poules/faverolle.jpg"));
		}
		Oiseau oiseau5 = oiseauRepository.findByRace("brahma");
		if (oiseau5 == null) {
			oiseauRepository.save(new Oiseau("poule", "brahma", 40, "amerique", "2024-12-11", "src/assets/images/poules/brahma.jpg"));
		}
		Oiseau oiseau6 = oiseauRepository.findByRace("rousse");
		if (oiseau6 == null) {
			oiseauRepository.save(new Oiseau("poule", "rousse", 12, "amerique", "2024-12-8", "src/assets/images/poules/rousse.jpg"));
		}
		Oiseau oiseau7 = oiseauRepository.findByRace("frizzle");
		if (oiseau7 == null) {
			oiseauRepository.save(new Oiseau("poule", "frizzle", 35, "asie", "2024-12-11", "src/assets/images/poules/frizzle.jpg"));
		}
		Oiseau oiseau8 = oiseauRepository.findByRace("sebastopol");
		if (oiseau8 == null) {
			oiseauRepository.save(new Oiseau("oie", "sebastopol", 80, "europe", "2024-12-11", "src/assets/images/oies/sebastopol.jpg"));
		}
		Oiseau oiseau9 = oiseauRepository.findByRace("bernache");
		if (oiseau9 == null) {
			oiseauRepository.save(new Oiseau("oie", "bernache", 25, "asie", "2024-12-11", "src/assets/images/oies/bernache.jpg"));
		}
		Oiseau oiseau10 = oiseauRepository.findByRace("blanche");
		if (oiseau10 == null) {
			oiseauRepository.save(new Oiseau("oie", "blanche", 12, "asie", "2024-12-11", "src/assets/images/oies/blanche.jpg"));
		}
		Oiseau oiseau11 = oiseauRepository.findByRace("cendre");
		if (oiseau11 == null) {
			oiseauRepository.save(new Oiseau("oie", "cendre", 20, "asie", "2024-12-11", "src/assets/images/oies/cendre.jpg"));
		}
		Oiseau oiseau12 = oiseauRepository.findByRace("chine");
		if (oiseau12 == null) {
			oiseauRepository.save(new Oiseau("oie", "chine", 56, "asie", "2024-12-11", "src/assets/images/oies/chine.jpg"));
		}
		Oiseau oiseau13 = oiseauRepository.findByRace("narrangansett");
		if (oiseau13 == null) {
			oiseauRepository.save(new Oiseau("dinde", "narrangansett", 90, "asie", "2024-12-11", "src/assets/images/dindes/narragansett.jpg"));
		}
		Oiseau oiseau14 = oiseauRepository.findByRace("ocellated");
		if (oiseau14 == null) {
			oiseauRepository.save(new Oiseau("dinde", "ocellated", 200, "asie", "2024-12-11", "src/assets/images/dindes/ocellated.jpg"));
		}
		Oiseau oiseau15 = oiseauRepository.findByRace("sauvage");
		if (oiseau15 == null) {
			oiseauRepository.save(new Oiseau("dinde", "sauvage", 1, "asie", "2024-12-11", "src/assets/images/dindes/sauvage.jpg"));
		}
		Oiseau oiseau16 = oiseauRepository.findByRace("bain");
		if (oiseau16 == null) {
			oiseauRepository.save(new Oiseau("canard", "bain", 1, "asie", "2024-12-11", "src/assets/images/canards/bain.jpg"));
		}
		Oiseau oiseau17 = oiseauRepository.findByRace("branchu");
		if (oiseau17 == null) {
			oiseauRepository.save(new Oiseau("canard", "branchu", 60, "asie", "2024-12-11", "src/assets/images/canards/branchu.jpg"));
		}
		Oiseau oiseau18 = oiseauRepository.findByRace("colvert");
		if (oiseau18 == null) {
			oiseauRepository.save(new Oiseau("canard", "colvert", 10, "amerique", "2024-12-11", "src/assets/images/canards/colvert.jpg"));
		}
		Oiseau oiseau19 = oiseauRepository.findByRace("pekin");
		if (oiseau19 == null) {
			oiseauRepository.save(new Oiseau("canard", "pekin", 20, "asie", "2024-12-11", "src/assets/images/canards/pekin.jpg"));
		}
		Oiseau oiseau20 = oiseauRepository.findByRace("runner");
		if (oiseau20 == null) {
			oiseauRepository.save(new Oiseau("canard", "runner", 68, "asie", "2024-12-11", "src/assets/images/canards/runner.jpg"));
		}

		oiseauRepository.findAll().forEach(System.out::println);
	}


	public static void main(String[] args) {
		SpringApplication.run(BackendOiseauApplication.class, args);
	}

}
