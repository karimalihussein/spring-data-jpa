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
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@karim.com", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            StudentIdCard studentIdCard = new StudentIdCard(
                    "123456789",
                    student);

            studentIdCardRepository.save(studentIdCard);

            studentRepository.findById(1L)
                    .ifPresent(System.out::println);

            studentIdCardRepository.findById(1L)
                    .ifPresent(System.out::println);

            studentRepository.deleteById(1L);

        };
    }

    private void generateRandomStudents(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            Student student = new Student(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.number().numberBetween(18, 99));
            StudentIdCard card = new StudentIdCard(faker.number().digits(7), student);
            student.addBook(new Book(faker.book().title(), faker.book().author(), student, java.time.LocalDateTime.now()));
            studentIdCardRepository.save(card);
        }
    }

}
