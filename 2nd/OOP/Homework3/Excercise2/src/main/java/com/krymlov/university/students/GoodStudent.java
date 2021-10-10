package com.krymlov.university.students;

public class GoodStudent extends Student{

    public GoodStudent(String name) {
        super(name);
        this.state = "good";
    }

    @Override
    public void study() {
        read();
        write();
        read();
        write();
        relax();
    }
}
