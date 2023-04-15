package edu.mipt.accounts.applock;

import edu.mipt.accounts.Account;

public class Command {
    private final Account fromAcc;
    private final Account toAcc;
    private final long value;

    public Command(Account fromAcc, Account toAcc, long value) {
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.value = value;
    }

    public void execute() {
        fromAcc.withdraw(value);
        toAcc.deposit(value);
    }
}
