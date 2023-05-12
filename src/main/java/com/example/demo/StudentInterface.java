package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.*;


public interface StudentInterface extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM students s WHERE s.email = ?1")
    Student findStudentByEmail(String email);

    @Query("SELECT s FROM students s WHERE s.firstName = :firstName AND s.lastName = :lastName")
    Student findStudentByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT s FROM students s WHERE s.firstName = :firstName AND s.lastName = :lastName AND s.email = :email")
    Student findStudentByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);

    @Query("SELECT s FROM students s WHERE s.firstName = :firstName AND s.lastName = :lastName AND s.email = :email AND s.age = :age")
    Student findStudentByFirstNameAndLastNameAndEmailAndAge(String firstName, String lastName, String email, Integer age);

    @Query(value = "SELECT * FROM students WHERE email = :email", nativeQuery = true)
    Student findStudentByEmailNative(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM students s WHERE s.id = ?1")
    int deleteStudentById(Long id);

}
