package sample;
import java.io.IOException;
//import sample.control.*;
//import sample.control.PersonController;
import sample.control.PersonController;
import sample.control.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
//Main class which extends from Application Class
public class Main extends Application {
    //private PersonController personController;
    //This is our PrimaryStage (It contains everything)



    private Stage primaryStage;

    //This is the BorderPane of RootLayout
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        //1) Declare a primary stage (Everything will be on this stage)
        this.primaryStage = primaryStage;

        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("JavaFX App");

        //2) Initialize RootLayout
        initRootLayout();

        //3) Display the EmployeeOperations View
        showPersonView();
    }

    //Initializes the root layout.
      public void initRootLayout() {
        try {
            //First, load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.

              //Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            //Third, show the primary stage
             primaryStage.show(); //Display the primary stage
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Shows the employee operations view inside the root layout.
    public void showPersonView() {
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonsView.fxml"));
            AnchorPane personOperationsView = (AnchorPane) loader.load();



            // Set Employee Operations view into the center of root layout.
            rootLayout.setCenter(personOperationsView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    public void setMain(Main main){
        //this.main = main;
        personController.setMain(main);
    }*/
    public static void main(String[] args) {
        launch(args);
    }
}