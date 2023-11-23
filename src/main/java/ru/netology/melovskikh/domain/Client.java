package ru.netology.melovskikh.domain;

import lombok.Data;

@Data
public class Client implements ConsolePrintable {
    private int id;
    private String name;

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void printToConsole() {
        System.out.println(id + name);
    }
}
