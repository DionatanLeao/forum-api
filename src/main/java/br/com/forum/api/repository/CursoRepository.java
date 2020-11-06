package br.com.forum.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.api.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);

}
