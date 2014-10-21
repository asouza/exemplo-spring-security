package br.com.caelum.lojasupersegura.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.lojasupersegura.models.Role;
import br.com.caelum.lojasupersegura.models.User;

@Controller
public class CriaTodosUsuariosController {
	
	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping("/cria/usuarios")
	@Transactional
	public String cria(){
		User admin = newUser("admin","$2a$16$xrVPojjnbhAKVcCNCbpAV.8HaaPQuQzOKqKnbYXntSoYl/vf4HXvi","ROLE_ADMIN");
		User user = newUser("user","$2a$16$xrVPojjnbhAKVcCNCbpAV.8HaaPQuQzOKqKnbYXntSoYl/vf4HXvi", "ROLE_USER");
		User deus = newUser("deus","$2a$16$xrVPojjnbhAKVcCNCbpAV.8HaaPQuQzOKqKnbYXntSoYl/vf4HXvi","ROLE_DEUS","ROLE_ADMIN");
		entityManager.persist(admin);
		entityManager.persist(user);
		entityManager.persist(deus);
		
		return "";
	}

	private User newUser(String login, String password, String... roles) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setName(login);
		for(String roleName : roles){
			Role role = new Role(roleName);
			entityManager.merge(role);
			user.add(role);			
		}
		return user;
	}
}
