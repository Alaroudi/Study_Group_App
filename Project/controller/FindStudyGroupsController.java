package Project.controller;

import javafx.beans.property.SimpleStringProperty;

import java.io.File;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FindStudyGroupsController class controls the FindStudyGroups.fxml scene
 *
 * @autor Kassie Garcia
 */
public class FindStudyGroupsController implements Initializable {
    @FXML
    private JFXTextArea resultsTF;

    @FXML
    private JFXTextField typeTF;

    @FXML
    private JFXTextField numberTF;

    @FXML
    private JFXTextField snumberTF;

    @FXML
    private TableView<SearchTabModel> tbl;

    @FXML
    private TableColumn<SearchTabModel, String> col_CourseType;

    @FXML
    private TableColumn<SearchTabModel, String> col_CourseNumber;

    @FXML
    private TableColumn<SearchTabModel, String> col_SectionNumber;

    private Scanner scn1;
    private Scanner scn2;

    //the data that will be displayed in tableview
    ObservableList<SearchTabModel> list = FXCollections.observableArrayList();
    //for when we are searching through the tableview
    FilteredList<SearchTabModel> filter = new FilteredList<SearchTabModel>(list, e -> true);

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
            scn1 = new Scanner(new File("courseinfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //these lines will skip the beginning 2 lines that start with #
        scn1.nextLine();
        scn1.nextLine();

        //while theres a line to scan within the file
        while (scn1.hasNextLine()) {
            //add the course type, course number, and section number onto to list
            list.add(new SearchTabModel(scn1.next(), scn1.next(), scn1.next()));
            scn1.nextLine();
        }
        //close the scanner
        scn1.close();


    }

    /**
     * search(KeyEvent event) searches study groups when user searches by course type
     *
     * @param event
     */
    @FXML
    private void search(KeyEvent event) { //when user searches by by course type

        //narrows down the tableview to show what the user inputs in the coursetype textfield
        typeTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super SearchTabModel>) (SearchTabModel std) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (std.getCT().contains(newValue)) {
                    return true;

                }

                return false;

            });

        });

        SortedList<SearchTabModel> sort = new SortedList<SearchTabModel>(filter);
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
    private void search2(KeyEvent event) {

        //narrows down the tableview to show what the user inputs in the course number textfield
        numberTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super SearchTabModel>) (SearchTabModel std) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (std.getCN().contains(newValue)) {
                    return true;

                }

                return false;

            });

        });

        SortedList<SearchTabModel> sort = new SortedList<SearchTabModel>(filter);
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
    private void search3(KeyEvent event) {

        //narrows down the tableview to show what the user inputs in the section number textfield
        snumberTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super SearchTabModel>) (SearchTabModel std) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (std.getSN().contains(newValue)) {
                    return true;

                }

                return false;

            });

        });

        SortedList<SearchTabModel> sort = new SortedList<SearchTabModel>(filter);
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

        String strCT, strCN, strSN, str1, str2, str3;
        if (event.getClickCount() == 2) {
            //get the CT, CN, and SN that the user clicked on
            strCT = col_CourseType.getCellData(tbl.getSelectionModel().getSelectedItem());
            strCN = col_CourseNumber.getCellData(tbl.getSelectionModel().getSelectedItem());
            strSN = col_SectionNumber.getCellData(tbl.getSelectionModel().getSelectedItem());

            //display the study group information from the file
            try {
                scn2 = new Scanner(new File("courseinfo.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //these lines will skip the beginning 2 lines that start with #
            scn2.nextLine();
            scn2.nextLine();

            while (scn2.hasNextLine()) { //while there's a line to scan within the file
                str1 = scn2.next(); //get the CT
                str2 = scn2.next(); //get the CN
                str3 = scn2.next(); //get the SN
                //search through the file to find the clicked row's study group information
                if (str1.equals(strCT) && str2.equals(strCN) && str3.equals(strSN)) {

                    //if there's a study group
                    if (!scn2.hasNext("CS") && !scn2.hasNext("MAT")) {
                        //set the text of the results text area to the study group information
                        resultsTF.setText("The meet days are " + scn2.next() + "\nThe meet time is " + scn2.next() + "\nThe Location on Campus is " + scn2.next() + "\nTo join the group or for more info, please contact the group leader at " + scn2.next());
                        break;
                    }
                    //if there's is not a study group
                    else {
                        //set the text of the results text area to an error message
                        resultsTF.setText("No study group was found.\nTo create a study group for this course\nclick on the create study group tab");
                        break;
                    }
                }
                if (scn2.hasNextLine()) {
                    scn2.nextLine();
                }
            }


            //close file
            scn2.close();
            //we don't want the user to type in the text area
            resultsTF.setEditable(false);
        }
    }

    /**
     * SearchTabModel stores course information
     */
    public class SearchTabModel {
        //A class that stores the course type , course number, and section number.
        //Makes it easier to put all the info into a Collection
        SimpleStringProperty coursetype;
        SimpleStringProperty coursenumber;
        SimpleStringProperty sectionnumber;

        public SearchTabModel(String coursetype, String coursenumber, String sectionnumber) {
            this.coursetype = new SimpleStringProperty(coursetype);
            this.coursenumber = new SimpleStringProperty(coursenumber);
            this.sectionnumber = new SimpleStringProperty(sectionnumber);
        }

        public String getCT() {
            return coursetype.get();
        }

        public String getCN() {
            return coursenumber.get();
        }

        public String getSN() {
            return sectionnumber.get();
        }

        public final SimpleStringProperty CTProperty() {
            return coursetype;
        }

        public final SimpleStringProperty CNProperty() {
            return coursenumber;
        }

        public final SimpleStringProperty SNProperty() {
            return sectionnumber;
        }
    }
}

	
	
	
	
	
	

