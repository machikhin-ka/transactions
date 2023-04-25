package edu.mipt.accounts.applock;

public class Lock {
    private static final Object tieLock = new Object();

    public void execute(Object first, Object second, Command command) {
        int firstId = System.identityHashCode(first);
        int secondId = System.identityHashCode(second);

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
