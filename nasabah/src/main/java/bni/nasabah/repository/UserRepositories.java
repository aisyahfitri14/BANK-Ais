/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.repository;

import bni.nasabah.model.*;
import java.util.Date;
import java.util.Optional;
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
public interface UserRepositories extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
    
    @Query(value="SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    public User userByEmail(String email);
    
    @Query(value="SELECT * FROM user WHERE id = ?1", nativeQuery = true)
    public User userByID(Integer id);
    
    @Query(value="SELECT password FROM user WHERE email = ?1", nativeQuery = true)
    public String userPassword(String email);
    
    @Modifying
    @Transactional
    @Query(value="INSERT INTO `user`(`nama_lengkap`, `password`, `alamat`, `tempat_lahir`, `tgl_lahir`, `nik`, `phone`, `email`, `status`, `is_verified`) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,0,1)", nativeQuery = true)
    void insertNasabah(String namaLengkap, String password, String alamat, String tempatLahir, Date tglLahir, Integer nik, String phone, String email);
    
    @Modifying
    @Transactional
    @Query(value="DELETE FROM `user` WHERE id = ?1", nativeQuery = true)
    void deleteUser(Integer id);
}
