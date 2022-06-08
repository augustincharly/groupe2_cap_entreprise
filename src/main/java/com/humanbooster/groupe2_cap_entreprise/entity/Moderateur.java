package com.humanbooster.groupe2_cap_entreprise.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="moderateur")
public class Moderateur extends Utilisateur{

	@Column(name = "numeroDeTelephone")
	private String numeroDeTelephone;

	public String getNumeroDeTelephone() {
		return numeroDeTelephone;
	}

	public void setNumeroDeTelephone(String numeroDeTelephone) {
		this.numeroDeTelephone = numeroDeTelephone;
	}

	public Moderateur(String pseudo, String motDePasse, String email, String numeroDeTelephone) {
		super(pseudo, motDePasse, email);
		this.numeroDeTelephone = numeroDeTelephone;
	}

	public Moderateur(String pseudo, String motDePasse, String email) {
		super(pseudo, motDePasse, email);
	}

	@Override
	public String toString() {
		return "Moderateur [numeroDeTelephone=" + numeroDeTelephone + "]";
	}
	
	
}
