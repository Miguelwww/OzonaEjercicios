package com.miguel.ejercicio1;

import com.miguel.ejercicio1.model.Node;
import com.miguel.ejercicio1.repository.NodeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.UUID;

@WebFluxTest
class Ejercicio1ApplicationTests {

	@Test
	void contextLoads() {
	}

}
