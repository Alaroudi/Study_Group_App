/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    private JFXButton createStudyGroupButton;
    @FXML
    private JFXButton settingsButtons;
    @FXML
    private Button logoutButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private JFXButton myStudyGroupsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO

        username_mainscene.setText(user.getFirstname() + " " + user.getLasttname());

        try {
            SplitPane root = (SplitPane)FXMLLoader.load(getClass().getResource("/Project/view/FindStudyGroups.fxml"));
            mainPane.setCenter(root);   
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   
        
    }

    @FXML
    private void handleButtons(ActionEvent event) throws IOException {
        if (event.getSource() ==  findStudyGroupButton){
            
            SplitPane root = (SplitPane)FXMLLoader.load(getClass().getResource("/Project/view/FindStudyGroups.fxml"));
            mainPane.setCenter(root);   
        }
        else if (event.getSource() == createStudyGroupButton){
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Project/view/CreateGroupView.fxml"));
            mainPane.setCenter(root);


        }
        else if (event.getSource() == myStudyGroupsButton){
            
            
            
        }
        else if(event.getSource() == settingsButtons){
            Pane root = (Pane)FXMLLoader.load(getClass().getResource("/Project/view/Settings.fxml"));
            mainPane.setCenter(root);
        }
  
    }

    @FXML
    private void Logout(ActionEvent event) {
         Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
    
    
}
