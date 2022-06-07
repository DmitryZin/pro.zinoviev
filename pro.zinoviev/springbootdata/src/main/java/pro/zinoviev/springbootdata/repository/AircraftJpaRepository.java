package pro.zinoviev.springbootdata.repository;

import org.springframework.data.repository.CrudRepository;
import pro.zinoviev.springbootdata.entity.AircraftJpa;

public interface AircraftJpaRepository extends CrudRepository<AircraftJpa, Long> {
}
