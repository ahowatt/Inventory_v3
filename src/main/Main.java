package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;
import model.inhousePart;
import model.outsourcePart;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root, 850, 480));
        stage.show();
    }


    public static void main(String[] args){
        inhousePart tire = new inhousePart(001, "Tire", 15.99, 3,0,10,112);
        inhousePart engine = new inhousePart(002, "Engine", 50.89, 6, 0, 15, 113);
        outsourcePart wings = new outsourcePart(005, "Wings", 72.35, 8, 0, 20, "Wings R Us");
        Product superBike = new Product(10, "Super Bike", 150.00, 2, 0, 10);

        Inventory.addPart(tire);
        Inventory.addPart(engine);
        Inventory.addPart(wings);
        Inventory.addProduct(superBike);

        launch(args);
    }
}
