package ru.netology.melovskikh.domain;

import lombok.Data;

@Data
public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    public LoanOperation(int loanId) {
        this.loanId = loanId;
    }

    @Override
    public void printToConsole() {
        System.out.println("ID: " + loanId);
    }
}
