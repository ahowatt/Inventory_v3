package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class modifyPart {
    public RadioButton modOutPart;
    public RadioButton modInPart;
    public ToggleGroup modinout;
    public Label changingLabel;

    public void onCancelModifyP(ActionEvent actionEvent) throws IOException {
        Parent onCancelModifyP = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(onCancelModifyP,850,400);
        stage.setTitle("Main Menu");
        stage.setScene(scene); //pass the created scene to the stage
        stage.show();
    }

    public void onSaveModify(ActionEvent actionEvent) {
    }

    public void onModInPart(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }

    public void onModOutPart(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }
}
