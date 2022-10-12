package di;


import di.repository.MessageService;
import di.repository.impl.EmailService;

import javax.management.InstanceNotFoundException;

public class Main {
    public static void main(String[] args) throws InstanceNotFoundException {
//        MessageService messageService = new EmailService();
        MessageService messageService = (MessageService) Injector.getInstance("messageService");

        UserController userController = new UserController(messageService);
        userController.send();
    }

}
