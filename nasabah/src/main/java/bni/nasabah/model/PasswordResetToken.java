/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Aisyah
 */
@Entity
@Data
public class PasswordResetToken {
 
    private static final int EXPIRATION = 60 * 24;
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private Integer id;
 
    private String token;
 
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "email")
    private User user;
 
    private Date expiryDate;

    public PasswordResetToken() {
    }
    
    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}