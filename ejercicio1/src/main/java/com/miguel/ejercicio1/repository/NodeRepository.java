package com.miguel.ejercicio1.repository;

import com.miguel.ejercicio1.model.Node;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NodeRepository extends ReactiveMongoRepository<Node, String> {

}
