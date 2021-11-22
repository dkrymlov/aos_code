package com.task2;

public class ConsoleLog implements ILog {

    @Override
    public void print(Email email) {
        System.out.println("Email from '" + email.from + "' to '" + email.to + "' was send");
    }
}
