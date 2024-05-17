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

@Table(name = "cp_tb_autor")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Autor {
    @Id
    @GeneratedValue
    @Column(name = "cd_autor")
    private Long codigo;

    @Column(name = "nm_autor", length = 100, nullable = false)
    private String autor;

    @Column(name = "ds_biografia", length = 500)
    private String biografia;

    @Column(name = "ds_nascionalidade", length = 50, nullable = false)
    private String ds_nacionalidade;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dataNascimento;


    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros;
}
