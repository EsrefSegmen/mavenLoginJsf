/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.es.phdcentral.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tech
 */
@Entity
@Table(name = "tbluser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbluser.findAll", query = "SELECT t FROM Tbluser t"),
    @NamedQuery(name = "Tbluser.findByIdtblUser", query = "SELECT t FROM Tbluser t WHERE t.idtblUser = :idtblUser"),
    @NamedQuery(name = "Tbluser.findByName", query = "SELECT t FROM Tbluser t WHERE t.name = :name"),
    @NamedQuery(name = "Tbluser.findBySurname", query = "SELECT t FROM Tbluser t WHERE t.surname = :surname"),
    @NamedQuery(name = "Tbluser.findByUserName", query = "SELECT t FROM Tbluser t WHERE t.userName = :userName"),
    @NamedQuery(name = "Tbluser.findByPassword", query = "SELECT t FROM Tbluser t WHERE t.password = :password"),
    @NamedQuery(name = "Tbluser.findByEmail", query = "SELECT t FROM Tbluser t WHERE t.email = :email"),
    @NamedQuery(name = "Tbluser.findByClassId", query = "SELECT t FROM Tbluser t WHERE t.classId = :classId"),
    @NamedQuery(name = "Tbluser.findByIsActive", query = "SELECT t FROM Tbluser t WHERE t.isActive = :isActive"),
    @NamedQuery(name = "Tbluser.findByUserType", query = "SELECT t FROM Tbluser t WHERE t.userType = :userType")})
public class Tbluser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tblUser")
    private Long idtblUser;
    @Size(max = 60)
    @Column(name = "name")
    private String name;
    @Size(max = 60)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "email")
    private String email;
    @Column(name = "classId")
    private Integer classId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isActive")
    private int isActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userType")
    private int userType;

    public Tbluser() {
    }

    public Tbluser(Long idtblUser) {
        this.idtblUser = idtblUser;
    }

    public Tbluser(Long idtblUser, String userName, String password, String email, int isActive, int userType) {
        this.idtblUser = idtblUser;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.userType = userType;
    }

    public Long getIdtblUser() {
        return idtblUser;
    }

    public void setIdtblUser(Long idtblUser) {
        this.idtblUser = idtblUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblUser != null ? idtblUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbluser)) {
            return false;
        }
        Tbluser other = (Tbluser) object;
        if ((this.idtblUser == null && other.idtblUser != null) || (this.idtblUser != null && !this.idtblUser.equals(other.idtblUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.es.phdcentral.beans.Tbluser[ idtblUser=" + idtblUser + " ]";
    }
    
}
