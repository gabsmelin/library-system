package br.com.sistema.biblioteca.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CadastrarUsuarioDto(
        @NotBlank(message = "Não se pode inserir um valor vazio.")
        String nome,

        @NotBlank(message = "Não se pode inserir um valor vazio.")
        @Size(max = 11)
        String telefone,

        @NotBlank(message = "Não se pode inserir um valor vazio.")
        String email) {
}
