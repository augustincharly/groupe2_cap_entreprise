package com.humanbooster.groupe2_cap_entreprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.humanbooster.groupe2_cap_entreprise.controller"})
@SpringBootApplication
public class Groupe2CapEntrepriseApplication {

	public static void main(String[] args) {
		SpringApplication.run(Groupe2CapEntrepriseApplication.class, args);
	}

}

