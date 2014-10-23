package br.com.caelum.lojasupersegura.acl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acl_sid")
public class SID {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean principal;
	private String sid;

	public SID(boolean principal, String sid) {
		super();
		this.principal = principal;
		this.sid = sid;
	}
	
	private SID(){}

}
