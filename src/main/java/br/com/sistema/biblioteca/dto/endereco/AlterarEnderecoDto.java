package br.com.sistema.biblioteca.dto.endereco;

import br.com.sistema.biblioteca.model.Estados;
import jakarta.validation.constraints.NotBlank;

public record AlterarEnderecoDto(
        @NotBlank(message = "Não se pode inserir um valor vazio.")
        Estados estado,

        @NotBlank(message = "Não se pode inserir um valor vazio.")
        String cep,

        @NotBlank(message = "Não se pode inserir um valor vazio.")
        String bairro,

        @NotBlank(message = "Não se pode inserir um valor vazio.")
        String logradouro,

        @NotBlank(message = "Não se pode inserir um valor vazio.")
        Integer numero
) {
}
