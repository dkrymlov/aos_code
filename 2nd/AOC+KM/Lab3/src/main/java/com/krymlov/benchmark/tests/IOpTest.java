package com.krymlov.benchmark.tests;

public interface IOpTest {
    long getOperations(String type);
    long testByte();
    long testShort();
    long testInt();
    long testLong();
    long testFloat();
    long testChar();
    long testDouble();
}
