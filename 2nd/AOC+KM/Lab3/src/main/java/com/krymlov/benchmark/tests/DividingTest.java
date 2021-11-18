package com.krymlov.benchmark.tests;

public class DividingTest implements IOpTest{

    public DividingTest() {
    }

    //returns number of operations per 1 sec
    @Override
    public long testByte(){
        long operations = 0;
        byte number = Byte.MAX_VALUE;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number <= 1){
                number = Byte.MAX_VALUE;
            }
            number /= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testShort(){
        long operations = 0;
        short number = Short.MAX_VALUE;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number <= 1){
                number = Short.MAX_VALUE;
            }
            number /= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testInt(){
        long operations = 0;
        int number = Integer.MAX_VALUE;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number <= 1){
                number = Integer.MAX_VALUE;
            }
            number /= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testLong(){
        long operations = 0;
        long number = Long.MAX_VALUE;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number <= 1){
                number = Long.MAX_VALUE;
            }
            number /= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testChar(){
        long operations = 0;
        char number = Character.MAX_VALUE;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number <= 1){
                number = Character.MAX_VALUE;
            }
            number /= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testFloat(){
        long operations = 0;
        float number = Float.MAX_VALUE;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number <= 1){
                number = Float.MAX_VALUE;
            }
            number /= (1 + (Math.random() * 3));
            operations++;
        }
        return operations;
    }

    @Override
    public long testDouble(){
        long operations = 0;
        double number = Double.MAX_VALUE;
        long time = System.currentTimeMillis()+1000;
        while (System.currentTimeMillis() < time){
            if (number <= 1){
                number = Double.MAX_VALUE;
            }
            number /= (1 + (Math.random() * 3));
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
