package ru.netology.melovskikh.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
    private final int id;
    private final String name;
}
