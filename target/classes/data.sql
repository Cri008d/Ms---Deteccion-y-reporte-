INSERT INTO usuarios (nombre, correo, contrase_a, rol)
VALUES ('Bombero Oficial', 'bombero@valleseguro.cl', '$2a$10$wH6Z7xI.WNVp.eYPR72UoO5pB0N8Ivxw6A66mH77gR8YshM1u7Bpe', 'FUNCIONARIO')
ON CONFLICT (correo) DO NOTHING;