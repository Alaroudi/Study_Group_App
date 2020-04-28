/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Signup class controls the Singup.fxml scene
 *
 * @author alaro
 */
public class SignupController implements Initializable {

    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField username_email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField password_conf;
    @FXML
    private FontAwesomeIcon closeButton;
    @FXML
    private Label passError;
    @FXML
    private Label successful;
    @FXML
    private JFXButton signupButton;
    @FXML
    private JFXDatePicker birthDate;
    @FXML
    private AnchorPane rootAnchorPane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rootAnchorPane.setOpacity(0);
        fadeInTransition();


    }


    /**
     * Close(MouseEvent event) closes the scene
     *
     * @param event
     */
    @FXML
    private void Close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    /**
     * signup(ActionEvent event) signs a user up when they click on 'Signup' button
     *
     * @param event
     */
    @FXML
    private void signup(ActionEvent event) {
        if(firstname.getText() == null || lastname.getText() == null ||
                birthDate.getValue() == null || username_email.getText() == null ||
                password.getText() == null || password_conf.getText() == null) {
            return;
        }

        try {
             FileWriter fstream = new FileWriter("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\Users.txt", true);
            // FileWriter fstream = new FileWriter("Project/model/Users.txt", true);
            BufferedWriter print = new BufferedWriter(fstream);
            LocalDate date = birthDate.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


            if (password.getText().equals(password_conf.getText())) {

                passError.setVisible(false);
                print.write(firstname.getText() + "," + lastname.getText() + ","
                        + formatter.format(date) + "," + username_email.getText() + ","
                        + password.getText() + "\n");

                successful.setText("Successful");

            } else {
                passError.setText("Passwords don't match!");
                passError.setVisible(true);
            }


            print.close();

        } catch (IOException e) {

            System.err.println("Error while writing to file: " +
                    e.getMessage());
        }

    }

    /**
     * fadeInTransition() handles the fade in transition
     */
    private void fadeInTransition() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setNode(rootAnchorPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

    }


}
