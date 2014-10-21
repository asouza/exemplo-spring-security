package br.com.caelum.lojasupersegura.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.lojasupersegura.models.Comentario;

@Repository
public class ComentarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void adiciona(Comentario comentario) {
		entityManager.persist(comentario);
	}

	public Comentario carrega(Integer id) {
		return entityManager.find(Comentario.class,id);
	}
}
