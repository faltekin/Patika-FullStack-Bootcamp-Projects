package dev.patika.veterinaryManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId",columnDefinition = "serial")
    private Long id;


    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @ManyToOne
    @JoinColumn(name = "appointment_animal_id",referencedColumnName = "animalId")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id",referencedColumnName = "doctorId")
    private Doctor doctor;

}
