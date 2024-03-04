package dev.patika.veterinaryManagement.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {

    @Positive(message = "ID değeri pozitif olmalı")
    private Long id;
    @NotNull(message = "Doktor ismi boş veya null olamaz")
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
