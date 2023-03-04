package ru.clevertec.by.paramonov;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ServerTest {
    private Server server;

    @BeforeEach
    void setUp(){
        server = new Server();
    }
    @Test
    void checkCalculateShouldReturnReducedBy2Value() {
        Request request = new Request(123456);
        Response responseExpected = new Response(request.getIntValue() >> 2);
        Response responseActual = server.calculate(request);
        Assertions.assertThat(responseActual).isEqualTo(responseExpected);
    }
}