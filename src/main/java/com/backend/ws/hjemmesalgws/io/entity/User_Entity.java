package com.backend.ws.hjemmesalgws.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;

@Entity(name="users") //Bruges til at gnerere et "table" i db
public class User_Entity implements Serializable {

    private static final long serialVersionUID = -4345321339936059843L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String userId; //id der returneres til brugeren

    @Column(nullable=false, length=30)
    private String firstName;

    @Column(nullable=false, length=30)
    private String lastName;


    //@Column(nullable=false, length=80, unique=true) //Sætte column til at være unik
    @Column(nullable=false, length=80)
    private String email;

    @Column(nullable=false, length=6)
    private String gender;

    @Column(nullable=false, length=10)
    private String birthday;

    @Column(nullable=false, length=100)
    private String address;

    @Column(nullable=false, length=4)
    private String postalNr;

    @Column(nullable=false, length=20)
    private String phonenumber;

    @Column(nullable=false)
    private String encryptedPassword;

    //Evt. password ved fejl.

    private String emailVerificationToken;

    @Column(nullable=false)
    private boolean emailVerificationStatus = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalNr() {
        return postalNr;
    }

    public void setPostalNr(String postalNr) {
        this.postalNr = postalNr;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
}
