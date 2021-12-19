package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void toAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(root,800,600);
        stage.setTitle("Add Parts");
        stage.setScene(scene); //pass the created scene to the stage
        stage.show();
    }
}
