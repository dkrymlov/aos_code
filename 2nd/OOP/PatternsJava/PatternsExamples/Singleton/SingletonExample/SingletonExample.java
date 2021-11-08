package com.Patterns.Singleton.SingletonExample;



import com.Patterns.Singleton.Processor.Processor;
import com.Patterns.Singleton.Singleton.Singleton;

import java.util.Scanner;

public class SingletonExample
{
    public static void main(String[] args) {
        Singleton logger = Singleton.GetInstance();
        Singleton l1 = Singleton.GetInstance();

        Processor processor = new Processor(1);
        Processor processor1 = new Processor(10);

        logger.Log("Почалася робота...");
        processor.ProcessTo(5);
        logger.Log("Робота завершилася...");

        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
    }
}