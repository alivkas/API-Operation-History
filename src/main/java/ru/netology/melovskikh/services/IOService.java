package ru.netology.melovskikh.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.domain.Operation;

import java.util.Arrays;
import java.util.Scanner;

@Data
@NoArgsConstructor
public class IOService {
    private static Scanner scanner = new Scanner(System.in);

    public static Client inputClient(int countOfClients) {
        System.out.println("Client name: ");
        String name = scanner.nextLine();

        return new Client(countOfClients, name);
    }

    public static Operation inputOperation(int operationId) {
        System.out.println("Sum: ");
        int sum = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Currency: ");
        String currency = scanner.nextLine();

        System.out.println("Merchant: ");
        String merchant = scanner.nextLine();

        return new Operation(sum, currency, merchant, operationId);
    }

    public static int inputClientID() {
        System.out.println("Client: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        return clientId;
    }

    public static void print() {
        System.out.println(StorageService.customerStorageService.toString());
        System.out.println(StorageService.operationStorageService.toString());
        System.out.println(Arrays.deepToString(StorageService.getStatement()));
        System.out.println(Arrays.toString(StorageService.getClientOperationsCount()));
    }

    public static String setAnswer() {
        System.out.println("Do you want to enter next operation? Y/N");
        return scanner.nextLine().toUpperCase();
    }
}
