package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            generateRandomStudents(studentRepository, studentIdCardRepository, courseRepository);
        };
    }

    private void generateRandomStudents(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository, CourseRepository courseRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            Student student = new Student(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.number().numberBetween(18, 99));
            StudentIdCard card = new StudentIdCard(faker.number().digits(7), student);
            Course course = new Course(faker.educator().course() + " " + faker.number().digits(4), faker.educator().university());
            courseRepository.save(course);
            student.addBook(new Book(faker.book().title(), faker.book().author(), student, java.time.LocalDateTime.now()));
            studentIdCardRepository.save(card);
        }
    }

}
