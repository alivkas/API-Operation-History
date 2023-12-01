package ru.netology.melovskikh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.domain.Operation;
import ru.netology.melovskikh.services.StatementService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    StatementService statementService;

    @Test
    public void getOperationTest() {
        Map<Integer, List<Operation>> customerOperations = statementService.getOperation();
        List<Operation> operations1 = customerOperations.get(1);
        List<Operation> operations2 = customerOperations.get(2);
        assertNotNull(customerOperations);
        assertEquals(2, customerOperations.size());
        assertEquals(2, operations1.size());
        assertEquals(3, operations2.size());
    }

    @Test
    public void saveOperationTest() {
        Operation operation1 = new Operation(1000, "RUB", "Merch", 2);
        Operation operation2 = new Operation(900, "RUB", "Merch", 3);
        Client client = new Client(3, "Vasya");

        statementService.saveOperation(operation1, client);
        statementService.saveOperation(operation2, client);

        List<Operation> customerOperations = statementService.getOperation().get(3);
        List<Operation> expectedOperations = Arrays.asList(
                new Operation(1000, "RUB", "Merch", 2),
                new Operation(900, "RUB", "Merch", 3)
        );
        assertEquals(expectedOperations, customerOperations);
    }
}
