package br.com.forum.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.forum.api.entity.Curso;
import br.com.forum.api.entity.Topico;

@Controller
public class TopicosController {
	
	@RequestMapping("/topicos")
	@ResponseBody
	public List<Topico> lista() {
		Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));

		return Arrays.asList(topico);
	}
}
