package ru.netology.melovskikh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology.melovskikh.controller.dto.ClientDTO;
import ru.netology.melovskikh.controller.dto.GetClientsResponse;
import ru.netology.melovskikh.controller.dto.GetOperationResponse;
import ru.netology.melovskikh.controller.dto.OperationDTO;
import ru.netology.melovskikh.domain.Operation;
import ru.netology.melovskikh.services.AsyncInputOperationService;
import ru.netology.melovskikh.services.StatementService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/operations/")
@RequiredArgsConstructor
public class OperationsController {
    private final AsyncInputOperationService operationService;
    private final StatementService statementService;

    // Поиск операций клиента по его id
    @GetMapping(path = "/{clientId}")
    public List<Operation> getOperation(@PathVariable int clientId) {
        Map<Integer, List<Operation>> clientOperations = statementService.getOperation();
        return clientOperations.get(clientId);
    }

    // Создание операции под id клиента
    @PostMapping(path = "/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDTO setOperation(@RequestBody OperationDTO operationDTO, @PathVariable int clientId) {
        Map<Integer, List<Operation>> clientOperations = statementService.getOperation();

        if (clientOperations.containsKey(clientId)) {
            List<Operation> operations = clientOperations.get(clientId);
            operations.add(new Operation(operationDTO.getSum(),
                    operationDTO.getCurrency(),
                    operationDTO.getMerchant(),
                    operationDTO.getId()));
        } else {
            List<Operation> operations = new ArrayList<>();
            operations.add(new Operation(operationDTO.getSum(),
                    operationDTO.getCurrency(),
                    operationDTO.getMerchant(),
                    operationDTO.getId()));
            clientOperations.put(clientId, operations);
        }

        operationService.addOperation(new Operation(operationDTO.getSum(),
                operationDTO.getCurrency(),
                operationDTO.getMerchant(),
                operationDTO.getId()));

        return new OperationDTO(operationDTO.getId(),
                operationDTO.getSum(),
                operationDTO.getCurrency(),
                operationDTO.getMerchant());
    }

    // Удаление определенной операции клиента
    @DeleteMapping(path = "/{clientId}/{operationId}")
    public void deleteOperation(@PathVariable int clientId, @PathVariable int operationId) {
        List<Operation> operations = statementService.getOperation().get(clientId);
        operations.removeIf(operation -> operation.getId() == operationId);
    }
}
