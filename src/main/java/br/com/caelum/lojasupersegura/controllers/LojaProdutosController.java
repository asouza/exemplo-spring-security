package br.com.caelum.lojasupersegura.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.lojasupersegura.daos.ProdutoDAO;
import br.com.caelum.lojasupersegura.models.Produto;

@Controller
//TODO sei la qual o melhor nome aqui
public class LojaProdutosController {
	
	@Autowired
	private ProdutoDAO produtos;

	@RequestMapping("/")
	public ModelAndView index(){
		List<Produto> lista = produtos.lista();
		ModelAndView result = new ModelAndView("loja/index");
		result.addObject("produtos", lista);
		return result;
	}
	
	@RequestMapping("/produto/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id){
		Produto produto = produtos.carrega(id);
		System.out.println(produto.getComentarios());
		ModelAndView result = new ModelAndView("loja/produtos/detalhe");
		result.addObject("produto",produto);
		return result;
	}
}
