package edu.mipt.accounts;

public interface AccountRepository {
    Account findById(long id);
}