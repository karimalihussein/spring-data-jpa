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
    CommandLineRunner commandLineRunner(StudentInterface studentInterface, StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            generateRandomStudents(studentInterface, studentIdCardRepository);
        };
    }

    private void generateRandomStudents(StudentInterface studentInterface, StudentIdCardRepository studentIdCardRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            Student student = new Student(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.number().numberBetween(18, 99));
            StudentIdCard card = new StudentIdCard(faker.number().digits(7), student);
            studentIdCardRepository.save(card);
        }
    }

}
