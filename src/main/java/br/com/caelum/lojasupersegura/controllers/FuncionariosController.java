package br.com.caelum.lojasupersegura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionarios")
public class FuncionariosController {

	@RequestMapping("/index")
	public String index(){
		return "funcionarios/index";
	}
}
