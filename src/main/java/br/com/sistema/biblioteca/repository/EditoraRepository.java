package br.com.sistema.biblioteca.repository;


import br.com.sistema.biblioteca.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
