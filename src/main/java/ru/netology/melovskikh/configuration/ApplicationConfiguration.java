package ru.netology.melovskikh.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.melovskikh.services.AsyncInputOperationService;
import ru.netology.melovskikh.services.CustomerService;
import ru.netology.melovskikh.services.StatementService;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public CustomerService customerService() {
        System.out.println("Customer service created");
        return new CustomerService();
    }

    @Bean
    public StatementService statementService() {
        System.out.println("Statement service created");
        return new StatementService();
    }

    @Bean
    public AsyncInputOperationService asyncInputOperationService(StatementService statementService) {
        System.out.println("AsyncInputOperationService service created");
        return new AsyncInputOperationService(statementService);
    }
}
