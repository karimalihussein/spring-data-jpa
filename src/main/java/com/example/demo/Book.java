package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "books")
@Table(name = "books", schema = "public")
public class Book {
    @Id
    @SequenceGenerator(name = "books_sequence", sequenceName = "books_sequence", allocationSize = 1, initialValue = 1, schema = "public")
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "books_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, columnDefinition = "TEXT", length = 50)
    private String title;

    @Column(name = "author", nullable = false, columnDefinition = "TEXT", length = 50)
    private String author;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "books_student_id_fk"))
    private Student student;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private java.time.LocalDateTime createdAt;



    public Book(String title, String author, Student student, java.time.LocalDateTime createdAt) {
        this.title = title;
        this.author = author;
        this.student = student;
        this.createdAt = createdAt;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title=" + title +
                ", author=" + author +
                ", student=" + student +
                ", createdAt=" + createdAt +
                '}';
    }
}
