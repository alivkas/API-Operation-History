package ru.netology.melovskikh.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.domain.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();
    private final CustomerService customerService;

    public void saveOperation(Operation operation) {
        for (Client client : customerService.getCustomers()) {
            List<Operation> operations = storage.get(client.getId());
            if (operations == null) {
                List<Operation> customerOperations = new ArrayList<>();
                customerOperations.add(operation);
                storage.put(client.getId(), customerOperations);
            } else {
                operations.add(operation);
            }
        }
    }

    public Map<Integer, List<Operation>> getOperation() {
        return storage;
    }

    @PostConstruct
    public void init() {
        List<Operation> operations1 = new ArrayList<>();
        operations1.add(new Operation(1000, "RUB", "Coffe", 1));
        operations1.add(new Operation(300, "RUB", "Coffe", 2));
        storage.put(1, operations1);

        List<Operation> operations2 = new ArrayList<>();
        operations2.add(new Operation(1000, "USD", "Coffe", 1));
        operations2.add(new Operation(300, "USD", "Coffe", 2));
        operations2.add(new Operation(300, "USD", "Coffe", 3));
        storage.put(2, operations2);
    }
}
