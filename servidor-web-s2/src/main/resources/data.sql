
-- INSERTS FUNCIONÁRIOS
INSERT INTO funcionario (nome, cpf, telefone, email, senha)
VALUES ('João da Silva', '12345678901', '(11) 99999-0001', 'joao.silva@exemplo.com', 'senha123'),
('Maria Oliveira', '23456789012', '(11) 98888-0002', 'maria.oliveira@exemplo.com', 'segura456'),
('Carlos Santos', '34567890123', '(11) 97777-0003', 'carlos.santos@exemplo.com', 'senha789');

-- INSERTS ENDEREÇOS
INSERT INTO endereco (
    tipo_logradouro,
    nome_logradouro,
    numero,
    complemento,
    bairro,
    cep,
    nome_localidade,
    sigla_cidade
) VALUES
('Rua', 'Praça da Árvore', 314, 'Apto 1', 'Jardim Silveira', '04241064', 'São Paulo', 'SP'),
('Avenida', 'Paulista', 1000, 'Bloco B', 'Bela Vista', '01311000', 'São Paulo', 'SP'),
('Travessa', 'Monte Alegre', 57, 'Casa 3', 'Perdizes', '05014000', 'São Paulo', 'SP');


-- INSERTS GENERO
INSERT INTO tipo_genero (nome, descricao) VALUES
('Masculino', 'Pessoa que se identifica com o gênero masculino'),
('Feminino', 'Pessoa que se identifica com o gênero feminino'),
('Não-binário', 'Pessoa que não se identifica nem como homem nem como mulher'),
('Agênero', 'Pessoa que não se identifica com nenhum gênero'),
('Gênero fluido', 'Pessoa cuja identidade de gênero varia ao longo do tempo'),
('Bigênero', 'Pessoa que se identifica com dois gêneros, simultaneamente ou alternadamente'),
('Transgênero', 'Pessoa cuja identidade de gênero é diferente do sexo atribuído no nascimento'),
('Intergênero', 'Pessoa cuja identidade de gênero está entre masculino e feminino');



-- INSERTS BENEFICIÁRIOS
INSERT INTO beneficiario (
    nome_registro, nome_social, dt_nasc, cpf, raca, nome_mae,
    foto_perfil, sisa, status_enum, data_ativacao,
    fk_funcionario, fk_endereco, fk_genero
) VALUES
('Lucas Oliveira', 'Lu Oliveira', '1997-09-12', '32576924590', 'PARDO', 'Maria de Lurdes',
 'lucas.jpg', '92817', 'ATIVO', '2025-05-01 10:00:00',
 1, 2, 3),

('Ana Souza', 'Anita Souza', '1995-04-20', '14567812300', 'BRANCO', 'Joana Souza',
 'ana.png', '73920', 'INATIVO', '2025-04-15 09:30:00',
 3, 2, 4),

('Carlos Nunes', 'C. Nunes', '1992-02-10', '98765432100', 'PRETO', 'Helena Nunes',
 'carlos.jpeg', '12678', 'ATIVO', '2025-04-29 14:10:00',
 1, 2, 2);