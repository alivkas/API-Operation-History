package ru.netology.melovskikh.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationDTO {
    private final int id;
    private final int sum;
    private final String currency;
    private final String merchant;
}
