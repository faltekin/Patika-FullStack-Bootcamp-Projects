package dev.patika.veterinaryManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId",columnDefinition = "serial")
    private Long id;

    @Column(name = "customer_name",nullable = false)
    private String name;

    @Column(name = "customer_phone",nullable = false,unique = true)
    private String phone;

    @Column(name = "customer_mail",nullable = false,unique = true)
    private String mail;

    @Column(name = "customer_address")
    private String address;

    @Column(name = "customer_city")
    private String city;


    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Animal> animalList;


}
