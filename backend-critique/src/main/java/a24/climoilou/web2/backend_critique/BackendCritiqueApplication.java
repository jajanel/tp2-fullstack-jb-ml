package a24.climoilou.web2.backend_critique;

import a24.climoilou.web2.backend_critique.controllers.CritiqueController;
import a24.climoilou.web2.backend_critique.models.Critique;
import a24.climoilou.web2.backend_critique.repositories.CritiqueRepository;
import a24.climoilou.web2.backend_critique.validators.CritiqueValidateur;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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


	}

	@Bean
	CritiqueValidateur getCritiqueValidateur(){
		return new CritiqueValidateur();
	}
}
