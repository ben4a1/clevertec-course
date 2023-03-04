package ru.clevertec.by.paramonov;

import java.util.Random;

public class Runner {
    private static final int START_MAX_LIST_SIZE = 20;
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        Client client = new Client();
        client.sendRequestList();
    }
}
