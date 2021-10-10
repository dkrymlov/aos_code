package com.krymlov.university.students;

public class BadStudent extends Student{

    public BadStudent(String name) {
        super(name);
        this.state = "bad";
    }

    @Override
    public void study() {
        relax();
        relax();
        relax();
        relax();
        read();
    }
}
