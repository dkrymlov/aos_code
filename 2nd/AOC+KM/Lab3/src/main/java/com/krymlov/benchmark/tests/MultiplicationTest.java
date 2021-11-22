package com.krymlov.benchmark.tests;

public class MultiplicationTest implements IOpTest{

    public static void main(String[] args) {
        MultiplicationTest multiplicationTest = new MultiplicationTest();
        PlusTest plusTest = new PlusTest();
        System.out.println(multiplicationTest.testByte());
        System.out.println(multiplicationTest.testShort());
        System.out.println(multiplicationTest.testInt());
        System.out.println(multiplicationTest.testLong());
        System.out.println(multiplicationTest.testFloat());
        System.out.println(multiplicationTest.testDouble());
    }

    public MultiplicationTest() {
    }

    //returns number of operations per 1 sec
    @Override
    public long testByte(){
        System.out.println("byte");
        long operations = 0;
        byte a;
        byte b = 3;
        byte c = 1;
        byte d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (byte) (c * b);
            b = (byte) (c * a);
            c = (byte) (d * b);
            d = (byte) (a * b);
            operations += 4;
            //System.out.println(a + " " + b + " " + c + " " + d);
        }
        return operations*10;
    }

    @Override
    public long testShort(){
        System.out.println("short");
        long operations = 0;
        short a;
        short b = 3;
        short c = 1;
        short d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (short) (c * b);
            b = (short) (c * a);
            c = (short) (d * b);
            d = (short) (a * b);
            operations += 4;
            //System.out.println(a + " " + b + " " + c + " " + d);
        }
        return operations*10;
    }

    @Override
    public long testInt(){
        System.out.println("int");
        long operations = 0;
        int a;
        int b = 3;
        int c = 2;
        int d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c * b);
            b = (c * a);
            c = (d * b);
            d = (a * b);
            operations += 4;
            //System.out.println(a + " " + b + " " + c + " " + d);
        }
        return operations*10;
    }

    @Override
    public long testLong(){
        System.out.println("long");
        long operations = 0;
        long a;
        long b = 3;
        long c = 2;
        long d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c * b);
            b = (c * a);
            c = (d * b);
            d = (a * b);
            operations += 4;
            //System.out.println(a + " " + b + " " + c + " " + d);
        }
        return operations*10;
    }

    @Override
    public long testChar(){
        System.out.println("char");
        long operations = 0;
        char a;
        char b = 3;
        char c = 2;
        char d = 5;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (char) (c * b);
            b = (char) (c * a);
            c = (char) (d * b);
            d = (char) (a * b);
            operations += 4;
            //System.out.println(a + " " + b + " " + c + " " + d);
        }
        return operations*10;
    }

    @Override
    public long testFloat(){
        System.out.println("float");
        long operations = 0;
        float a;
        float b = 3.2F;
        float c = 2.5F;
        float d = 5.1F;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c * b);
            b = (c * a);
            c = (d * b);
            d = (a * b);
            operations += 4;
            //System.out.println(a + " " + b + " " + c + " " + d);
        }
        return operations*10;
    }

    @Override
    public long testDouble(){
        System.out.println("double");
        long operations = 0;
        double a;
        double b = 3.25D;
        double c = 2.34D;
        double d = 5.123D;
        long time = System.currentTimeMillis()+100;
        while (System.currentTimeMillis() < time){
            a = (c * b);
            b = (c * a);
            c = (d * b);
            d = (a * b);
            operations += 4;
            //System.out.println(a + " " + b + " " + c + " " + d);
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
