/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.repository;

import bni.nasabah.model.PasswordResetToken;
import bni.nasabah.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aisyah
 */
@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Integer>{
    @Query(value="SELECT * FROM password_reset_token WHERE token = ?1", nativeQuery = true)
    public PasswordResetToken findByToken(String token);
    
    @Modifying
    @Transactional
    @Query(value="UPDATE password_reset_token SET is_expired = 1 WHERE token = ?1", nativeQuery = true)
    void updateToken(String token);
    
}
