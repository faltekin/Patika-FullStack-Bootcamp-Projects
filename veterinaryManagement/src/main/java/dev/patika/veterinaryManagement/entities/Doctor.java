package dev.patika.veterinaryManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorId",columnDefinition = "serial")
    private Long id;

    @Column(name = "doctor_name",nullable = false)
    private String name;

    @Column(name = "doctor_phone",nullable = false,unique = true)
    private String phone;

    @Column(name = "doctor_mail",nullable = false,unique = true)
    private String mail;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "doctor_city")
    private String city;


    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<AvailableDate> availableDateList;

}
