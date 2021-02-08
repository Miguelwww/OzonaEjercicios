package com.miguel.ejercicio1.config;

import com.miguel.ejercicio1.model.Node;
import com.miguel.ejercicio1.repository.NodeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class NodeService {

    private final NodeRepository nodeRepository;

    NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Flux<Node> all() {
        return this.nodeRepository.findAll();
    }

    public Mono<Node> create(String name, String description) {
        return this.nodeRepository
                .save(new Node(UUID.randomUUID().toString(), name, description));
    }
}