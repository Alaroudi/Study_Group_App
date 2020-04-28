package Project.controller;

import Project.model.UserInfo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

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
 * ForgotPasswordController class controls the ForgotPassword.fxml scene
 *
 * @author Alana Quinones Garcia - ded687
 */
public class ForgotPasswordController implements Initializable {
    @FXML
    private JFXTextField usernameField;
    @FXML
    private FontAwesomeIcon closeButton;
    @FXML
    private JFXButton recoverPasswordButton;
    @FXML
    private Label error;
    @FXML
    private Label success;
    @FXML
    private JFXDatePicker birthDateField;
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
     * recoverPassword(ActionEvent event) recovers a user's password when they click on 'Recover Password' button
     *
     * @param event
     */
    @FXML
    private void recoverPassword(ActionEvent event) {
        String username = usernameField.getText();
        LocalDate birthDate = birthDateField.getValue();

        // handle empty input
        if (username == null || birthDate == null) {
            error.setText("Please enter username and birth date");
            return;
        }

        // format birth date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formBirthDate = formatter.format(birthDate);

        // verify credentials
        String password = verifyRecovery(username, formBirthDate);
        if(password.equals("not valid")) {
            success.setVisible(false);
            success.setVisible(true);
            error.setText("Incorrect username and/or birth date");
        }
        else {
            error.setVisible(false);
            success.setVisible(true);
            success.setText("Password: " + password);
        }
    }

    /**
     * verifyRecovery(String username, String formBirthDate) verifies a user's
     * credentials to decide whether to provide forgotten password
     * @param username
     * @param formBirthDate
     * @return whether the user's credentials were valid or not
     */
    public String verifyRecovery(String username, String formBirthDate) {
        try {
            File file = new File("Project/model/Users.txt");
            Scanner scan;
            scan = new Scanner(file);
            scan.useDelimiter("[,\n]");

            UserInfo tmpUser = new UserInfo();
            while (scan.hasNext()) {
                tmpUser.setUserInfo(scan.next(), scan.next(), scan.next(), scan.next(), scan.next());
                if (tmpUser.getUsername().equals(username) && tmpUser.getBirthDate().equals(formBirthDate)) {
                    return tmpUser.getPassword();
                }
            }
            return "not valid";
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return "not valid";
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
