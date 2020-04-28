package Project.controller;

import Project.model.UserInfo;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.scene.control.Label;

public class SettingsController extends LoginController implements Initializable {
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField lastNameField;

    @FXML
    private JFXTextField firstNameField;

    @FXML
    private JFXDatePicker birthdateField;
    @FXML
    private Label changesConfirm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    @FXML
    void saveSettings(ActionEvent event) {
        if(firstNameField.getText().contentEquals("") &&
                lastNameField.getText().contentEquals("") &&
                birthdateField.getValue() == null &&
                usernameField.getText().contentEquals("") &&
                passwordField.getText().contentEquals("")) {
            return;
        }

        Scanner scan;
        String newFirstName = firstNameField.getText().trim();
        if(newFirstName.contentEquals("")) {
            newFirstName = user.getFirstname();
        }
        String newLastName = lastNameField.getText().trim();
        if(newLastName.contentEquals("")) {
            newLastName = user.getLasttname();
        }

        String newBirthDate;
        if(birthdateField.getValue() != null) {
            LocalDate date = birthdateField.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            newBirthDate = formatter.format(date);
        }
        else{

             newBirthDate = user.getBirthDate();
        }
        String newUsername = usernameField.getText().trim();
        if(newUsername.contentEquals("")) {
            newUsername = user.getUsername();
        }
        String newPassword = passwordField.getText();
        if(newPassword.contentEquals("")) {
            newPassword = user.getPassword();
        }


        try{
            File oldFile = new File("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\Users.txt");
            scan = new Scanner(oldFile);
            scan.useDelimiter("[,\n]");

            File tmpFile = new File("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\tmpUsers.txt");

            FileWriter fileWriter = new FileWriter("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\tmpUsers.txt",false);
            BufferedWriter print = new BufferedWriter(fileWriter);

            UserInfo tmpUser = new UserInfo();
            while(scan.hasNext())
            {
                tmpUser.setUserInfo(scan.next(), scan.next(), scan.next(), scan.next(), scan.next());
                if (tmpUser.getUsername().equals(user.getUsername()) && tmpUser.getPassword().equals(user.getPassword()))
                {
                    print.write(newFirstName+ "," + newLastName + ","
                            + newBirthDate + "," +  newUsername + ","
                            + newPassword +"\n");
                }
                else {
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
            
            oldFile.delete();
            File newFile = new File("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\Users.txt");
            tmpFile.renameTo(newFile);

            firstNameField.clear();
            lastNameField.clear();
            usernameField.clear();
            passwordField.clear();
            changesConfirm.setText("Chaneges has been saved!");
        }catch (FileNotFoundException e){
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
