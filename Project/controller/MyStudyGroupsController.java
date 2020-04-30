package Project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import Project.model.studyGroup;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.beans.property.SimpleStringProperty;
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
	private TableView<studyGroup> tbl;
	
	@FXML
	private TableColumn<studyGroup, String> courseInfo;
	
	@FXML
	private TableColumn<studyGroup, String> day;
	
	@FXML
	private TableColumn<studyGroup, String> time;
	
	@FXML
	private TableColumn<studyGroup, String> location;
	
	@FXML
	private TableColumn<studyGroup, String> contact;
	
	@FXML
	private TableColumn<studyGroup, String> seats;
	
	
	Scanner mainScanner;
	
    //the data that will be displayed in tableview
    ObservableList<studyGroup> list = FXCollections.observableArrayList();
    
    
	@Override
	    public void initialize(URL arg0, ResourceBundle arg1) 
	{       //when the FXML Loads

	        //set the data onto the tableview
	        courseInfo.setCellValueFactory(new PropertyValueFactory<>("CI"));
	        day.setCellValueFactory(new PropertyValueFactory<>("MD"));
	        time.setCellValueFactory(new PropertyValueFactory<>("MT"));
	        location.setCellValueFactory(new PropertyValueFactory<>("ML"));
	        contact.setCellValueFactory(new PropertyValueFactory<>("C"));
	        seats.setCellValueFactory(new PropertyValueFactory<>("SA"));
	        tbl.setItems(list);

	        //scan in data from the groupinfo.txt file
	        try {
	            mainScanner = new Scanner(new File("C:\\Users\\guaco\\Downloads\\Study_Group_App-master\\Study_Group_App\\Project\\model\\groupinfo.txt"));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }


	        //while theres a line to scan within the file
	        while (mainScanner.hasNextLine()) 
	        {	
	        	//add only the groups belonging to the specific user
	        	if(mainScanner.next() == user.getUsername())
	        	{
		        	String course = mainScanner.next() + mainScanner.next() + " " + mainScanner.next();
		        	list.add(new studyGroup(course, mainScanner.next(), mainScanner.next(), mainScanner.next(), mainScanner.next(), mainScanner.next()));
	        	}
	        	mainScanner.nextLine();
	        }
	        
	        //close the scanner
	            mainScanner.close();

	     }
	
	    
	 

}

