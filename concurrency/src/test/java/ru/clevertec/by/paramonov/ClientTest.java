package ru.clevertec.by.paramonov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

class ClientTest {
    @Test
    void checkSendRequestListShouldReturnEquals() {
        List<Request> emptyRequestList = new ArrayList<>();
        List<Integer> integerList = new Random(50)
                .ints(15).boxed()
                .toList();
        List<Request> requestList = integerList.stream()
                .map(Request::new)
                .toList();
        List<Response> responseListExpected = integerList.stream()
                .map(integer -> new Response(integer / 2))
                .toList();
        Client client = new Client();
        client.setRequestList(emptyRequestList);
        client.setRequestList(requestList);
        client.sendRequestList();
        assertThat(responseListExpected).isEqualTo(client.getResponseList());
    }

}