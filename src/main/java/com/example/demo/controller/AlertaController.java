package com.example.demo.controller;

import com.example.demo.model.AlertaMasivaDTO;
import com.example.demo.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/alertas")
@CrossOrigin(origins = "*")
public class AlertaController {
    @Autowired
    private AlertaService alertaService;

    @PostMapping("/difundir")
    public ResponseEntity<String> difundirAlerta(@RequestBody AlertaMasivaDTO alerta) {
        // Validación básica de campos
        if (alerta.getAsunto() == null || alerta.getAsunto().trim().isEmpty() ||
            alerta.getMensaje() == null || alerta.getMensaje().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El asunto y el mensaje son obligatorios.");
        }

        // Llamar al servicio que conecta con Node.js
        boolean exito = alertaService.difundirAlertaMasiva(alerta);

        if (exito) {
            return ResponseEntity.ok("Alerta masiva difundida con éxito al sistema de notificaciones.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo procesar el envío masivo en estos momentos.");
        }
    }
}
