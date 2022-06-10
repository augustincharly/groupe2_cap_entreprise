package com.humanbooster.groupe2_cap_entreprise.formwrapper;

public class AvisJeuFormWrapper {
	
	private Long jeu_id;
	private String avis_description;
	private Float avis_note;
	
	public Long getJeu_id() {
		return jeu_id;
	}
	public void setJeu_id(Long jeu_id) {
		this.jeu_id = jeu_id;
	}


	
	public Float getAvis_note() {
		return avis_note;
	}
	public void setAvis_note(Float avis_note) {
		this.avis_note = avis_note;
	}
	public String getAvis_description() {
		return avis_description;
	}
	public void setAvis_description(String avis_description) {
		this.avis_description = avis_description;
	}
	
	
}
