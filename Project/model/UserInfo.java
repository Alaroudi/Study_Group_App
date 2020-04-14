/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.model;

/**
 *
 * @author alaro
 */
public class UserInfo {
    
   private String firstname, lastname, birthDate, username, password;
   
   public void setUserInfo (String firstname, String lasttname, String birthDate, String username, String password){
      
       this.firstname = firstname;
       this.lastname = lasttname;
       this.birthDate = birthDate;
       this.username = username;
       this.password = password;
       
       
   }
   
   public String getFirstname (){
       return firstname;
   }
   public String getLasttname (){
       return lastname;
   }
   public String getBirthDate (){
       return birthDate;
   }
   public String getUsername (){
       return username;
   }
   public String getPassword (){
       return password;
   }
           
                   
    
}
