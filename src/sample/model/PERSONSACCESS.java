package sample.model;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.connect.ConnectDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.lang.model.type.NullType;

public class PERSONSACCESS {



    //*******************************
    //SELECT a Person
    //*******************************
    public static PERSONS searchPerson (String Firstname,String Midname,String Lastname,String Bdate,String sex,String PhoneNo,String Email,String City,String State, String Zip ,String AddressLine) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        //String selectStmt = "SELECT * FROM PERSON WHERE fname = ? AND mname = ? AND lname = ? AND BirthDate = ? AND sex = ? AND phonenumber = ? AND MailId= ? AND City = ? AND State = ? AND Zip = ? AND Line = ?";
        String  selectStmt = "{call sp_searchs(?,?,?,?,?,?,?,?,?,?,?)}";

        if(Firstname.isEmpty()) {Firstname= null;}
        if(Midname.isEmpty()) {Midname= null;}
        if(Lastname.isEmpty()) {Lastname= null;}
        if(Bdate.isEmpty()) {Bdate= null;}
        if(sex.isEmpty()) {sex= null;}
        if(PhoneNo.isEmpty()) {PhoneNo= null;}
        if(Email.isEmpty()) {Email= null;}
        if(City.isEmpty()) {City= null;}
        if(State.isEmpty()) {State= null;}
        if(Zip.isEmpty()) {Zip= null;}
        if(AddressLine.isEmpty()) {AddressLine= null;}

        System.out.println(selectStmt);
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rspe = ConnectDB.dbExecuteSQuery(selectStmt,Firstname, Midname, Lastname, sex,Bdate, PhoneNo, Email, City, State,  Zip , AddressLine);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            PERSONS person = getPersonFromResultSet(rspe);

            //Return employee object
            return person;
        } catch (SQLException e) {
            System.out.println("While searching person an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
    private static PERSONS getPersonFromResultSet(ResultSet rs) throws SQLException
    {
        PERSONS per = null;
        if (rs.next()) {
            per = new PERSONS();
            per.setPersonId(rs.getInt("PersonID"));
            per.setfname(rs.getString("fname"));
            per.setmname(rs.getString("mname"));
            per.setlname(rs.getString("lname"));
            per.setMailId(rs.getString("MailId"));
            per.setphonenumber(rs.getString("phonenumber"));
            per.setsex(rs.getString("sex"));
            per.setBirthDate(rs.getDate("BirthDate"));
            per.setCity(rs.getString("City"));
            per.setState(rs.getString("State"));
            per.setZip(rs.getString("Zip"));
            per.setLine(rs.getString("Line"));
        }
        return per;
    }

    //*******************************
    //SELECT Persons
    //*******************************
    public static ObservableList<PERSONS> searchPersons () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT \n" +
                "    * FROM  PERSON  FULL OUTER JOIN PHONENO ON pno_PersonID = PersonID FULL OUTER JOIN EMAIL ON mail_PersonID = PersonID \n" +
                "      FULL OUTER JOIN ADDRESS ON add_PersonID = PersonID FULL OUTER JOIN LINE ON l_PersonID = PersonID";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPers = ConnectDB.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<PERSONS> perList = getPersonList(rsPers);

            //Return employee object
            return perList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from employees operation //rght
    private static ObservableList<PERSONS> getPersonList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<PERSONS> perList = FXCollections.observableArrayList();

        while (rs.next()) {
            PERSONS per = new PERSONS();
            per.setPersonId(rs.getInt("PersonID"));
            per.setfname(rs.getString("fname"));
            per.setmname(rs.getString("mname"));
            per.setlname(rs.getString("lname"));
            per.setBirthDate(rs.getDate("BirthDate"));
            per.setsex(rs.getString("sex"));
            per.setphonenumber(rs.getString("phonenumber"));
            per.setMailId(rs.getString("MailId"));
            per.setCity(rs.getString("City"));
            per.setState(rs.getString("State"));
            per.setZip(rs.getString("Zip"));
            per.setLine(rs.getString("Line"));
            //Add person to the ObservableList
            perList.add(per);
        }
        //return perList (ObservableList of Person)
        return perList;
    }

    //*************************************
    //UPDATE an persons's last name
    //*************************************
    public static int updatePerLname (String PID, String Firstname,String Midname,String Lastname,String Bdate,String sex,String PhoneNo,String Email,String City,String State, String Zip ,String AddressLine) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement


        //String updateStmt = " UPDATE  PERSON SET fname = ? , mname = ? , lname = ? , BirthDate = ? , sex = ? , phonenumber = ? , MailId= ? , City = ? , State = ? , Zip = ? , Line = ?  WHERE PersonID = '" + PID + "'";
        String updateStmt = "{call sp_updates(?,?,?,?,?,?,?,?,?,?,?,'"+PID+"')}";



        //Execute UPDATE operation
        try {

            if(Firstname.isEmpty()) {Firstname= null;}
            if(Midname.isEmpty()) {Midname= null;}
            if(Lastname.isEmpty()) {Lastname= null;}
            if(Bdate.isEmpty()) {Bdate= null;}
            if(sex.isEmpty()) {sex= null;}
            if(PhoneNo.isEmpty()) {PhoneNo= null;}
            if(Email.isEmpty()) {Email= null;}
            if(City.isEmpty()) {City= null;}
            if(State.isEmpty()) {State= null;}
            if(Zip.isEmpty()) {Zip= null;}
            if(AddressLine.isEmpty()) {AddressLine= null;}

           int ans = ConnectDB.dbExecuteUpdate(updateStmt,Firstname, Midname, Lastname, sex ,Bdate , PhoneNo, Email, City, State,  Zip , AddressLine);
           return ans;
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }

    }

    //*************************************

    //DELETE a Person
    //*************************************
    public static int deletePerson (String PersonID) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM PERSON WHERE PersonID=?";/*
                "BEGIN\n" +
                        "   DELETE FROM PERSON\n" +
                        "         WHERE PersonID ="+ ? +";\n" +
                        "   COMMIT;\n" +
                        "END;";*/

        //Execute UPDATE operation
        try {
            int ans=ConnectDB.dbExecuteDI(updateStmt,PersonID);
            return ans;
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }


    //*************************************
    //INSERT a Person
    //*************************************
    public static void insertPer(String Firstname,String Midname,String Lastname,String Bdate,String sex,String PhoneNo,String Email,String City,String State, String Zip ,String AddressLine) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "{call sp_inserts(?,?,?,?,?,?,?,?,?,?,?)}";
        /*String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO PERSON\n" +
                        "(PersonID , fname , mname , lname , BirthDate , sex )\n" +
                        "VALUES\n" +
                        "(NEXT VALUE FOR sequence_persons, '" + Firstname + "','" + Midname + "','" + Lastname + "','" + Bdate + "','" + sex + "')\n" +



                        "END;";*/
        try{

            if(Firstname.isEmpty()) {Firstname= null;}
            if(Midname.isEmpty()) {Midname= null;}
            if(Lastname.isEmpty()) {Lastname= null;}
            if(Bdate.isEmpty()) {Bdate= null;}
            if(sex.isEmpty()) {sex= null;}
            if(PhoneNo.isEmpty()) {PhoneNo= null;}
            if(Email.isEmpty()) {Email= null;}
            if(City.isEmpty()) {City= null;}
            if(State.isEmpty()) {State= null;}
            if(Zip.isEmpty()) {Zip= null;}
            if(AddressLine.isEmpty()) {AddressLine= null;}



            ConnectDB.dbExecuteUpdate(updateStmt,Firstname, Midname, Lastname, sex ,Bdate , PhoneNo, Email, City, State,  Zip , AddressLine);

            /*int i = ConnectDB.dbExecuteDI(updateStmt);

            String updStmt =
                    "BEGIN\n" +
                    "INSERT INTO PHONENO\n" +
                    "(pno_PersonID,phonenumber)\n" +
                    "VALUES\n" +
                    "('"+i+"','" + PhoneNo + "')\n" +

                    "INSERT INTO EMAIL\n" +
                    "(mail_PersonID,MailId)\n" +
                    "VALUES\n" +
                    "('"+i+"','" + Email + "')\n" +

                    "INSERT INTO ADDRESS\n" +
                    "(add_PersonID,State,City,Zip)\n" +
                    "VALUES\n" +
                    "('"+i+"','" + State+"','" + City+"','" + Zip + "')\n" +

                    "INSERT INTO LINE\n" +
                    "(l_PersonID,Line)\n" +
                    "VALUES\n" +
                    "('"+i+"','" + AddressLine + "')\n" +

            "END;";
            int j = ConnectDB.dbExecuteDI(updStmt);*/

        } catch (SQLException e) {
            System.out.print("Error occurred while Insert Operation: " + e);
            throw e;
        }
    }

}