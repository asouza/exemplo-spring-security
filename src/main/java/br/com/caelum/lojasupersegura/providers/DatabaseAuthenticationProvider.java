package br.com.caelum.lojasupersegura.providers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.caelum.lojasupersegura.models.User;

@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
	@Autowired
	private HttpServletRequest request;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String login = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		User user = (User) userDetailsService.loadUserByUsername(login);
				
		
		if (user == null) {
            throw new BadCredentialsException("usuario nao encontrado");
        }
 
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        
		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return StringUtils.isEmpty(request.getParameter("funcionario"));
	}

}
