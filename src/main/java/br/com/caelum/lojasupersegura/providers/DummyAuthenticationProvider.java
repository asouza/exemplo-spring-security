package br.com.caelum.lojasupersegura.providers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DummyAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		System.out.println("autenticando o funcionario");
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return !StringUtils.isEmpty(request.getParameter("funcionario"));
	}

}
