package br.com.sistema.biblioteca.model;


import br.com.sistema.biblioteca.dto.editora.AlterarEditoraDto;
import br.com.sistema.biblioteca.dto.editora.CadastrarEditoraDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter @Setter

@Table(name = "cp_tb_editora")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Editora {
    @Id
    @GeneratedValue
    @Column(name = "cd_editora")
    private Long codigo;

    @Column(name = "nm_editora", length = 100, nullable = false)
    private String nome;

    @Column(name = "nr_cnpj", length = 14, nullable = false)
    private String cnpj;

    @Column(name = "nr_telefone", length = 11, nullable = false)
    private String telefone;

    @Column(name = "dt_fundacao", nullable = false)
    private LocalDate dataFundacao;

    @Column(name = "ds_email", length = 100, nullable = false)
    private String email;

    @OneToMany(mappedBy = "editora")
    private List<Livro> livro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_endereco")
    private Endereco endereco;

    public Editora(CadastrarEditoraDto dto) {
        nome = dto.nome();
        cnpj = dto.cnpj();
        telefone = dto.telefone();
        email = dto.email();
        dataFundacao = dto.dataFundacao();
    }

    public void alterar(AlterarEditoraDto dto) {
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
