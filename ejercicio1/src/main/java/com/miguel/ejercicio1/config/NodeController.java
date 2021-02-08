package com.miguel.ejercicio1.config;

import com.miguel.ejercicio1.model.Node;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/nodes", produces = MediaType.APPLICATION_JSON_VALUE)
@org.springframework.context.annotation.Profile("classic")

public class NodeController {

    private final MediaType mediaType = MediaType.APPLICATION_JSON;
    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }
    @GetMapping
    Publisher<Node> getAll() {
        return this.nodeService.all();
    }

    @PostMapping
    Publisher<ResponseEntity<Node>> create(@RequestBody Node node) {
        return this.nodeService
                .create(node.getNombre(), node.getDescripcion())
                .map(n -> ResponseEntity.created(URI.create("/nodes/" + n.getId()))
                        .contentType(mediaType)
                        .build());
    }
}
