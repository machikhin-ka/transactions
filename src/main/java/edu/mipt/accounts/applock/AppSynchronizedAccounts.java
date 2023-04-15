package edu.mipt.accounts.applock;

import edu.mipt.accounts.AccountRepository;
import edu.mipt.accounts.Accounts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppSynchronizedAccounts implements Accounts {
    private final AccountRepository accountRepository;
    private final Lock lock = new Lock();

    @Override
    public void transfer(long fromAccountId, long toAccountId, long amount) {
        var fromAccount = accountRepository.findById(fromAccountId);
        var toAccount = accountRepository.findById(toAccountId);

        lock.execute(fromAccount, toAccount, new Command(fromAccount, toAccount, amount));
    }
}