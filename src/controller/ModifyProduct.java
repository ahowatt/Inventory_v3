package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

/** Modifies an existing product object. */
public class ModifyProduct implements Initializable {
    /** Table column that displays part ID. */
    public TableColumn partID;
    /** Table column that displays part name. */
    public TableColumn partName;
    /** Table column that displays part inventory. */
    public TableColumn partInv;
    /** Table column that displays part price. */
    public TableColumn partPrice;
    /** Table column that displays associated part IDs. */
    public TableColumn assocPartID;
    /** Table column that displays associated part names. */
    public TableColumn assocPartName;
    /** Table column that displays associated part inventory. */
    public TableColumn assocPartInv;
    /** Table column that displays associated part price. */
    public TableColumn assocPartPrice;
    /** Text field to search parts. */
    public TextField partSearch;
    /** Button to add an associated part. */
    public Button prodAddPart;
    /** Button to remove associated part. */
    public Button removeAssocPart;
    /** Button to save the modified product. */
    public Button saveProd;
    /** Button that cancels the modification. */
    public Button cancelProd;
    /** Text field for product ID. */
    public TextField prodID;
    /** Text field for the product name. */
    public TextField modProdName;
    /** Text field for the product inventory. */
    public TextField modProdInv;
    /** Text field for the product price. */
    public TextField modProdPrice;
    /** Text field for the product minimum. */
    public TextField modProdMin;
    /** Text field for the product maximum. */
    public TextField modProdMax;
    /** Table displaying parts. */
    public TableView partsTable;
    /** Table displaying associated parts. */
    public TableView assocPartsTable;
    /** Observable list of associated parts. */
    public ObservableList<Part> addedParts = FXCollections.observableArrayList();
    /** Initializes the selected product before it's selected. */
    Product selectedProduct = null;

    public ModifyProduct() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.partsTable.setItems(Inventory.getAllParts());
        this.partID.setCellValueFactory(new PropertyValueFactory("id"));
        this.partName.setCellValueFactory(new PropertyValueFactory("name"));
        this.partInv.setCellValueFactory(new PropertyValueFactory("stock"));
        this.partPrice.setCellValueFactory(new PropertyValueFactory("price"));

    }

    /** Searches part by ID.
     * @param id
     * @return p, part with matching ID.
     */
    private Part searchByIDPart(int id){
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part p : allParts){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    /** Searches part by search string.
     * @param partialName
     * @return list of parts with matching partial strings.
     */
    private ObservableList<Part> searchByPartName(String partialName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part p : allParts){
            if(p.getName().contains(partialName)){
                namedParts.add(p);
            }
        }
        return namedParts;
    }

    /** Searches for a part by ID or partial string.
     * @param actionEvent
     */
    public void onPartSearch(ActionEvent actionEvent) {
        String a = partSearch.getText();
        ObservableList<Part> parts = searchByPartName(a);

        if(parts.size() == 0) {
            try {
                int id = Integer.parseInt(a);
                Part p = searchByIDPart(id);
                if (p != null) {
                    parts.add(p);
                }

            }catch(NumberFormatException ignored){
                Inventory.errorMessage("The part was not found.");
                return;
            }
        }
        partsTable.setItems(parts);
        partSearch.setText("");
    }

    /** Adds associated part to product.
     * @param actionEvent
     */
    public void onProdAddPart(ActionEvent actionEvent) {
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            Inventory.errorMessage("Please select a part.");
        }
        addedParts.add(part);
        assocPartsTable.setItems(addedParts);
    }

    /** Removes an associated part.
     * @param actionEvent
     */
    public void onRemoveAssocPart(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("You are removing a part.");
        alert.setContentText("Do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Part part = (Part) assocPartsTable.getSelectionModel().getSelectedItem();
            addedParts.remove(part);
        }
    }

    /** Removes the selected product and creates a new product with the selected values.
     * @param actionEvent
     * @throws IOException
     */
    public void onSaveProd(ActionEvent actionEvent) throws IOException {

        try{
            Integer.parseInt((modProdInv.getText()));
        }catch(NumberFormatException e)
        {
          Inventory.errorMessage("The inventory must be an integer.");
        }

        try{
            Double.parseDouble((modProdPrice.getText()));
        }catch(NumberFormatException e){
            Inventory.errorMessage("The price must be a double XX.XX");
        }

        try{
            Integer.parseInt(modProdMax.getText());
        }catch(NumberFormatException e){
            Inventory.errorMessage("The maximum must be an integer.");
        }

        try{
            Integer.parseInt(modProdMin.getText());
        }catch(NumberFormatException e){
            Inventory.errorMessage("The minimum must be an integer.");
        }

        int Id = Integer.parseInt(prodID.getText());

        String name = this.modProdName.getText();
        if (this.modProdName.getLength() == 0) {
            Inventory.errorMessage("The product needs a name.");
            return;
        }
        int inv = Integer.parseInt(modProdInv.getText());
        double price = Double.parseDouble(modProdPrice.getText());
        int max = Integer.parseInt(modProdMax.getText());
        int min = Integer.parseInt(modProdMin.getText());

        if(min > max){
            Inventory.errorMessage("Min must be smaller than max.");
            return;
        }

        if( inv <min || inv >max ){
            Inventory.errorMessage("Inventory must be between min and max.");
            return;
        }

        Inventory.addProduct(new Product(Id, name, price, inv, min, max));
        Inventory.getAllProducts().remove(selectedProduct);


        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene  = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene (scene));
        stage.show();
    }

    /** Receives values from the product selected on the main menu.
     * @param selectedProduct
     */
    public void receiveProduct(Product selectedProduct){
        this.selectedProduct = selectedProduct;
        prodID.setText(String.valueOf(selectedProduct.getId()));
        modProdName.setText(selectedProduct.getName());
        modProdInv.setText(String.valueOf(selectedProduct.getInv()));
        modProdPrice.setText(String.valueOf(selectedProduct.getPrice()));
        modProdMax.setText(String.valueOf(selectedProduct.getMax()));
        modProdMin.setText(String.valueOf(selectedProduct.getMin()));


        assocPartsTable.setItems(selectedProduct.getAllAssociatedParts());
        assocPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    /** Returns to the main menu.
     * @param actionEvent
     * @throws IOException
     */
    public void onCancelProd(ActionEvent actionEvent) throws IOException {
        Parent onCancelAddProd = (Parent) FXMLLoader.load(this.getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(onCancelAddProd, 850.0D, 480.0D);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}