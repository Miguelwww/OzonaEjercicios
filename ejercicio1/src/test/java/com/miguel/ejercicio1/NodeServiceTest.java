package com.miguel.ejercicio1;

import com.miguel.ejercicio1.config.NodeService;
import com.miguel.ejercicio1.model.Node;
import com.miguel.ejercicio1.repository.NodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.UUID;
import java.util.function.Predicate;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@Import(NodeService.class)
public class NodeServiceTest {

    private final NodeService service;
    private final NodeRepository repository;

    public NodeServiceTest(@Autowired NodeService service, @Autowired NodeRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Test
    public void getAll() {
        Flux<Node> saved = Flux.just(
                new Node(null, "Manuel", "Ejemplo Test 1"),
                new Node(null, "Pedro", "Ejemplo Test 2"),
                new Node(null, "Santi", "Ejemplo Test 3")
        );

        Flux<Node> composite = service.all().thenMany(saved);
        Predicate<Node> match = node -> saved.any(saveItem -> saveItem.equals(node)).block();

        StepVerifier
                .create(composite)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .expectNextMatches(match)
                .verifyComplete();
    }

    @Test
    public void save() {
        Mono<Node> profileMono = this.service.create("TestGuardar", "Ejemplo para guardar en el test");
        StepVerifier
                .create(profileMono)
                .expectNextMatches(saved -> StringUtils.hasText(saved.getId()))
                .verifyComplete();
    }
}