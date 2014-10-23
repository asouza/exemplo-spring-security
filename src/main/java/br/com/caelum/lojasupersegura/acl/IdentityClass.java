package br.com.caelum.lojasupersegura.acl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acl_class")
public class IdentityClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "class")
	private String klass;

	private IdentityClass() {
	}

	public IdentityClass(String klass) {
		super();
		this.klass = klass;
	}
	
	

}
