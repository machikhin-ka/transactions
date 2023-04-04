package edu.mipt.accounts.applock;

import edu.mipt.accounts.Account;
import edu.mipt.accounts.AccountRepository;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<Long, Account> accounts;

    public InMemoryAccountRepository(List<Account> accounts) {
        this.accounts = accounts.stream().collect(toMap(Account::getId, identity()));
    }

    @Override
    public Account findById(long id) {
        return accounts.get(id);
    }
}
