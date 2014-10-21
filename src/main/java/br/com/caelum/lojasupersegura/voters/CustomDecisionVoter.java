package br.com.caelum.lojasupersegura.voters;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import br.com.caelum.lojasupersegura.models.User;

public class CustomDecisionVoter implements AccessDecisionVoter {
	
    private int ACCESS_GRANTED = 1;
    private int ACCESS_ABSTAIN = 0;
    private int ACCESS_DENIED = -1;

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object,
			Collection attributes) {
		// TODO Auto-generated method stub
		FilterInvocation filterInvocation = (FilterInvocation) object;
		//User user = (User) authentication.getPrincipal();
		return ACCESS_GRANTED;
	}

}
