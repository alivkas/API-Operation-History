package ru.netology.melovskikh.services;

import lombok.Data;
import ru.netology.melovskikh.domain.Operation;
import ru.netology.melovskikh.exeptions.CustomerOperationOutOfBoundException;

import java.util.List;

@Data
public class OperationService implements Operatable {
    private static StorageService<Operation> operations = StorageService.operationStorageService;

    @Override
    public void add() {
        int operationId = 0;
        while (true) {
            Operation operation = IOService.inputOperation(operationId);
            operations.setElement(operation);
            operationId++;

            try {
                StatementService.saveStatement(operationId, IOService.inputClientID());
            } catch (CustomerOperationOutOfBoundException e) {
                System.out.println(e.getMessage());
            }

            if (IOService.setAnswer().equals("N") || operationId == StorageService.MAX_OPERATIONS) {
                break;
            }
        }
    }

    public void addOperation(Operation operation) {

    }


//    public void delete(int operationId) {
//        operations.remove(operationId);
//    }
}
