package service;

import model.Account;

public interface AccountService {
    Account addAcount(String name, double amount);
    void listAccounts();
    Account updateAccount(Long id, double amount);
}
