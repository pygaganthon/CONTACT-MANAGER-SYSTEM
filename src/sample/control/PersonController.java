package sample.control;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.concurrent.Executor;
//import java.util.*;
//import sample.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.model.PERSONS;
import sample.model.PERSONSACCESS;
//import sample.Main;
public class PersonController {
    //private Main main;
    @FXML
    private TextArea result;
    @FXML
    private TextField FirstnameText;
    @FXML
    private TextField MidnameText;
    @FXML
    private TextField LastnameText;
    @FXML
    private TextField BdateText;
    @FXML
    private TextField sexText;
    @FXML
    private TextField UPIDText;
    @FXML
    private TextField DPIDText;
    @FXML
    private TextField PhoneNoText;
    @FXML
    private TextField EmailText;
    @FXML
    private TextField CityText;
    @FXML
    private TextField StateText;
    @FXML
    private TextField ZipText;
    @FXML
    private TextField AddressLineText;
    @FXML
    private TableView personTable;
    @FXML
    private TableColumn<PERSONS, Integer>  PersonIdColumn;
    @FXML
    private TableColumn<PERSONS, String>  FirstNameColumn;
    @FXML
    private TableColumn<PERSONS, String> MidNameColumn;
    @FXML
    private TableColumn<PERSONS, String> LastNameColumn;
    @FXML
    private TableColumn<PERSONS, Date> BirthDateColumn;
    @FXML
    private TableColumn<PERSONS, String> SexColumn;
    @FXML
    private TableColumn<PERSONS, String> PhoneNoColumn;
    @FXML
    private TableColumn<PERSONS, String> EmailColumn;
    @FXML
    private TableColumn<PERSONS, String> CityColumn;
    @FXML
    private TableColumn<PERSONS, String> StateColumn;
    @FXML
    private TableColumn<PERSONS, String> ZipColumn;
    @FXML
    private TableColumn<PERSONS, String> AddressLineColumn;

    @FXML
    private boolean valid(String Firstname,String Midname,String Lastname,String Bdate,String sex,String PhoneNo,String Email,String City,String State, String Zip ,String AddressLine) {
        boolean t = true;  //result.setText
        String a="",b="",c="",d="",e="",f="",g="",h="",i="",j="",k="";
        if (Firstname.length() > 50) {a = "Firstname exceeds normal length\n"; t=false;}
        if (Midname.length() > 50) {b = "Midname exceeds normal length\n"; t=false;}
        if (Lastname.length() > 50) {c = "Lastname exceeds normal length\n"; t=false;}
        if (sex.length() > 1) {d = "Sex exceeds normal length\n"; t=false;}
        if (PhoneNo.length() > 12) {e = "Phone no exceeds normal length\n"; t=false;}

        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-mm-dd");

        //if (Bdate ) {f = "Email exceeds normal length\n"; t=false;}
        formatter1.setLenient(false);

        try {
            formatter1.parse(Bdate.trim());

        } catch (ParseException pe) {
            k = "Birth Date is not proper Data type\n"; t=false;
        }
        if (Email.length() > 65) {f = "Email exceeds normal length\n"; t=false;}
        if (City.length() > 35) {g = "City exceeds normal length\n"; t=false;}
        if (State.length() > 35) {h = "State exceeds normal length\n"; t=false;}
        if(Zip.length()>10) {i = "Zip exceeds normal length\n"; t=false;}
        if(AddressLine.length()>100) {j = "Address line exceeds normal length"; t=false;}
        if(t==false) {result.setText(a+b+c+d+e+f+g+h+i+j+k);}
        return t;
    }
    //Search a person
    @FXML
    private void searchPerson (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Employee information
            PERSONS per = PERSONSACCESS.searchPerson(FirstnameText.getText(),MidnameText.getText(),LastnameText.getText(),BdateText.getText(),sexText.getText(),PhoneNoText.getText(),EmailText.getText(),CityText.getText(),StateText.getText(), ZipText.getText() , AddressLineText.getText());
            //Populate Employee on TableView and Display on TextArea

            populateAndShowPerson(per);
        } catch (SQLException e) {
            e.printStackTrace();
            result.setText("Error occurred while getting person information from DB.\n" + e);
            throw e;
        }
    }

    //Search all persons
    @FXML
    private void searchPersons(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Persons information
            ObservableList<PERSONS> perData = PERSONSACCESS.searchPersons();
            //Populate Employees on TableView
            populatePersons(perData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting persons information from DB.\n" + e);
            throw e;
        }
    }

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {

        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */
        PersonIdColumn.setCellValueFactory(cellData -> cellData.getValue().PersonIdProperty().asObject());
        FirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        MidNameColumn.setCellValueFactory(cellData -> cellData.getValue().midNameProperty());
        LastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        BirthDateColumn.setCellValueFactory(cellData -> cellData.getValue().BirthDateProperty());
        SexColumn.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        PhoneNoColumn.setCellValueFactory(cellData -> cellData.getValue().phonenumberProperty());
        EmailColumn.setCellValueFactory(cellData -> cellData.getValue().MailIdProperty());
        CityColumn.setCellValueFactory(cellData -> cellData.getValue().CityProperty());
        StateColumn.setCellValueFactory(cellData -> cellData.getValue().StateProperty());
        ZipColumn.setCellValueFactory(cellData -> cellData.getValue().ZipProperty());
        AddressLineColumn.setCellValueFactory(cellData -> cellData.getValue().LineProperty());

    }
    /*
    public void setMain(Main main) {
        this.main = main;

        // pass the main app to the drawerContentController:
        //RootLayoutController.setMain(main);
    }*/

    //Populate PERSONS
    @FXML
    private void populatePerson (PERSONS per) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<PERSONS> perData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        perData.add(per);
        //Set items to the employeeTable
        personTable.setItems(perData);
    }

    //Set PERSONS information to Text Area
    @FXML
    private void setPerInfoToTextArea (PERSONS per) {
        result.setText("Person Id: " + per.getPersonId() + "\n" +
                "First Name: " + per.getfname() + "\n" +
                "Mid Name: " + per.getmname() + "\n" +
                "Last Name: " + per.getlname() + "\n" +
                "Birth Date: " + per.getBirthDate() + "\n" +
                "Sex: " + per.getsex() + "\n" +
                "PhoneNo: " + per.getphonenumber() + "\n" +
                "Email: " + per.getMailId() + "\n" +
                "City: " + per.getCity() + "\n" +
                "State: " + per.getState() + "\n" +
                "Zip: " + per.getZip() + "\n" +
                "AddressLine: " + per.getLine());
    }

    //Populate Employee for TableView and Display Employee on TextArea
    @FXML
    private void populateAndShowPerson(PERSONS per) throws ClassNotFoundException {
       if (per != null) {
            populatePerson(per);
            setPerInfoToTextArea(per);
        } else {
            result.setText("This person does not exist!\n");
        }
    }

    //Populate Employees for TableView//rght
    @FXML
    private void populatePersons (ObservableList<PERSONS> perData) throws ClassNotFoundException {
        //Set items to the employeeTable
        personTable.setItems(perData);
    }

    //Update employee's email with the email which is written on newEmailText field
    @FXML
    private void updatePersonlname (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            if(valid(FirstnameText.getText(),MidnameText.getText(),LastnameText.getText(),BdateText.getText(),sexText.getText(),PhoneNoText.getText(),EmailText.getText(),CityText.getText(),StateText.getText(), ZipText.getText() , AddressLineText.getText()))
            { int ans = PERSONSACCESS.updatePerLname(UPIDText.getText(), FirstnameText.getText(),MidnameText.getText(),LastnameText.getText(),BdateText.getText(),sexText.getText(),PhoneNoText.getText(),EmailText.getText(),CityText.getText(),StateText.getText(), ZipText.getText() , AddressLineText.getText());

            if(ans!=0) result.setText("Record updated for person with person id: " + UPIDText.getText() + "\n");
            else result.setText("PersonID not exist");}
        } catch (SQLException e) {
            result.setText("Problem occurred while updating last name: " + e);
        }
    }

    //Insert an employee to the DB
    @FXML
    private void insertPerson(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            if(valid(FirstnameText.getText(),MidnameText.getText(),LastnameText.getText(),BdateText.getText(),sexText.getText(),PhoneNoText.getText(),EmailText.getText(),CityText.getText(),StateText.getText(), ZipText.getText() , AddressLineText.getText()))
            {PERSONSACCESS.insertPer(FirstnameText.getText(),MidnameText.getText(),LastnameText.getText(),BdateText.getText(),sexText.getText(),PhoneNoText.getText(),EmailText.getText(),CityText.getText(),StateText.getText(), ZipText.getText() , AddressLineText.getText());
            result.setText("Person is Inserted! \n");}
        } catch (SQLException e) {
            result.setText("Problem occurred while inserting Person " + e);
            throw e;
        }
    }


    //Delete an employee with a given employee Id from DB
    @FXML
    private void deletePerson (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            int ans = PERSONSACCESS.deletePerson(DPIDText.getText());
            if(ans!=0) result.setText("Record deleted for person with person id: " + DPIDText.getText() + "\n");
            else result.setText("PersonID not exist");

        } catch (SQLException e) {
            result.setText("Problem occurred while deleting person " + e);
            throw e;
        }
    }

}