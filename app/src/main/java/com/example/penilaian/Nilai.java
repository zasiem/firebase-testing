package com.example.penilaian;

public class Nilai {

    private String nama, surname;
    private int marks;

    public Nilai(int marks, String nama, String surname) {
        this.marks = marks;
        this.nama = nama;
        this.surname = surname;
    }

    public Nilai() {

    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
