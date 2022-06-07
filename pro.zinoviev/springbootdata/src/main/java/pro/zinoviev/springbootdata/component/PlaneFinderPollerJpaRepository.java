package pro.zinoviev.springbootdata.component;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pro.zinoviev.springbootdata.entity.AircraftJpa;
import pro.zinoviev.springbootdata.repository.AircraftJpaRepository;

@EnableScheduling
@Component
@RequiredArgsConstructor
public class PlaneFinderPollerJpaRepository {
    @NotNull
    private final AircraftJpaRepository repository;
    private WebClient client = WebClient.create("http://localhost:7634/aircraft");

    @Scheduled(fixedRate = 1000)
    private void pollPlanes(){
        repository.deleteAll();

        client.get()
                .retrieve()
                .bodyToFlux(AircraftJpa.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(repository::save);

        repository.findAll().forEach(System.out::println);
    }
}
