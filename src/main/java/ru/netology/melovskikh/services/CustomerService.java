package ru.netology.melovskikh.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.melovskikh.domain.Client;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerService {
    //GET /customers
    //GET /customers/{id}
    //POST /customers
    //DELETE /customers/{id}
    private final List<Client> storage = new ArrayList<>();

    public void addCustomer(int id, String name) {
        Client client = new Client(id, name);
        storage.add(client);
    }

    public List<Client> getCustomers() {
        return storage;
    }

    @PostConstruct
    public void init() {
        storage.add(new Client(1, "Spring"));
        storage.add(new Client(2, "Boot"));
    }
}
