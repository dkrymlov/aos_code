package com.krymlov.benchmark.tests;

public class PlusTest implements IOpTest {

    public static void main(String[] args) {
        PlusTest plusTest = new PlusTest();
        System.out.println(plusTest.testByte());
        System.out.println(plusTest.testShort());
        System.out.println(plusTest.testInt());
        System.out.println(plusTest.testLong());
        System.out.println(plusTest.testFloat());
        System.out.println(plusTest.testDouble());
    }

    public PlusTest() {
    }

    //returns number of operations per 1 sec
    @Override
    public long testByte(){
        System.out.println("byte");
        long operations = 0;
        byte a;
        byte b = 2;
        byte c = 3;
        byte d = 1;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (byte) (c + b);
            b = (byte) (c + a);
            c = (byte) (d + b);
            d = (byte) (a + b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testShort(){
        System.out.println("short");
        long operations = 0;
        short a;
        short b = 2;
        short c = 3;
        short d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (short) (c + b);
            b = (short) (c + a);
            c = (short) (d + b);
            d = (short) (a + b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testInt(){
        System.out.println("int");
        long operations = 0;
        int a;
        int b = 2;
        int c = 3;
        int d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c + b);
            b = (c + a);
            c = (d + b);
            d = (a + b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testLong(){
        System.out.println("long");
        long operations = 0;
        long a;
        long b = 2;
        long c = 3;
        long d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c + b);
            b = (c + a);
            c = (d + b);
            d = (a + b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testChar(){
        System.out.println("char");
        long operations = 0;
        char a;
        char b = 2;
        char c = 3;
        char d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (char) (c + b);
            b = (char) (c + a);
            c = (char) (d + b);
            d = (char) (a + b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testFloat(){
        System.out.println("float");
        long operations = 0;
        float a;
        float b = 2.5F;
        float c = 3.2F;
        float d = 5.1F;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c + b);
            b = (c + a);
            c = (d + b);
            d = (a + b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testDouble(){
        System.out.println("double");
        long operations = 0;
        double a;
        double b = 2.52;
        double c = 3.23;
        double d = 5.15;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c + b);
            b = (c + a);
            c = (d + b);
            d = (a + b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long getOperations(String type) {
        long result = 0;
        if (type.equals("byte")){
            result = testByte();
        }else if(type.equals("short")){
            result = testShort();
        }else if(type.equals("int")){
            result = testInt();
        }else if(type.equals("long")){
            result = testLong();
        }else if(type.equals("char")){
            result = testChar();
        }else if(type.equals("float")){
            result = testFloat();
        }else if(type.equals("double")){
            result = testDouble();
        }
        return result;
    }
}
