package ru.netology.melovskikh.services;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.domain.Operation;

import java.util.Arrays;
import java.util.Scanner;

@Data
@NoArgsConstructor
public class IOService {
    private static StorageService<Client> clients = StorageService.customerStorageService;
    private static StorageService<Operation> operations = StorageService.operationStorageService;
    private static int[][] statement = StorageService.getStatement();
    private static int[] clientOperationsCount = StorageService.getClientOperationsCount();
    private Scanner scanner = new Scanner(System.in);
    @Getter
    private static String name;
    @Getter
    private static int sum;
    @Getter
    private static String currency;
    @Getter
    private static String merchant;
    @Getter
    private static int clientId;
    private String answer;

    public void input() {
        System.out.println("Client name: ");
        name = scanner.nextLine();

        System.out.println("Sum: ");
        sum =scanner.nextInt();
        scanner.nextLine();

        System.out.println("Currency: ");
        currency = scanner.nextLine();

        System.out.println("Merchant: ");
        merchant = scanner.nextLine();

        System.out.println("Client: ");
        clientId = scanner.nextInt();
        scanner.nextLine();
    }

    public void print() {
        System.out.println(clients.toString());
        System.out.println(operations.toString());
        System.out.println(Arrays.deepToString(statement));
        System.out.println(Arrays.toString(clientOperationsCount));
    }

    public void setAnswer() {
        System.out.println("Do you want to enter next operation? Y/N");
        answer = scanner.nextLine().toUpperCase();
    }
}
