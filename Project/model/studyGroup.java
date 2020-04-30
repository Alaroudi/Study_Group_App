package Project.model;

import javafx.beans.property.SimpleStringProperty;

public class studyGroup{
	
	 SimpleStringProperty course;
	 SimpleStringProperty meetDay;
	 SimpleStringProperty meetTime;
	 SimpleStringProperty meetLocation;
	 SimpleStringProperty meetContact;
	 SimpleStringProperty seats;

	 public studyGroup(String course, String day, String time, String loc, String contact, String seats){
		 this.course = new SimpleStringProperty(course);
		 this.meetDay = new SimpleStringProperty(day);
		 this.meetTime = new SimpleStringProperty(time);
		 this.meetLocation = new SimpleStringProperty(loc);
		 this.meetContact = new SimpleStringProperty(contact);
		 this.seats = new SimpleStringProperty(seats);
	 }

	 
	
}

