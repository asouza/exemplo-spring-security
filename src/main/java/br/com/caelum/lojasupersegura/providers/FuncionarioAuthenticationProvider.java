package br.com.caelum.lojasupersegura.providers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.caelum.lojasupersegura.models.Funcionario;
import br.com.caelum.lojasupersegura.models.Role;

@Component
public class FuncionarioAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private HttpServletRequest request;
	private Map<String,Funcionario> funcionarios = new HashMap<>();
	
	{
		funcionarios.put("alberto", new Funcionario("ceci","password",new Role("ROLE_DEV")));
		funcionarios.put("paulo", new Funcionario("paulo","password",new Role("ROLE_DIRETOR")));
		funcionarios.put("claudio", new Funcionario("claudio","password",new Role("ROLE_DEUS")));
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String login = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Funcionario funcionario = funcionarios.get(login);
		
		if (funcionario == null) {
            throw new BadCredentialsException("Funcionario nao encontrado");
        }
 
        if (!funcionario.getPassword().equals(password)) {
            throw new BadCredentialsException("Senha errada");
        }		
        
		return new UsernamePasswordAuthenticationToken(funcionario, password, funcionario.getRoles());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return !StringUtils.isEmpty(request.getParameter("funcionario"));
	}

}
