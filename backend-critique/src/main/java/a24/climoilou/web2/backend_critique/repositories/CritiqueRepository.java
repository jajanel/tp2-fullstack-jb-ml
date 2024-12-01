package a24.climoilou.web2.backend_critique.repositories;

import a24.climoilou.web2.backend_critique.models.Critique;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CritiqueRepository extends CrudRepository<Critique, Long> {

Collection<Critique> findFirstByRaceOiseau(String raceOiseau);

Iterable<Critique> findAllByRaceOiseau (String raceOiseau);

Boolean existsByRaceOiseau(String raceOiseau);

@Query("SELECT (c.temperament + c.beaute + c.utilisation)/3 FROM Critique c WHERE c.id = :id")
Double calculNoteGlobale(@Param("id") Long id);



}
