package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository,
            CourseRepository courseRepository
    ) {
        return args -> {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@karim.com", firstName, lastName);
            Student student = new Student(firstName, lastName, email, faker.number().numberBetween(17, 55));

            student.addBook(new Book("Clean Code", "Robert Martin", student, LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt", student, LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("Effective Java", "Joshua Bloch", student, LocalDateTime.now().minusDays(4)));

            StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
            student.setStudentIdCard(studentIdCard);

            student.addEnrolment(new Enrolment(new EnrolmentId(1L, 1L), student, new Course("Computer Science", "IT"), LocalDateTime.now()));
            student.addEnrolment(new Enrolment(new EnrolmentId(1L, 2L), student, new Course("Back-End diploma", "IT"), LocalDateTime.now().minusDays(18)));
            student.addEnrolment(new Enrolment(new EnrolmentId(1L, 3L), student, new Course("Front-End diploma", "IT"), LocalDateTime.now().minusDays(18)));


            studentRepository.save(student);


        };
    }

    private void generateRandomStudents(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository, CourseRepository courseRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            Student student = new Student(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.number().numberBetween(18, 99));
            StudentIdCard card = new StudentIdCard(faker.number().digits(7), student);
            studentIdCardRepository.save(card);
            Course course = new Course(faker.educator().course() + " " + faker.number().digits(4), faker.educator().university());
            courseRepository.save(course);
            studentRepository.save(student);
        }
    }

}
