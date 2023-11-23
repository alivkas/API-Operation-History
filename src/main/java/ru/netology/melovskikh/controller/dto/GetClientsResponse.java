package ru.netology.melovskikh.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetClientsResponse {
    private final List<ClientDTO> client;
}
