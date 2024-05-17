package br.com.sistema.biblioteca.model;

import br.com.sistema.biblioteca.dto.usuario.AlterarUsuarioDto;
import br.com.sistema.biblioteca.dto.usuario.CadastrarUsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

@Table(name = "cp_tb_usuario")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue
    @Column(name = "cd_usuario")
    private Long codigo;

    @Column(name = "nm_usuario", length = 100, nullable = false)
    private String nome;

    @Column(name = "dt_cadastro", nullable = false)
    @CreatedDate
    private LocalDateTime dataCadastro;

    @Column(name = "nr_telefone", length = 11, nullable = false)
    private String telefone;

    @Column(name = "ds_email", length = 150, nullable = false)
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimos;

    public Usuario(CadastrarUsuarioDto dto) {
        nome = dto.nome();
        telefone = dto.telefone();
        email = dto.email();
    }

    public void alterar(AlterarUsuarioDto dto) {
        if(dto.nome() != null) {
            nome = dto.nome();
        }
        if(dto.telefone() != null) {
            telefone = dto.telefone();
        }
        if(dto.email() != null) {
            email = dto.email();
        }
    }
}
