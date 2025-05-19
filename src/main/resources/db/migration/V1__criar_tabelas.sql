CREATE TABLE autor (
                       cod_au BIGINT PRIMARY KEY,
                       nome VARCHAR(255) NOT NULL
);

CREATE TABLE assunto (
                         cod_as BIGINT PRIMARY KEY,
                         descricao VARCHAR(255) NOT NULL
);

CREATE TABLE livro (
                       cod_livro BIGINT PRIMARY KEY,
                       titulo VARCHAR(40) NOT NULL,
                       editora VARCHAR(40),
                       edicao INT,
                       ano_publicacao VARCHAR(4),
                       valor DECIMAL(16, 4),
                       data_criacao TIMESTAMP,
                       data_alteracao TIMESTAMP
);

CREATE TABLE livro_autor (
                             livro_codl BIGINT NOT NULL,
                             autor_codau BIGINT NOT NULL,
                             PRIMARY KEY (livro_codl, autor_codau),
                             FOREIGN KEY (livro_codl) REFERENCES livro(cod_livro),
                             FOREIGN KEY (autor_codau) REFERENCES autor(cod_au)
);

CREATE TABLE livro_assunto (
                               livro_codl BIGINT NOT NULL,
                               assunto_codas BIGINT NOT NULL,
                               PRIMARY KEY (livro_codl, assunto_codas),
                               FOREIGN KEY (livro_codl) REFERENCES livro(cod_livro),
                               FOREIGN KEY (assunto_codas) REFERENCES assunto(cod_as)
);