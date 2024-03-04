package dev.patika.veterinaryManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animalId",columnDefinition = "serial")
    private Long id;

    @Column(name = "animal_name",nullable = false)
    private String name;

    @Column(name = "animal_species",nullable = false)
    private String species;

    @Column(name = "animal_breed",nullable = false)
    private String breed;

    @Column(name = "animal_gender",nullable = false)
    private String gender;

    @Column(name = "animal_colour",nullable = false)
    private String colour;


    @Column(name = "animal_dateOfBirth")
    private LocalDate dateOfBirth;


    @ManyToOne
    @JoinColumn(name = "animal_customer_id",referencedColumnName = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "animal",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccineList;

    @OneToMany(mappedBy = "animal",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;

}
