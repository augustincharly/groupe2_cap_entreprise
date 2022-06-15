package com.humanbooster.groupe2_cap_entreprise.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.humanbooster.groupe2_cap_entreprise.entity.Joueur;
import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;
import com.humanbooster.groupe2_cap_entreprise.entity.Utilisateur;
import com.humanbooster.groupe2_cap_entreprise.repository.UtilisateurRepository;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
//	@Autowired
//	private Utilisateur utilisateur;
	
//    public CustomUserDetailsService(Utilisateur utilisateurToInject) {
//        this.utilisateur = utilisateurToInject;
//    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur= utilisateurRepository.findByPseudo(username);
				
		if(utilisateur==null) {
			throw new UsernameNotFoundException(username+ " not found" );
		}
		
		User userDetails = new User(utilisateur.getPseudo(), utilisateur.getMotDePasse(), getGrantedAuthorities(utilisateur));
		
		
		return userDetails;
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(Utilisateur utilisateur) {
		List<GrantedAuthority> authorities= new ArrayList<>();
		if(utilisateur instanceof Joueur) {
			authorities.add(new SimpleGrantedAuthority("ROLE_JOUEUR"));
		}else if (utilisateur instanceof Moderateur) {
			authorities.add(new SimpleGrantedAuthority("ROLE_MODERATEUR"));
		}		
		
		return authorities;
	}

	public String getUsername() {
		return getUsername();
	}
	
//    public boolean hasRole(String roleName) {
//    	if(roleName.equals("JOUEUR")) {
//    		return (utilisateur instanceof Joueur) ? true : false; 
//    	}else if(roleName.equals("MODERATEUR")) {
//    		return (utilisateur instanceof Moderateur) ? true : false; 
//    	}
//		return false;
//    }
	
//	  public boolean hasRole(String roleName) {
//		if(roleName.equals("JOUEUR")) {
//			return ( user instanceof Joueur) ? true : false; 
//		}else if(roleName.equals("MODERATEUR")) {
//			return (user instanceof Moderateur) ? true : false; 
//		}
//		return false;
//}

    
    


    
}
