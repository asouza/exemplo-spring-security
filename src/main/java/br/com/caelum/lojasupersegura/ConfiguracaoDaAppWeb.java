package br.com.caelum.lojasupersegura;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class ConfiguracaoDaAppWeb extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ConfiguracaoSeguranca.class,ConfiguracaoJPA.class,ConfiguracaoSegurancaFuncionario.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{WebInitializer.class,WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
		
}
