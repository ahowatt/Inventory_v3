package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPart implements Initializable {
    public Label changingLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSavePart(ActionEvent actionEvent) {
    }

    public void onCancelPart(ActionEvent actionEvent) throws IOException {
        Parent onCancelPart = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(onCancelPart,850,400);
        stage.setTitle("Main Menu");
        stage.setScene(scene); //pass the created scene to the stage
        stage.show();
    }

    public void onAddInPart(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }

    public void onAddOutPart(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }
}
