package ru.netology.melovskikh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.melovskikh.controller.dto.ClientDTO;
import ru.netology.melovskikh.controller.dto.GetClientsResponse;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/customers/")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public GetClientsResponse getClients() {
        List<Client> clients = customerService.getCustomers();

//        List<ClientDTO> clientDtos = clients.stream()
//                .map(client -> new ClientDTO(client.getId(), client.getName()))
//                .toList();

        List<ClientDTO> clientDTOS = new ArrayList<>();
        for (Client client : clients) {
            ClientDTO clientDTO = new ClientDTO(client.getId(), client.getName());
            clientDTOS.add(clientDTO);
        }

        return new GetClientsResponse(clientDTOS);
    }

    @GetMapping(path = "/{customerId}")
    public ClientDTO getCustomer(@PathVariable int customerId) {
        return customerService.getCustomers().stream()
                .filter(client -> client.getId() == customerId)
                .map(client -> new ClientDTO(client.getId(), client.getName()))
                .findFirst().orElse(null);
    }
}
