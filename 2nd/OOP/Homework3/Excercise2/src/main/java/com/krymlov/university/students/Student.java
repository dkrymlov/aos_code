package com.krymlov.university.students;

public abstract class Student {
    private String name;
    protected String state;

    public Student(String name){
        this.name = name;
        this.state = null;
    }

    public void getInfo(){
        System.out.println("Name: " + name);
    }
    public void getFullInfo(){
        System.out.println("Name: " + name + ", state: " + state);
    }
    public void relax(){
        System.out.println("relaxing");
    }
    public void read(){
        System.out.println("reading");
    }
    public void write(){
        System.out.println("writing");
    }
    public abstract void study();
}
