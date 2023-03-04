package ru.clevertec.by.paramonov;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private static final int START_MAX_LIST_SIZE = 20;
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        Client client = new Client();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(client::sendRequestList);
        executorService.shutdown();
    }
}
