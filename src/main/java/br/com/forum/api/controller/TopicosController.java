package br.com.forum.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.api.controller.dto.DetalhesTopicoDto;
import br.com.forum.api.controller.dto.TopicoDto;
import br.com.forum.api.controller.form.TopicoForm;
import br.com.forum.api.controller.form.UpdateTopicoForm;
import br.com.forum.api.entity.Topico;
import br.com.forum.api.repository.CursoRepository;
import br.com.forum.api.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
		Topico topico = topicoForm.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();		
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@GetMapping
	public List<TopicoDto> listar(String nomeCurso) {
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDto.converter(topicos);
		}
	}
	
	@GetMapping("/{id}")
	public DetalhesTopicoDto detalhar(@PathVariable Long id) {
		Topico topico = topicoRepository.getOne(id);
		return new DetalhesTopicoDto(topico);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateTopicoForm form) {
		Topico topico = form.update(id, topicoRepository);
		return ResponseEntity.ok(new TopicoDto(topico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		topicoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
