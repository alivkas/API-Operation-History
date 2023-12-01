package ru.netology.melovskikh.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.melovskikh.configuration.OperationsProcessingProperties;
import ru.netology.melovskikh.domain.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
public class AsyncInputOperationService {
    private final Queue<Operation> operations = new LinkedList<>();

    private final StatementService statementService;
    private final OperationsProcessingProperties properties;

    public boolean addOperation(Operation operation){
        System.out.println("Operation added for processing" + operation);
        return operations.offer(operation);
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

    private void processQueue() {
        while (true) {
            Operation operation = operations.poll();
            if (operation == null) {
                try {
                    System.out.println("No operation");
                    Thread.sleep(properties.getTimeout());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation:" + operation);
            }
        }
    }

    private void processOperation(Operation operation) {
        statementService.saveOperation(operation);
    }

    @PostConstruct
    public void init() {
        this.startAsyncOperationProcessing();
    }
}
