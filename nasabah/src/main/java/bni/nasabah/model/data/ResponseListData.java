/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.model.data;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author Aisyah
 * @param <E> Entity data
 */
@Getter
public class ResponseListData <E> {
    
    private List<E> data;

    public ResponseListData(List<E> data) {
        this.data = data;
    }
}
