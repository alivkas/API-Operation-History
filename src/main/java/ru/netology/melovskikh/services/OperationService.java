package ru.netology.melovskikh.services;

import lombok.Data;
import ru.netology.melovskikh.domain.Operation;

import java.util.List;

@Data
public class OperationService implements Operatable {
    private static StorageService<Operation> operations = StorageService.operationStorageService;

    @Override
    public void add(int operationId) {
        Operation operation = new Operation(IOService.getSum(), IOService.getCurrency(), IOService.getMerchant(), operationId);
        operations.setElement(operation);
    }

//    public void delete(int operationId) {
//        operations.remove(operationId);
//    }
}
