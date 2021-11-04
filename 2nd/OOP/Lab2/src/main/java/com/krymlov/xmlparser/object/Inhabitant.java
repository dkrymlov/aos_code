package com.krymlov.xmlparser.object;

public class Inhabitant {
    private String fullname;
    private String age;
    private String faculty;
    private String cathedra;
    private String grade;
    private String homeplace;
    private String payment;

    private Inhabitant(String fullname, String age, String faculty,
                       String cathedra, String grade, String homeplace, String payment) {
        this.fullname = fullname;
        this.age = age;
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.grade = grade;
        this.homeplace = homeplace;
        this.payment = payment;
    }

    private Inhabitant(){}

    public static Inhabitant getInstance(){
        return new Inhabitant();
    }

    public static Inhabitant getInstance(String fullname, String age,
                                         String faculty, String cathedra,
                                         String grade, String homeplace, String payment){
        return new Inhabitant(fullname, age, faculty, cathedra, grade, homeplace, payment);
    }

    @Override
    public String toString(){
        return fullname + ", " + age + ", " + faculty + ", "
                + cathedra + ", " + grade + ", " + homeplace + ", " + payment;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAge() {
        return age;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getCathedra() {
        return cathedra;
    }

    public String getGrade() {
        return grade;
    }

    public String getHomeplace() {
        return homeplace;
    }

    public String getPayment() {
        return payment;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setCathedra(String cathedra) {
        this.cathedra = cathedra;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setHomeplace(String homeplace) {
        this.homeplace = homeplace;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
