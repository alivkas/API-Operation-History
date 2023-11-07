package ru.netology.melovskikh.services;

import lombok.Data;
import ru.netology.melovskikh.domain.Client;

@Data
public class CustomerService implements Operatable {
    private static StorageService<Client> clients = StorageService.customerStorageService;

    @Override
    public void add() {
        int countOfClients = 0;
        while (true) {
            Client client = IOService.inputClient(countOfClients);
            clients.setElement(client);
            countOfClients++;

            if (IOService.setAnswer().equals("N") || countOfClients == StorageService.MAX_CLIENTS) {
                break;
            }
        }
    }

//    public void delete(int countOfClients) {
//        clients.remove(countOfClients);
//    }
}
