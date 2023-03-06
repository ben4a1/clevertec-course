package ru.clevertec.by.paramonov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class ServerTest {
    private Server server;

    @BeforeEach
    void setUp(){
        server = new Server();
    }
    @Test
    void checkCalculateShouldReturnReducedBy2Value() {
        Request request = new Request(123456);
        Response responseExpected = new Response(request.getIntValue() >> 1);
        Response responseActual = server.calculate(request);
        assertThat(responseActual).isEqualTo(responseExpected);
    }
}