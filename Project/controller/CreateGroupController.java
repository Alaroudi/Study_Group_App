package Project.controller;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.TextAlignment;

/**
 * CreateCroupController class controls the CreateGroupView.fxml scene
 *
 * @author Kassie Garcia
 */
public class CreateGroupController implements Initializable {
    @FXML
    private JFXTextField dayTF;
    @FXML
    private JFXTimePicker timeTF;
    @FXML
    private JFXTextField locationTF;
    @FXML
    private JFXTextField contactTF;
    @FXML
    private JFXTextField courseTypeTF;
    @FXML
    private Label errorLabel;
    @FXML
    private Spinner<?> seats;

    private String studyGroupAdd, meetDay, meetTime, meetLocation, meetContact, CourseInfo, availableSeats;

    private Scanner scn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        SpinnerValueFactory quantityValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,100,10);
        seats.setValueFactory(quantityValue);
    }

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
        errorLabel.setTextAlignment(TextAlignment.CENTER);
        //get the text from all the text field
        CourseInfo = courseTypeTF.getText().toUpperCase().trim();
        //get the meet days and remove all withe spaces
        meetDay = dayTF.getText().toUpperCase().replaceAll("\\s", "");
        if (timeTF.getValue() != null){
            meetTime = timeTF.getValue().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString().replaceAll("\\s", "");
        }   
        meetLocation = locationTF.getText().toUpperCase().trim();
        meetContact = contactTF.getText().toUpperCase().trim(); 
        availableSeats = seats.getValue().toString();
        
        //if the user input for course information is invalid format
        if (!(CourseInfo.matches("^(CS||MAT) \\d{4} \\d{3}"))) {
            //print error message to screen
            errorLabel.setText("Invalid Course Information Format. Try again!");
           
            //exit the method
            return;
        }
        
        //if user input of the meeting day is not  M T W R F S U or a combo of those letters
        if (!meetDay.matches("[MTWRFSU]+")|| meetDay.length() > 7) {

            //print error message to screen
            errorLabel.setText("Invalid Day Format. Try again1");
            
            //exit the method
            return;

        }
        //if we don't get a meeting time in ##:##PM/AM format
        if (!(meetTime.matches("^\\d{1,2}:\\d{2}(AM||PM)"))) {

            //print error message to screen
            errorLabel.setText("Invalid Time Format. Try again!");
            
            //exit the method
            return;
        }
        //if the user input for meeting location isnt an UTSA Building acronym
        if (meetLocation.equals("")) {

            //print error message to screen
            errorLabel.setText("Invalid Location Format. Try again!");
            
            //exit the method
            return;
        }
        //the input here is less restricted, basically anything with no spaces and an @ or numbers
        // as contact info can be an email or a phone number
        if (meetContact.equals("")) {
            //print error message to screen
            errorLabel.setText("Invalid Contact Information Format. Try again!");
            
            //exit the method
            return;
        }
        
        
        if (verifyCourse(CourseInfo)){
            studyGroupAdd = " " + meetDay + " " + meetTime + " " + meetLocation + " " + meetContact+ " " + availableSeats;       
            replaceSelected(CourseInfo, studyGroupAdd);
            errorLabel.setText("Sucessfully added.");
            dayTF.clear();
            timeTF.setValue(LocalTime.now());
            locationTF.clear();
            contactTF.clear();
            courseTypeTF.clear();
            
        }
        else{
            
            errorLabel.setText("Course already has a study group.");
            
            
        }

        
    }

    //a method that adds the study group information to the txt file
    private void replaceSelected(String type,String replaceWith) {
        try {
            // input the file content to the StringBuffer "inputBuffer"
            BufferedReader file = new BufferedReader(new FileReader("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\courseinfo.txt"));
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
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\courseinfo.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
    
    private boolean verifyCourse(String course){
        
         //scan in the courseinfo file to read it
        try {
            scn = new Scanner(new File("C:\\Users\\alaro\\Documents\\NetBeansProjects\\Project\\src\\Project\\model\\courseinfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        

         //these lines will skip the beginning 2 lines that start with #
        scn.nextLine();
        scn.nextLine();
        String fileCourse;
        //while there's a line to scan within the file
        while (scn.hasNextLine()) {
            //add the study group info into the file
            //if the course is found and it does not have a study group
            fileCourse = scn.nextLine().trim();
            if (course.equals(fileCourse)) {
                
            	return true;
            }

        }
        scn.close();
        return false;
     
    }
}
	
	
	
	
