package ru.netology.melovskikh.services;

import lombok.Data;
import ru.netology.melovskikh.domain.Client;

@Data
public class CustomerService implements Operatable {
    private static StorageService<Client> clients = StorageService.customerStorageService;

    @Override
    public void add(int countOfClients) {
        Client client = new Client(countOfClients, IOService.getName());
        clients.setElement(client);
    }

//    public void delete(int countOfClients) {
//        clients.remove(countOfClients);
//    }
}
