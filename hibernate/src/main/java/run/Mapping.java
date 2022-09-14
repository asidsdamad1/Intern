package run;

import org.hibernate.SessionFactory;
import service.AccountService;
import service.impl.AccountServiceImpl;

public class Mapping {
    private static SessionFactory factory;

    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();

        //add
        accountService.addAcount("Kevin", 200.122);
        //update
        accountService.updateAccount(1L, 12334);
        //list
        accountService.listAccounts();

    }

}
