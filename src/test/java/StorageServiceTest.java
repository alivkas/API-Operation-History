import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.domain.Operation;

public class StorageServiceTest {
    @Test
    public void getOperationTest() {
        StorageService<Operation> operationStorageService = new StorageService<>();

        int sum = 100;
        int id = 0;
        String currency = "RUB";
        String merchant = "Shoko";
        Operation operation = new Operation(sum, currency, merchant, id);

        operationStorageService.setElement(operation);
        Operation storageOperation = operationStorageService.getElement(0);
        Assertions.assertEquals(sum, storageOperation.getSum());
        Assertions.assertEquals(currency, storageOperation.getCurrency());
        Assertions.assertEquals(merchant, storageOperation.getMerchant());
    }

    @Test
    public void getClientTest() {
        StorageService<Client> clientStorageService = new StorageService<>();

        int id = 0;
        String name = "Petya";
        Client client = new Client(id, name);

        clientStorageService.setElement(client);
        Client storageOperation = clientStorageService.getElement(0);
        Assertions.assertEquals(id, storageOperation.getId());
        Assertions.assertEquals(name, storageOperation.getName());
    }
}
