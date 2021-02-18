/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.service;

import bni.nasabah.model.PasswordResetToken;
import bni.nasabah.repository.PasswordTokenRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aisyah
 */
@Service
public class SecurityUserService {
    @Autowired
    private PasswordTokenRepository passwordTokenRepository;
    
    public PasswordResetToken getData(String token){
        return passwordTokenRepository.findByToken(token);
    }
    
    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken" : "validToken";
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }
}
