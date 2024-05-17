package br.com.sistema.biblioteca.dto.usuario;

import br.com.sistema.biblioteca.model.Usuario;

import java.time.LocalDateTime;

public record DetalhesUsuarioDto(Long codigo, String nome, LocalDateTime dataCadastro, String telefone, String email) {
    public DetalhesUsuarioDto(Usuario usuario) {
        this(usuario.getCodigo(), usuario.getNome(), usuario.getDataCadastro(), usuario.getTelefone(), usuario.getEmail());
    }
}
