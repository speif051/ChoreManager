package com.bestteam.choremanager;

/**
 * Created by frank on 2017-11-29.
 */

public class User {
    private String firstname;
    private String lastname;

    public User(){

    }

    public User(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    public String getName() { return firstname + " " + lastname; }
}
