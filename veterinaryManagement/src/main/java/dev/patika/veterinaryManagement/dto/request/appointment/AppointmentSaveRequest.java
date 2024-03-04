package dev.patika.veterinaryManagement.dto.request.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {

    private LocalDateTime appointmentDate;
    private Long doctorId;
    private Long animalId;


}
