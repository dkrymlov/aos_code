package com.krymlov.benchmark.tests;

public class MinusTest implements IOpTest{

    public static void main(String[] args) {
        MinusTest minusTest = new MinusTest();
        System.out.println(minusTest.testByte());
        System.out.println(minusTest.testShort());
        System.out.println(minusTest.testInt());
        System.out.println(minusTest.testLong());
        System.out.println(minusTest.testFloat());
        System.out.println(minusTest.testDouble());
    }

    public MinusTest() {
    }

    //returns number of operations per 1 sec
    @Override
    public long testByte(){
        System.out.println("byte");
        long operations = 0;
        byte a;
        byte b = 127;
        byte c = 121;
        byte d = 110;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (byte) (c - b);
            b = (byte) (c - a);
            c = (byte) (d - b);
            d = (byte) (a - b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testShort(){
        System.out.println("short");
        long operations = 0;
        short a;
        short b = 23;
        short c = 25343;
        short d = 15678;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (short) (c - b);
            b = (short) (c - a);
            c = (short) (d - b);
            d = (short) (a - b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testInt(){
        System.out.println("int");
        long operations = 0;
        int a;
        int b = 23;
        int c = 1356;
        int d = 1561;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c - b);
            b = (c - a);
            c = (d - b);
            d = (a - b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testLong(){
        System.out.println("long");
        long operations = 0;
        long a;
        long b = 232L;
        long c = 13564L;
        long d = 15615L;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c - b);
            b = (c - a);
            c = (d - b);
            d = (a - b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testChar(){
        System.out.println("char");
        long operations = 0;
        char a;
        char b = 12;
        char c = 345;
        char d = 356;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (char) (c - b);
            b = (char) (c - a);
            c = (char) (d - b);
            d = (char) (a - b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testFloat(){
        System.out.println("float");
        long operations = 0;
        float a;
        float b = 12.5F;
        float c = 3455.2F;
        float d = 356.6F;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c - b);
            b = (c - a);
            c = (d - b);
            d = (a - b);
            operations += 4;
        }
        return operations*10;
    }

    @Override
    public long testDouble(){
        System.out.println("double");
        long operations = 0;
        double a;
        double b = 12.54D;
        double c = 3455.41D;
        double d = 356.23D;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c - b);
            b = (c - a);
            c = (d - b);
            d = (a - b);
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
