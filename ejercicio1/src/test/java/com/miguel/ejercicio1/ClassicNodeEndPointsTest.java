package com.miguel.ejercicio1;

import com.miguel.ejercicio1.config.NodeController;
import com.miguel.ejercicio1.config.NodeService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;


@Log4j2
@Import({NodeController.class, NodeService.class})
@ActiveProfiles("classic")
public class ClassicNodeEndPointsTest extends AbstractNodeEndpoints {

    @BeforeAll
    static void before() {
        log.info("running non-classic tests");
    }

    ClassicNodeEndPointsTest(@Autowired WebTestClient client) {
        super(client);
    }
}