package br.com.sistema.biblioteca.dto.endereco;

import br.com.sistema.biblioteca.dto.editora.DetalhesEditoraDto;
import br.com.sistema.biblioteca.dto.usuario.DetalhesUsuarioDto;
import br.com.sistema.biblioteca.model.Endereco;
import br.com.sistema.biblioteca.model.Estados;

public record DetalhesEnderecoEditoraDto(Long codigo, Estados estado, String cep, String bairro, String logradouro, Integer numero, DetalhesEditoraDto detalhesEditora) {
    public DetalhesEnderecoEditoraDto(Endereco endereco) {
        this(endereco.getCodigo(), endereco.getEstado(), endereco.getCep(), endereco.getBairro(), endereco.getLogradouro(), endereco.getNumero(), new DetalhesEditoraDto(endereco.getEditora()));
    }
}
