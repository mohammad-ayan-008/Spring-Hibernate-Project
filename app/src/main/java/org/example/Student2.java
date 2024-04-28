package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_database")
public class Student2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 12, name = "Student_Name")
    private String name;

    public Student2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student2(String name) {
        this.name = name;
    }

    public Student2() {}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        // TODO: Implement this method
        return "id "+id+" Name "+name;
    }
    
}
