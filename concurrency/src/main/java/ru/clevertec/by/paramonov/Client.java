package ru.clevertec.by.paramonov;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
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
        List<Callable<Response>> callableListOfResponse = requestList.stream().map(request -> (Callable<Response>) () -> server.calculate(request)).toList();
        try {
            List<Future<Response>> futureList = executorService.invokeAll(callableListOfResponse);
            futureList.forEach(responseFuture -> {
                try {
                    lock.lock();
                    responseList.add(responseFuture.get());
                    lock.unlock();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
