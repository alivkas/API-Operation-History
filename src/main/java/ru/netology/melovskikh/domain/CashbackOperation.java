package ru.netology.melovskikh.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CashbackOperation extends Operation implements ConsolePrintable {
    private int cashbackAmount;

    public CashbackOperation(int cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    @Override
    public void printToConsole() {
        System.out.println("Кэшбек: " + cashbackAmount);
    }
}
