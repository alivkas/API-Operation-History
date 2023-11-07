package ru.netology.melovskikh.services;

import lombok.Data;
import ru.netology.melovskikh.exeptions.CustomerOperationOutOfBoundException;

@Data
public class StatementService {
    private static final int[][] statement = StorageService.getStatement();
    private static final int[] clientOperationsCount = StorageService.getClientOperationsCount();

    public static void saveStatement(int operationId, int clientId) throws CustomerOperationOutOfBoundException {
        if (clientId > StorageService.MAX_CLIENTS || operationId > StorageService.MAX_OPERATIONS) {
            throw new CustomerOperationOutOfBoundException(clientId, operationId);
        }

        int operationsCountForClient = clientOperationsCount[clientId];
        statement[clientId][operationsCountForClient] = operationId;
        clientOperationsCount[clientId]++;
    }
}
