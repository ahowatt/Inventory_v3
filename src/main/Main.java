package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.Inventory;

/** Main class.
 * @author Abigail Howatt
 * Javadocs in javadoc folder.
 */
public class Main extends Application{

    @Override
    /**
     * Displays the main menu.
     */
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root, 850, 480));
        stage.show();
    }

    /**
     * Main method.
     * @param args
     * Loads test data.
     */
    public static void main(String[] args){

        inHousePart tire = new inHousePart(1,"Tire", 15.99, 2, 0, 8, 358);
        outSourcePart wings = new outSourcePart(2,"Wings", 25.99, 4, 0, 8,"Wings R Us");
        //Product SuperBike = new Product(1,"Super Bike", 105.99, 3, 0, 5);
        //Product mountainBike = new Product(2,"Mountain Bike", 150.48, 1,0,4);

        Inventory.addPart(tire);
        Inventory.addPart(wings);
        //Inventory.addProduct(SuperBike);
        //Inventory.addProduct(mountainBike);

        launch(args);
    }
}
