package pro.zinoviev.springbootjpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.zinoviev.springbootjpa.entity.Coffee;
import pro.zinoviev.springbootjpa.repository.CoffeeRepository;

import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class RestApiDemoController {
    private final CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    //@RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeesById(@PathVariable String id){
        return coffeeRepository.findById(id);
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee){
        return coffeeRepository.save(coffee);
    }

    /**
     * запросы PUT должны обновлять указанный ресурс при его наличии
     * и создавать его, при отсутствии
     */
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        return (coffeeRepository.existsById(id)) ?
                new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK) :
                new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id){
        coffeeRepository.deleteById(id);
    }

}
