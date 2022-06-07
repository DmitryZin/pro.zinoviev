package pro.zinoviev.springbootdata.repository;

import org.springframework.data.repository.CrudRepository;
import pro.zinoviev.springbootdata.domain.AircraftRepo;

public interface AircraftRepository extends CrudRepository<AircraftRepo, Long> {
}
