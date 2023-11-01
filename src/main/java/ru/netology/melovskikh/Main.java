package ru.netology.melovskikh;

import ru.netology.melovskikh.exeptions.CustomerOperationOutOfBoundException;
import ru.netology.melovskikh.services.*;

public class Main {
    private static final IOService ioService = new IOService();
    private static final CustomerService customerService = new CustomerService();
    private static final OperationService operationService = new OperationService();
    private static final StatementService statementService = new StatementService();

    public static void main(String[] args) {
        int countOfClients = 0;
        int operationId = 0;

        while (true) {
            ioService.input();

            customerService.add(countOfClients);
            countOfClients++;

            operationService.add(operationId);
            operationId++;

            try {
                statementService.saveStatement(operationId);
            } catch (CustomerOperationOutOfBoundException e) {
                System.out.println(e.getMessage());
            }

            ioService.setAnswer();
            if (ioService.getAnswer().equals("N") || operationId == StorageService.MAX_OPERATIONS
                    || countOfClients == StorageService.MAX_CLIENTS) {
                break;
            }
        }

        ioService.print();
    }
}