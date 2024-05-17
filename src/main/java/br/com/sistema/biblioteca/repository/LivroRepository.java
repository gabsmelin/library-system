package br.com.sistema.biblioteca.repository;


import br.com.sistema.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}

