package Project.controller;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * CreateCroupController class controls the CreateGroupView.fxml scene
 *
 * @autor Kassie Garcia
 */
public class CreateGroupController {
    @FXML
    private JFXTextField dayTF;
    @FXML
    private JFXTextField timeTF;
    @FXML
    private JFXTextField locationTF;
    @FXML
    private JFXTextField contactTF;
    @FXML
    private JFXTextField courseTypeTF;
    @FXML
    private Label errorLabel;

    private String str, str1, str2, str3, str4, str5;

    private Scanner scn;

    /**
     * handleCreateGroup(ActionEvent event) creates a study group when user clicks on 'Create Group' button
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleCreateGroup(ActionEvent event) throws IOException { //when the create group button is clicked

        errorLabel.setText("");
        //get the text from all the text fields
        str1 = dayTF.getText().toUpperCase();
        str2 = timeTF.getText().toUpperCase();
        str3 = locationTF.getText().toUpperCase();
        str4 = contactTF.getText().toUpperCase();
        str5 = courseTypeTF.getText().toUpperCase();

        //if user input isnt  M T W R F S U or a combo of those letters
        if (str1.contains("A") || str1.contains("B") || str1.contains("C") || str1.contains("D")
                || str1.contains("E") || str1.contains("G") || str1.contains("H") || str1.contains("I")
                || str1.contains("J") || str1.contains("K") || str1.contains("L") || str1.contains("N")
                || str1.contains("O") || str1.contains("P") || str1.contains("Q") || str1.contains("V")
                || str1.contains("X") || str1.contains("Y") || str1.contains("Z") || str1.length() > 7) {

            //print error message to screen
            errorLabel.setText("Invalid Day Format. Try again");
            //clear the textfield
            dayTF.clear();
            //exit the method
            return;

        }
        //if we don't get a time in ##:##PM/AM format
        if (!(str2.matches("^\\d{2}:\\d{2}PM") || str2.matches("^\\d{2}:\\d{2}AM") || str2.matches("^\\d{1}:\\d{2}PM")
                || str2.matches("^\\d{1}:\\d{2}AM"))) {

            //print error message to screen
            errorLabel.setText("Invalid Time Format. Try again");
            //clear the textfield
            timeTF.clear();
            //exit the method
            return;
        }
        //if we don't get an UTSA Building acronym
        if (!(str3.equals("SU") || str3.equals("MS") || str3.equals("ART") || str3.equals("JPL")
                || str3.equals("FLN") || str3.equals("EB") || str3.equals("AET") || str3.equals("BSB")
                || str3.equals("BSE") || str3.equals("HSU") || str3.equals("IAB") || str3.equals("CC")
                || str3.equals("RWC") || str3.equals("MH") || str3.equals("BB") || str3.equals("NPB")
                || str3.equals("GSR") || str3.equals("RRC") || str3.equals("BQS") || str3.equals("LV")
                || str3.equals("AH") || str3.equals("CV") || str3.equals("UO") || str3.equals("CH"))) {

            //print error message to screen
            errorLabel.setText("Invalid Location Format. Try again");
            //clear the textfield
            locationTF.clear();
            //exit the method
            return;
        }
        //the input here is less restricted, basically anything with no spaces and an @ or numbers
        // as contact info can be an email or a phone number
        if (str4.contains(" ") || !(str4.contains("@") || str4.matches(".*\\d.*"))) {
            //print error message to screen
            errorLabel.setText("Invalid Contact Information Format. Try again");
            //clear the textfield
            contactTF.clear();
            //exit the method
            return;
        }
        //if the course information is invalid format
        if (!(str5.matches("^CS \\d{4} \\d{3}") || str5.matches("^MAT \\d{4} \\d{3}") || str5.matches("^CS \\d{4} \\d{1}A\\d{1}")
                || str5.matches("^CS \\d{4} \\d{1}B\\d{1}") || str5.matches("^CS \\d{4} \\d{1}C\\d{1}")
                || str5.matches("^CS \\d{4} \\d{1}D\\d{1}"))) {
            //print error message to screen
            errorLabel.setText("Invalid Course Information Format. Try again");
            //clear the textfield
            courseTypeTF.clear();
            //exit the method
            return;
        }

        try {
            scn = new Scanner(new File("courseinfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //these lines will skip the beginning 2 lines that start with #
        scn.nextLine();
        scn.nextLine();

        //while there's a line to scan within the file
        while (scn.hasNextLine()) {
            //add the study group info into the file
            //if the course is found and it doesnt have a study group
            if (str5.equals(scn.nextLine())) {
                str = " " + str1 + " " + str2 + " " + str3 + " " + str4;
                replaceSelected(str, str5);
                errorLabel.setText("Sucessfully added.");
                break;
            }

        }
        //if the course already had a study group
        if (!errorLabel.getText().equals("Sucessfully added.")) {
            errorLabel.setText("Course already has a study group.");
        }
        //close the scanner
        scn.close();

        //clear all the text fields
        dayTF.clear();
        timeTF.clear();
        locationTF.clear();
        contactTF.clear();
        courseTypeTF.clear();
    }

    //a method that adds the study group information to the txt file
    public static void replaceSelected(String replaceWith, String type) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("courseinfo.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();

            inputStr = inputStr.replaceAll(type, type + replaceWith);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("courseinfo.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
	
	
	
	

