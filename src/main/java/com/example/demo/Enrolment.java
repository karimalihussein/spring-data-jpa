package com.example.demo;


import javax.persistence.*;

@Entity
@Table(name = "enrolments", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "enrolments_student_id_course_id_unique", columnNames = {"student_id", "course_id"})
})
public class Enrolment {
        @SequenceGenerator(name = "enrolments_sequence", sequenceName = "enrolments_sequence", allocationSize = 1, initialValue = 1, schema = "public")
        @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "enrolments_sequence")
        @Column(name = "id", updatable = false, nullable = false)
        @Id
        private Long id;

        @ManyToOne
        @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "enrolments_student_id_fk"))
        private Student student;

        @ManyToOne
        @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "enrolments_course_id_fk"))
        private Course course;

        public Enrolment(Student student, Course course) {
            this.student = student;
            this.course = course;
        }

        public Enrolment() {
        }
}
