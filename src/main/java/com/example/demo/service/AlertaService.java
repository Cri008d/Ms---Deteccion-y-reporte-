package com.example.demo.service;

import com.example.demo.model.AlertaMasivaDTO;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {
    private final RestTemplate restTemplate = new RestTemplate();
    
    private final String NODE_SERVICE_URL = "https://ms-notificaciones-api.onrender.com/api/notificaciones/enviar";

    public boolean difundirAlertaMasiva(AlertaMasivaDTO alerta) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AlertaMasivaDTO> request = new HttpEntity<>(alerta, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(NODE_SERVICE_URL, request, String.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.err.println("Error al conectar con el microservicio de Notificaciones: " + e.getMessage());
            return false;
        }
    }
}
