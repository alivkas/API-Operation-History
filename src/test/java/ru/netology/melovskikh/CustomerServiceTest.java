package ru.netology.melovskikh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.services.CustomerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    CustomerService customerService;

    @Test
    public void getCustomersTest() {
        List<Client> clients = customerService.getCustomers();
        Client client1 = clients.get(0);
        Client client2 = clients.get(1);
        assertEquals(1, client1.getId());
        assertEquals("Spring", client1.getName());
        assertEquals(2, client2.getId());
        assertEquals("Boot", client2.getName());
        assertEquals(2, clients.size());
    }

    @Test
    public void addCustomersTest() {
        customerService.addCustomer(3, "Fedya");
        assertEquals(new Client(3, "Fedya"), customerService.getCustomers().get(2));
    }
}
