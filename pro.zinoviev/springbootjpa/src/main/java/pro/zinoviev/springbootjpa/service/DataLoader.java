package pro.zinoviev.springbootjpa.service;

import org.springframework.stereotype.Component;
import pro.zinoviev.springbootjpa.entity.Coffee;
import pro.zinoviev.springbootjpa.repository.CoffeeRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoader {
    private final CoffeeRepository coffeeRepository;

    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void LoadData(){
        this.coffeeRepository.saveAll(List.of(
                new Coffee("Cafe Samurai"),
                new Coffee("Larenco"),
                new Coffee("Arabica")
        ));
    }
}
