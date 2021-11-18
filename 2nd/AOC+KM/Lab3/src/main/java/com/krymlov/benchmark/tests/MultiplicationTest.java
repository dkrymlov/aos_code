package com.krymlov.benchmark.tests;

public class MultiplicationTest implements IOpTest{

    public MultiplicationTest() {
    }

    //returns number of operations per 1 sec
    @Override
    public long testByte(){
        long operations = 0;
        byte number = 1;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number >= Byte.MAX_VALUE/10){
                number = 1;
            }
            number *= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testShort(){
        long operations = 0;
        short number = 1;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number >= Short.MAX_VALUE/10){
                number = 1;
            }
            number *= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testInt(){
        long operations = 0;
        int number = 1;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number >= Integer.MAX_VALUE/10){
                number = 1;
            }
            number *= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testLong(){
        long operations = 0;
        long number = 1;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number >= Long.MAX_VALUE/10){
                number = 1;
            }
            number *= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testChar(){
        long operations = 0;
        char number = 1;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number >= Character.MAX_VALUE/10){
                number = 1;
            }
            number *= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testFloat(){
        long operations = 0;
        float number = 1;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number >= Float.MAX_VALUE/10){
                number = 1;
            }
            number *= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testDouble(){
        long operations = 0;
        double number = 1;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number >= Double.MAX_VALUE/10){
                number = 1;
            }
            number *= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long getOperations(String type) {
        long result = 0;
        if (type.equals("byte")){
            result = testByte();
            System.out.println("byte");
        }else if(type.equals("short")){
            result = testShort();
            System.out.println("short");
        }else if(type.equals("int")){
            result = testInt();
            System.out.println("int");
        }else if(type.equals("long")){
            result = testLong();
            System.out.println("long");
        }else if(type.equals("char")){
            result = testChar();
            System.out.println("char");
        }else if(type.equals("float")){
            result = testFloat();
            System.out.println("float");
        }else if(type.equals("double")){
            result = testDouble();
            System.out.println("double");
        }
        return result;
    }
}
