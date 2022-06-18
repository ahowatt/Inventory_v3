package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.inHousePart;
import model.outSourcePart;

/** Add part controller.
 *This controls the Add Part form.
 * @author Abigail Howatt
 */
public class AddPart implements Initializable {
    /** This label changes depending on whether in-house or outsourced is selected. */
    public Label changingLabel;
    /** This is the text field that gets the part name.*/
    public TextField addPartName;
    /** This is the text field that gets the inventory. */
    public TextField addPartInv;
    /** This is the text field that gets the price. */
    public TextField addPartPrice;
    /** This is the text field that gets the maximum.*/
    public TextField addPartMax;
    /** This is the text field for the machine number or company name. */
    public TextField nameID;
    /** This is the text field for the minimum. */
    public TextField addPartMin;
    /** Button that saves the new part. */
    public Button savePart;
    /** Button that cancels adding a new part. */
    public Button cancelPart;
    /** Radion button to create a new in-house part. */
    public RadioButton addInPart;
    /** Toggle group that controls the radio buttons. */
    public ToggleGroup inOutPart;
    /** Radio button to create a new outsourced part. */
    public RadioButton addOutPart;

    /**This method initializes the AddPart class. */
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**This method saves the created part.
     * @throws IOException
     * Method takes parameters for a new object of the Part class.
     * Parameters are validated and the new part is added to the list.
     */
    public void onSavePart(ActionEvent actionEvent) throws IOException {

            try{
                Double.parseDouble((this.addPartPrice.getText()));
            }
            catch (NumberFormatException e){
                Inventory.errorMessage("The price must be a double XX.XX");
            }

            try{
                Integer.parseInt(this.addPartInv.getText());
            }
            catch(NumberFormatException e){
                Inventory.errorMessage("The inventory must be an integer. ");
            }

            try{
                Integer.parseInt(this.addPartMin.getText());
            }
            catch(NumberFormatException e){
                Inventory.errorMessage("The minimum must be an integer.");
            }

            try{
                Integer.parseInt(this.addPartMax.getText());
            }
            catch (NumberFormatException e){
                Inventory.errorMessage("The maximum must be an integer.");
            }
            String name = this.addPartName.getText();
            if(addPartName.getLength() == 0){
                Inventory.errorMessage("The part must have a name.");
                return;
            }

            double price = Double.parseDouble(this.addPartPrice.getText());
            int stock = Integer.parseInt(this.addPartInv.getText());
            int min = Integer.parseInt(this.addPartMin.getText());
            int max = Integer.parseInt(this.addPartMax.getText());

            if(min > max){
                Inventory.errorMessage("Min must be smaller than max.");
                return;
            }

            if(stock<min || stock>max ){
                Inventory.errorMessage("Stock must be between min and max.");
                return;
            }

            if (this.addInPart.isSelected()) {
                int machineID = Integer.parseInt(this.nameID.getText());
                Inventory.addPart(new inHousePart(Inventory.createID(), name,price,stock,min,max,machineID));

            } else if (this.addOutPart.isSelected()) {
                String companyName = this.nameID.getText();
                Inventory.addPart(new outSourcePart(Inventory.createID(), name,price,stock,min,max,companyName));
            }

            if(addInPart.isSelected() == false && addOutPart.isSelected() == false){
                Inventory.errorMessage("In-house or outsourced must be selected.");
            }

            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene  = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setTitle("Main Menu");
            stage.setScene(new Scene (scene));
            stage.show();


    }

    /** @param actionEvent
     * On a mouse click, the cancel button returns to the main screen.
     * @throws IOException
     */
    public void onCancelPart(ActionEvent actionEvent) throws IOException {
        Parent onCancelPart = (Parent)FXMLLoader.load(this.getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(onCancelPart, 850.0D, 480.0D);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /** @param actionEvent
     * On the button click the Changing Label reads "Machine ID".
     * Creates an instance of the In-house part class. */
    public void onAddInPart(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }

    /** @param actionEvent
     * On the button click the changing label reads "Company Name".
     * Creates an instance of the Outsource part class. */
    public void onAddOutPart(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }


}
