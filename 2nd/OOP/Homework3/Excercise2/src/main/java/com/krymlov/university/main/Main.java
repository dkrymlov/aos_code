package com.krymlov.university.main;

import com.krymlov.university.groups.Group;
import com.krymlov.university.students.BadStudent;
import com.krymlov.university.students.GoodStudent;
import com.krymlov.university.students.Student;

public class Main {
    public static void main(String[] args) {
        Student student1 = new BadStudent("Кримлов Данило");
        Student student2 = new BadStudent("Сударкин Георгий");
        Student student3 = new GoodStudent("Романкив Святослав");

        Group group = new Group("K25");
        group.addAllStudents(student1, student2, student3);
        group.getFullInfo();
        group.getInfo();
    }
}
