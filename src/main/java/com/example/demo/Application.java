package com.example.demo;

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
    CommandLineRunner commandLineRunner(StudentInterface studentInterface) {
        return args -> {
            Student maria = new Student("Maria", "Jones", "maria@gmail.com", 21);
            Student alex = new Student("Alex", "Smith", "alex@gmail.com", 25);
            Student john = new Student("John", "Doe", "john@gmail.com", 30);
            Student jane = new Student("Jane", "Doe", "jane@gmail.com", 32);
            Student bob = new Student("Bob", "Doe", "bob@gmail.com", 35);
            studentInterface.saveAll(java.util.List.of(maria, alex, john, jane, bob));

            System.out.println("Find student by email: " + studentInterface.findStudentByEmail("alex@gmail.com"));
            System.out.println("Find student by first name and last name: " + studentInterface.findStudentByFirstNameAndLastName("John", "Doe"));
            System.out.println("Find student by first name, last name and email: " + studentInterface.findStudentByFirstNameAndLastNameAndEmail("Jane", "Doe", "jane@gmail.com"));
            System.out.println("Find student by first name, last name, email and age: " + studentInterface.findStudentByFirstNameAndLastNameAndEmailAndAge("Bob", "Doe", "bob@gmail.com", 35));
            System.out.println("Find student by email native: " + studentInterface.findStudentByEmailNative("maria@gmail.com"));
            System.out.println(studentInterface.deleteStudentById(1L));
        };
    }

}
