package br.com.caelum.lojasupersegura;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(1)
public class ConfiguracaoSegurancaFuncionario extends
		WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/funcionarios/**")
				.access("hasRole('ROLE_DEV') or hasRole('ROLE_DEUS') or hasRole('ROLE_DIRETOR')");

	}
}
