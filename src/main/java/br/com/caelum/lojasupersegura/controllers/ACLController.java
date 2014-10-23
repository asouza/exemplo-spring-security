package br.com.caelum.lojasupersegura.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.lojasupersegura.acl.IdentityClass;
import br.com.caelum.lojasupersegura.acl.SID;
import br.com.caelum.lojasupersegura.models.Comentario;

@Controller
public class ACLController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@RequestMapping("/acl/configura")
	public String configura(){
		IdentityClass comentario = new IdentityClass(Comentario.class.getName());
		entityManager.persist(comentario);
		
		entityManager.persist(new SID(true, "user"));
		entityManager.persist(new SID(true, "admin"));
		entityManager.persist(new SID(true, "deus"));
		
		return "";
	}
	
}
