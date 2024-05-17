package br.com.sistema.biblioteca.dto.editora;

import br.com.sistema.biblioteca.model.Editora;

import java.time.LocalDate;

public record DetalhesEditoraDto(Long codigo, String nome, String cnpj, String telefone, LocalDate dataFundacao, String email) {
    public DetalhesEditoraDto(Editora editora) {
        this(editora.getCodigo(), editora.getNome(), editora.getCnpj(), editora.getTelefone(), editora.getDataFundacao(), editora.getEmail());
    }

}
