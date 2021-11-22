package com.krymlov.benchmark.tests;

public class DividingTest implements IOpTest{

    public static void main(String[] args) {
        DividingTest dividingTest = new DividingTest();
        System.out.println(dividingTest.testByte());
        System.out.println(dividingTest.testShort());
        System.out.println(dividingTest.testInt());
        System.out.println(dividingTest.testLong());
        System.out.println(dividingTest.testFloat());
        System.out.println(dividingTest.testDouble());
    }

    public DividingTest() {
    }

    //returns number of operations per 1 sec
    @Override
    public long testByte(){
        System.out.println("byte");
        long operations = 0;
        byte a;
        byte b = 1;
        byte c = 3;
        byte d = 121;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (byte) (c / b);
            b = (byte) (c / a);
            c = (byte) (d / b);
            d = (byte) (a / b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testShort(){
        System.out.println("short");
        long operations = 0;
        short a;
        short b = 1;
        short c = 3;
        short d = 121;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (short) (c / b);
            b = (short) (c / a);
            c = (short) (d / b);
            d = (short) (a / b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testInt(){
        System.out.println("int");
        long operations = 0;
        int a;
        int b = 1;
        int c = 3;
        int d = 121;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c / b);
            b = (c / a);
            c = (d / b);
            d = (a / b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testLong(){
        System.out.println("long");
        long operations = 0;
        long a;
        long b = 1;
        long c = 3;
        long d = 121;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c / b);
            b = (c / a);
            c = (d / b);
            d = (a / b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testChar(){
        System.out.println("char");
        long operations = 0;
        char a;
        char b = 1;
        char c = 3;
        char d = 121;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (char) (c / b);
            b = (char) (c / a);
            c = (char) (d / b);
            d = (char) (a / b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testFloat(){
        System.out.println("float");
        long operations = 0;
        float a;
        float b = 1.2F;
        float c = 3.1F;
        float d = 121.7F;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c / b);
            b = (c / a);
            c = (d / b);
            d = (a / b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testDouble(){
        System.out.println("double");
        long operations = 0;
        double a;
        double b = 1.23D;
        double c = 3.14F;
        double d = 121.01F;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c / b);
            b = (c / a);
            c = (d / b);
            d = (a / b);
            operations += 4;
        }
        return operations*10;
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
