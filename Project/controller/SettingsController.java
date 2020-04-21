package Project.controller;

import Project.Main;
import Project.model.UserInfo;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;

import javafx.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SettingsController extends MainSceneController implements Initializable {
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private JFXTextField lastNameField;

    @FXML
    private JFXTextField firstNameField;

    @FXML
    private SplitPane Settings;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       firstNameField.setPromptText("First Name");
       lastNameField.setPromptText("Last Name");
       usernameField.setPromptText("Username");
       passwordField.setPromptText("Password");
    }

    @FXML
    void saveSettings(ActionEvent event) {
        Scanner scan;
        String newFirstName = firstNameField.getText();
        if(newFirstName.contentEquals("")) {
            newFirstName = user.getFirstname();
        }
        String newLastName = lastNameField.getText();
        if(newLastName.contentEquals("")) {
            newLastName = user.getLasttname();
        }
        String newUsername = usernameField.getText();
        if(newUsername.contentEquals("")) {
            newUsername = user.getUsername();
        }
        String newPassword = passwordField.getText();
        if(newPassword.contentEquals("")) {
            newPassword = user.getPassword();
        }
        UserInfo changedUser = new UserInfo();
        changedUser.setUserInfo(newFirstName, newLastName, user.getBirthDate(), newUsername, newPassword);

        try{
            File oldFile = new File("Project/model/Users.txt");
            scan = new Scanner(oldFile);
            scan.useDelimiter("[,\n]");

            File tmpFile = new File("Project/model/tmpUsers.txt");
            FileWriter fileWriter = new FileWriter("Project/model/tmpUsers.txt",false);
            BufferedWriter print = new BufferedWriter(fileWriter);

            UserInfo tmpUser = new UserInfo();
            while(scan.hasNext())
            {
                tmpUser.setUserInfo(scan.next(), scan.next(), scan.next(), scan.next(), scan.next());
                if (tmpUser.getUsername().equals(user.getUsername()) && tmpUser.getPassword().equals(user.getPassword()))
                {
                    print.write(newFirstName+ "," + newLastName + ","
                            + user.getBirthDate() + "," +  newUsername + ","
                            + newPassword +"\n");
                }
                else {
                    print.write(tmpUser.getFirstname() + "," + tmpUser.getLasttname() + ","
                            + tmpUser.getBirthDate() + "," + tmpUser.getUsername() + ","
                            + tmpUser.getPassword() + "\n");
                }
            }

            
            oldFile.delete();
            File newFile = new File("Project/model/Users.txt");
            tmpFile.renameTo(newFile);
            print.flush();
            print.close();

            firstNameField.clear();
            lastNameField.clear();
            usernameField.clear();
            passwordField.clear();
        }catch (FileNotFoundException e){
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
