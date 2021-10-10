package com.krymlov.university.groups;

import com.krymlov.university.students.Student;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<Student> studentList = new ArrayList<Student>(3);

    public Group(String name){
        this.name = name;
    }

    public void addAllStudents(Student first, Student second, Student third){
        addStudent(first);
        addStudent(second);
        addStudent(third);
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public void getInfo(){
        System.out.println(this.name);
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).getInfo();
        }
    }

    public void getFullInfo(){
        System.out.println(this.name);
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).getFullInfo();
        }
    }
}
