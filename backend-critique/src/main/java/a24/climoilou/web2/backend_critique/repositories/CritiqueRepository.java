package a24.climoilou.web2.backend_critique.repositories;

import a24.climoilou.web2.backend_critique.models.Critique;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CritiqueRepository extends CrudRepository<Critique, Long> {

Collection<Critique> findFirstByNomOiseau(String nomOiseau);

Iterable<Critique> findAllByNomOiseau(String nomOiseau);

}
