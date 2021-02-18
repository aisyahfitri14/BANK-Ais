/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.model;

import javax.validation.Valid;
import lombok.Data;

/**
 *
 * @author Aisyah
 */
@Data
public class PasswordDto {
    private String oldPassword, confirm;

    private  String token;

    @Valid
    private String newPassword;

    public PasswordDto() {
    }

    public PasswordDto(String oldPassword, String confirm, String token, String newPassword) {
        this.oldPassword = oldPassword;
        this.confirm = confirm;
        this.token = token;
        this.newPassword = newPassword;
    }

    
    
    
}
