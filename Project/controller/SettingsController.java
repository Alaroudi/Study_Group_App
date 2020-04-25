package Project.controller;

import Project.model.UserInfo;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * SettingsController class controls the Settings.fxml scene
 *
 * @autor Alana Quinones Garcia - ded687
 */
public class SettingsController extends MainSceneController implements Initializable {
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private JFXTextField lastNameField;
    @FXML
    private JFXTextField firstNameField;

    /**
     * initialize(URL url, ResourceBundle rb) initializes the Settings.fxml scene
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");
    }

    /**
     * saveSettings(ActionEvent event) changes a user's settings when they click on 'save changes' button
     *
     * @param event
     */
    @FXML
    public void saveSettings(ActionEvent event) {
        // handle empty input
        String newFirstName = firstNameField.getText();
        if (newFirstName.contentEquals("")) {
            newFirstName = user.getFirstname();
        }
        String newLastName = lastNameField.getText();
        if (newLastName.contentEquals("")) {
            newLastName = user.getLasttname();
        }
        String newUsername = usernameField.getText();
        if (newUsername.contentEquals("")) {
            newUsername = user.getUsername();
        }
        String newPassword = passwordField.getText();
        if (newPassword.contentEquals("")) {
            newPassword = user.getPassword();
        }

        // create user with changed info
        UserInfo changedUser = new UserInfo();
        changedUser.setUserInfo(newFirstName, newLastName, user.getBirthDate(), newUsername, newPassword);
        try {
            // read from old user info file
            File oldFile = new File("Project/model/Users.txt");
            Scanner scan;
            scan = new Scanner(oldFile);
            scan.useDelimiter("[,\n]");

            // create tmp file
            File tmpFile = new File("Project/model/tmpUsers.txt");
            FileWriter fileWriter = new FileWriter("Project/model/tmpUsers.txt", false);
            BufferedWriter print = new BufferedWriter(fileWriter);

            // read from old file into tmp file
            UserInfo tmpUser = new UserInfo();
            while (scan.hasNext()) {
                tmpUser.setUserInfo(scan.next(), scan.next(), scan.next(), scan.next(), scan.next());
                if (tmpUser.getUsername().equals(user.getUsername()) && tmpUser.getPassword().equals(user.getPassword())) {
                    print.write(newFirstName + "," + newLastName + ","
                            + user.getBirthDate() + "," + newUsername + ","
                            + newPassword + "\n");
                } else {
                    print.write(tmpUser.getFirstname() + "," + tmpUser.getLasttname() + ","
                            + tmpUser.getBirthDate() + "," + tmpUser.getUsername() + ","
                            + tmpUser.getPassword() + "\n");
                }
            }

            // close/flush everything
            scan.close();
            fileWriter.flush();
            print.flush();
            print.close();
            fileWriter.close();

            boolean isDeleted = oldFile.delete();
            File newFile = new File("Project/model/Users.txt");
            boolean isRenamed = tmpFile.renameTo(newFile);

            firstNameField.clear();
            lastNameField.clear();
            usernameField.clear();
            passwordField.clear();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
