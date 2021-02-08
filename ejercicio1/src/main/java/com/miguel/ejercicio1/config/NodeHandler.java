package com.miguel.ejercicio1.config;

import com.miguel.ejercicio1.model.Node;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class NodeHandler {

    private final NodeService nodeService;

    public NodeHandler(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public Mono<ServerResponse> all(ServerRequest serverRequest){
        return defaultReadResponse(this.nodeService.all());
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Flux<Node> flux = serverRequest
                .bodyToFlux(Node.class)
                .flatMap(toWrite -> this.nodeService.create(toWrite.getNombre(), toWrite.getDescripcion()));
        return defaultWriteResponse(flux);
    }

    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Node> nodes) {
        return Mono
                .from(nodes)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/profiles/" + p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }
    private static Mono<ServerResponse> defaultReadResponse(Publisher<Node> nodes) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(nodes, Node.class);
    }
}
