package com.example.demo;

import javax.persistence.*;

@Entity(name = "students")
@Table(name = "students", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "students_email_unique", columnNames = "email")
})
public class Student {
    @Id
    @SequenceGenerator(name = "students_sequence", sequenceName = "students_sequence", allocationSize = 1, initialValue = 1, schema = "public")
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "students_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT", length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT", length = 50)
    private String lastName;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT", length = 255)
    private String email;
    @Column(name = "age", nullable = false, columnDefinition = "INTEGER", length = 3)
    private Integer age;
    @OneToOne(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval = true)
    private StudentIdCard studentIdCard;
    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private java.util.List<Book> books = new java.util.ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "enrolments", schema = "public",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "enrolments_student_id_fk")),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "enrolments_course_id_fk")))
    private java.util.List<Course> courses = new java.util.ArrayList<>();


    public java.util.List<Book> getBooks() {
        return books;
    }

    public void setBooks(java.util.List<Book> books) {
        this.books = books;
    }


    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student() {
    }

    public void addBook(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void enrollToCourse(Course course) {
        if (!this.courses.contains(course)) {
            this.courses.add(course);
            course.getStudents().add(this);
        }
    }
    public void unEnrollFromCourse(Course course) {
            if (this.courses.contains(course)) {
                this.courses.remove(course);
                course.getStudents().remove(this);
            }
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
                ", email=" + email + ", age=" + age + "]";
    }




}
