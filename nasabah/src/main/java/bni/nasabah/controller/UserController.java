/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.controller;

import bni.nasabah.service.UserService;
import java.util.List;
import bni.nasabah.model.*;
import bni.nasabah.model.data.*;
import bni.nasabah.service.*;
import bni.nasabah.service.NotificationMailer;
import java.security.SecureRandom;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

/**
 *
 * @author Aisyah
 */
@RestController
@RequestMapping("api")
public class UserController {
    private UserService service;
    private Mailer mail;
    
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
    
    @Autowired
    NotificationMailer notification;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private Helper random;
    
    @Autowired
    private SecurityUserService securityUserService;

    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<User> getAll(){
        return service.getAllData();
    } 
    
    @GetMapping("search")
    public User byId(@RequestBody User user){
        Integer id = user.getId();
        return service.getDataById(id);
    }
    
    @PostMapping
    public newUser createUser(@RequestBody User user){
        try{
            user.setPassword(random.randomString());
            User data = service.inserUser(user);
            User getUser = service.getDataByEmail(data.getEmail());
            newUser passId = new newUser(getUser.getId(), user.getPassword());
            
//            Mailer mail = new Mailer();
//            mail.setTo(user.getEmail());
//            mail.setSubject("New Account");
//            
//            Context context = new Context();
//            context.setVariable("title","Information New User Account");
//            context.setVariable("id", getUser.getId());
//            context.setVariable("account",user.getNamaLengkap());
//            context.setVariable("password",user.getPassword());
//            
//            notification.sendEmail(mail,null,context,"email/NotifAccount");
            
            return passId;
        }
        catch(Exception ex){
            return null;
        }
    }
    
    @GetMapping("reset-password")
    public String resetPassword(Authentication auth) {
        User user = service.getDataByEmail(auth.getName());
        String token = UUID.randomUUID().toString();
        service.createPasswordResetTokenForUser(user, token);
        System.out.println(token);
        
//        Mailer mail = new Mailer();
//        mail.setTo(user.getEmail());
//        mail.setSubject("Reset Account Password");
//        
//        Context context = new Context();
//        context.setVariable("title","Reset password");
//        context.setVariable("token", token);
//         
//        notification.sendEmail(mail,null,context,"email/ResetAccount");
        
        return token;
    }
    
    @PostMapping("save-password")
    public ResponseMessage<User> updatePassword(@RequestBody PasswordDto passwordDto, Authentication auth){
        String result = securityUserService.validatePasswordResetToken(passwordDto.getToken());
                
        if (result == "validToken") {
            PasswordResetToken data = securityUserService.getData(passwordDto.getToken());
                        
            if (data.isIsExpired()) {
                return new ResponseMessage(null, "Token is expired");
            }
            else {
                if (auth.getName().equals(data.getUser().getEmail())) {
                    User user = service.getDataByEmail(data.getUser().getEmail());
                    if (passwordDto.getNewPassword().equals(passwordDto.getConfirm())) {
                        service.changeUserPassword(user, passwordDto.getNewPassword(), passwordDto.getToken());
                        return new ResponseMessage(null, "Success"); 
                    }
                    else {
                        return new ResponseMessage(null, "Password do not match"); 
                    }
                }
                else {
                    return new ResponseMessage(null, "Wrong token");
                }
            }
        }
        else {
            return new ResponseMessage(null, "Invalid token");
        }
    }
    
    @DeleteMapping()
    public String deleteUser(@RequestBody User user){
        try {
            service.deleteUser(user.getId());
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }
}
