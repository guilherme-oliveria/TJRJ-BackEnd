INSERT INTO assunto (cod_as, descricao) VALUES
                                            (1, 'Ficção'),
                                            (2, 'Técnico'),
                                            (3, 'Biografia');

INSERT INTO autor (cod_au, nome) VALUES
                                     (1, 'Paul. J. Deitel'),
                                     (2, 'Robert C. Martin');

INSERT INTO livro (cod_livro, titulo, editora, edicao, ano_publicacao, valor) VALUES
                                                                                  (1, 'Guia Java', 'Editora X', 1, '2020', 50.00),
                                                                                  (2, 'Codigo Limpo', 'Editora Y', 2, '2021', 75.50);

INSERT INTO livro_autor (livro_codl, autor_codau) VALUES
                                                      (1, 1),
                                                      (1, 2),
                                                      (2, 1);

INSERT INTO livro_assunto (livro_codl, assunto_codas) VALUES
                                                          (1, 1),
                                                          (1, 2),
                                                          (2, 3);