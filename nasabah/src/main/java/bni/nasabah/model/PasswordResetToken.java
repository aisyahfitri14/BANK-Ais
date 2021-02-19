/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Aisyah
 */
@Entity
@Data
public class PasswordResetToken {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private Integer id;
 
    private String token;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_expired", nullable = false)
    private boolean isExpired;
 
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "email")
    private User user;
 
    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, boolean isExpired, User user) {
        this.token = token;
        this.isExpired = isExpired;
        this.user = user;
    }
    
    public boolean isIsExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }
    
}