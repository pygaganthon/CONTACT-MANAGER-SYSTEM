
package sample.control;

import sample.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;


public class RootLayoutController {
    private Main main;   //new
    //private PersonController personController;
    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("This is a sample JAVAFX application!");
        alert.setContentText("You can search, delete, update, insert a new person with this program.");
        alert.show();
    }
    //new

    public void setMain(Main main){
        this.main = main;
        //personController.setMain(main);
    }

}