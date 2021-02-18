/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Aisyah
 */
@Entity
@Table(name = "user", catalog = "bni", schema = "")
@Data
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByNamaLengkap", query = "SELECT u FROM User u WHERE u.namaLengkap = :namaLengkap")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByAlamat", query = "SELECT u FROM User u WHERE u.alamat = :alamat")
    , @NamedQuery(name = "User.findByTempatLahir", query = "SELECT u FROM User u WHERE u.tempatLahir = :tempatLahir")
    , @NamedQuery(name = "User.findByTglLahir", query = "SELECT u FROM User u WHERE u.tglLahir = :tglLahir")
    , @NamedQuery(name = "User.findByNik", query = "SELECT u FROM User u WHERE u.nik = :nik")
    , @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByStatus", query = "SELECT u FROM User u WHERE u.status = :status")
    , @NamedQuery(name = "User.findByIsVerified", query = "SELECT u FROM User u WHERE u.isVerified = :isVerified")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nama_lengkap", nullable = false, length = 250)
    private String namaLengkap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "password", nullable = false, length = 250)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "alamat", nullable = false, length = 250)
    private String alamat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "tempat_lahir", nullable = false, length = 250)
    private String tempatLahir;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tgl_lahir", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tglLahir;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nik", nullable = false)
    private int nik;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "email", nullable = false, length = 250)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status", nullable = false)
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<PasswordResetToken> passwordResetTokenList;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }
    
    public User(String password) {
        this.password = password;
    }

    public User(Integer id, String namaLengkap, String password, String alamat, String tempatLahir, Date tglLahir, int nik, String phone, String email, int status, boolean isVerified) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.password = password;
        this.alamat = alamat;
        this.tempatLahir = tempatLahir;
        this.tglLahir = tglLahir;
        this.nik = nik;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.isVerified = isVerified;
    }
    
    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bni.nasabah.model.User[ id=" + id + " ]";
    }
    
}
