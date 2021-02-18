/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.model;

import lombok.Data;

/**
 *
 * @author Aisyah
 */
@Data
public class Mailer {
    private String to;
    private String urlVerify;
    private String subject;
    private String cc;
    private String bcc;

    public Mailer(){
        
    }

    public Mailer(String to, String subject) {
        this.to = to;
        this.subject = subject;
    }
    
    
}
