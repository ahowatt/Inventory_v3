package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import controller.MainMenu;
import model.inHousePart;
import model.outSourcePart;

/** Modifies an existing product object.
 * @author Abigail Howatt
 */
public class modifyPart implements Initializable {
    /** Label changes to reflect in-house or outsourced parts. */
    public Label changingLabel;
    /** Text field that shows/gets part name. */
    public TextField modPartName;
    /** Text field that shows/gets part inventory. */
    public TextField modPartInv;
    /** Text field that shows/gets part price. */
    public TextField modPartPrice;
    /** Text field that shows/gets part maximum. */
    public TextField modPartMax;
    /** Text field that shows/gets the machine ID or company name. */
    public TextField modNameID;
    /** Text field that shows/gets part minimum. */
    public TextField modPartMin;
    /** Button that saves the modified part. */
    public Button saveModPart;
    /** Button that returns to the main menu. */
    public Button cancelModPart;
    /** Radio button that creates an instance of an in-house part. */
    public RadioButton addInPart;
    /** Radio button that creates an instance of an outsourced part. */
    public RadioButton addOutPart;
    /** Toggle group that controls the radio buttons. */
    public ToggleGroup inOutPart;
    /** Text field that displays the part ID. */
    public TextField modPartID;

    /** Initializes the selected part before it is selected. */
    Part selectedPart = null;

    public modifyPart() {
    }

    /** Receives the selected information passed from the main menu.
     * Sets fields to the passed information.
     * @param selectedPart
     */
    public void receivePart(Part selectedPart){
        this.selectedPart = selectedPart;
        if(selectedPart instanceof inHousePart){
            changingLabel.setText("Machine ID");
            modNameID.setText(String.valueOf(((inHousePart) selectedPart).getMachineID()));
            addInPart.setSelected(true);
        }
        else if(selectedPart instanceof outSourcePart){
            changingLabel.setText("Company Name");
            modNameID.setText(((outSourcePart) selectedPart).getCompanyName());
            addOutPart.setSelected(true);
        }

        modPartID.setText(String.valueOf(selectedPart.getId()));
        modPartName.setText(selectedPart.getName());
        modPartInv.setText(String.valueOf(selectedPart.getStock()));
        modPartPrice.setText(String.valueOf(selectedPart.getPrice()));
        modPartMax.setText(String.valueOf(selectedPart.getMax()));
        modPartMin.setText(String.valueOf(selectedPart.getMin()));

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /** Removes the selected part and creates a new part with the provided values.
     * Requires parameters for the part constructor.
     * @param actionEvent
     * @throws IOException
     */
    public void onSaveModPart(ActionEvent actionEvent) throws IOException {

        try{
            Integer.parseInt((modPartInv.getText()));
        }catch(NumberFormatException e){
            Inventory.errorMessage("The inventory must be an integer.");
        }

        try{
            Double.parseDouble(modPartPrice.getText());
        }catch(NumberFormatException e){
            Inventory.errorMessage("The price must be a double XX.XX");
        }

        try{
            Integer.parseInt(modPartMax.getText());
        }catch(NumberFormatException e){
            Inventory.errorMessage("The maximum must be an integer.");
        }

        try{
            Integer.parseInt(modPartMin.getText());
        }catch(NumberFormatException e){
            Inventory.errorMessage("The minimum must be an integer.");
        }

        int Id = Integer.parseInt(modPartID.getText());
        String name = modPartName.getText();

        if(modPartName.getLength() == 0){
            Inventory.errorMessage("The part must have a name.");
            return;
        }

        int stock = Integer.parseInt(modPartInv.getText());
        double price = Double.parseDouble(modPartPrice.getText());
        int max = Integer.parseInt(modPartMax.getText());
        int min = Integer.parseInt(modPartMin.getText());

        if(min > max){
            Inventory.errorMessage("Min must be smaller than max.");
            return;
        }

        if(stock<min || stock>max ){
            Inventory.errorMessage("Stock must be between min and max.");
            return;
        }

        if (this.addInPart.isSelected()) {
            int machineID = Integer.parseInt(this.modNameID.getText());
            Inventory.addPart(new inHousePart(Id, name,price,stock,min,max,machineID));

        } else if (this.addOutPart.isSelected()) {
            String companyName = this.modNameID.getText();
            Inventory.addPart(new outSourcePart(Id, name,price,stock,min,max,companyName));
        }

        if(addInPart.isSelected() == false && addOutPart.isSelected() == false){
            Inventory.errorMessage("In-house or outsourced must be selected.");
        }

        Inventory.getAllParts().remove(selectedPart);

        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene  = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene (scene));
        stage.show();
    }

    /** Returns to the main menu.
     * @param actionEvent
     */
    public void onCancelModPart(ActionEvent actionEvent) throws IOException {
        Parent onCancelPart = (Parent)FXMLLoader.load(this.getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(onCancelPart, 850.0D, 480.0D);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /** Sets the label to machine ID. */
    public void onModInPart(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }

    /** Sets the label to company name. */
    public void onModOutPart(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }



}
