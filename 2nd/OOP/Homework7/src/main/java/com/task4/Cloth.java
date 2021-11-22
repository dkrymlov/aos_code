package com.task4;

public class Cloth implements IItem, IDiscount, ISize, IColor, IPromocode{
    public void setPrice(double price) { }
    public void applyDiscount(String discount) { }
    public void applyPromocode(String promocode) { }
    public void setSize(byte size) { }
    public void setColor(byte color) { }
}
