/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.model.data;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Aisyah
 */
@Data
public class AuthDataResponse {
    private List<String> authorities;

    public AuthDataResponse(List<String> authorities) {
        this.authorities = authorities;
    }

    public AuthDataResponse() {
    }
}
