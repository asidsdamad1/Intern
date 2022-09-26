package di.repository.impl;

import di.repository.MessageService;

public  class EmailService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("Email message: " + message);
    }
}
