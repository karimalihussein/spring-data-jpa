package com.example.demo;

import javax.persistence.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "student_id_card_card_number_unique", columnNames = "card_number") } )
public class StudentIdCard  {

    @Id
    @SequenceGenerator(name = "student_id_card_sequence", sequenceName = "student_id_card_sequence", allocationSize = 1, initialValue = 1, schema = "public")
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "student_id_card_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "card_number", nullable = false, columnDefinition = "TEXT", length = 50)
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "student_id_card_student_id_fk"))
    private Student student;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }


}
