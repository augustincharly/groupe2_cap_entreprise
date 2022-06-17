package com.humanbooster.groupe2_cap_entreprise.formwrapper;

public class FilterWrapper {

	private Long jeu_id;
	private Long joueur_id;
	private Boolean moderated;

	public Long getJeu_id() {
		return jeu_id;
	}

	public void setJeu_id(Long jeu_id) {
		this.jeu_id = jeu_id;
	}

	public Long getJoueur_id() {
		return joueur_id;
	}

	public void setJoueur_id(Long joueur_id) {
		this.joueur_id = joueur_id;
	}

	public FilterWrapper() {
		super();
	}

	public Boolean getModerated() {
		return moderated;
	}

	public void setModerated(Boolean moderated) {
		this.moderated = moderated;
	}

	@Override
	public String toString() {
		return "FilterWrapper [jeu_id=" + jeu_id + ", joueur_id=" + joueur_id + ", moderated=" + moderated + "]";
	}

}
