package com.miguel.ejercicio1;

import com.miguel.ejercicio1.model.Node;
import com.miguel.ejercicio1.repository.NodeRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent>{

    private final NodeRepository repository;

    public DataInitializer(NodeRepository repository) {
            this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        repository
                .deleteAll()
                .thenMany(
                        Flux
                                .just("A", "B", "C", "D")
                                .map(name -> new Node(UUID.randomUUID().toString(), name, "Ejemplo " + name))
                                .flatMap(repository::save)
                )
                .thenMany(repository.findAll())
                .subscribe();
    }
}
