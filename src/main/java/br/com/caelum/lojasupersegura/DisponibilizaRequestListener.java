package br.com.caelum.lojasupersegura;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.addListener(RequestContextListener.class);
		System.out.println("Listener adicionado");
	}

}
