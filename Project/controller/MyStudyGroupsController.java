package Project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

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
	private TableView<String> tbl;
	
	@FXML
	private TableColumn<String, String> courseInfo;
	
	@FXML
	private TableColumn<String, String> day;
	
	@FXML
	private TableColumn<String, String> time;
	
	@FXML
	private TableColumn<String, String> location;
	
	@FXML
	private TableColumn<String, String> contact;
	
	@FXML
	private TableColumn<String, String> seats;
	
	@FXML
	private FontAwesomeIcon back;
	
	
	private Scanner mainScanner;
    private Scanner lineScanner;
	
    //the data that will be displayed in tableview
    ObservableList<String> list = FXCollections.observableArrayList();
    
    
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
	            mainScanner = new Scanner(new File("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\groupinfo.txt"));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }


	        //while theres a line to scan within the file
	        while (mainScanner.hasNextLine()) 
	        {
	        	list.add(mainScanner.next());  
	        }
	     
	            mainScanner.close();
	     }
	
	//returns a user back to the main stage after clicking back buttton
	@FXML
    private void goBack(MouseEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/Project/view/Signup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        
        Stage oldStage = (Stage) back.getScene().getWindow();
        oldStage.close();
    }


	    
	 

}
