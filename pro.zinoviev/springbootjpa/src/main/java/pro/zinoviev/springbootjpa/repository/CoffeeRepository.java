package pro.zinoviev.springbootjpa.repository;

import org.springframework.data.repository.CrudRepository;
import pro.zinoviev.springbootjpa.entity.Coffee;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {
}
