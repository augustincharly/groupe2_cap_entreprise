package com.humanbooster.groupe2_cap_entreprise.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        //CustomUserDetailsService userDetails = (CustomUserDetailsService) authentication.getPrincipal();
    	authentication = SecurityContextHolder.getContext().getAuthentication();
    	String role = authentication.getAuthorities().toString();
    	System.out.println(role);
    	 String redirectURL = request.getContextPath();

       if(role.contains("ROLE_JOUEUR")) {
    	   redirectURL = "/joueur/avis";
    	   System.out.println("Role joueur reconnu");
       }else if(role.contains("ROLE_MODERATEUR")) {
    	   redirectURL = "/moderateur/avis";
    	   System.out.println("Role moderateur reconnu");
       }
       System.out.println("Role non reconnu");
       
         
//        if (userDetails.hasRole("JOUEUR")) {
//            redirectURL = "/joueur/avis";
//        }else if (userDetails.hasRole("MODERATEUR")) {
//            redirectURL = "/moderateur/avis";
//        }
         
        response.sendRedirect(redirectURL);
         
    }
 
}