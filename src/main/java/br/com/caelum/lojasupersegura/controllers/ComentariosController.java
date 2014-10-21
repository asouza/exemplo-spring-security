package br.com.caelum.lojasupersegura.controllers;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.lojasupersegura.daos.ComentarioDAO;
import br.com.caelum.lojasupersegura.models.Comentario;
import br.com.caelum.lojasupersegura.models.User;

@Controller
@RequestMapping("/comentarios")
public class ComentariosController {
	
	@Autowired
	private ComentarioDAO comentarios;

	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public String cria(Comentario comentario){
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		comentario.setCriador(principal);
		comentarios.adiciona(comentario);
		return "redirect:/produto/"+comentario.getProduto().getId();
		
	}
	
	@RequestMapping(value="/edita/{id}",method=RequestMethod.GET)	
	public ModelAndView editaForm(@PathParam("id") Integer id){
		//TODO aqui eu vou ter que verificar se o cara é o dono do comentario
		Comentario comentario = comentarios.carrega(id);
		ModelAndView result = new ModelAndView("comentarios/edit_form");
		result.addObject("comentario",comentario);
		return result;
	}
	
}
