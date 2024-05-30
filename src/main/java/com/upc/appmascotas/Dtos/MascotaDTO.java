package com.upc.appmascotas.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaDTO {
    private Long id;
    private String address;
    private LocalDate birthDate;
    private int size;
    private String status;
}
