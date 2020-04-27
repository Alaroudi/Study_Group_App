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
 * @author Kassie Garcia
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

    private String studyGroupAdd, meetDay, meetTime, meetLocation, meetContact, meetCourseInfo;

    private Scanner scn;

    /**
     * handleCreateGroup(ActionEvent event) creates a study group when user clicks on 'Create Group' button
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleCreateGroup(ActionEvent event) throws IOException { //when the create group button is clicked
    	//get rid of error label message if user tries again
        errorLabel.setText("");
        
        //get the text from all the text fields
        meetDay = dayTF.getText().toUpperCase();
        meetTime = timeTF.getText().toUpperCase();
        meetLocation = locationTF.getText().toUpperCase();
        meetContact = contactTF.getText().toUpperCase();
        meetCourseInfo = courseTypeTF.getText().toUpperCase();

        //if user input of the meeting day is not  M T W R F S U or a combo of those letters
        if (meetDay.contains("A") || meetDay.contains("B") || meetDay.contains("C") || meetDay.contains("D")
                || meetDay.contains("E") || meetDay.contains("G") || meetDay.contains("H") || meetDay.contains("I")
                || meetDay.contains("J") || meetDay.contains("K") || meetDay.contains("L") || meetDay.contains("N")
                || meetDay.contains("O") || meetDay.contains("P") || meetDay.contains("Q") || meetDay.contains("V")
                || meetDay.contains("X") || meetDay.contains("Y") || meetDay.contains("Z") || meetDay.length() > 7) {

            //print error message to screen
            errorLabel.setText("Invalid Day Format. Try again");
            //clear the textfield
            dayTF.clear();
            //exit the method
            return;

        }
        //if we don't get a meeting time in ##:##PM/AM format
        if (!(meetTime.matches("^\\d{2}:\\d{2}PM") || meetTime.matches("^\\d{2}:\\d{2}AM") || meetTime.matches("^\\d{1}:\\d{2}PM")
                || meetTime.matches("^\\d{1}:\\d{2}AM"))) {

            //print error message to screen
            errorLabel.setText("Invalid Time Format. Try again");
            //clear the textfield
            timeTF.clear();
            //exit the method
            return;
        }
        //if the user input for meeting location isnt an UTSA Building acronym
        if (!(meetLocation.equals("SU") || meetLocation.equals("MS") || meetLocation.equals("ART") || meetLocation.equals("JPL")
                || meetLocation.equals("FLN") || meetLocation.equals("EB") || meetLocation.equals("AET") || meetLocation.equals("BSB")
                || meetLocation.equals("BSE") || meetLocation.equals("HSU") || meetLocation.equals("IAB") || meetLocation.equals("CC")
                || meetLocation.equals("RWC") || meetLocation.equals("MH") || meetLocation.equals("BB") || meetLocation.equals("NPB")
                || meetLocation.equals("GSR") || meetLocation.equals("RRC") || meetLocation.equals("BQS") || meetLocation.equals("LV")
                || meetLocation.equals("AH") || meetLocation.equals("CV") || meetLocation.equals("UO") || meetLocation.equals("CH"))) {

            //print error message to screen
            errorLabel.setText("Invalid Location Format. Try again");
            //clear the textfield
            locationTF.clear();
            //exit the method
            return;
        }
        //the input here is less restricted, basically anything with no spaces and an @ or numbers
        // as contact info can be an email or a phone number
        if (meetContact.contains(" ") || !(meetContact.contains("@") || meetContact.matches(".*\\d.*"))) {
            //print error message to screen
            errorLabel.setText("Invalid Contact Information Format. Try again");
            //clear the textfield
            contactTF.clear();
            //exit the method
            return;
        }
        //if the user input for course information is invalid format
        if (!(meetCourseInfo.matches("^CS \\d{4} \\d{3}") || meetCourseInfo.matches("^MAT \\d{4} \\d{3}") || meetCourseInfo.matches("^CS \\d{4} \\d{1}A\\d{1}")
                || meetCourseInfo.matches("^CS \\d{4} \\d{1}B\\d{1}") || meetCourseInfo.matches("^CS \\d{4} \\d{1}C\\d{1}")
                || meetCourseInfo.matches("^CS \\d{4} \\d{1}D\\d{1}"))) {
            //print error message to screen
            errorLabel.setText("Invalid Course Information Format. Try again");
            //clear the textfield
            courseTypeTF.clear();
            //exit the method
            return;
        }

        //scan in the courseinfo file to read it
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
            //if the course is found and it does not have a study group
            if (meetCourseInfo.equals(scn.nextLine())) {
            	//create the line that will be added to courseinfo.txt
                studyGroupAdd = " " + meetDay + " " + meetTime + " " + meetLocation + " " + meetContact;
                
                //replace the previous line with our new line with all the study group info
                replaceSelected(studyGroupAdd, meetCourseInfo);
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
            // input the file content to the StringBuffer "inputBuffer"
            BufferedReader file = new BufferedReader(new FileReader("courseinfo.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            //while there's a line to read, add to inputBuffer 
            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            //close file
            file.close();
            
            //convert the inputBuffer to a string 
            String inputStr = inputBuffer.toString();
            //replace all instances of the course information with NO study group to have a new study group 
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
	
	
	
	