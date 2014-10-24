package br.com.caelum.lojasupersegura.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		criaPermissoesParaODono(comentario);	
		criaPermissoesParaOsOutros(comentario);
		return "redirect:/produto/"+comentario.getProduto().getId();
		
	}
	
	
	private void criaPermissoesParaOsOutros(Comentario comentario) {
		ObjectIdentity oi = new ObjectIdentityImpl(Comentario.class, comentario.getId());
		Acl acl = mutableAclService.readAclById(oi);
	}

	@Autowired
	private MutableAclService mutableAclService;
	
	private Acl criaPermissoesParaODono(Comentario comentario) {
		// Create an ACL identity for this element
        ObjectIdentity identity = new ObjectIdentityImpl(comentario);
        MutableAcl acl = mutableAclService.createAcl(identity);

        PrincipalSid principalSid = new PrincipalSid(SecurityContextHolder.getContext().getAuthentication());
		acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, principalSid, true);
        acl.insertAce(acl.getEntries().size(), BasePermission.ADMINISTRATION, principalSid, true);
        mutableAclService.updateAcl(acl);	
        return acl;
	}
	
	@RequestMapping("/remove/{idProduto}/comentario/{id}")
	@Transactional
	public String remove(@PathVariable("idProduto") Integer idProduto,@PathVariable("id") Integer id){
		comentarios.remove(comentarios.carrega(id));
		return "redirect:/produto/"+idProduto;
	}

	@RequestMapping(value="/edita/{id}",method=RequestMethod.GET)	
	public ModelAndView editaForm(@PathVariable("id") Integer id){
		//TODO aqui eu vou ter que verificar se o cara é o dono do comentario
		Comentario comentario = comentarios.carrega(id);
		ModelAndView result = new ModelAndView("comentarios/edit_form");
		result.addObject("comentario",comentario);
		return result;
	}
	
}
