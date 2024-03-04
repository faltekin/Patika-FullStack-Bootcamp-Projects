package dev.patika.veterinaryManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "availabledates")
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AvailableDateId")
    private Long id;


    @Column(name = "available_date")
    private LocalDate availableDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abailableDate_doctor_id",referencedColumnName = "doctorId")
    private Doctor doctor;

}
