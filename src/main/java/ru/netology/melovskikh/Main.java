package ru.netology.melovskikh;

import ru.netology.melovskikh.domain.Operation;
import ru.netology.melovskikh.services.*;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        StatementService statementService = new StatementService();

        AsyncInputOperationService asyncInputOperationService = new AsyncInputOperationService(statementService);
        asyncInputOperationService.startAsyncOperationProcessing();

        Operation o1 = new Operation(100, "RUB", "Shoko", 0);
        asyncInputOperationService.addOperation(o1);
        Operation o2 = new Operation(200, "RUB", "Shoko", 0);
        asyncInputOperationService.addOperation(o2);

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Operation o3 = new Operation(300, "RUB", "Shoko", 0);
        Operation o4 = new Operation(400, "RUB", "Shoko", 0);
        asyncInputOperationService.addOperation(o3);
        asyncInputOperationService.addOperation(o4);

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(statementService.getOperation());
    }
}