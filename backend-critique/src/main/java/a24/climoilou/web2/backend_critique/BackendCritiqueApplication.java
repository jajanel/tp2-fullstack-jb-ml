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
public class BackendCritiqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCritiqueApplication.class, args);
	}


	@Bean
	CritiqueValidateur getCritiqueValidateur(){
		return new CritiqueValidateur();
	}
}
