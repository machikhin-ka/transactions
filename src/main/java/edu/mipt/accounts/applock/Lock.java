package edu.mipt.accounts.applock;

import edu.mipt.accounts.Account;

public class Lock {
    private static final Object tieLock = new Object();

    public void execute(Account first, Account second, Command command) {
        long firstId = first.getId();
        long secondId = second.getId();

        if (firstId < secondId) {
            lock(first, second, command);
            return;
        }

        if (secondId < firstId) {
            lock(second, first, command);
            return;
        }

        synchronized (tieLock) {
            lock(first, second, command);
        }
    }

    private void lock(Object externalLock, Object internalLock, Command command) {
        synchronized (externalLock) {
            synchronized (internalLock) {
                command.execute();
            }
        }
    }
}
