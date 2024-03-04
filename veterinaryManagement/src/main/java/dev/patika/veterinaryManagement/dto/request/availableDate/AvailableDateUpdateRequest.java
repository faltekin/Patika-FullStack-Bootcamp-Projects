package dev.patika.veterinaryManagement.dto.request.availableDate;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {

    @Positive(message = "ID değeri pozitif olmalı")
    private Long id;
    private LocalDate availableDate;
    private Long doctorId;
}
