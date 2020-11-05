package br.com.forum.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.api.controller.dto.TopicoDto;
import br.com.forum.api.entity.Curso;
import br.com.forum.api.entity.Topico;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@GetMapping
	public List<TopicoDto> lista() {
		Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));

		return TopicoDto.converter(Arrays.asList(topico));
	}
}
