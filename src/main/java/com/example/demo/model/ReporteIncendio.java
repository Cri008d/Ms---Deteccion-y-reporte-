package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReporteIncendio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    @Column(nullable = false)
    private String tipoIncendio;
    
    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime fechaReporte;

    @ManyToOne 
    @JoinColumn(name = "usuario_id") 
    private Usuario usuario;
}
