package a24.climoilou.web2.backend_oiseau.Repositories;

import a24.climoilou.web2.backend_oiseau.Models.Oiseau;
import org.springframework.data.repository.CrudRepository;

public interface OiseauRepository extends CrudRepository <Oiseau, Long> {
    Oiseau findByRace(String race);
    Oiseau findOiseauById(Long id);
}
