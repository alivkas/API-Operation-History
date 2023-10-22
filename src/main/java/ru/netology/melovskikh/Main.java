package ru.netology.melovskikh;

import ru.netology.melovskikh.domain.Operation;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.exeptions.CustomerOperationOutOfBoundException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final int MAX_OPERATIONS = 1_000;
    public static final int MAX_CLIENTS = 100;
    private static final Operation[] operations = new Operation[MAX_OPERATIONS];
    private static final Client[] clients = new Client[MAX_CLIENTS];
    private static final int[][] statement = new int[MAX_CLIENTS][MAX_OPERATIONS / MAX_CLIENTS];
    private static final int[] clientOperationsCount = new int[MAX_CLIENTS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        inputClient(scanner);
        inputOperation(scanner);

        System.out.println(Arrays.toString(clients));
        System.out.println(Arrays.toString(operations));
        System.out.println(Arrays.deepToString(statement));
        System.out.println(Arrays.toString(clientOperationsCount));
    }

    private static void inputClient(Scanner scanner) {
        int countOfClients = 0;

        while (true) {
            System.out.println("Client name: ");
            String name = scanner.nextLine();

            Client client = new Client(countOfClients, name);

            clients[countOfClients] = client;
            countOfClients++;

            System.out.println("Do you want to enter next operation? Y/N");
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals("N") || countOfClients == MAX_CLIENTS) {
                break;
            }
        }
    }

    private static void inputOperation(Scanner scanner) {
        int operationId = 0;

        while (true) {
            System.out.println("Sum: ");
            int sum = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Currency: ");
            String currency = scanner.nextLine();

            System.out.println("Merchant: ");
            String merchant = scanner.nextLine();

            Operation operation = new Operation(sum, currency, merchant, operationId);
            operations[operationId] = operation;
            operationId++;

            System.out.println("Client: ");
            int clientId = scanner.nextInt();
            scanner.nextLine();

            try {
                saveOperation(operationId, clientId);
            } catch (CustomerOperationOutOfBoundException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Do you want to enter next operation? Y/N");
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals("N") || operationId == MAX_OPERATIONS) {
                break;
            }
        }
    }

    private static void saveOperation(int operationId, int clientId) throws CustomerOperationOutOfBoundException {
        if (clientId > MAX_CLIENTS || clientId < 0
                || operationId > MAX_OPERATIONS || operationId < 0) {
            throw new CustomerOperationOutOfBoundException(clientId, operationId);
        }

        int operationsCountForClient = clientOperationsCount[clientId];
        statement[clientId][operationsCountForClient] = operationId;
        clientOperationsCount[clientId]++;
    }
}