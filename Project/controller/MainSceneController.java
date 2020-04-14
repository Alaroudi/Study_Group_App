/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alaro
 */
public class MainSceneController extends LoginController implements Initializable {


    @FXML
    private Text username_mainscene;
    @FXML
    private JFXButton findStudyGroupButton;
    @FXML
    private SplitPane findStudyGroup;
    @FXML
    private JFXButton createStudyGroupButton;
    @FXML
    private JFXButton findStudyMaterialsButton;
    @FXML
    private JFXButton settingsButtons;
    @FXML
    private BorderPane Settings;
    @FXML
    private BorderPane createStudyGroup;
    @FXML
    private BorderPane findStudyMaterials;
    @FXML
    private Button logoutButton;
   
    
    
    
    
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        System.out.println(user.getFirstname());
       
        username_mainscene.setText(user.getFirstname() + " " + user.getLasttname());
    }    



    @FXML
    private void handleButtons(ActionEvent event) {
        if (event.getSource() == findStudyGroupButton)
        {
            findStudyGroup.toFront();
           
             
        }
        else if (event.getSource() == createStudyGroupButton)
        {
            createStudyGroup.toFront();
            
        }
         else if (event.getSource() == findStudyMaterialsButton)
        {
            findStudyMaterials.toFront();
            
        }
         else if (event.getSource() == settingsButtons)
        {
            Settings.toFront();
            
        }
    }

    @FXML
    private void Logout(ActionEvent event) {
         Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
    
  
 
   
    
    
}
