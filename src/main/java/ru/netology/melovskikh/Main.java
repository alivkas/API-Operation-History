package ru.netology.melovskikh;

import ru.netology.melovskikh.services.*;

public class Main {
    private static final CustomerService customerService = new CustomerService();
    private static final OperationService operationService = new OperationService();
    private static final AsyncInputOperationService asyncInputOperationService = new AsyncInputOperationService(operationService);

    public static void main(String[] args) {
        customerService.add();
        operationService.add();
        asyncInputOperationService.startAsyncOperationProcessing();

        IOService.print();
    }
}