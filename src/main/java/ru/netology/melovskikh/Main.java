package ru.netology.melovskikh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.melovskikh.configuration.ApplicationConfiguration;
import ru.netology.melovskikh.services.*;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        AsyncInputOperationService asyncInputOperationService = context.getBean(AsyncInputOperationService.class);
        StatementService statementService = context.getBean(StatementService.class);

        asyncInputOperationService.startAsyncOperationProcessing();
        System.out.println(statementService.getOperation());
//        CustomerService customerService = new CustomerService();
//        StatementService statementService = new StatementService();
//
//        AsyncInputOperationService asyncInputOperationService = new AsyncInputOperationService(statementService);

//        Operation o1 = new Operation(100, "RUB", "Shoko", 0);
//        asyncInputOperationService.addOperation(o1);
//        Operation o2 = new Operation(200, "RUB", "Shoko", 0);
//        asyncInputOperationService.addOperation(o2);
//
//        try {
//            Thread.sleep(5_000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        Operation o3 = new Operation(300, "RUB", "Shoko", 0);
//        Operation o4 = new Operation(400, "RUB", "Shoko", 0);
//        asyncInputOperationService.addOperation(o3);
//        asyncInputOperationService.addOperation(o4);
//
//        try {
//            Thread.sleep(1_000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}