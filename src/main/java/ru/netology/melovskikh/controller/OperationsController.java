package ru.netology.melovskikh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology.melovskikh.controller.dto.GetClientsResponse;
import ru.netology.melovskikh.controller.dto.GetOperationResponse;
import ru.netology.melovskikh.controller.dto.OperationDTO;
import ru.netology.melovskikh.domain.Operation;
import ru.netology.melovskikh.services.AsyncInputOperationService;
import ru.netology.melovskikh.services.StatementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/operations/")
@RequiredArgsConstructor
public class OperationsController {
    private final AsyncInputOperationService operationService;
    private final StatementService statementService;

    @GetMapping
    public GetOperationResponse getOperations() {
        Map<Integer, List<Operation>> operations = statementService.getOperation();
        List<OperationDTO> operationDTOS = new ArrayList<>();

        for (List<Operation> operationsList : operations.values()) {
            for (Operation operation: operationsList) {
                OperationDTO operationDTO = new OperationDTO(operation.getId(),
                        operation.getSum(),
                        operation.getCurrency(),
                        operation.getMerchant());
                operationDTOS.add(operationDTO);
            }
        }
        return new GetOperationResponse(operationDTOS);
    }

    @GetMapping(path = "/{id}")
    public OperationDTO getOperation(@PathVariable int id) {
        OperationDTO operationDTO = null;
        for (List<Operation> operations : statementService.getOperation().values()) {
            operationDTO = operations.stream()
                    .filter(operation -> operation.getId() == id)
                    .map(operation -> new OperationDTO(operation.getId(),
                            operation.getSum(),
                            operation.getCurrency(),
                            operation.getMerchant()))
                    .findFirst().orElse(null);
        }
        return operationDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void setOperation(@RequestBody OperationDTO operationDTO) {
        operationService.addOperation(new Operation(operationDTO.getSum(),
                operationDTO.getCurrency(),
                operationDTO.getMerchant(),
                operationDTO.getId()));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOperation(@PathVariable int id) {
        List<Operation> operations = statementService.getOperation().get(id);
        operations.removeIf(operation -> operation.getId() == id);
    }
}
