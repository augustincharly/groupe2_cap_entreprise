package com.humanbooster.groupe2_cap_entreprise.transformer;

import org.springframework.stereotype.Component;

@Component
public class TransformerFactory {

	public static AvisTransformer getAvisTransformer() {
		return new AvisTransformer();
	}

	public static JeuTransformer getJeuTransformer() {
		return new JeuTransformer();
	}

}
