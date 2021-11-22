package com.task1;

import java.util.List;

public class Order {
    private java.util.List<Item> itemList;

    void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    List<Item> getItemList() {
        return itemList;
    }

    public void сalculateTotalSum() {/*...*/}
    public void getItems() {/*...*/}
    public void getItemCount() {/*...*/}
    public void addItem(Item item) {/*...*/}
    public void deleteItem(Item item) {/*...*/}
}
