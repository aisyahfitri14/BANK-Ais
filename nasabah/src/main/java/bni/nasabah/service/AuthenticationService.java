/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.service;

import java.util.ArrayList;
import java.util.List;
import bni.nasabah.model.*;
import bni.nasabah.model.data.*;
import bni.nasabah.repository.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aisyah
 */
@Service
public class AuthenticationService{

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private MyUserDetailService service;
    
    @Autowired
    private UserRepositories userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(AuthDataRequest request) {
        MyUserDetail user = (MyUserDetail) service.loadUserByUsername(request.getUsername());
        if (user.isAccountNonLocked()) {
            try {
                UsernamePasswordAuthenticationToken authReq
                        = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
                
                Authentication auth = authManager.authenticate(authReq);
                
                SecurityContextHolder.getContext().setAuthentication(auth);
                return "success";
            } catch (AuthenticationException e) {
                return "failed";
            }
        }

        return "lock";
    }
    
    public AuthDataResponse createAuthenticationResponse() {
        List<String> authorities = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }

        return new AuthDataResponse(authorities);
    }
}