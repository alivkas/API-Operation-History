package ru.netology.melovskikh.services;

import lombok.Data;
import ru.netology.melovskikh.exeptions.CustomerOperationOutOfBoundException;

@Data
public class StatementService {
    private static final int[][] statement = StorageService.getStatement();
    private static final int[] clientOperationsCount = StorageService.getClientOperationsCount();

    public void saveStatement(int operationId) throws CustomerOperationOutOfBoundException {
        int clientId = IOService.getClientId();
        if (clientId > StorageService.MAX_CLIENTS || operationId > StorageService.MAX_OPERATIONS) {
            throw new CustomerOperationOutOfBoundException(clientId, operationId);
        }

        int operationsCountForClient = clientOperationsCount[clientId];
        statement[clientId][operationsCountForClient] = operationId;
        clientOperationsCount[clientId]++;
    }
}
