package Project.controller;

import Project.model.UserStudyGroups;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;

/**
 * MyStudyGroupsController class controls the MyStudyGroups.fxml scene
 *
 * @author Simon Rendon
 */

public class MyStudyGroupsController extends LoginController implements Initializable{

	@FXML
	private AnchorPane rootAnchor;
	
	@FXML
	private TableView<UserStudyGroups> tbl;
	
	@FXML
	private TableColumn<UserStudyGroups, String> courseInfo;
	
	@FXML
	private TableColumn<UserStudyGroups, String> day;
	
	@FXML
	private TableColumn<UserStudyGroups, String> time;
	
	@FXML
	private TableColumn<UserStudyGroups, String> location;
	
	@FXML
	private TableColumn<UserStudyGroups, String> contact;
	
	@FXML
	private TableColumn<UserStudyGroups, String> seats;
	
	
	
	private Scanner mainScanner;
        
	
        //the data that will be displayed in tableview
        ObservableList<UserStudyGroups> list = FXCollections.observableArrayList();
     
    
	@Override
	    public void initialize(URL arg0, ResourceBundle arg1) 
	{      //when the FXML Loads

	        //set the data onto the tableview
	        courseInfo.setCellValueFactory(new PropertyValueFactory<>("CT"));
	        day.setCellValueFactory(new PropertyValueFactory<>("MD"));
	        time.setCellValueFactory(new PropertyValueFactory<>("MT"));
	        location.setCellValueFactory(new PropertyValueFactory<>("ML"));
	        contact.setCellValueFactory(new PropertyValueFactory<>("C"));
	        seats.setCellValueFactory(new PropertyValueFactory<>("AS"));

	        tbl.setItems(list);
	        

	        //scan in data from the groupinfo.txt file
	        try {
	            mainScanner = new Scanner(new File("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\groupinfo.txt"));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }


	        //while theres a line to scan within the file
	       //while theres a line to scan within the file
	        while (mainScanner.hasNextLine()) 
	        {	
	        	//add only the groups belonging to the specific user
	        	if(mainScanner.next().equals(user.getUsername()))
	        	{
		        	String course = mainScanner.next() + mainScanner.next() + " " + mainScanner.next();
		        	list.add(new UserStudyGroups(course, mainScanner.next(), mainScanner.next(), mainScanner.next(), mainScanner.next(), mainScanner.next()));
	        	}
	        	mainScanner.nextLine();
	        }
	        
	        //close the scanner
	            mainScanner.close();
	     }
	

	    
	 

}
