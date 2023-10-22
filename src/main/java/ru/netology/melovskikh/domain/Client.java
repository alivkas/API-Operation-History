package ru.netology.melovskikh.domain;

import lombok.Data;

@Data
public class Client {
    private int id;
    private String name;

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id: " + id +
                ", name: " + name +
                "}";
    }
}
