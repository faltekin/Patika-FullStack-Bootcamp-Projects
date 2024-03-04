package dev.patika.veterinaryManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private LocalDateTime appointmentDate;
    private Long animalId;
    private Long doctorId;
}
