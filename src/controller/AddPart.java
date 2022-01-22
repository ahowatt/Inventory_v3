package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPart implements Initializable {
    public Label changingLabel;
    public TextField addPartID;
    public TextField addPartName;
    public TextField addPartInv;
    public TextField addPartPrice;
    public TextField addPartMax;
    public TextField nameID;
    public TextField addPartMin;
    public Button savePart;
    public Button cancelPart;
    public RadioButton addInPart;
    public ToggleGroup inoutpart;
    public RadioButton addOutPart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSavePart(ActionEvent actionEvent) {
        int id = nextPartID();
        String name = addPartName.getText();
        double price = Double.parseDouble(addPartPrice.getText());
        int inv = Integer.parseInt(addPartInv.getText());
        int min = Integer.parseInt(addPartMin.getText());
        int max = Integer.parseInt(addPartMax.getText());

        if (addInPart.isSelected()){
            int machineID = Integer.parseInt(nameID.getText());
            }
        else if (addOutPart.isSelected()){
            String companyName = nameID.getText();
        }
        //else dialog box, select in-house or outsourced
    }


    public void onCancelPart(ActionEvent actionEvent) throws IOException {
        Parent onCancelPart = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(onCancelPart,850,600);
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

    public void onAddPartID(ActionEvent actionEvent) {
    }

    public void onAddPartName(ActionEvent actionEvent) {
    }

    public void onAddPartInv(ActionEvent actionEvent) {
    }

    public void onAddPartPrice(ActionEvent actionEvent) {
    }

    public void onAddPartMax(ActionEvent actionEvent) {
    }

    public void onNameID(ActionEvent actionEvent) {
    }

    public void onAddPartMin(ActionEvent actionEvent) {
    }
    public static int nextPartID(){
        int nextPartID = 0;
        for (int i = 0; i < Inventory.getAllParts().size(); i++){
            nextPartID++;
        }
    return nextPartID;
    }
}
