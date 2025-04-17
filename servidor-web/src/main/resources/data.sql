
-- INSERTS FUNCIONÁRIOS
INSERT INTO funcionario (nome, cpf, telefone, email, senha)
VALUES ('João da Silva', '12345678901', '(11) 99999-0001', 'joao.silva@exemplo.com', 'senha123'),
('Maria Oliveira', '23456789012', '(11) 98888-0002', 'maria.oliveira@exemplo.com', 'segura456'),
('Carlos Santos', '34567890123', '(11) 97777-0003', 'carlos.santos@exemplo.com', 'senha789');




-- INSERTS BENEFICIÁRIOS
INSERT INTO beneficiario
(nome, dt_nasc, cpf, genero_enum, raca, nome_mae, foto_perfil, sisa, status_enum, data_ativacao, fk_funcionario)
VALUES
('Ana Paula', '1990-05-10', '12345678901', 'MULHER_TRANS', 'PARDO', 'Maria Paula', 'ana.jpg', 'SISA123', 'ATIVO', '2024-03-15 09:00:00', 1),
('Carlos Eduardo', '1985-11-22', '23456789012', 'HOMEM_CIS', 'BRANCO', 'Joana Eduardo', 'carlos.jpg', 'SISA456', 'INATIVO', '2023-11-01 14:30:00', 2),
('Juliana Silva', '2000-07-18', '34567890123', 'MULHER_TRANS', 'PRETO', 'Lucia Silva', 'juliana.jpg', 'SISA789', 'ATIVO', '2025-01-10 08:45:00', 3);
