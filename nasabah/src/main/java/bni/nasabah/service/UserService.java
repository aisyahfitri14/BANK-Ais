/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.service;

import bni.nasabah.repository.UserRepositories;
import java.util.List;
import bni.nasabah.model.*;
import bni.nasabah.repository.*;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aisyah
 */
@Service
public class UserService {
    @Autowired
    private UserRepositories repo;
    
    @Autowired
    private PasswordTokenRepository passwordTokenRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
           
    public List<User> getAllData() {
        return repo.findAll();
    }
    
    public User getDataById(Integer id){
        return repo.userByID(id);
    }
    
    public User getDataByEmail(String email){
        return repo.userByEmail(email);
    }
    
    public User inserUser(User user){
        
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        
        repo.insertNasabah(user.getNamaLengkap(), password, user.getAlamat(), user.getTempatLahir(), 
                user.getTglLahir(), user.getNik(), user.getPhone(), user.getEmail());
        
        return user;
    }
    
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }
    
    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        repo.save(user);
    }
    
    public String userPassword(String email){
        return repo.userPassword(email);
    }
    
    public void deleteUser(Integer id){
        repo.deleteUser(id);
        
    }
}
