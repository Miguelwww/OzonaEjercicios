package com.miguel.ejercicio1;

import com.miguel.ejercicio1.model.Node;
import com.miguel.ejercicio1.repository.NodeRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Log4j2
@WebFluxTest
public abstract class AbstractNodeEndpoints {

    private final WebTestClient client;

    @MockBean
    private NodeRepository repository;

    public AbstractNodeEndpoints(WebTestClient client) {
        this.client = client;
    }

    @Test
    public void getAll() {

        log.info("running  " + this.getClass().getName());


        Mockito
                .when(this.repository.findAll())
                .thenReturn(Flux.just(new Node("1", "Test", "A"), new Node("2", "Test2", "B")));


        this.client
                .get()
                .uri("/nodes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo("1")
                .jsonPath("$.[0].nombre").isEqualTo("Test")
                .jsonPath("$.[0].descripcion").isEqualTo("A")
                .jsonPath("$.[1].id").isEqualTo("2")
                .jsonPath("$.[1].nombre").isEqualTo("Test2")
                .jsonPath("$.[1].descripcion").isEqualTo("B");
    }

    @Test
    public void save() {
        Node data = new Node(UUID.randomUUID().toString(), "Prueba23233", "Prueba de 2341234");
        Mockito
                .when(this.repository.save(Mockito.any(Node.class)))
                .thenReturn(Mono.just(data));
        MediaType json = MediaType.APPLICATION_JSON;
        this
                .client
                .post()
                .uri("/nodes")
                .contentType(json)
                .body(Mono.just(data), Node.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(json);
    }

}