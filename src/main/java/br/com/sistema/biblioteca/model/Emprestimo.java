package br.com.sistema.biblioteca.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cp_tb_emprestimo")
@EntityListeners(AuditingEntityListener.class)
public class Emprestimo
{
    @Id
    @GeneratedValue
    @Column(name = "cd_emprestimo", nullable = false)
    private Long codigo;

    @Column(name = "dt_emprestimo", nullable = false)
    private LocalDateTime dataEmprestimo;

    @Column(name = "dt_devolucao_prevista", nullable = false)
    private LocalDateTime dataDevolucaoPrevista;

    @Column(name = "dt_devolucao_real", nullable = false)
    private LocalDateTime dataDevolucaoReal;

    @Column(name = "vl_multa", nullable = false)
    private Double multa;

    @Column(name = "ds_status", nullable = false, length = 30)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cd_usuario")
    private Usuario usuario;

    @OneToMany()
    private List<Livro> livros;
}
