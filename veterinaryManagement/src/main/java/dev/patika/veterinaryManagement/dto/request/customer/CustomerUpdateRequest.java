package dev.patika.veterinaryManagement.dto.request.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    @Positive(message = "ID değeri pozitif olmalı")
    private Long id;
    @NotNull(message = "Müşteri ismi boş veya null olamaz")
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;

}
