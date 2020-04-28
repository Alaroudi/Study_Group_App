//A class that stores the course type , course number, and section number. 
//Makes it easier to put all the info into a Collection

package Project.model;

import javafx.beans.property.SimpleStringProperty;

public class SearchTable {
    SimpleStringProperty coursetype;
    SimpleStringProperty coursenumber; 
    SimpleStringProperty sectionnumber;

    public SearchTable(String coursetype, String coursenumber, String sectionnumber){
            this.coursetype = new SimpleStringProperty(coursetype);
            this.coursenumber = new SimpleStringProperty(coursenumber);
            this.sectionnumber = new SimpleStringProperty(sectionnumber);
    }
    public String getCT(){
            return coursetype.get();
    }
    public String getCN(){
            return coursenumber.get();
    }
    public String getSN(){
            return sectionnumber.get();
    }
    public final SimpleStringProperty CTProperty(){
            return coursetype;
    }
    public final SimpleStringProperty CNProperty(){
            return coursenumber;
    }
    public final SimpleStringProperty SNProperty(){
            return sectionnumber;
    }
    
}
