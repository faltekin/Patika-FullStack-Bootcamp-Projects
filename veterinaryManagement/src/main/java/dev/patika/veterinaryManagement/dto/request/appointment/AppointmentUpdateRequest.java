package dev.patika.veterinaryManagement.dto.request.appointment;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {

    @Positive(message = "ID değeri pozitif olmalı")
    private Long id;
    private LocalDateTime appointmentDate;
    private Long animalId;
    private Long doctorId;
}
