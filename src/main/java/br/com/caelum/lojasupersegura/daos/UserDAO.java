package br.com.caelum.lojasupersegura.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.caelum.lojasupersegura.models.User;

@Repository("userDAO")
public class UserDAO implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("Tentando logar com "+username);
		String jpql = "select u from User u where u.login = :login";
		List<User> users = em.createQuery(jpql,User.class).setParameter("login", username).getResultList();
		if(users.isEmpty()){
			throw new UsernameNotFoundException("O usuario "+username+" não existe");
		}
		return users.get(0);
	}

}
