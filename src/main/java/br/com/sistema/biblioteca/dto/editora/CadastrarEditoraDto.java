package br.com.sistema.biblioteca.dto.editora;

import java.time.LocalDate;

public record CadastrarEditoraDto(String nome, String cnpj, String telefone, LocalDate dataFundacao, String email) {
}
