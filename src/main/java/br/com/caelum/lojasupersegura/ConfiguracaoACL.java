package br.com.caelum.lojasupersegura;


import javax.sql.DataSource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
@ComponentScan(basePackages = { "br.com.caelum.lojasupersegura" })
public class ConfiguracaoACL {

	@Autowired
	private DataSource dataSource;

	@Bean
	public AclAuthorizationStrategy aclAuthorization() {
		AclAuthorizationStrategyImpl aclAuthorizationStrategy = new AclAuthorizationStrategyImpl(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_DEUS"),
				new SimpleGrantedAuthority("ROLE_USER"));
		return aclAuthorizationStrategy;
	}

	@Bean
	public PermissionGrantingStrategy permissionGrantingStrategy() {
		PermissionGrantingStrategy grantingStrategy = new DefaultPermissionGrantingStrategy(
				new ConsoleAuditLogger());
		return grantingStrategy;
	}

	@Bean
	public Ehcache ehCache() {
		CacheManager cacheManager = CacheManager.create();
		if(!cacheManager.cacheExists("aclCache")){
			cacheManager.addCache("aclCache");
		}
		
		Ehcache ehCache = cacheManager.getEhcache("aclCache");
		return ehCache;
	}

	@Bean
	public AclCache aclCache(Ehcache ehCache,
			PermissionGrantingStrategy grantingStrategy,
			AclAuthorizationStrategy aclAuthorizationStrategy) {
		AclCache aclCache = new EhCacheBasedAclCache(ehCache, grantingStrategy,
				aclAuthorizationStrategy);

		return aclCache;
	}

	@Bean
	public BasicLookupStrategy basicLookupStrategy(AclCache aclCache,
			AclAuthorizationStrategy aclAuthorizationStrategy,
			PermissionGrantingStrategy grantingStrategy) {

		return new BasicLookupStrategy(dataSource, aclCache, aclAuthorizationStrategy,
				grantingStrategy);				
	}
	
	@Bean	
	public MutableAclService mutableAclService(BasicLookupStrategy lookupStrategy,AclCache aclCache){
		JdbcMutableAclService service = new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
		service.setClassIdentityQuery("select last_insert_id()");
		service.setSidIdentityQuery("select last_insert_id()");
		return service;	
	}
	
	@Bean
	public MethodSecurityExpressionHandler expressionHandler(PermissionEvaluator permissionEvaluator){				
		DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
		handler.setPermissionEvaluator(permissionEvaluator);
		return handler;
	}
	
	@Bean
	public PermissionEvaluator permissionEvaluator(MutableAclService aclService){
		AclPermissionEvaluator aclPermissionEvaluator = new AclPermissionEvaluator(aclService);
		return aclPermissionEvaluator;
	}
	
}
