package a24.climoilou.web2.backend_critique;

import a24.climoilou.web2.backend_critique.controllers.CritiqueController;
import a24.climoilou.web2.backend_critique.models.Critique;
import a24.climoilou.web2.backend_critique.repositories.CritiqueRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackendCritiqueApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendCritiqueApplication.class, args);
	}

	private Logger logger = LoggerFactory.getLogger(CritiqueController.class);

	@Autowired
	private CritiqueRepository critiqueRepository;




	@Override
	public void run(String... args) throws Exception {
		logger.info("******* Initialisation des critiques par défaut *******");
		//POur l'initialisation des critiques par défaut dans notre bd


		logger.info("******* Création d'instances de critiques *******");


		logger.info("******* Ajout des critiques à la base de donnée *******");


		logger.info("******* Fin de l'initialisation des critiques par défaut *******");


	}
}
