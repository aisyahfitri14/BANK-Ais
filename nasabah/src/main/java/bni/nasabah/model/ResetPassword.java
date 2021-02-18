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
public class ResetPassword {
    private String password, confirm, token;

    public ResetPassword() {
    }

    public ResetPassword(String password, String confirm, String token) {
        this.password = password;
        this.confirm = confirm;
        this.token = token;
    }
}
