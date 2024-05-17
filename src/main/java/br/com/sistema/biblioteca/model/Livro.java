package br.com.sistema.biblioteca.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter @Setter

@Table(name = "cp_tb_livro")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Livro {
    @Id
    @GeneratedValue
    @Column(name = "cd_livro")
    private Long codigo;

    @Column(name = "ds_titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "nm_autor", length = 100, nullable = false)
    private String autor;

    @Column(name = "ds_genero", length = 50, nullable = false)
    private String genero;

    @Column(name = "dt_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name = "ds_sinopse", length = 500, nullable = false)
    private String sinopse;

    @Column(name = "ds_ibsn", length = 13, nullable = false)
    private String ibsn;

    @ManyToOne()
    @JoinColumn(name = "cd_editora")
    private Editora editora;

    @ManyToOne()
    @JoinColumn(name = "cd_emprestimo")
    private Emprestimo emprestimo;

    @ManyToMany
    @JoinTable(name = "cp_tb_autor_livro",
            joinColumns = @JoinColumn(name = "cd_livro"),
            inverseJoinColumns = @JoinColumn(name = "cd_autor"))
    private List<Autor> autores;
}

