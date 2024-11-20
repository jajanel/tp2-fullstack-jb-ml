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
import java.util.logging.Logger;

@SpringBootApplication
public class BackendOiseauApplication {

	@Autowired
	private static OiseauRepository oiseauRepository;

	@Bean
	CommandLineRunner initData() {
		return this::run;
	}

	private void run(String... strings) {
		Logger logger = (Logger) LoggerFactory.getLogger(BackendOiseauApplication.class);
		logger.info("init Data*********************************");

		oiseauRepository.save(new Oiseau("poule", "ouiseau", 2, "Asie", new Date(), "aucuneSrc"));
		oiseauRepository.findAll().forEach(System.out::println);
	}


	public static void main(String[] args) {
		SpringApplication.run(BackendOiseauApplication.class, args);
	}

}
