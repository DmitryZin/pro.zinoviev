package pro.zinoviev.springbootdata.component;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pro.zinoviev.springbootdata.domain.AircraftRepo;
import pro.zinoviev.springbootdata.repository.AircraftRepository;

import java.util.Objects;

@EnableScheduling
@Component
public class PlaneFinderPollerRepository {
    private final WebClient client = WebClient.create("http://localhost:7634/aircraft");
    private final RedisConnectionFactory connectionFactory;
    private final AircraftRepository repository;

    PlaneFinderPollerRepository(RedisConnectionFactory connectionFactory, AircraftRepository repository){
        this.connectionFactory = connectionFactory;
        this.repository = repository;
    }

    @Scheduled(fixedRate = 1000)
    private void pollPlanes(){
        connectionFactory.getConnection().serverCommands().flushDb();

        client.get()
                .retrieve()
                .bodyToFlux(AircraftRepo.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(repository::save);

        repository.findAll().forEach(System.out::println);
    }
}
