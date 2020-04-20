/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.controller;

import Project.model.UserInfo;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




/**
 *
 * @author alaro
 */
public class LoginController implements Initializable  {

    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXCheckBox show;
    @FXML
    private FontAwesomeIcon closeButton;
    @FXML
    private JFXTextField username_email;
    @FXML
    private Text wrongSigns;
    
    protected static UserInfo user = new UserInfo();

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }   
  
    
     

    @FXML
    private void signIn(ActionEvent event) throws IOException {
         
        boolean x = verifyLogin(username_email.getText(), password.getText());
        
        if (x == true)
        {
            wrongSigns.setVisible(false);
            Parent root = FXMLLoader.load(getClass().getResource("/Project/view/MainScene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
                    
        }
        else
        {
            wrongSigns.setVisible(true);
        }   
        
    }

   
    @FXML
    private void showPass(ActionEvent event) {
        
       
        if (show.isSelected() && !password.getText().equals(""))
        {
            String str = password.getText();
            password.setPromptText(str); 
        }
        else
        {
            password.setPromptText("Password");
            
        }
        
    }

    @FXML
    private void Signup(ActionEvent event) throws IOException {
        
              
        Parent root = FXMLLoader.load(getClass().getResource("/Project/view/Signup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    private void Close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    
   
    

    private boolean verifyLogin(String username, String password)
    {
        
        Scanner scan;
        
        
        try{
            File file = new File("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\Users.txt");
            scan = new Scanner(file);
            scan.useDelimiter("[,\n]");
                
        while(scan.hasNext())
        {   
            user.setUserInfo(scan.next(), scan.next(), scan.next(), scan.next(), scan.next());
            
            if (username.equals(user.getUsername()) && password.equals(user.getPassword()))
            {
                return true;
                
            }
            
        }
                
        }catch (FileNotFoundException e){
            System.out.println(e);
        }

        
        return false;
        
        
    }

  
    
  
    
    


    
   
    

   
    
    
    
    
}
