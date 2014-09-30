package br.com.caelum.lojasupersegura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@RequestMapping("/index")
	public String index(){
		return "carrinho/index";
	}
}
