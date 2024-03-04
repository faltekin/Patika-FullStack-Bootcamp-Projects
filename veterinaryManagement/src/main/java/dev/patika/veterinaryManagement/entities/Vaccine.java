package dev.patika.veterinaryManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vaccines")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id",columnDefinition = "serial")
    private Long id;

    @Column(name = "vaccine_name",nullable = false)
    private String name;

    @Column(name = "vaccine_code",nullable = false,unique = true)
    private String code;


    @Column(name = "vaccine_protectionStartDate")
    private LocalDate protectionStartDate;


    @Column(name = "vaccine_protectionFinishDate")
    private LocalDate protectionFinishDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vaccine_animal_id",referencedColumnName = "animalId")
    private Animal animal;



}
