package br.com.sistema.biblioteca.controller;

import br.com.sistema.biblioteca.dto.endereco.CadastrarEnderecoDto;
import br.com.sistema.biblioteca.dto.endereco.DetalhesEnderecoUsuarioDto;
import br.com.sistema.biblioteca.dto.usuario.AlterarUsuarioDto;
import br.com.sistema.biblioteca.dto.usuario.CadastrarUsuarioDto;
import br.com.sistema.biblioteca.dto.usuario.DetalhesUsuarioDto;
import br.com.sistema.biblioteca.model.Endereco;
import br.com.sistema.biblioteca.model.Usuario;
import br.com.sistema.biblioteca.repository.EnderecoRepository;
import br.com.sistema.biblioteca.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> cadastrar(@RequestBody @Valid CadastrarUsuarioDto dto, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dto);
        usuarioRepository.save(usuario);
        var url = uriBuilder.path("/usuarios/{codigo}").buildAndExpand(usuario.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesUsuarioDto(usuario));
    }

    @PostMapping("{codigo}/endereco")
    @Transactional
    public ResponseEntity<DetalhesEnderecoUsuarioDto> cadastroEndereco(@PathVariable("codigo") Long codigo, @RequestBody @Valid CadastrarEnderecoDto dto, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioRepository.getReferenceById(codigo);
        var endereco = new Endereco(dto, usuario);
        enderecoRepository.save(endereco);
        var url = uriBuilder.path("endereco/{codigo}").buildAndExpand(endereco.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesEnderecoUsuarioDto(endereco));
    }

    @GetMapping
    public ResponseEntity<List<DetalhesUsuarioDto>> listar(Pageable pageable) {
        var usuarios = usuarioRepository.findAll(pageable).stream().map(DetalhesUsuarioDto::new).toList();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<DetalhesUsuarioDto> pesquisa(@PathVariable("codigo") Long codigo) {
        var usuarios = usuarioRepository.getReferenceById(codigo);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuarios));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> alteracao(@PathVariable("codigo") Long codigo, @RequestBody @Valid AlterarUsuarioDto dto) {
        var usuario = usuarioRepository.getReferenceById(codigo);
        usuario.alterar(dto);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<Void> remover(@PathVariable("codigo") Long codigo) {
        usuarioRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
