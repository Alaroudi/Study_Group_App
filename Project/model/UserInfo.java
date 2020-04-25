/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.model;

/**
 * UserInfo class stores user information
 *
 * @author alaro
 */
public class UserInfo {

    private String firstname, lastname, birthDate, username, password;

    /**
     * setUserInfo (String firstname, String lasttname, String birthDate, String username, String password) sets the user information
     *
     * @param firstname
     * @param lasttname
     * @param birthDate
     * @param username
     * @param password
     */
    public void setUserInfo(String firstname, String lasttname, String birthDate, String username, String password) {

        this.firstname = firstname;
        this.lastname = lasttname;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;


    }

    /**
     * getFirstname () gets the user's first name
     *
     * @return user's first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * getLasttname() gets the user's last name
     *
     * @return user's last name
     */
    public String getLasttname() {
        return lastname;
    }

    /**
     * getBirthDate() gets the user's birth date
     *
     * @return user's birth date
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * getUsername() gets the user's username
     *
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getPassword() gets the user's password
     *
     * @return user's password
     */
    public String getPassword() {
        return password;
    }


}
