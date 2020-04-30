package Project.controller;

import Project.model.SearchTable;
import Project.model.UserInfo;

import com.jfoenix.controls.JFXButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Predicate;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


public class FindStudyGroupsController implements Initializable {
   
    @FXML
    private TextArea resultsTA;
    @FXML
    private JFXTextField typeTF;

    @FXML
    private JFXTextField numberTF;

    @FXML
    private JFXTextField snumberTF;

    @FXML
    private TableView<SearchTable> tbl;

    @FXML
    private TableColumn<SearchTable, String> col_CourseType;

    @FXML
    private TableColumn<SearchTable, String> col_CourseNumber;

    @FXML
    private TableColumn<SearchTable, String> col_SectionNumber;

    //the data that will be displayed in tableview
    ObservableList<SearchTable> list = FXCollections.observableArrayList();
    //for when we are searching through the tableview
    FilteredList<SearchTable> filter = new FilteredList<SearchTable>(list, e -> true);
    @FXML
    private SplitPane splitPane;
    @FXML
    private JFXButton joinButton;
    
    private Scanner scanner1;
    private Scanner scanner2;
    
    private static String  CourseInfo, meetDay, meetTime, meetLocation, meetContact,  availableSeats;
    

    /**
     * initialize(URL arg0, ResourceBundle arg1) initializes the FindStudyGroups.fxml scene
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { //when the FXML Loads

        //set the data onto the tableview
        col_CourseType.setCellValueFactory(new PropertyValueFactory<>("CT"));
        col_CourseNumber.setCellValueFactory(new PropertyValueFactory<>("CN"));
        col_SectionNumber.setCellValueFactory(new PropertyValueFactory<>("SN"));
        tbl.setItems(list);

        //scan in data from the courseinfo.txt file
        try {
            scanner1 = new Scanner(new File("C:\\Users\\guaco\\Downloads\\Study_Group_App-master\\Study_Group_App\\Project\\model\\courseinfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //these lines will skip the beginning 2 lines that start with #
        scanner1.nextLine();
        scanner1.nextLine();

        //while theres a line to scan within the file
        while (scanner1.hasNextLine()) {
            //add the course type, course number, and section number onto to list
            list.add(new SearchTable(scanner1.next(), scanner1.next(), scanner1.next()));
            scanner1.nextLine();
        }
        //close the scanner
        scanner1.close();


    }

    /**
     * search(KeyEvent event) searches study groups when user searches by course type
     *
     * @param event
     */
    @FXML
    private void courseTypeSearch(KeyEvent event) { //when user searches by by course type

        //narrows down the tableview to show what the user inputs in the coursetype textfield
        typeTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super SearchTable>) (SearchTable searchTabObj) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (searchTabObj.getCT().contains(newValue)) {
                    return true;

                }

                return false;

            });

        });
        //display the new sortedList corresponding to user input
        SortedList<SearchTable> sort = new SortedList<SearchTable>(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(sort);

    }

    /**
     * search2(KeyEvent event) narrows down the tableview to show what the user inputs
     * in the course number textfield
     *
     * @param event
     */
    @FXML
    private void courseNumberSearch(KeyEvent event) {

        //narrows down the tableview to show what the user inputs in the course number textfield
        numberTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super SearchTable>) (SearchTable searchTabObj) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (searchTabObj.getCN().contains(newValue)) {
                    return true;

                }

                return false;

            });

        });
      //display the new sortedList corresponding to user input
        SortedList<SearchTable> sort = new SortedList<SearchTable>(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(sort);

    }

    /**
     * search3(KeyEvent event) narrows down the tableview to show what the user inputs
     * in the section number textfield
     *
     * @param event
     */
    @FXML
    private void courseSectionSearch(KeyEvent event) {

        //narrows down the tableview to show what the user inputs in the section number textfield
        snumberTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super SearchTable>) (SearchTable searchTabObj) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (searchTabObj.getSN().contains(newValue)) {
                    return true;

                }

                return false;

            });

        });
      //display the new sortedList corresponding to user input
        SortedList<SearchTable> sort = new SortedList<SearchTable>(filter);
        sort.comparatorProperty().bind(tbl.comparatorProperty());
        tbl.setItems(sort);

    }

    /**
     * handleSGInfo(MouseEvent event) shows study group information when user double clicks on the table row
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleSGInfo(MouseEvent event) throws IOException { //when the user double clicks on the table row
    	//the string form of the course information the user clicks on
        String strCourseType, strCourseNumber, strSectionNumber;
        //the string form of the course information stored within the courseinfo.txt file
        String scanCourseType, scanCourseNumber, scanSectionNumber;
        
        //if the user double clicks on a course
        if (event.getClickCount() == 2) {
            //get the CourseType, CourseNumber, and SectionNumber that the user clicked on
            strCourseType = col_CourseType.getCellData(tbl.getSelectionModel().getSelectedItem());
            strCourseNumber = col_CourseNumber.getCellData(tbl.getSelectionModel().getSelectedItem());
            strSectionNumber = col_SectionNumber.getCellData(tbl.getSelectionModel().getSelectedItem());

            //display the study group information from the file
            try {
                scanner2 = new Scanner(new File("C:\\Users\\guaco\\Downloads\\Study_Group_App-master\\Study_Group_App\\Project\\model\\courseinfo.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //these lines will skip the beginning 2 lines that start with #
            scanner2.nextLine();
            scanner2.nextLine();

            while (scanner2.hasNextLine()) { //while there's a line to scan within the file
                scanCourseType = scanner2.next(); //get the CourseType from file
                scanCourseNumber = scanner2.next(); //get the CourseNumber from file
                scanSectionNumber = scanner2.next(); //get the SectionNumber from file
                
                CourseInfo = scanCourseType.trim() + " " + scanCourseNumber.trim() + " " + scanSectionNumber.trim();
                
                //search through the file to find the clicked row's study group information
                if (scanCourseType.equals(strCourseType) && scanCourseNumber.equals(strCourseNumber) && scanSectionNumber.equals(strSectionNumber)) {

                    //if there's a study group, so if our hasNext() is study group information and not a coursetype of CS or MAT
                    if (!scanner2.hasNext("CS") && !scanner2.hasNext("MAT")) {
                        //set the text of the results text area to the study group information
                        meetDay = scanner2.next().trim(); 
                        meetTime = scanner2.next().trim(); 
                        meetLocation = scanner2.next().trim(); 
                        meetContact = scanner2.next().trim();  
                        availableSeats= scanner2.next().trim();
                        resultsTA.setText("The meet days are " + meetDay + "\nThe meet time is " + meetTime + "\nThe Location on Campus is " + meetLocation + "\nFor more info, please contact the group leader at " + meetContact + "\nAvailable Seats: " + availableSeats);
                        
                       
                        joinButton.setVisible(true);

                        break;
                    }
                    //if there's is not a study group
                    else {
                        //set the text of the results text area to an error message
                        resultsTA.setText("No study group was found.\nTo create a study group for this course\nclick on the create study group tab");
                        
                        joinButton.setVisible(false);
                        break;
                    }
                }
                //if there is a new line to scan, scan it in
                if (scanner2.hasNextLine()) {
                    scanner2.nextLine();                   
                }
            }


            //close file
            scanner2.close();
 
            //Transition to Study Group Results
            DoubleProperty dprop = splitPane.getDividers().get(0).positionProperty();
            KeyValue keyValue = new KeyValue(dprop, 0.65);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), keyValue));
            timeline.play();
        }
    }
    
    /*
     * handleJoinButton takes the study group info given by the user and saves it in the groupinfo.txt file.
    */		
    @FXML
    private void handleJoinButton(ActionEvent event) {
        
    	try(FileWriter fw = new FileWriter("C:\\Users\\guaco\\Downloads\\Study_Group_App-master\\Study_Group_App\\Project\\model\\groupinfo.txt", true);
    		    BufferedWriter bw = new BufferedWriter(fw);
    		    PrintWriter out = new PrintWriter(bw))
    		{
    		    out.printf("%s %s %s %s %s %s %s\n", LoginController.user.getUsername(), CourseInfo, meetDay, meetTime, meetLocation, meetContact,  availableSeats);
    		} 
    			catch (IOException e) {
    		    e.printStackTrace();
    		}
    	
        
        
    }

    
}
