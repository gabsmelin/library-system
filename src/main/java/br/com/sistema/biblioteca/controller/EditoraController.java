package br.com.sistema.biblioteca.controller;

import br.com.sistema.biblioteca.dto.editora.AlterarEditoraDto;
import br.com.sistema.biblioteca.dto.editora.CadastrarEditoraDto;
import br.com.sistema.biblioteca.dto.editora.DetalhesEditoraDto;
import br.com.sistema.biblioteca.dto.endereco.AlterarEnderecoDto;
import br.com.sistema.biblioteca.dto.endereco.CadastrarEnderecoDto;
import br.com.sistema.biblioteca.dto.endereco.DetalhesEnderecoEditoraDto;
import br.com.sistema.biblioteca.dto.usuario.AlterarUsuarioDto;
import br.com.sistema.biblioteca.dto.usuario.DetalhesUsuarioDto;
import br.com.sistema.biblioteca.model.Editora;
import br.com.sistema.biblioteca.model.Endereco;
import br.com.sistema.biblioteca.repository.EditoraRepository;
import br.com.sistema.biblioteca.repository.EnderecoRepository;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("editoras")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;


    @GetMapping("{codigo}/endereco")
    public ResponseEntity<DetalhesEnderecoEditoraDto> listagemEndereco(@PathVariable("codigo") Long codigo) {
        var editora = editoraRepository.findById(codigo);
        return ResponseEntity.ok(new DetalhesEnderecoEditoraDto(editora.get().getEndereco()));
    }


    @PostMapping("{codigo}/endereco")
    @Transactional
    public ResponseEntity<DetalhesEnderecoEditoraDto> cadastroEndereco(@PathVariable("codigo") Long codigo, @RequestBody @Valid CadastrarEnderecoDto dto, UriComponentsBuilder uriBuilder) {
        var editora = editoraRepository.getReferenceById(codigo);
        var endereco = new Endereco(dto, editora);
        enderecoRepository.save(endereco);
        var url = uriBuilder.path("endereco/{codigo}").buildAndExpand(endereco.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesEnderecoEditoraDto(endereco));
    }

    @PutMapping("{codigoEditora}/endereco/{codigoEndereco}")
    @Transactional
    public ResponseEntity<DetalhesEnderecoEditoraDto> alterarEndereco(@PathVariable("codigo") Long codigoEndereco, @PathVariable("codigo") Long codigoEditora, @RequestBody @Valid AlterarEnderecoDto dto) {
        var editora = editoraRepository.getReferenceById(codigoEditora);
        var endereco = enderecoRepository.getReferenceById(codigoEndereco);
        editora.getEndereco().alterar(dto);
        return ResponseEntity.ok(new DetalhesEnderecoEditoraDto(endereco));
    }

    @GetMapping
    public ResponseEntity <List<DetalhesEditoraDto>> listagem(Pageable pageable) {
        var editora = editoraRepository.findAll().stream().map(DetalhesEditoraDto::new).toList();
        return ResponseEntity.ok(editora);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<DetalhesEditoraDto> pesquisa(@PathVariable("codigo") Long codigo) {
        var editora = editoraRepository.getReferenceById(codigo);
        return ResponseEntity.ok(new DetalhesEditoraDto(editora));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEditoraDto> cadastro(@RequestBody @Valid CadastrarEditoraDto dto, UriComponentsBuilder uriBuilder) {
        var editora = new Editora(dto);
        editoraRepository.save(editora);
        var url = uriBuilder.path("editora/{codigo}").buildAndExpand(editora.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesEditoraDto(editora));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<DetalhesEditoraDto> alteracao(@PathVariable("codigo") Long codigo, @RequestBody @Valid AlterarEditoraDto dto) {
        var editora = editoraRepository.getReferenceById(codigo);
        editora.alterar(dto);
        return ResponseEntity.ok(new DetalhesEditoraDto(editora));
    }

    @DeleteMapping("{codigo}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable("codigo") Long codigo) {
        editoraRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
