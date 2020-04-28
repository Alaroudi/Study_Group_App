//A class that stores the course type , course number, and section number. 
//Makes it easier to put all the info into a Collection

package Project.model;

import javafx.beans.property.SimpleStringProperty;

public class SearchTable {
    SimpleStringProperty courseType;
    SimpleStringProperty courseNumber; 
    SimpleStringProperty sectionNumber;

    public SearchTable(String courseType, String courseNumber, String sectionNumber){
            this.courseType = new SimpleStringProperty(courseType);
            this.courseNumber = new SimpleStringProperty(courseNumber);
            this.sectionNumber = new SimpleStringProperty(sectionNumber);
    }
    public String getCT(){
            return courseType.get();
    }
    public String getCN(){
            return courseNumber.get();
    }
    public String getSN(){
            return sectionNumber.get();
    }
    public final SimpleStringProperty CTProperty(){
            return courseType;
    }
    public final SimpleStringProperty CNProperty(){
            return courseNumber;
    }
    public final SimpleStringProperty SNProperty(){
            return sectionNumber;
    }
    
}
