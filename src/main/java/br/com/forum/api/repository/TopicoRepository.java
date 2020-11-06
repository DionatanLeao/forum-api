package br.com.forum.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.api.entity.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
