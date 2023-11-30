package ru.netology.melovskikh.controller.dto;

import lombok.Data;
import ru.netology.melovskikh.domain.Operation;

import java.util.List;
import java.util.Map;

@Data
public class GetOperationResponse {
    private final Map<Integer, List<OperationDTO>> operation;
}
