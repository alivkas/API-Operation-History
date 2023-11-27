package ru.netology.melovskikh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody ClientDTO request) {
        customerService.addCustomer(request.getId(), request.getName());
        // Shows the added user
        return new ClientDTO(request.getId(), request.getName());
    }

    @DeleteMapping(path = "/{customerId}")
    public GetClientsResponse deleteClient(@PathVariable int customerId) {
        List<Client> clients = customerService.getCustomers();
        clients.removeIf(client -> client.getId() == customerId);
        List<ClientDTO> clientDtos = clients.stream()
                .map(client -> new ClientDTO(client.getId(), client.getName()))
                .toList();
        return new GetClientsResponse(clientDtos);
    }
}
