package ru.netology.melovskikh.services;

import lombok.Data;
import ru.netology.melovskikh.domain.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class AsyncInputOperationService {
    private Queue<Operation> queue = new LinkedList<>();
    private final OperationService operationService;

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation:" + operation);
                operationService.addOperation(operation);
            }
        }
    }

    public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }
}
