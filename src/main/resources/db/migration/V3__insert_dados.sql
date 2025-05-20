INSERT INTO assunto (cod_as, descricao) VALUES
                                            (nextval('seq_assunto'), 'Ficção'),
                                            (nextval('seq_assunto'), 'Técnico'),
                                            (nextval('seq_assunto'), 'Biografia');

INSERT INTO autor (cod_au, nome) VALUES
                                     (nextval('seq_autor'), 'Paul. J. Deitel'),
                                     (nextval('seq_autor'), 'Robert C. Martin');

INSERT INTO livro (cod_livro, titulo, editora, edicao, ano_publicacao, valor) VALUES
                                                                                  (nextval('seq_livro'), 'Guia Java', 'Editora X', 1, '2020', 50.00),
                                                                                  (nextval('seq_livro'), 'Codigo Limpo', 'Editora Y', 2, '2021', 75.50);

INSERT INTO livro_autor (livro_codl, autor_codau) VALUES
                                                      (1, 1),
                                                      (1, 2),
                                                      (2, 1);

INSERT INTO livro_assunto (livro_codl, assunto_codas) VALUES
                                                          (1, 1),
                                                          (1, 2),
                                                          (2, 3);