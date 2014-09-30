package br.com.caelum.lojasupersegura.handlers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth) throws IOException,
			ServletException {
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		GrantedAuthorityImpl permissaoUsuario = new GrantedAuthorityImpl("ROLE_USER");
		GrantedAuthorityImpl permissaoAdmin = new GrantedAuthorityImpl("ROLE_ADMIN");
		String contexto = "/loja-super-segura";
		if(authorities.contains(permissaoAdmin)){
			response.sendRedirect(contexto+"/dash/index");
			return ;
		}
		if(authorities.contains(permissaoUsuario)){
			response.sendRedirect(contexto+"/");
			return ;
		}
		System.out.println("nao caiu em nenhum");
	}

}
