package ru.netology.melovskikh.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.melovskikh.domain.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public void saveOperation(Operation operation) {
        List<Operation> operations = storage.get(operation.getId());
        if (operations == null) {
            List<Operation> customerOperations = new ArrayList<>();
            customerOperations.add(operation);
            storage.put(operation.getId(), customerOperations);
        } else {
            operations.add(operation);
        }
    }

    public String getOperation() {
        return storage.toString();
    }
}
