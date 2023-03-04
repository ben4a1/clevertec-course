package ru.clevertec.by.paramonov;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class Client {
    private static final int REQUEST_LIST_SIZE = 15;
    private static final int THREAD_COUNT = REQUEST_LIST_SIZE / 5;
    private final Lock lock;
    private Server server;
    private List<Request> requestList;
    private List<Response> responseList;
    private ExecutorService executorService;

    public Client() {
        server = new Server();
        responseList = new ArrayList<>();
        lock = new ReentrantLock();
        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        requestList = new Random().ints(REQUEST_LIST_SIZE).boxed().map(Request::new).toList();
    }

    public void sendRequestList() {
        requestList.forEach(request -> executorService.submit(() -> {
            int requestIntValue = request.getIntValue();
            System.out.println("Client sending request with Value: " + requestIntValue + ". Current thread " + Thread.currentThread().getName());
            Response calculate = server.calculate(request);
            if (calculate != null) {
                int responseIntValue = calculate.getIntValue();
                System.out.println("Client getting response with Value: " + responseIntValue + ". Current thread " + Thread.currentThread().getName());
                lock.lock();
                responseList.add(calculate);
                lock.unlock();
            }
        }));
        executorService.shutdown();
    }

}
