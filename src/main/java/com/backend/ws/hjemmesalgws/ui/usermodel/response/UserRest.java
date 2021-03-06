package com.backend.ws.hjemmesalgws.ui.usermodel.response;

//klassen skal ikke returnere password, da dette kan opsnappes af andre. Men det kan overveejes om alle andre ting fra db skal.
public class UserRest {
    private String userId; //Skal ikke være databaseID, da en "fremmed" der opsnapper beskeden, så kan finde de resterende data.
    //Skal være tilfældigt genereret
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String birthday;
    private String address;
    private String postalNr;
    private String phonenumber;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

}
