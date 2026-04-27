package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "reportes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReporteIncendio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double latitud;
    private Double longitud;
    private String tipoIncendio; 
    private String estado;
    private Instant fechaReporte;
}
