package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "courses", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "course_name_unique", columnNames = "name")
})
public class Course {

    @SequenceGenerator(name = "courses_sequence", sequenceName = "courses_sequence", allocationSize = 1, initialValue = 1, schema = "public")
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "courses_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    @Id
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT", length = 50)
    private String name;
    @Column(name = "department", nullable = false, columnDefinition = "TEXT", length = 50)
    private String department;

    @ManyToMany(mappedBy = "courses")
    private java.util.List<Student> students = new java.util.ArrayList<>();



    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }


    public Course() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public java.util.List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name +
                ", department='" + department +
                '}';
    }
}
