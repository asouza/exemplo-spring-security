package br.com.caelum.lojasupersegura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {
	/*
	 * GET /login renders the login page instead of /spring_security_login
	 * 
	 * POST /login authenticates the user instead of /j_spring_security_check
	 * 
	 * The username parameter defaults to username instead of j_username
	 * 
	 * The password parameter defaults to password instead of j_password
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password")
				.roles("USER").and().withUser("dash").password("password")
				.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//a configuração tem que ser toda junta, para ele mexer no mesmo objeto.
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/dash/**").hasRole("ADMIN")
				.antMatchers("/carrinho/index")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").anyRequest()
				.authenticated().and().formLogin().loginPage("/login");
//				.permitAll().successHandler(postAuth);

	}

}
