package br.com.caelum.lojasupersegura.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Funcionario {

	private String login;
	private String password;
	private List<Role> roles = new ArrayList<>();
	
	public Funcionario(String login,String password,Role... roles){
		this.login = login;
		this.password = password;
		this.roles.addAll(Arrays.asList(roles));
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void add(Role role){
		roles.add(role);
	}

}
