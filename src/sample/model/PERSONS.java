package sample.model;

import javafx.beans.property.*;
import java.sql.Date;

/**
 * Created by ONUR BASKIRT on 27.02.2016.
 */
public class PERSONS {
    //Declare PERSONS Table Columns

    private IntegerProperty PersonID;
    private StringProperty mname;
    private StringProperty fname;
    private StringProperty lname;
    private StringProperty MailId;
    private StringProperty phonenumber;
    private SimpleObjectProperty<Date> BirthDate;
    private StringProperty sex;
    private StringProperty City;
    private StringProperty State;
    private StringProperty Zip;
    private StringProperty Line;


    //Constructor
    public PERSONS() {
        this.PersonID = new SimpleIntegerProperty();
        this.fname = new SimpleStringProperty();
        this.mname = new SimpleStringProperty();
        this.lname = new SimpleStringProperty();
        this.MailId = new SimpleStringProperty();
        this.phonenumber = new SimpleStringProperty();
        this.BirthDate = new SimpleObjectProperty<>();
        this.sex = new SimpleStringProperty();
        this.City = new SimpleStringProperty();
        this.State = new SimpleStringProperty();
        this.Zip = new SimpleStringProperty();
        this.Line = new SimpleStringProperty();

    }


    public int getPersonId() {
        return PersonID.get();
    }

    public void setPersonId(int PersonId){
        this.PersonID.set(PersonId);
    }

    public IntegerProperty PersonIdProperty(){
        return PersonID;
    }
    //first_name
    public String getfname () {
        return fname.get();
    }

    public void setfname(String firstName){
        this.fname.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return fname;
    }

    //mid_name
    public String getmname () {
        return mname.get();
    }

    public void setmname(String midName){
        this.mname.set(midName);
    }

    public StringProperty midNameProperty() {
        return mname;
    }

    //last_name
    public String getlname () {
        return lname.get();
    }

    public void setlname(String lastName){
        this.lname.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lname;
    }


    public String getCity () {
        return City.get();
    }

    public void setCity (String city){
        this.City.set(city);
    }

    public StringProperty CityProperty() {
        return City;
    }


    public String getState () {
        return State.get();
    }

    public void setState (String state){
        this.State.set(state);
    }

    public StringProperty StateProperty() {
        return State;
    }


    public String getZip () {
        return Zip.get();
    }

    public void setZip (String zip){
        this.Zip.set(zip);
    }

    public StringProperty ZipProperty() {
        return Zip;
    }

    public String getLine () {
        return Line.get();
    }

    public void setLine (String line){
        this.Line.set(line);
    }

    public StringProperty LineProperty() {
        return Line;
    }

    //email
    public String getMailId () {
        return MailId.get();
    }

    public void setMailId (String email){
        this.MailId.set(email);
    }

    public StringProperty MailIdProperty() {
        return MailId;
    }

    //phone_number
    public String getphonenumber () {
        return phonenumber.get();
    }

    public void setphonenumber (String phoneNumber){
        this.phonenumber.set(phoneNumber);
    }

    public StringProperty phonenumberProperty() {
        return phonenumber;
    }


    //BirthDate
    public Object getBirthDate(){
        return BirthDate.get();
    }

    public void setBirthDate(Date BirthDate){
        this.BirthDate.set(BirthDate);
    }

    public SimpleObjectProperty<Date> BirthDateProperty(){
        return BirthDate;
    }

    //sex
    public String getsex () {
        return sex.get();
    }

    public void setsex (String Sex){
        this.sex.set(Sex);
    }

    public StringProperty sexProperty() {
        return sex;
    }

}