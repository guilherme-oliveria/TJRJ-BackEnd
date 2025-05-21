CREATE OR REPLACE VIEW vw_livros_por_autor AS
SELECT
    a.cod_au AS autor_id,
    a.nome AS autor_nome,
    l.cod_livro AS livro_id,
    l.titulo AS livro_titulo,
    l.editora,
    l.edicao,
    l.ano_publicacao,
    l.valor,
    (
        SELECT STRING_AGG(as2.descricao, ', ' ORDER BY as2.descricao)
        FROM livro_assunto las2
                 JOIN assunto as2 ON las2.assunto_codas = as2.cod_as
        WHERE las2.livro_codl = l.cod_livro
    ) AS assuntos
FROM autor a
         JOIN livro_autor la ON a.cod_au = la.autor_codau
         JOIN livro l ON la.livro_codl = l.cod_livro
order by a.cod_au asc ;