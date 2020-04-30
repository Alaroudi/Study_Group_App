/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author alaro
 */
public class UserStudyGroups {
    SimpleStringProperty  courseType,  meetDay,  meetTime, 
                          meetLocation,  contact,  availabeSeats;


    public UserStudyGroups(String courseType, String meetDay, String meetTime,String meetLocation, String contact, String availabeSeats){
            this.courseType = new SimpleStringProperty(courseType);
            this.meetDay = new SimpleStringProperty(meetDay);
            this.meetTime = new SimpleStringProperty(meetTime);
            this.meetLocation = new SimpleStringProperty(meetLocation);
            this.contact = new SimpleStringProperty(contact);
            this.availabeSeats = new SimpleStringProperty(availabeSeats);
    }
    
    public String getCT(){
            return courseType.get();
    }
    public String getMD(){
            return meetDay.get();
    }
    public String getMT(){
            return meetTime.get();
    }
    public String getML(){
            return meetLocation.get();
    }
    public String getC(){
            return contact.get();
    }
    public String getAS(){
            return availabeSeats.get();
    }
    public final SimpleStringProperty CTProperty(){
            return courseType;
    }
    public final SimpleStringProperty MDProperty(){
            return meetDay;
    }
    public final SimpleStringProperty MTProperty(){
            return meetTime;
    }
    public final SimpleStringProperty MLProperty(){
            return meetLocation;
    }
    public final SimpleStringProperty CProperty(){
            return contact;
    }
    public final SimpleStringProperty ASProperty(){
            return availabeSeats;
    }
    
}
