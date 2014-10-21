package br.com.caelum.lojasupersegura.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.caelum.lojasupersegura.daos.ProdutoDAO;
import br.com.caelum.lojasupersegura.models.Produto;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtos;

	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public String cria(Produto produto){
		System.out.println("olaolaola222");
		produtos.adiciona(produto);
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String criaForm(){
		System.out.println("olaolaola");
		return "produtos/cria_form";
	}
}
