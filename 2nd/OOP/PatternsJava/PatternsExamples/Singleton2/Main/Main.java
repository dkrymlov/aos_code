package com.Patterns.Singleton2.Main;

import com.Patterns.Singleton2.Processor.Processor;
import com.Patterns.Singleton2.ThreadSafeSingleton.ThreadSafeSingleton;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ThreadSafeSingleton logger = ThreadSafeSingleton.GetInstance();
        Processor processor = new Processor(1);
        logger.Log("Почалася робота...");
        processor.ProcessTo(5);
        logger.Log("Робота завершилася...");

        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
    }
}
