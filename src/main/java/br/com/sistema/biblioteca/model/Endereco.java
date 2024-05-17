package br.com.sistema.biblioteca.model;


import br.com.sistema.biblioteca.dto.endereco.AlterarEnderecoDto;
import br.com.sistema.biblioteca.dto.endereco.CadastrarEnderecoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@NoArgsConstructor
@Getter @Setter

@Table(name = "cp_tb_endereco")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Endereco {
    @Id
    @GeneratedValue
    @Column(name = "cd_endereco")
    private Long codigo;

    @Column(name = "ds_estado", length = 2, nullable = false)
    private Estados estado;

    @Column(name = "ds_cep", length = 8, nullable = false)
    private String cep;

    @Column(name = "ds_bairro", length = 100, nullable = false)
    private String bairro;

    @Column(name = "ds_logradouro", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "ds_numero", precision = 10, nullable = false)
    private Integer numero;


    @OneToOne(mappedBy = "endereco")
    private Editora editora;

    @OneToOne(mappedBy = "endereco")
    private Usuario usuario;

    public Endereco(CadastrarEnderecoDto dto, Editora editora) {
        estado = dto.estado();
        cep = dto.cep();
        bairro = dto.bairro();
        logradouro = dto.logradouro();
        numero = dto.numero();
        this.editora = editora;
    }

    public Endereco(CadastrarEnderecoDto dto, Usuario usuario) {
        estado = dto.estado();
        cep = dto.cep();
        bairro = dto.bairro();
        logradouro = dto.logradouro();
        numero = dto.numero();
        this.usuario = usuario;
    }

    public void alterar(AlterarEnderecoDto dto) {
        if(dto.estado() != null) {
            estado = dto.estado();
        }
        if(dto.cep() != null) {
            cep = dto.cep();
        }
        if(dto.bairro() != null) {
            bairro = dto.bairro();
        }
        if(dto.bairro() != null) {
            logradouro = dto.logradouro();
        }
        if(dto.bairro() != null) {
            numero = dto.numero();
        }
    }
}
