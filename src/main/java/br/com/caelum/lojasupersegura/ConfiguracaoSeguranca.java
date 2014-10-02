package br.com.caelum.lojasupersegura;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.caelum.lojasupersegura.handlers.CustomAccessDeniedHandler;
import br.com.caelum.lojasupersegura.handlers.CustomLogoutSuccessHandler;
import br.com.caelum.lojasupersegura.handlers.CustomSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "br.com.caelum.lojasupersegura" })
@Order(0)
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
		// auth.inMemoryAuthentication().withUser("user").password("password")
		// .roles("USER").and().withUser("dash").password("password")
		// .roles("ADMIN").and().withUser("deus").password("password")
		// .roles("ADMIN", "DEUS");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AuthenticationSuccessHandler postAuthHandler = new CustomSuccessHandler();
		LogoutSuccessHandler logoutSuccessHandler = new CustomLogoutSuccessHandler();
		AccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
		// a configuração tem que ser toda junta, para ele mexer no mesmo
		// objeto.
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/dash/**").hasRole("ADMIN")
				.antMatchers("/carrinho/index")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll()
				.successHandler(postAuthHandler)
				.failureUrl("/login?msg=nao_logado").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout22"))
				.logoutSuccessHandler(logoutSuccessHandler).and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.and().rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(60);

	}

	@Autowired
	private DataSource datasource;

	private PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(datasource);
		return tokenRepository;
	}

	@Autowired
	private UserDetailsService users;
	@Autowired
	private List<AuthenticationProvider> authenticationProviders;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// auth.userDetailsService(users).passwordEncoder(
		// new BCryptPasswordEncoder(16));
		for (AuthenticationProvider provider : authenticationProviders) {
			auth.authenticationProvider(provider);
		}
		// super.configure(auth);
	}

}
