package ru.netology.melovskikh.services;

import lombok.Data;
import lombok.Getter;
import ru.netology.melovskikh.domain.Client;
import ru.netology.melovskikh.domain.Operation;

import java.util.ArrayList;
import java.util.List;

@Data
public class StorageService<T> {
    public static final int MAX_OPERATIONS = 1_000;
    public static final int MAX_CLIENTS = 100;
    private final List<T> storage = new ArrayList<>();
    static StorageService<Client> customerStorageService = new StorageService<>();
    static StorageService<Operation> operationStorageService = new StorageService<>();
    @Getter
    private static final int[][] statement = new int[MAX_CLIENTS][MAX_OPERATIONS / MAX_CLIENTS];
    @Getter
    private static final int[] clientOperationsCount = new int[MAX_CLIENTS];

    public T getElement(int position) {
        return storage.get(position);
    }

    public void setElement(T element) {
        storage.add(element);
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}
