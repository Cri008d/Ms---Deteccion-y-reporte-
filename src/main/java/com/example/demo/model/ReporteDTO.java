package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReporteDTO {

    private Double latitud;
    private Double longitud;
    private String tipoIncendio;
}
