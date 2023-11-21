package ru.netology.melovskikh.services;

import lombok.Data;
import ru.netology.melovskikh.domain.Client;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerService {
    private final List<Client> storage = new ArrayList<>();

    public void addCustomer(int id, String name) {
        Client client = new Client(id, name);
        storage.add(client);
    }
}
