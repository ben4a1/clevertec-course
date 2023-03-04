package ru.clevertec.by.paramonov;

import java.util.Random;

public class Server {
    private static final int TIME_FOR_WAIT_BOUND = 2000;
    private static final Random RANDOM = new Random();

    public Response calculate(Request request) {
        int millisecForWait = generateTimeForWait();
        int requestIntValue = request.getIntValue();
        try {
            System.out.println("Server waiting for " + millisecForWait + " milliseconds" + ". Current thread " + Thread.currentThread().getName());
            Thread.sleep(millisecForWait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Response(requestIntValue - (requestIntValue / 2));
    }

    private int generateTimeForWait() {
        return RANDOM.nextInt(TIME_FOR_WAIT_BOUND);
    }
}
